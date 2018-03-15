package com.sparkua.shoppingcart.service;

import com.sparkua.shoppingcart.model.CartItem;
import com.sparkua.shoppingcart.model.Product;
import com.sparkua.shoppingcart.model.ShoppingCart;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Service;
import org.springframework.web.context.WebApplicationContext;

import java.util.*;

/**
 * Created by Spark on 3/14/18.
 */
@Service
@Scope(value = WebApplicationContext.SCOPE_SESSION, proxyMode = ScopedProxyMode.TARGET_CLASS)
public class ShoppingCartService {

    private ShoppingCart shoppingCart = new ShoppingCart();

    public List<CartItem> findAll(){
        return shoppingCart.getItems();
    }

    public CartItem findProduct(Product product){
        return shoppingCart.getCartItembyProduct(product);
    }

    public ShoppingCart addProduct(Product product, Integer quantity){
        CartItem cartItem = new CartItem(product, quantity);
        if (shoppingCart.getItems().contains(cartItem)) {
            Integer ind =  shoppingCart.getItems().indexOf(cartItem);
            shoppingCart.getItems().get(ind).addQuantity(quantity);
        } else {
            shoppingCart.getItems().add(cartItem);
        }
        return shoppingCart;
    }

    public ShoppingCart addProduct(CartItem cartItem){
        if (shoppingCart.getItems().contains(cartItem)) {
            Integer ind =  shoppingCart.getItems().indexOf(cartItem);
            shoppingCart.getItems().get(ind).addQuantity(cartItem.getQuantity());
        } else {
            shoppingCart.getItems().add(cartItem);
        }
        return shoppingCart;
    }

//    public ShoppingCart updateProduct(ShoppingCart shoppingCart, Long id){
//
//        return shoppingCart;
//    }

    public void deleteCartItemByProduct(Product product){
        deleteCartItem(shoppingCart.getCartItembyProduct(product));
    }

    public void deleteCartItem(CartItem cartItem){
        if (shoppingCart.getItems().contains(cartItem)){
            shoppingCart.getItems().remove(cartItem);
        }
    }

    public void clearShoppingCart(){

    }

}
