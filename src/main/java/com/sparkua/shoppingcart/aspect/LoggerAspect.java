package com.sparkua.shoppingcart.aspect;

import com.sparkua.shoppingcart.model.Product;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

/**
 * Created by Spark on 3/15/18.
 *
 * Aspect Logger handler
 */
@Aspect
@Component
public class LoggerAspect {

    private static final Logger LOGGER = LoggerFactory.getLogger(LoggerAspect.class);

    public LoggerAspect () {}

    @Before("execution(* com.sparkua.shoppingcart..*.*(..))")
    public void logMethodAccessBefore(JoinPoint joinPoint) {
        LOGGER.info("Starting: " + joinPoint.getSignature().getName());
    }

    @AfterReturning("execution(* com.sparkua.shoppingcart..*.*(..))")
    public void logMethodAccessAfter(JoinPoint joinPoint) {
        LOGGER.info("Completed: " + joinPoint.getSignature().getName());
    }

    @After("execution(* com.sparkua.shoppingcart.service.ShoppingCartService.addProduct (com.sparkua.shoppingcart.model.Product,  Integer)) && args(product, quantity)")
    public void afterAddProduct(Product product, Integer quantity) {
        LOGGER.info(product.getName() + " added to cart with " + quantity + " quantity");
    }

}
