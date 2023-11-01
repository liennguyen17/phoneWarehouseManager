package com.ltw.phonewarehousemanager.service.supplier;

import com.ltw.phonewarehousemanager.constant.Constants;
import com.ltw.phonewarehousemanager.dto.entity.SupplierDTO;
import com.ltw.phonewarehousemanager.dto.model.Supplier;
import com.ltw.phonewarehousemanager.dto.request.supplier.CreateSupplierRequest;
import com.ltw.phonewarehousemanager.dto.request.supplier.DeleteSupplierRequest;
import com.ltw.phonewarehousemanager.dto.request.supplier.GetSupplierListRequest;
import com.ltw.phonewarehousemanager.dto.request.supplier.UpdateSupplierRequest;
import com.ltw.phonewarehousemanager.dto.response.ErrorDetail;
import com.ltw.phonewarehousemanager.exception.DataNotFoundException;
import com.ltw.phonewarehousemanager.repository.product.ProductRepository;
import com.ltw.phonewarehousemanager.repository.suppliser.CustomSupplierQuery;
import com.ltw.phonewarehousemanager.repository.suppliser.SupplierRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service("SupplierService")
@RequiredArgsConstructor
@Slf4j
public class SupplierServiceImpl implements SupplierService{
    private final ProductRepository productRepository;
    private final SupplierRepository supplierRepository;
    private final SupplierUpdateMapper supplierUpdateMapper;
    private final ModelMapper modelMapper;

    @Override
    @Transactional
    public SupplierDTO createSupplier(String userId, CreateSupplierRequest request) {
            log.info("createSupplier - userId: {}, request: {}", userId, request);
            Supplier supplier = modelMapper.map(request, Supplier.class);
//            supplier.setName(request.getName());
//            supplier.setPhone(request.getPhone());
//            supplier.setAddress(request.getAddress());
            supplier.setCreatedBy(userId);
            supplier.setCreatedTime(new Timestamp(System.currentTimeMillis()));
            return modelMapper.map(supplierRepository.saveAndFlush(supplier), SupplierDTO.class);
    }

    @Override
    @Transactional
    public SupplierDTO updateSupplier(String userId, UpdateSupplierRequest request) {
        log.info("createSupplier - userId: {}, request: {}", userId, request);
        Optional<Supplier> supplier = supplierRepository.findById(request.getId());
        if(supplier.isPresent()){
            Supplier updateValue = supplier.get();
            supplierUpdateMapper.updateSupplierFromDto(request, updateValue);
            updateValue.setModifiedBy(userId);
            updateValue.setModifiedTime(new Timestamp(System.currentTimeMillis()));
            return modelMapper.map(supplierRepository.saveAndFlush(updateValue), SupplierDTO.class);
        }else {
            throw new DataNotFoundException(Constants.ErrorMessageSupplierValidation.NOT_FIND_SUPPLIER_BY_ID);
        }
    }

    @Override
    public List<ErrorDetail> deleteSupplier(String userId, DeleteSupplierRequest request) {
        log.info("DeleteCategory - userId: {}, request: {}", userId, request);
        List<ErrorDetail> errorDetails = new ArrayList<>();
        List<Supplier> supplierList = supplierRepository.findAllById(request.getIds());
        for(Long requestId : request.getIds()){
            boolean isExist = supplierList.stream().anyMatch(supplier -> supplier.getId().equals(requestId));
            if(!isExist){
                ErrorDetail errorDetail = new ErrorDetail();
                errorDetail.setId(requestId.toString());
                errorDetail.setMessage(Constants.ErrorMessageSupplierValidation.NOT_FIND_SUPPLIER_BY_ID + requestId);
                errorDetails.add(errorDetail);
            }
        }
        if(errorDetails.isEmpty()){
            productRepository.deleteAllBySupplierIn(supplierList);
            supplierRepository.deleteAllById(request.getIds());
            //nao viet san pham roi bo sung them viec xoa nha san cung cap tu san pham
            return null;
        }
        return errorDetails;
    }

    @Override
    public SupplierDTO getSupplierById(Long id) {
        Optional<Supplier> supplier = supplierRepository.findById(id);
        if(!supplier.isPresent()){
            throw new DataNotFoundException(Constants.ErrorMessageSupplierValidation.NOT_FIND_SUPPLIER_BY_ID + id);
        }
        return modelMapper.map(supplier.get(), SupplierDTO.class);
    }

    @Override
    public Page<Supplier> getListSupplier(String userId, GetSupplierListRequest request) {
        if(request.getGetAll()){
            request.setLimit((int) supplierRepository.count());
        }
        return supplierRepository.findAll(PageRequest.of(request.getStart(), request.getLimit()));
    }


}
