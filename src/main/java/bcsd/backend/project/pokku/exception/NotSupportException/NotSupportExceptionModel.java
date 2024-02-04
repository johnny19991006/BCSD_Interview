package bcsd.backend.project.pokku.exception.NotSupportException;

public record NotSupportExceptionModel(
        int errorCode,
        String message,
        String hint
) {
}
