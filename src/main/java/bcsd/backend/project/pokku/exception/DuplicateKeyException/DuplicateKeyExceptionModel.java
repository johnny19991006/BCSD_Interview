package bcsd.backend.project.pokku.exception.DuplicateKeyException;

public record DuplicateKeyExceptionModel(
        int errorCode,
        String message,
        String hint
) {
}
