package com.javatechie.crud.example.service;

import com.javatechie.crud.example.entity.Product;
import com.javatechie.crud.example.repository.ProductRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.api.extension.Extensions;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class ProductServiceTest {

    @Mock
    ProductRepository repository;

    @InjectMocks
    ProductService productService;

    @Test
    void saveProduct() {
        Product product = new Product();
        when(repository.save(any(Product.class))).thenReturn((product));

        //Method is returning saved object if saved successfully
        //Using AssertJ method to check if not returning null
        Product savedProduct = productService.saveProduct(new Product());
        assertThat(savedProduct).isNotNull();

        //Verifying if repository.save method is being called with any instance of Product
        verify(repository).save(any(Product.class));
    }

    @Test
    void saveProducts() {
        //Verifying if  repository.save method is being called with any instance of Product and returning list of products
        Product product = new Product();
        List<Product> productList = new ArrayList<>();
        productList.add(product);
        when(repository.saveAll(anyList())).thenReturn(productList);

        List list = productService.saveProducts(anyList());
        assertEquals(1, list.size());

        //Verifying if call to method repository.saveAll is happening once
        verify(repository).saveAll(anyList());
    }

    @Test
    void getProducts() {
        //Verifying if repository is being called and returning correct values
        Product product = new Product();
        List<Product> productList = new ArrayList<>();
        productList.add(product);
        when(repository.findAll()).thenReturn(productList);

        List<Product> products = productService.getProducts();
        assertEquals(1, products.size());

        //Verifying if repository is being called with any instance of Product
        verify(repository).findAll();
    }


    @Test
    void getProductById() {
        Product product = new Product();
        when(repository.findById(anyInt())).thenReturn(Optional.of(product));
        Product productById = productService.getProductById(anyInt());
        assertNotNull(productById);

        verify(repository).findById(anyInt());
    }

    @Test
    void deleteProduct() {
        productService.deleteProduct(1 );

        verify(repository).deleteById(anyInt());
    }

    @Test
    void updateProduct() {

    }
}