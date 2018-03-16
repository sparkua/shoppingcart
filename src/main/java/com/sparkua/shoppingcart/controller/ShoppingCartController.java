package com.sparkua.shoppingcart.controller;

import com.sparkua.shoppingcart.exception.ProductNotFoundException;
import com.sparkua.shoppingcart.model.CartItem;
import com.sparkua.shoppingcart.model.ShoppingCart;
import com.sparkua.shoppingcart.service.ProductService;
import com.sparkua.shoppingcart.service.ShoppingCartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Created by Spark on 3/14/18.
 *
 * Shopping cart controller
 */
@RestController
@RequestMapping("/api/cart")
public class ShoppingCartController {

    @Autowired
    ShoppingCartService cartService;

    @Autowired
    ProductService productService;

    @GetMapping
    public List<CartItem> findAll() {
        return cartService.findAll();
    }

    @GetMapping(value = "", params = "productId")
    public CartItem findProductInCartById(@RequestParam("productId") Long productId) throws ProductNotFoundException {
        return cartService.findProduct(productService.getProductById(productId));
    }

    @PostMapping
    public ShoppingCart addProduct(CartItem cartItem) {
        return cartService.addProduct(cartItem);
    }

    @PostMapping("/{productId}/{quantity}")
    public ShoppingCart addProductById(@PathVariable("productId") Long productId, @PathVariable("quantity") Integer quantity)  throws ProductNotFoundException{
        return cartService.addProduct(productService.getProductById(productId), quantity);
    }

    @DeleteMapping("/{productId}")
    public void deleteProductItem(@PathVariable("productId") Long id) throws ProductNotFoundException {
        cartService.deleteCartItemByProduct(productService.getProductById(id));
    }

    @DeleteMapping
    public void ClearCart( ) {
        cartService.clearShoppingCart();
    }


}
