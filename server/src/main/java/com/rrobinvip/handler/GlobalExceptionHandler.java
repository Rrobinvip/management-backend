package com.rrobinvip.handler;

import com.rrobinvip.constant.MessageConstant;
import com.rrobinvip.exception.BaseException;
import com.rrobinvip.result.Result;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.sql.SQLIntegrityConstraintViolationException;

/**
 * Global exception handler
 */
@RestControllerAdvice
@Slf4j
public class GlobalExceptionHandler {

    /**
     * Capture exceptions
     *
     * @param ex
     * @return
     */
    @ExceptionHandler
    public Result exceptionHandler(BaseException ex) {
        log.error("Exceptions：{}", ex.getMessage());
        return Result.error(ex.getMessage());
    }

    @ExceptionHandler
    public Result exceptionHandler(SQLIntegrityConstraintViolationException ex) {
        String errorMessage = ex.getMessage();
        log.error("SQL Exception: {}", errorMessage);
        if (errorMessage.contains("Duplicate entry")) {
            String[] split = errorMessage.split(" ");
            String duplicateUsername = split[2];
            return Result.error(String.format("%s: %s", MessageConstant.ACCOUNT_EXISTS, duplicateUsername));
        } else {
            return Result.error(String.format("%s", MessageConstant.UNKNOWN_ERROR));
        }
    }
}
