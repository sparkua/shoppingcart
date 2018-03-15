package com.sparkua.shoppingcart.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * Created by Spark on 3/15/18.
 *
 * Exception for no product found error
 */
@ResponseStatus(value = HttpStatus.NOT_FOUND, reason = "No products found")
public class ProductNotFoundException extends Exception {

    public ProductNotFoundException() {
        super();
    }

    public ProductNotFoundException(String message) {
        super(message);

    }

}