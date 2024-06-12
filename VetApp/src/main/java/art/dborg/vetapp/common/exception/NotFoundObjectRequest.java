package art.dborg.vetapp.common.exception;

public class NotFoundObjectRequest extends RuntimeException{
    public NotFoundObjectRequest(String message) {
        super(message);
    }
}
