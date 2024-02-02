package bcsd.backend.project.pokku.exception.UnknownException;

public record UnknownExceptionModel(
        int errorCode,
        String message,
        String hint
) {
}
