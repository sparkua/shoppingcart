package com.sparkua.shoppingcart.service;

import com.sparkua.shoppingcart.exception.ProductNotFoundException;
import com.sparkua.shoppingcart.model.Product;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by Spark on 3/14/18.
 */
@Service
public class ProductService {

    private final List<Product> listProducts = Arrays.asList(
            new Product(Long.valueOf(1), "Canon 70D", "semi proffetional DSLR camera", "DSLR camera", 0, 778.95),
            new Product(Long.valueOf(2), "Canon 5D Mark IV", "proffetional DSLR camera", "DSLR camera", 0, 2778.95),
            new Product(Long.valueOf(3), "Macbook Pro", "proffetional laptop", "Laptop", 0, 1878.0),
            new Product(Long.valueOf(4), "Macbook Air", "mass market laptop", "Laptop", 0, 899.0),
            new Product(Long.valueOf(5), "Dell XPS", "windows based laptop", "Laptop", 0, 1475.05),
            new Product(Long.valueOf(6), "Acer", "cheap laptop", "Laptop", 0, 599.99)
    );

    public List<Product> findAll(){
        return listProducts;
    }

    public Product getProductById(Long id) throws ProductNotFoundException{
        Product productResult = null;
        for (Product product: listProducts) {
            if (product.getId().equals(id))
                productResult = product;
        }
        if (productResult != null)
            return productResult;
        else
            throw new ProductNotFoundException("No product found with id:" + id);
    }

    public List<Product> getProductByType(String type){
        List<Product> productList = new ArrayList<>();
        for (Product product: listProducts) {
            if (product.getType().equals(type))
                productList.add(product);
        }
        return productList;
    }
}
