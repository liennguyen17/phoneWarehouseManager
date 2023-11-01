package com.ltw.phonewarehousemanager.product;

import com.ltw.phonewarehousemanager.dto.entity.ProductDTO;
import com.ltw.phonewarehousemanager.dto.model.Product;
import com.ltw.phonewarehousemanager.dto.request.product.CreateProductRequest;
import com.ltw.phonewarehousemanager.service.product.ProductService;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
@Slf4j
public class TestProductService {
    @Autowired
    private ProductService productService;

    @Test
    public void testCreateProduct(){
        String name = "dien thoai samsung";
        CreateProductRequest request = new CreateProductRequest();
        request.setName(name);
        request.setDescription("demo demo");
        request.setSupplier(1L);

        ProductDTO productDTO = productService.createProduct("admin2", request);
        log.info("Create product success: {}", productDTO);

    }
}
