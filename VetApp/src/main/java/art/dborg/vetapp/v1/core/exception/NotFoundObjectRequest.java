package art.dborg.vetapp.v1.core.exception;

public class NotFoundObjectRequest extends RuntimeException{
    public NotFoundObjectRequest(String message) {
        super(message);
    }
}
