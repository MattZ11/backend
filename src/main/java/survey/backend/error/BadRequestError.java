package survey.backend.error;

public class BadRequestError extends RuntimeException{  //RuntimeException appelée pour les erreurs qui apparaissent seulement lorsque la méthode est appelée
public BadRequestError(String message) {
    super(message);
}
    public static BadRequestError withNoArgs(String itemType) {
        return new BadRequestError(
                itemType);
    }
}
