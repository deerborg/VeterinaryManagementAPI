package art.dborg.vetapp.common.result;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

// Class representing the result of an operation
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Result {
    // Status indicating whether the operation was successful or not
    private boolean status;

    // Message providing details about the result of the operation
    private String message;

    // HTTP status code representing the result of the operation
    private String httpCode;
}
