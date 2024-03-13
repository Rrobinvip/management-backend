package com.rrobinvip.annotation;

import com.rrobinvip.enumeration.OperationType;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Custom annotation, notify autofill on some public fields (createTime, updateTime, createUser, updateUser)
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface AutoFill {
    // DB operation type, only focus on UPDATE and INSERT
    OperationType value();
}
