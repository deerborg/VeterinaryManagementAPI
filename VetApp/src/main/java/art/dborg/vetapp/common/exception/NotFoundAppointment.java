package art.dborg.vetapp.common.exception;

public class NotFoundAppointment extends RuntimeException{
    public NotFoundAppointment(String message) {
        super(message);
    }
}
