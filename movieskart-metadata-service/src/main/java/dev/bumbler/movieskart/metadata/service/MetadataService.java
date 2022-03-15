package dev.bumbler.movieskart.metadata.service;

import com.google.common.base.Preconditions;
import dev.bumbler.movieskart.metadata.exceptions.MetadataServiceException;
import dev.bumbler.movieskart.metadata.model.MetadataServiceResponse;
import dev.bumbler.movieskart.metadata.model.Movie;
import dev.bumbler.movieskart.metadata.repo.MetadataRepository;
import dev.bumbler.movieskart.metadata.util.Constants;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class MetadataService {

    private MetadataRepository metadataRepository;

    @Autowired
    public MetadataService(MetadataRepository metadataRepository) {
        this.metadataRepository = metadataRepository;
    }

    public MetadataServiceResponse getMovieById(String movieId) throws MetadataServiceException {
        Preconditions.checkArgument(StringUtils.isNotEmpty(movieId));
        Optional<Movie> movie = metadataRepository.findById(Long.valueOf(movieId));
        if (movie.isPresent()) {
            return prepareMetadataServiceResponse(Arrays.asList(movie.get()));
        } else {
            throw new MetadataServiceException("Movie not found");
        }
    }

    public MetadataServiceResponse getMovieByTitle(String movieTitle) throws MetadataServiceException {
        Preconditions.checkArgument(StringUtils.isNotEmpty(movieTitle));
        List<Movie> movieList = metadataRepository.findByTitleContaining(movieTitle);
        if (Objects.nonNull(movieList) && movieList.size() > 0) {
            return prepareMetadataServiceResponse(movieList);
        } else {
            throw new MetadataServiceException("Movie not found");
        }
    }

    private MetadataServiceResponse prepareMetadataServiceResponse(List<Movie> movies) {
        return new MetadataServiceResponse(movies, HttpStatus.OK,
                Constants.METADATA_SERVICE_SUCCESS_RESPONSE_MESSAGE);
    }

    public MetadataServiceResponse postMovie(Movie movie) {
        try {
            return prepareMetadataServiceResponse(Arrays.asList(metadataRepository.save(movie)));
        } catch(Exception ex) {
            throw new MetadataServiceException("Bad Request");
        }
    }
}
