package art.dborg.vetapp.common.exception;

import art.dborg.vetapp.common.result.Result;
import art.dborg.vetapp.common.result.ResultData;
import art.dborg.vetapp.common.utilities.ResultHelper;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.resource.NoResourceFoundException;

import java.lang.NullPointerException;
import java.util.List;

@ControllerAdvice
public class GlobalExceptionHandler {


    /**
     * Exception handler for MethodArgumentNotValidException.
     * <p>
     * This method handles MethodArgumentNotValidException, which occurs when a valid method argument is not provided.
     * It retrieves the list of errors and creates an error response with the error list.
     *
     * @param e The MethodArgumentNotValidException object.
     * @return A ResponseEntity containing the error response and HTTP status code.
     */
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ResultData<List<String>>> handleValidationErrors(MethodArgumentNotValidException e) {
        List<String> errorList = e.getBindingResult().getFieldErrors().stream().map(FieldError::getDefaultMessage).toList();
        return new ResponseEntity<>(ResultHelper.ERROR(errorList), HttpStatus.BAD_REQUEST);
    }

    /**
     * Exception handler for DataIntegrityViolationException.
     * <p>
     * This method handles DataIntegrityViolationException, which occurs when a data integrity violation occurs.
     * It returns a response indicating that the data provided is not unique.
     *
     * @return A ResponseEntity containing the error response and HTTP status code.
     */
    @ExceptionHandler(DataIntegrityViolationException.class)
    public ResponseEntity<Result> handleDataIntegrityViolationException() {
        return new ResponseEntity<>(ResultHelper.NOT_UNIQUE(), HttpStatus.BAD_REQUEST);
    }

    /**
     * Exception handler for HttpMessageNotReadableException.
     * <p>
     * This method handles HttpMessageNotReadableException, which occurs when the HTTP message is not readable.
     * It returns a response indicating that the message is unreadable.
     *
     * @return A ResponseEntity containing the error response and HTTP status code.
     */
    @ExceptionHandler(HttpMessageNotReadableException.class)
    public ResponseEntity<Result> handleNotReadableException() {
        return new ResponseEntity<>(ResultHelper.UNREADABLE(), HttpStatus.BAD_GATEWAY);
    }

    /**
     * Exception handler for NotFoundException.
     * <p>
     * This method handles NotFoundException, which occurs when a resource is not found.
     * It returns a response indicating that the resource was not found.
     *
     * @return A ResponseEntity containing the error response and HTTP status code.
     */
    @ExceptionHandler(NotFoundException.class)
    public ResponseEntity<Result> handleNotFoundException() {
        return new ResponseEntity<>(ResultHelper.NOT_FOUND_ID(), HttpStatus.NOT_FOUND);
    }

    /**
     * Exception handler for NullPointerException.
     * <p>
     * This method handles NullPointerException, which occurs when a NullPointerException occurs.
     * It returns a response indicating that null values were encountered.
     *
     * @return A ResponseEntity containing the error response and HTTP status code.
     */
    @ExceptionHandler(NullPointerException.class)
    public ResponseEntity<Result> handleNullPointerException() {
        return new ResponseEntity<>(ResultHelper.NULL_VALUES(), HttpStatus.INTERNAL_SERVER_ERROR);
    }

    /**
     * Exception handler for NotFoundCustomerException.
     * <p>
     * This method handles NotFoundCustomerException, which occurs when a customer is not found.
     * It returns a response indicating that the customer was not found.
     *
     * @return A ResponseEntity containing the error response and HTTP status code.
     */
    @ExceptionHandler(NotFoundCustomerException.class)
    public ResponseEntity<Result> handleNotFoundCustomerException() {
        return new ResponseEntity<>(ResultHelper.NOT_FOUND_CUSTOMER(), HttpStatus.NOT_FOUND);
    }

    /**
     * Exception handler for NotFoundObjectRequest.
     * <p>
     * This method handles NotFoundObjectRequest, which occurs when an object is not found in the request.
     * It returns a response indicating that the requested object was not found.
     *
     * @return A ResponseEntity containing the error response and HTTP status code.
     */
    @ExceptionHandler(NotFoundObjectRequest.class)
    public ResponseEntity<Result> handleNotFoundObjectException() {
        return new ResponseEntity<>(ResultHelper.NOT_FOUND(), HttpStatus.NOT_FOUND);
    }

