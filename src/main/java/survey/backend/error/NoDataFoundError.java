package survey.backend.error;

public class NoDataFoundError extends RuntimeException {

    public NoDataFoundError(String message) {
        super(message);
    }

    public static NoDataFoundError withId(String itemType, int id) {
        return new NoDataFoundError(
                itemType
                        + " with id "
                        + id
                        + " not found, try again !");
    }

    //  uniquement pour message erreur pas de résultat
    public static NoDataFoundError noResults(String itemType, String message) {
        return new NoDataFoundError(
                itemType
                        + " returns 0 results"
                        + " with "
                        + message
        );

    }
}