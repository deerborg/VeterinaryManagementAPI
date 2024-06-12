package art.dborg.vetapp.common.exception;

public class NotFoundAnimalException extends RuntimeException{
    public NotFoundAnimalException(String message) {
        super(message);
    }
}
