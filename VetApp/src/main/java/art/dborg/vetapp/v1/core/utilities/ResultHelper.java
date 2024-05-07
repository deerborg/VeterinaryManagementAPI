package art.dborg.vetapp.v1.core.utilities;

import art.dborg.vetapp.v1.core.Result.Result;
import art.dborg.vetapp.v1.core.Result.ResultData;

public class ResultHelper {
    public static <T>ResultData<T> CREATED(T data){
        return new ResultData<>(true,Message.CREATED,"201",data);
    }
    public static <T> ResultData<T> OK(T data){
        return new ResultData<>(true,Message.OK,"200",data);
    }
    public static <T> ResultData<T> DELETE(T data){
        return new ResultData<>(true,Message.OK,"200",data);
    }
    public static Result NOT_FOUND_ID(){
        return new Result(false,Message.NOT_FOUND_ID,"400");
    }
    public static Result NULL_POINTER(){
        return new Result(false,Message.NULL_POINTER,"500");
    }
    public static Result NULL_VALUES(){
        return new Result(false,Message.NULL_VALUES,"400");
    }
    public static Result NOT_FOUND_CUSTOMER(){
        return new Result(false,Message.NOT_FOUND_CUSTOMER,"404");
    }
    public static Result NOT_FOUND(){
        return new Result(false,Message.NOT_FOUND,"404");
    }
    public static Result NOT_FOUND_ANIMAL(){
        return new Result(false,Message.NOT_FOUND_ANIMAL,"404");
    }
}
