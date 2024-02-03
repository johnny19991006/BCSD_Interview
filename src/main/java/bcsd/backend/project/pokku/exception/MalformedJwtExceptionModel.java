package bcsd.backend.project.pokku.exception;

public record MalformedJwtExceptionModel(int errorCode,
                                         String message,
                                         String hint) {
}
