package com.ltw.phonewarehousemanager.service.product;

import com.ltw.phonewarehousemanager.constant.Constants;
import com.ltw.phonewarehousemanager.dto.entity.ProductDTO;
import com.ltw.phonewarehousemanager.dto.entity.ProductDeleteDTO;
import com.ltw.phonewarehousemanager.dto.entity.ProductUpdateValueDTO;
import com.ltw.phonewarehousemanager.dto.model.Product;
import com.ltw.phonewarehousemanager.dto.model.Supplier;
import com.ltw.phonewarehousemanager.dto.request.product.CreateProductRequest;
import com.ltw.phonewarehousemanager.dto.request.product.DeleteProductRequest;
import com.ltw.phonewarehousemanager.dto.request.product.GetProductRequest;
import com.ltw.phonewarehousemanager.dto.request.product.UpdateProductRequest;
import com.ltw.phonewarehousemanager.dto.response.ErrorDetail;
import com.ltw.phonewarehousemanager.exception.BaseValidateException;
import com.ltw.phonewarehousemanager.exception.DataNotFoundException;
import com.ltw.phonewarehousemanager.repository.product.CustomProductQuery;
import com.ltw.phonewarehousemanager.repository.product.ProductRepository;
import com.ltw.phonewarehousemanager.repository.suppliser.SupplierRepository;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Slf4j
public class ProductServiceImpl implements ProductService {
    private final ProductRepository productRepository;
    private final SupplierRepository supplierRepository;
    private final ProductUpdateMapper productUpdateMapper;
    private final ModelMapper modelMapper;

    @Override
    public ProductDTO createProduct(String userId, CreateProductRequest request) {
//        Supplier supplier = supplierRepository.findById(request.getSupplierId()).orElseThrow(() -> new RuntimeException(Constants.ErrorMessageSupplierValidation.NOT_FIND_SUPPLIER_BY_ID + request.getSupplierId()));
        try {
            log.info("createProduct- userId: {], request: {}", userId, request);
            Product product = modelMapper.map(request, Product.class);
            product.setCreatedTime(new Timestamp(System.currentTimeMillis()));
            product.setCreatedBy(userId);
//            product.setSupplier(supplier);
//            checkSupplierExits(product, request.getSupplierId());
            if (request.getSupplier() != null) {
                Supplier supplier = supplierRepository.findById(request.getSupplier())
                        .orElseThrow(() -> new RuntimeException(Constants.ErrorMessageSupplierValidation.NOT_FIND_SUPPLIER_BY_ID + request.getSupplier()));
                product.setSupplier(supplier);
            }
            return modelMapper.map(productRepository.saveAndFlush(product), ProductDTO.class);
        } catch (DataIntegrityViolationException e) {
            throw new BaseValidateException(Constants.ErrorMessageProductValidation.ERROR_CREAT);
        }
    }

    @Override
    public ProductDTO updateProduct(String userId, UpdateProductRequest request) {
        Optional<Product> product = productRepository.findById(request.getId());
        if(!product.isPresent()){
            throw new DataNotFoundException(Constants.ErrorMessageProductValidation.NOT_FIND_PRODUCT_BY_ID + request.getId());
        }
        ProductUpdateValueDTO updateValueDTO = modelMapper.map(request, ProductUpdateValueDTO.class);

        Product updateValue = product.get();
        productUpdateMapper.updateProductFromDto(updateValueDTO, updateValue);
        updateValue.setModifiedBy(userId);
        updateValue.setModifiedTime(new Timestamp(System.currentTimeMillis()));
        checkSupplierExits(updateValue, request.getSupplierId());
        return modelMapper.map(productRepository.saveAndFlush(updateValue), ProductDTO.class);
    }

    @Override
    public List<ErrorDetail> deleteProduct(String userId, DeleteProductRequest request) {
        List<ProductDeleteDTO> productDeleteDTOS = productRepository.findAllById(request.getIds()).stream()
                .map(e -> modelMapper.map(e, ProductDeleteDTO.class))
                .toList();
        List<ErrorDetail> errorDetails = new ArrayList<>();
        for(Long requestId : request.getIds()){
            boolean isExit = productDeleteDTOS.stream().anyMatch(product -> product.getId().equals(requestId));
            if(!isExit){
                ErrorDetail errorDetail = new ErrorDetail();
                errorDetail.setId(requestId.toString());
                errorDetail.setMessage(Constants.ErrorMessageProductValidation.NOT_FIND_PRODUCT_BY_ID + requestId);
                errorDetails.add(errorDetail);
            }
        }
        if(errorDetails.isEmpty()){
            productRepository.deleteAllById(request.getIds());
            return null;
        }
        return errorDetails;
    }

    @Override
    public ProductDTO getProductById(Long id, String userId) {
        Optional<Product> product = productRepository.findById(id);
        if(!product.isPresent()){
            throw new DataNotFoundException(Constants.ErrorMessageProductValidation.NOT_FIND_PRODUCT_BY_ID + id);
        }
        return modelMapper.map(product.get(), ProductDTO.class);
    }


    @Override
    public Page<Product> getProductByParam(CustomProductQuery.ProductFilterParam param, PageRequest pageRequest) {
        Specification<Product> specification = CustomProductQuery.getFilterProduct(param);
        return productRepository.findAll(specification, pageRequest);
    }


    private Product checkSupplierExits(Product product, Long supplierId) {
        Optional<Supplier> supplierOptional = supplierRepository.findById(supplierId);
        if (supplierOptional.isPresent()) {
            product.setSupplier(supplierOptional.get());
            return productRepository.saveAndFlush(product);
        } else {
//            ErrorDetail errorDetail = new ErrorDetail();
//            errorDetail.setMessage(Constants.ErrorMessageSupplierValidation.NOT_FIND_SUPPLIER_BY_ID + supplierId);
            throw new DataNotFoundException(Constants.ErrorMessageSupplierValidation.NOT_FIND_SUPPLIER_BY_ID + supplierId);
        }
    }
}
