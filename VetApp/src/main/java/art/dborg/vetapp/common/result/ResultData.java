package art.dborg.vetapp.common.result;

import lombok.Getter;
import lombok.NoArgsConstructor;

// This class extends the base Result class to provide additional functionality for returning data along with the result of an operation.
@Getter
@NoArgsConstructor
public class ResultData <T> extends Result {
    // The data associated with the result
    public T data;

    // Constructor to initialize the ResultData object with status, message, HTTP code, and data
    public ResultData(boolean status, String message, String httpCode, T data) {
        super(status, message, httpCode);
        this.data = data;
    }
}
