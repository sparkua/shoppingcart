package com.sparkua.shoppingcart;

import com.sparkua.shoppingcart.controller.ProductController;
import com.sparkua.shoppingcart.controller.ShoppingCartController;
import com.sparkua.shoppingcart.model.CartItem;
import com.sparkua.shoppingcart.model.Product;
import com.sparkua.shoppingcart.service.ProductService;
import com.sparkua.shoppingcart.service.ShoppingCartService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import static org.mockito.BDDMockito.given;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.mock.http.MockHttpOutputMessage;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.io.IOException;
import java.nio.charset.Charset;
import java.util.Arrays;
import java.util.List;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ShoppingCartApplicationTests {

    @Mock
    private ProductService productService;

    @InjectMocks
    private ProductController productController;

	@Test
	public void contextLoads() {
	}

    private MockMvc mockMvc;


    @Before
    public void init(){
        MockitoAnnotations.initMocks(this);
        mockMvc = MockMvcBuilders
                .standaloneSetup(productController)
                .build();
    }

    @Test
    public void product_by_id_success() throws Exception {
        Product product =
                new Product(Long.valueOf(1), "Canon 70D", "semi proffetional DSLR camera", "DSLR camera", 0, 778.95);
        when(productService.getProductById(1L)).thenReturn(product);

        mockMvc.perform(get("/api/product/{id}", 1))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
                .andExpect(jsonPath("$.id", is(1)))
                .andExpect(jsonPath("$.name", is("Canon 70D")));
        verify(productService, times(1)).getProductById(1L);
        verifyNoMoreInteractions(productService);
    }

    @Test
    public void product_all_success() throws Exception {
        List<Product> listProducts = Arrays.asList(
                new Product(Long.valueOf(1), "Canon 70D", "semi proffetional DSLR camera", "DSLR camera", 0, 778.95),
                new Product(Long.valueOf(2), "Canon 5D Mark IV", "proffetional DSLR camera", "DSLR camera", 0, 2778.95),
                new Product(Long.valueOf(3), "Macbook Pro", "proffetional laptop", "Laptop", 0, 1878.0),
                new Product(Long.valueOf(4), "Macbook Air", "mass market laptop", "Laptop", 0, 899.0),
                new Product(Long.valueOf(5), "Dell XPS", "windows based laptop", "Laptop", 0, 1475.05),
                new Product(Long.valueOf(6), "Acer", "cheap laptop", "Laptop", 0, 599.99)
        );
        when(productService.findAll()).thenReturn(listProducts);

        mockMvc.perform(get("/api/product"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
                .andExpect(jsonPath("$", hasSize(6)));
        verify(productService, times(1)).findAll();
        verifyNoMoreInteractions(productService);
    }

}
