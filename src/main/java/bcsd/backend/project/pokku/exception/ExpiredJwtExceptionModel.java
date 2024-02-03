package bcsd.backend.project.pokku.exception;

public record ExpiredJwtExceptionModel(        int errorCode,
                                               String message,
                                               String hint) {
}
