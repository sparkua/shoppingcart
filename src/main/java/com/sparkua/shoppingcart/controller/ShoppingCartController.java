package com.sparkua.shoppingcart.controller;

import com.sparkua.shoppingcart.exception.ProductNotFoundException;
import com.sparkua.shoppingcart.model.CartItem;
import com.sparkua.shoppingcart.model.Product;
import com.sparkua.shoppingcart.model.ShoppingCart;
import com.sparkua.shoppingcart.service.ProductService;
import com.sparkua.shoppingcart.service.ShoppingCartService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

/**
 * Created by Spark on 3/14/18.
 */
@RestController
@RequestMapping("/api/cart")
public class ShoppingCartController {
    private final Logger logger = LoggerFactory.getLogger(ShoppingCartController.class);

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
    public ShoppingCart addProduct(@RequestBody CartItem cartItem) {
        return cartService.addProduct(cartItem);
    }

    @PostMapping("/{productId}/{quantity}")
    public ShoppingCart addProductById(@PathVariable("productId") Long productId, @PathVariable("quantity") Integer quantity)  throws ProductNotFoundException{
        return cartService.addProduct(productService.getProductById(productId), quantity);
    }

/*    @PutMapping("/{id}")
    public ShoppingCart updateProduct(@RequestBody ShoppingCart shoppingCart, @PathVariable("id") Long id) {
        return cartService.updateProduct(shoppingCart, id);
    }*/

    @DeleteMapping("/{productId}")
    public void deleteProductItem(@PathVariable("productId") Long id) throws ProductNotFoundException {
        cartService.deleteCartItemByProduct(productService.getProductById(id));
    }

    @DeleteMapping
    public void ClearCart( ) {
        cartService.clearShoppingCart();
    }


}