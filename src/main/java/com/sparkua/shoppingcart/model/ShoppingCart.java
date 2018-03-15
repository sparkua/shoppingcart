package com.sparkua.shoppingcart.model;

import java.util.*;

/**
 * Created by Spark on 3/15/18.
 */
public class ShoppingCart {

    //private Map<Product, Integer> items;
    private List<CartItem> items;
    private String status;
    private Date createdDate;

    public static final String STATUS_INPROGRESS = "IN_PROGRESS";
    public static final String STATUS_PAID = "PAID";

    public ShoppingCart() {
        this.items = new ArrayList<>();
        this.status = STATUS_INPROGRESS;
        this.createdDate = new Date();
    }

    public List<CartItem> getItems() {
        return items;
    }

    public void setItems( List<CartItem> items) {
        this.items = items;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Date getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(Date createdDate) {
        this.createdDate = createdDate;
    }

    public CartItem getCartItembyProduct(Product product){
        for (CartItem cartItem:this.items) {
            if (cartItem.getProduct().equals(product))
                return cartItem;
        }
        return null;
    }
}
