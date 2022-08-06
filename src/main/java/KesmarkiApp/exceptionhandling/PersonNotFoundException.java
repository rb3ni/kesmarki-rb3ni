package KesmarkiApp.exceptionhandling;

public class PersonNotFoundException extends RuntimeException {
    private int idNotFound;

    public PersonNotFoundException(int idNotFound) {
        this.idNotFound = idNotFound;
    }

    public int getIdNotFound() {
        return idNotFound;
    }
}
