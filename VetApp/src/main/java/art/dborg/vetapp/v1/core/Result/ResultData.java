package art.dborg.vetapp.v1.core.Result;

import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class ResultData <T> extends Result{
    public T data;

    public ResultData(boolean status, String message, String httpCode, T data) {
        super(status, message, httpCode);
        this.data = data;
    }
}
