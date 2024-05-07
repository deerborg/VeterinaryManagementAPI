package art.dborg.vetapp.v1.core.config;

import art.dborg.vetapp.v1.core.Result.Result;
import art.dborg.vetapp.v1.core.exception.NotFoundAnimalException;
import art.dborg.vetapp.v1.core.exception.NotFoundObjectRequest;
import art.dborg.vetapp.v1.core.exception.NotFoundCustomerException;
import art.dborg.vetapp.v1.core.exception.NotFoundException;
import art.dborg.vetapp.v1.core.utilities.Message;
import art.dborg.vetapp.v1.core.utilities.ResultHelper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

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
}
