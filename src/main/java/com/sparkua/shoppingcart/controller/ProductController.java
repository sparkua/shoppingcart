package com.sparkua.shoppingcart.controller;

import com.sparkua.shoppingcart.exception.ProductNotFoundException;
import com.sparkua.shoppingcart.model.Product;
import com.sparkua.shoppingcart.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Created by Spark on 3/14/18.
 *
 * Product rest controller
 */
@RestController
@RequestMapping("/api")
public class ProductController {

    @Autowired
    ProductService productService;

    @GetMapping("/product")
    public List<Product> findAll() {
        return productService.findAll();
    }

    @GetMapping("/product/{id}")
    public Product findById(@PathVariable("id") long id) throws ProductNotFoundException{
        return productService.getProductById(id);
    }

    @GetMapping(value = "/product", params = "type")
    public List<Product> findByType(@RequestParam("type") String type){
        return productService.getProductByType(type);
    }

}
