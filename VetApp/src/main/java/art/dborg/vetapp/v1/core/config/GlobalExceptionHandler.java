package art.dborg.vetapp.v1.core.config;

import art.dborg.vetapp.v1.core.Result.Result;
import art.dborg.vetapp.v1.core.exception.*;
import art.dborg.vetapp.v1.core.utilities.Message;
import art.dborg.vetapp.v1.core.utilities.ResultHelper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.lang.NullPointerException;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(NotFoundException.class)
    public ResponseEntity<Result> handleNotFoundException(){
        return new ResponseEntity<>(ResultHelper.NOT_FOUND_ID(),HttpStatus.NOT_FOUND);
    }
    @ExceptionHandler(NullPointerException.class)
    public ResponseEntity<Result> handleNullPointerException(){
        return new ResponseEntity<>(ResultHelper.NULL_VALUES(),HttpStatus.INTERNAL_SERVER_ERROR);
    }
    @ExceptionHandler(NotFoundCustomerException.class)
    public ResponseEntity<Result> handleNotFoundCustomerException(){
        return new ResponseEntity<>(ResultHelper.NOT_FOUND_CUSTOMER(),HttpStatus.NOT_FOUND);
    }
    @ExceptionHandler(NotFoundObjectRequest.class)
    public ResponseEntity<Result> handleNotFoundObjectException(){
        return new ResponseEntity<>(ResultHelper.NOT_FOUND(),HttpStatus.NOT_FOUND);
    }
    // Vaccine kaydı için hayvan sorgusu, hayvan yok ise bu hatayı döndürecek
    @ExceptionHandler(NotFoundAnimalException.class)
    public ResponseEntity<Result> handleNotFoundAnimal(){
        return new ResponseEntity<>(ResultHelper.NOT_FOUND_ANIMAL(),HttpStatus.NOT_FOUND);
    }
    @ExceptionHandler(DateMistmatchException.class)
    public ResponseEntity<Result> handleDateMismatchException(){
        return new ResponseEntity<>(ResultHelper.DATE_MISMATCH(),HttpStatus.BAD_REQUEST);
    }
    @ExceptionHandler(ForUpdateNotFoundId.class)
    public ResponseEntity<Result> handeUpdateNotFoundId(){
        return new ResponseEntity<>(ResultHelper.UPDATE_NOT_FOUND_ID(),HttpStatus.NOT_FOUND);
    }
    @ExceptionHandler(NoneSenseInformationException.class)
    public ResponseEntity<Result> handleBadInformationException(){
        return new ResponseEntity<>(ResultHelper.BAD_DATE(),HttpStatus.BAD_REQUEST);
    }
}
