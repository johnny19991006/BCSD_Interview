package bcsd.backend.project.pokku.exception.NullValueException;

public record NullValueExceptionModel(
        int errorCode,
        String message,
        String hint
) {
}
