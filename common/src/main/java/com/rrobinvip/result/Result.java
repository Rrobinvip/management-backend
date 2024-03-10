package com.rrobinvip.result;

import com.rrobinvip.constant.ReturnResultConstant;
import lombok.Data;

import java.io.Serializable;

/**
 * Backend return a union result
 * @param <T>
 */
@Data
public class Result<T> implements Serializable {

    private Integer code; //1 - success, others - failed
    private String msg; //error message
    private T data; //Data

    public static <T> Result<T> success() {
        Result<T> result = new Result<T>();
        result.code = ReturnResultConstant.SUCCESS;
        return result;
    }

    public static <T> Result<T> success(T object) {
        Result<T> result = new Result<T>();
        result.data = object;
        result.code = ReturnResultConstant.SUCCESS;
        return result;
    }

    public static <T> Result<T> error(String msg) {
        Result<T> result = new Result<T>();
        result.msg = msg;
        result.code = ReturnResultConstant.FAILED;
        return result;
    }

}
