package bcsd.backend.project.pokku.exception.NoSuchDataException;

public record NoSuchDataExceptionModel(
        int errorCode,
        String message,
        String hint
) {
}
