package bcsd.backend.project.pokku.exception.InputMismatchException;

public record InputMismatchExceptionModel(
        int errorCode,
        String message,
        String hint
) {
}
