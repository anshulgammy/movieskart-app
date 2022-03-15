package dev.bumbler.movieskart.metadata.exceptions;

public class MetadataServiceException extends RuntimeException {

    public MetadataServiceException() {
        super();
    }

    public MetadataServiceException(String message) {
        super(message);
    }

    public MetadataServiceException(String message, Throwable cause) {
        super(message, cause);
    }

    public MetadataServiceException(Throwable cause) {
        super(cause);
    }
}
