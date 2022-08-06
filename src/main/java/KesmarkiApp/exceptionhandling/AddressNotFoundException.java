package KesmarkiApp.exceptionhandling;

public class AddressNotFoundException extends RuntimeException {
    private int idNotFound;

    public AddressNotFoundException(int idNotFound) {
        this.idNotFound = idNotFound;
    }

    public int getIdNotFound() {
        return idNotFound;
    }
}
