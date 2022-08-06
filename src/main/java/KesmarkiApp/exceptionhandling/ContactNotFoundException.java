package KesmarkiApp.exceptionhandling;

public class ContactNotFoundException extends RuntimeException {
    private int idNotFound;

    public ContactNotFoundException(int idNotFound) {
        this.idNotFound = idNotFound;
    }

    public int getIdNotFound() {
        return idNotFound;
    }
}
