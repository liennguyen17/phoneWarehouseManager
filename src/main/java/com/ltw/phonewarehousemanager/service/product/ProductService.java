package com.ltw.phonewarehousemanager.service.product;

import com.ltw.phonewarehousemanager.dto.entity.ProductDTO;
import com.ltw.phonewarehousemanager.dto.model.Product;
import com.ltw.phonewarehousemanager.dto.request.product.CreateProductRequest;
import com.ltw.phonewarehousemanager.dto.request.product.DeleteProductRequest;
import com.ltw.phonewarehousemanager.dto.request.product.GetProductRequest;
import com.ltw.phonewarehousemanager.dto.request.product.UpdateProductRequest;
import com.ltw.phonewarehousemanager.dto.response.ErrorDetail;
import com.ltw.phonewarehousemanager.repository.product.CustomProductQuery;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

import java.util.List;


public interface ProductService {
    ProductDTO createProduct(String userId, CreateProductRequest request);
    ProductDTO updateProduct(String userId, UpdateProductRequest request);
    List<ErrorDetail> deleteProduct(String userId, DeleteProductRequest request);
    ProductDTO getProductById(Long id, String userId);
    Page<Product> getProductByParam(CustomProductQuery.ProductFilterParam param, PageRequest pageRequest);
}