    /**
     * Exception handler for NotFoundAnimalException.
     * <p>
     * This method handles NotFoundAnimalException, which occurs when an animal is not found.
     * It returns a response indicating that the animal was not found.
     *
     * @return A ResponseEntity containing the error response and HTTP status code.
     */
    @ExceptionHandler(NotFoundAnimalException.class)
    public ResponseEntity<Result> handleNotFoundAnimal() {
        return new ResponseEntity<>(ResultHelper.NOT_FOUND_ANIMAL(), HttpStatus.NOT_FOUND);
    }


    /**
     * Exception handler for DateMismatchException.
     * <p>
     * This method handles DateMismatchException, which occurs when there is a mismatch in vaccine protection dates.
     * It returns a response indicating that there is a mismatch in vaccine protection dates.
     *
     * @return A ResponseEntity containing the error response and HTTP status code.
     */
    @ExceptionHandler(DateMistmatchException.class)
    public ResponseEntity<Result> handleDateMismatchException() {
        return new ResponseEntity<>(ResultHelper.DATE_MISMATCH(), HttpStatus.BAD_REQUEST);
    }

    /**
     * Exception handler for ForUpdateNotFoundIdException.
     * <p>
     * This method handles ForUpdateNotFoundIdException, which occurs when an ID is not provided during update operations.
     * It returns a response indicating that an ID was not found during update operations.
     *
     * @return A ResponseEntity containing the error response and HTTP status code.
     */
    @ExceptionHandler(ForUpdateNotFoundIdException.class)
    public ResponseEntity<Result> handeUpdateNotFoundId() {
        return new ResponseEntity<>(ResultHelper.UPDATE_NOT_FOUND_ID(), HttpStatus.NOT_FOUND);
    }

    /**
     * Exception handler for NoneSenseInformationException.
     * <p>
     * This method handles NoneSenseInformationException, which occurs when invalid date information is provided.
     * It returns a response indicating that invalid date information was provided.
     *
     * @return A ResponseEntity containing the error response and HTTP status code.
     */
    @ExceptionHandler(NoneSenseInformationException.class)
    public ResponseEntity<Result> handleBadInformationException() {
        return new ResponseEntity<>(ResultHelper.BAD_DATE(), HttpStatus.BAD_REQUEST);
    }

    /**
     * Exception handler for NotUniqueValues.
     * <p>
     * This method handles NotUniqueValues, which occurs when duplicate values are provided in a request that requires unique data.
     * It returns a response indicating that duplicate values were provided.
     *
     * @return A ResponseEntity containing the error response and HTTP status code.
     */
    @ExceptionHandler(NotUniqueValues.class)
    public ResponseEntity<Result> handleNotUniqueValuesException() {
        return new ResponseEntity<>(ResultHelper.NOT_UNIQUE(), HttpStatus.BAD_REQUEST);
    }

    /**
     * Exception handler for NotFoundDoctorException.
     * <p>
     * This method handles NotFoundDoctorException, which occurs when a doctor is not found.
     * It returns a response indicating that the doctor was not found.
     *
     * @return A ResponseEntity containing the error response and HTTP status code.
     */
    @ExceptionHandler(NotFoundDoctorException.class)
    public ResponseEntity<Result> handleNotFoundDoctorException() {
        return new ResponseEntity<>(ResultHelper.NOT_FOUND_DOCTOR(), HttpStatus.BAD_REQUEST);
    }

    /**
     * Exception handler for SameValuesException.
     * <p>
     * This method handles SameValuesException, which occurs when the same values are encountered.
     * It returns a response indicating that the same values were encountered.
     *
     * @return A ResponseEntity containing the error response and HTTP status code.
     */
    @ExceptionHandler(SameValuesException.class)
    public ResponseEntity<Result> handeSameValuesException() {
        return new ResponseEntity<>(ResultHelper.SAME_VALUES(), HttpStatus.BAD_REQUEST);
    }

