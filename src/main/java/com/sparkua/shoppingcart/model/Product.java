package com.sparkua.shoppingcart.model;

import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.DecimalMin;

/**
 * Created by Spark on 3/15/18.
 *
 * Product model
 */
public class Product {

    private Long id;

    @Length(min = 3, message = "Name should have at least 3 characters")
    private String name;

    private String description;

    private String type;

    @DecimalMin(value = "0.01", message = "Price has to be bigger than $0.00")
    private Double price;

    public Product(Long id, String name, String description, String type, Integer quantity, Double price) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.type = type;
        this.price = price;
    }

    public Long getId() {
        return id;

    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }


    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;

        Product product = (Product) obj;

        return id.equals(product.getId());
    }
}
