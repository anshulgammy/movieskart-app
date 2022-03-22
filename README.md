# movieskart-app
MoviesKart Online Movies DVD/BlueRay Purchase Store.
MoviesKart simulates an online Movie DVDs/BlueRay selling store.
The intent is do develop a simple distributed application where maximum concepts from Microservices could be employed, and tested.

MoviesKart Application Architecture:
MoviesKart comprises of below Microservices-
1) movieskart-metadata-service: Facilitates information/metadata on whatever movies(along with rating info) the store has. 
2) movieskart-inventory-service: Facilitates users to rate the movie which is present in the store.
3) movieskart-order-service: Facilitates users to order the moviie from the store.
4) movieskart-orchestrator-service: Faciliates as the orchestrator for the entire application. This have been made resilient using Resilience4j CircuitBreaker.
5) movieskart-config-service: Facilitates the configuration for all the microservices in MoviesKart application.
6) movieskart-gateway-service: Faciliates Spring Cloud Gateway for routing the traffic, and implementing security.
7) movieskart-discovery-service: Faciliates Spring Cloud Eureka Discover server implementation for all the Microservices.
8) movieskart-model: Common module for all the microservices. This faciliates models which is being used across different microservices. This module is added in all the microservice.

To build the project:
Go to project root folder movieskart-app, and run: mvn clean install. This will build all the microservices, and create docker images for them respectively.
(Docker engine is required, as all the microservices are run in their docker container).
You can also go to each microservice folder and build them separately.
Need to update the Eureka Server IP address before you run microservices, else they wont be able to find where the eureka server is.


Run docker images(in the below order preferably):
movieskart-discovery-service       docker run -d -p 8705:8705 anshulgammy/movieskart-discovery-service:1.0-SNAPSHOT
movieskart-config-service          docker run -d -p 8706:8706 anshulgammy/movieskart-config-service:1.0-SNAPSHOT
movieskart-gateway-service         docker run -d -p 8080:8080 anshulgammy/movieskart-gateway-service:1.0-SNAPSHOT
movieskart-metadata-service        docker run -d -p 8701:8701 anshulgammy/movieskart-metadata-service:1.0-SNAPSHOT
movieskart-inventory-service       docker run -d -p 8702:8702 anshulgammy/movieskart-inventory-service:1.0-SNAPSHOT
movieskart-order-service           docker run -d -p 8703:8703 anshulgammy/movieskart-order-service:1.0-SNAPSHOT
movieskart-orchestrator-service    docker run -d -p 8704:8704 anshulgammy/movieskart-orchestrator-service:1.0-SNAPSHOT
