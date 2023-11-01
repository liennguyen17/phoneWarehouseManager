package com.ltw.phonewarehousemanager.controller;

import com.ltw.phonewarehousemanager.constant.Constants;
import com.ltw.phonewarehousemanager.constant.ErrorCodeDefs;
import com.ltw.phonewarehousemanager.dto.entity.ProductDTO;
import com.ltw.phonewarehousemanager.dto.entity.ProductListDTO;
import com.ltw.phonewarehousemanager.dto.model.Product;
import com.ltw.phonewarehousemanager.dto.request.product.CreateProductRequest;
import com.ltw.phonewarehousemanager.dto.request.product.DeleteProductRequest;
import com.ltw.phonewarehousemanager.dto.request.product.GetProductRequest;
import com.ltw.phonewarehousemanager.dto.request.product.UpdateProductRequest;
import com.ltw.phonewarehousemanager.dto.response.BaseItemResponse;
import com.ltw.phonewarehousemanager.dto.response.BaseListResponse;
import com.ltw.phonewarehousemanager.dto.response.BaseResponse;
import com.ltw.phonewarehousemanager.dto.response.ErrorDetail;
import com.ltw.phonewarehousemanager.service.product.ProductService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/admin/v1/products")
@RequiredArgsConstructor
public class ProductController {
    private final ProductService productService;
    private final ModelMapper modelMapper;

    @PostMapping
    public BaseItemResponse<ProductDTO> createProduct(@RequestHeader(value = Constants.HeaderConstant.HEADER_USER_ID) String userId,
                                                      @Valid @RequestBody CreateProductRequest request) {
        return BaseResponse.successData(productService.createProduct(userId, request));
    }

    @PutMapping
    public BaseItemResponse<ProductDTO> updateProduct(@RequestHeader(value = Constants.HeaderConstant.HEADER_USER_ID) String userId,
                                                      @Valid @RequestBody UpdateProductRequest request) {
        return BaseResponse.successData(productService.updateProduct(userId, request));
    }

    @DeleteMapping
    public BaseResponse deleteProduct(@RequestHeader(value = Constants.HeaderConstant.HEADER_USER_ID) String userId,
                                      @Valid @RequestBody DeleteProductRequest request) {
        List<ErrorDetail> errorDetails = productService.deleteProduct(userId, request);
        if (errorDetails == null) {
            return BaseResponse.successData("Xóa thành công sản phẩm!");
        } else {
            return BaseResponse.error(ErrorCodeDefs.ERR_VALIDATION,
                    ErrorCodeDefs.getMessage(ErrorCodeDefs.ERR_VALIDATION),
                    errorDetails);
        }
    }

    @GetMapping("{id}")
    public BaseItemResponse<ProductDTO> getProductById(@RequestHeader(value = Constants.HeaderConstant.HEADER_USER_ID) String userId,
                                                       @PathVariable("id") Long id) {
        return BaseResponse.successData(productService.getProductById(id, userId));
    }

    @PostMapping("/getList")
    public BaseListResponse<ProductListDTO> getListProduct(@RequestHeader(value = Constants.HeaderConstant.HEADER_USER_ID) String userId,
                                                           @Valid @RequestBody GetProductRequest request) {
        Page<Product> productPage = productService.getProductByParam(request, PageRequest.of(request.getStart(), request.getLimit()));
        return BaseResponse.successListData(productPage.getContent().stream()
                .map(p -> modelMapper.map(p, ProductListDTO.class))
                .collect(Collectors.toList()), (int) productPage.getTotalElements());


    }
}
