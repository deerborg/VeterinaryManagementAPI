package art.dborg.vetapp.v1.core.Result;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Result {
    private boolean status;
    private String message;
    private String httpCode;
}
