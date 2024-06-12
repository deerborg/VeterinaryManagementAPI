package art.dborg.vetapp.common.exception;

public class AppointmentAlreadyExists extends RuntimeException{
    public AppointmentAlreadyExists(String message) {
        super(message);
    }
}
