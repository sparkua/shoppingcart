package com.sparkua.shoppingcart.aspect;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Configuration;

/**
 * Created by Spark on 3/15/18.
 *
 * Aspect Exception handler
 */
@Aspect
@Configuration
public class ExceptionHandlerAspect {

    private static final Logger LOGGER = LoggerFactory.getLogger(ExceptionHandlerAspect.class);

    @AfterThrowing(pointcut = "execution(* com.sparkua.shoppingcart.service.*.*(..))", throwing = "exc")
    public void myAfterThrowing(JoinPoint joinPoint, Throwable exc) {

        LOGGER.error ("*** EXCEPTION: " + joinPoint.getSignature().getDeclaringTypeName() +"." +
                joinPoint.getSignature().getName() + ": " + exc.getMessage());
    }

}