    /**
     * Exception handler for NoResourceFoundException.
     * <p>
     * This method handles NoResourceFoundException, which occurs when a requested resource is not found.
     * It returns a response indicating that the requested resource was not found.
     *
     * @return A ResponseEntity containing the error response and HTTP status code.
     */
    @ExceptionHandler(NoResourceFoundException.class)
    public ResponseEntity<Result> resourceNotFoundException() {
        return new ResponseEntity<>(ResultHelper.RESOURCE_NOT_FOUND(), HttpStatus.BAD_GATEWAY);
    }

    /**
     * Exception handler for DoctorDaysConflictException.
     * <p>
     * This method handles DoctorDaysConflictException, which occurs when there is a conflict in doctor days.
     * It returns a response indicating a conflict in doctor days.
     *
     * @return A ResponseEntity containing the error response and HTTP status code.
     */
    @ExceptionHandler(DoctorDaysConflictException.class)
    public ResponseEntity<Result> handleDaysConflictException() {
        return new ResponseEntity<>(ResultHelper.DAYS_CONFLICT(), HttpStatus.BAD_GATEWAY);
    }

    /**
     * Exception handler for AppointmentConflictException.
     * <p>
     * This method handles AppointmentConflictException, which occurs when there is a conflict in appointments.
     * It returns a response indicating a conflict in appointments.
     *
     * @return A ResponseEntity containing the error response and HTTP status code.
     */
    @ExceptionHandler(AppointmentConflictException.class)
    public ResponseEntity<Result> handeAppointmentConflictException() {
        return new ResponseEntity<>(ResultHelper.APPOINTMENT_CONFLICT(), HttpStatus.BAD_REQUEST);
    }

    /**
     * Exception handler for NotFoundAppointment.
     * <p>
     * This method handles NotFoundAppointment, which occurs when an appointment is not found.
     * It returns a response indicating that the appointment was not found.
     *
     * @return A ResponseEntity containing the error response and HTTP status code.
     */
    @ExceptionHandler(NotFoundAppointment.class)
    public ResponseEntity<Result> handeNotFoundAppointmentException() {
        return new ResponseEntity<>(ResultHelper.NOT_FOUND_APPOINTMENT(), HttpStatus.BAD_REQUEST);
    }

    /**
     * Exception handler for AppointmentAlreadyExists.
     * <p>
     * This method handles AppointmentAlreadyExists, which occurs when an appointment already exists.
     * It returns a response indicating that the appointment already exists.
     *
     * @return A ResponseEntity containing the error response and HTTP status code.
     */
    @ExceptionHandler(AppointmentAlreadyExists.class)
    public ResponseEntity<Result> handeAppointmentAlreadyExistException() {
        return new ResponseEntity<>(ResultHelper.EXIST_APPOINTMENT(), HttpStatus.BAD_REQUEST);
    }

    /**
     * Exception handler for ForceUpdateException.
     * <p>
     * This method handles ForceUpdateException, which occurs when a force update is attempted.
     * It returns a response indicating that a force update was attempted.
     *
     * @return A ResponseEntity containing the error response and HTTP status code.
     */
    @ExceptionHandler(ForceUpdateException.class)
    public ResponseEntity<Result> handleForceUpdateException() {
        return new ResponseEntity<>(ResultHelper.FORCE_UPDATE(), HttpStatus.BAD_REQUEST);
    }

    /**
     * Exception handler for MissingServletRequestParameterException.
     * <p>
     * This method handles MissingServletRequestParameterException, which occurs when a request parameter is missing.
     * It returns a response indicating that a parameter is missing from the request.
     *
     * @return A ResponseEntity containing the error response and HTTP status code.
     */
    @ExceptionHandler(MissingServletRequestParameterException.class)
    public ResponseEntity<Result> handleMissingServletRequestParameterException() {
        return new ResponseEntity<>(ResultHelper.MISSING_PARAMETER(), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(NotFoundUserPasswordOrUsername.class)
    public ResponseEntity<Result> handleNotFoundUserPasswordOrUsernameException() {
        return new ResponseEntity<>(ResultHelper.UNAUTHORIZED(), HttpStatus.UNAUTHORIZED);
    }

}
