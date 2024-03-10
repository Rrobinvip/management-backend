package com.rrobinvip.aspect;

import com.rrobinvip.annotation.AutoFill;
import com.rrobinvip.constant.AutoFillConstant;
import com.rrobinvip.context.BaseContext;
import com.rrobinvip.enumeration.OperationType;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;
import java.time.LocalDateTime;

/**
 * Custom aspect, implementation of public field autofill logic
 */
@Aspect
@Component
@Slf4j
public class AutoFillAspect {
    /**
     * Entry points, intercept what method and when. Match all methods under mapper package which with AutoFill annotation.
     */
    @Pointcut("execution(* com.rrobinvip.mapper.*.*(..)) && @annotation(com.rrobinvip.annotation.AutoFill)")
    public void autoFillPointCut() {

    }


    /**
     * Before advice is a type of advice that is executed before the join point method (e.g., a method execution in your application).
     * It allows you to execute certain logic or checks before the actual business logic of the method is run.
     */
    @Before("autoFillPointCut()")
    public void autoFill(JoinPoint joinPoint) throws NoSuchMethodException {
        log.info("AutoFill intercepted, beginning public fields autofill...");

        // Acquire current annotation DB operation type
        MethodSignature signature = (MethodSignature) joinPoint.getSignature(); // Method signature object
        AutoFill autoFill = signature.getMethod().getAnnotation(AutoFill.class);
        OperationType operationType = autoFill.value(); // DB operation type

        // Acquire entity (through args)
        Object[] args = joinPoint.getArgs();
        if (args == null || args.length == 0) return;

        Object entity = args[0];

        // Assign value to public fields, createTime, createUser, updateTime, updateUser
        LocalDateTime now = LocalDateTime.now();
        Long currentId = BaseContext.getCurrentId();

        // Assign value through reflection based on operation type
        if (operationType == OperationType.INSERT) {
            try {
                Method setCreateTime = entity.getClass().getDeclaredMethod(AutoFillConstant.SET_CREATE_TIME, LocalDateTime.class);
                Method setCreateUser = entity.getClass().getDeclaredMethod(AutoFillConstant.SET_CREATE_USER, Long.class);

                // Use reflection to assign value
                setCreateTime.invoke(entity, now);
                setCreateUser.invoke(entity, currentId);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        try {
            Method setUpdateTime = entity.getClass().getDeclaredMethod(AutoFillConstant.SET_UPDATE_TIME, LocalDateTime.class);
            Method setUpdateUser = entity.getClass().getDeclaredMethod(AutoFillConstant.SET_UPDATE_USER, Long.class);

            setUpdateTime.invoke(entity, now);
            setUpdateUser.invoke(entity, currentId);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
