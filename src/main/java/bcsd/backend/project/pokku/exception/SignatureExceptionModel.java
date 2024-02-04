package bcsd.backend.project.pokku.exception;

public record SignatureExceptionModel(        int errorCode,
                                              String message,
                                              String hint) {
}
