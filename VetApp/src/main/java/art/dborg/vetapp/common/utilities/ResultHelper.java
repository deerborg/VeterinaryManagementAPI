package art.dborg.vetapp.common.utilities;

import art.dborg.vetapp.common.result.Result;
import art.dborg.vetapp.common.result.ResultData;

public class ResultHelper {
    /**
     * Creates a ResultData object with a success message, HTTP status code 201 (CREATED), and the provided data.
     *
     * @param data The data to include in the ResultData object.
     * @param <T>  The type of the data.
     * @return The created ResultData object.
     */
    public static <T> ResultData<T> CREATED(T data) {
        return new ResultData<>(true, Message.CREATED, "201", data);
    }

    /**
     * Creates a ResultData object with a success message, HTTP status code 200 (OK), and the provided data.
     *
     * @param data The data to include in the ResultData object.
     * @param <T>  The type of the data.
     * @return The created ResultData object.
     */
    public static <T> ResultData<T> OK(T data) {
        return new ResultData<>(true, Message.OK, "200", data);
    }

    /**
     * Creates a ResultData object with a resolution message, HTTP status code 200 (OK), and the provided data.
     *
     * @param data The data to include in the ResultData object.
     * @param <T>  The type of the data.
     * @return The created ResultData object.
     */
    public static <T> ResultData<T> RESOLVE(T data) {
        return new ResultData<>(true, Message.RESOLVE, "200", data);
    }

    /**
     * Creates a ResultData object with a deletion message, HTTP status code 200 (OK), and the provided data.
     *
     * @param data The data to include in the ResultData object.
     * @param <T>  The type of the data.
     * @return The created ResultData object.
     */
    public static <T> ResultData<T> DELETE(T data) {
        return new ResultData<>(true, Message.DELETED, "200", data);
    }

    /**
     * Creates a ResultData object with an error message, HTTP status code 409 (CONFLICT), and the provided data.
     *
     * @param data The data to include in the ResultData object.
     * @param <T>  The type of the data.
     * @return The created ResultData object.
     */
    public static <T> ResultData<T> ERROR(T data) {
        return new ResultData<>(false, Message.NOT_NULL_MESSAGE, "409", data);
    }


    /**
     * Creates a Result object with a "Not found ID" message and HTTP status code 404.
     *
     * @return The created Result object.
     */
    public static Result NOT_FOUND_ID() {
        return new Result(false, Message.NOT_FOUND_ID, "404");
    }

    /**
     * Creates a Result object with a "Null pointer" message and HTTP status code 500.
     *
     * @return The created Result object.
     */
    public static Result NULL_POINTER() {
        return new Result(false, Message.NULL_POINTER, "500");
    }

    /**
     * Creates a Result object with a "Null values" message and HTTP status code 404.
     *
     * @return The created Result object.
     */
    public static Result NULL_VALUES() {
        return new Result(false, Message.NULL_VALUES, "404");
    }

    /**
     * Creates a Result object with a "Not found customer" message and HTTP status code 404.
     *
     * @return The created Result object.
     */
    public static Result NOT_FOUND_CUSTOMER() {
        return new Result(false, Message.NOT_FOUND_CUSTOMER, "404");
    }

    /**
     * Creates a Result object with a "Not found" message and HTTP status code 404.
     *
     * @return The created Result object.
     */
    public static Result NOT_FOUND() {
        return new Result(false, Message.NOT_FOUND, "404");
    }

    /**
     * Creates a Result object with a "Not found animal" message and HTTP status code 404.
     *
     * @return The created Result object.
     */
    public static Result NOT_FOUND_ANIMAL() {
        return new Result(false, Message.NOT_FOUND_ANIMAL, "404");
    }

    /**
     * Creates a Result object with a "Date mismatch" message and HTTP status code 417.
     *
     * @return The created Result object.
     */
    public static Result DATE_MISMATCH() {
        return new Result(false, Message.DATE_MISMATCH, "417");
    }

    /**
     * Creates a Result object with an "Update not found ID" message and HTTP status code 404.
     *
     * @return The created Result object.
     */
    public static Result UPDATE_NOT_FOUND_ID() {
        return new Result(false, Message.UPDATE_NOT_FOUND_ID, "404");
    }

    /**
     * Creates a Result object with a "Bad date" message and HTTP status code 415.
     *
     * @return The created Result object.
     */
    public static Result BAD_DATE() {
        return new Result(false, Message.BAD_DATE, "415");
    }

    /**
     * Creates a Result object with a "Not unique" message and HTTP status code 415.
     *
     * @return The created Result object.
     */
    public static Result NOT_UNIQUE() {
        return new Result(false, Message.NOT_UNIQUE, "415");
    }

    /**
     * Creates a Result object with a "Not found doctor" message and HTTP status code 404.
     *
     * @return The created Result object.
     */
    public static Result NOT_FOUND_DOCTOR() {
        return new Result(false, Message.NOT_FOUND_DOCTOR, "404");
    }

    /**
     * Creates a Result object with a "Same values" message and HTTP status code 409.
     *
     * @return The created Result object.
     */
    public static Result SAME_VALUES() {
        return new Result(false, Message.SAME_VALUES, "409");
    }

    /**
     * Creates a Result object with a "Resource not found" message and HTTP status code 409.
     *
     * @return The created Result object.
     */
    public static Result RESOURCE_NOT_FOUND() {
        return new Result(false, Message.RESOURCE_NOT_FOUND, "409");
    }

    /**
     * Creates a Result object with a "Days conflict" message and HTTP status code 400.
     *
     * @return The created Result object.
     */
    public static Result DAYS_CONFLICT() {
        return new Result(false, Message.DAYS_CONFLICT, "400");
    }

    /**
     * Creates a Result object with an "Appointment conflict" message and HTTP status code 400.
     *
     * @return The created Result object.
     */
    public static Result APPOINTMENT_CONFLICT() {
        return new Result(false, Message.APPOINTMENT_CONFLICT, "400");
    }

    /**
     * Creates a Result object with a "Not found appointment" message and HTTP status code 400.
     *
     * @return The created Result object.
     */
    public static Result NOT_FOUND_APPOINTMENT() {
        return new Result(false, Message.NOT_FOUND_APPOINTMENT, "400");
    }

    /**
     * Creates a Result object with an "Existing appointment" message and HTTP status code 400.
     *
     * @return The created Result object.
     */
    public static Result EXIST_APPOINTMENT() {
        return new Result(false, Message.EXISTING_APPOINTMENT, "400");
    }

    /**
     * Creates a Result object with an "Unreadable" message and HTTP status code 502.
     *
     * @return The created Result object.
     */
    public static Result UNREADABLE() {
        return new Result(false, Message.UNREADABLE, "502");
    }

    /**
     * Creates a Result object with a "Force update" message and HTTP status code 502.
     *
     * @return The created Result object.
     */
    public static Result FORCE_UPDATE() {
        return new Result(false, Message.FORCE_UPDATE, "502");
    }

    /**
     * Creates a Result indicating a missing parameter error.
     * <p>
     * This method creates a Result object indicating a missing parameter error.
     * It sets the success flag to false, sets the message to indicate the missing parameter,
     * and sets the code to the provided error code "502".
     *
     * @return A Result object indicating a missing parameter error.
     */
    public static Result MISSING_PARAMETER() {
        return new Result(false, Message.MISSING_PARAMETER, "502");
    }

    public static Result UNAUTHORIZED(){
        return new Result(false,Message.UNAUTHORIZED,"401");
    }
}


