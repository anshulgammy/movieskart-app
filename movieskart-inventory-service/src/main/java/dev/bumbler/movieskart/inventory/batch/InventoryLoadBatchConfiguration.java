package dev.bumbler.movieskart.inventory.batch;

import dev.bumbler.movieskart.model.inventory.MoviesInventory;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.ItemWriter;
import org.springframework.batch.item.database.builder.JpaItemWriterBuilder;
import org.springframework.batch.item.file.builder.FlatFileItemReaderBuilder;
import org.springframework.batch.item.file.mapping.BeanWrapperFieldSetMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;

import javax.persistence.EntityManagerFactory;

@Configuration
@EnableBatchProcessing
public class InventoryLoadBatchConfiguration {

    private static final String[] COLUMN_NAMES = new String[]{"Id",
            "MovieId",
            "QuantityAvailable",
            "Sellable",
            "CurrentPrice"};

    @Bean
    public Job inventoryDataLoadBatchJob(
            JobBuilderFactory jobBuilderFactory,
            StepBuilderFactory stepBuilderFactory,
            ItemReader<MoviesInventory> itemReader,
            // ItemProcessor<T, U> T refers to the Raw Type as present while reading from the source. U refers to the
            // mapped class into which read data will be initialized.
            ItemProcessor<MoviesInventory, MoviesInventory> itemProcessor,
            ItemWriter<MoviesInventory> itemWriter,
            InventoryLoadBatchNotificationListener moviesDataLoadBatchNotificationListener) {
        // creating spring batch job step below.
        final Step inventoryDataLoadStep = stepBuilderFactory.get("inventory-data-load-step")
                .<MoviesInventory, MoviesInventory>chunk(10)
                .reader(itemReader)
                .processor(itemProcessor)
                .writer(itemWriter)
                .build();

        // creating and returning the job configured with spring batch, with the step created above.
        return jobBuilderFactory.get("inventory-data-load-job")
                .incrementer(new RunIdIncrementer())
                .listener(moviesDataLoadBatchNotificationListener)
                // we can use .flow() in case we have more than one
                // step for our ETL job. eg: .flow(step1).next(step2).next(step3) and so on.
                // for this particular use case, we have only one step, that is why we used .start(step)
                .start(inventoryDataLoadStep)
                .build();
    }

    @Bean
    public ItemReader<MoviesInventory> createFileItemReader() {
        return new FlatFileItemReaderBuilder<MoviesInventory>()
                .name("inventory-data-reader")
                .resource(new ClassPathResource("inventory-data\\inventory.csv"))
                .strict(false)
                .linesToSkip(1)
                .delimited()
                .names(COLUMN_NAMES)
                .fieldSetMapper(new BeanWrapperFieldSetMapper<MoviesInventory>() {{
                    setTargetType(MoviesInventory.class);
                }})
                .build();
    }

    @Bean
    public ItemWriter<MoviesInventory> createItemWriter(EntityManagerFactory entityManagerFactory) {
        return new JpaItemWriterBuilder<MoviesInventory>()
                .entityManagerFactory(entityManagerFactory)
                .build();
    }
}
