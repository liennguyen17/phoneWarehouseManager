package com.ltw.phonewarehousemanager.controller;

import com.ltw.phonewarehousemanager.constant.Constants;
import com.ltw.phonewarehousemanager.constant.ErrorCodeDefs;
import com.ltw.phonewarehousemanager.dto.entity.SupplierDTO;
import com.ltw.phonewarehousemanager.dto.model.Supplier;
import com.ltw.phonewarehousemanager.dto.request.supplier.CreateSupplierRequest;
import com.ltw.phonewarehousemanager.dto.request.supplier.DeleteSupplierRequest;
import com.ltw.phonewarehousemanager.dto.request.supplier.GetSupplierListRequest;
import com.ltw.phonewarehousemanager.dto.request.supplier.UpdateSupplierRequest;
import com.ltw.phonewarehousemanager.dto.response.BaseResponse;
import com.ltw.phonewarehousemanager.dto.response.ErrorDetail;
import com.ltw.phonewarehousemanager.service.supplier.SupplierService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/admin/v1/suppliers")
@RequiredArgsConstructor
public class SupplierController {
    private final SupplierService supplierService;
    private final ModelMapper modelMapper;

    @PostMapping
    public BaseResponse createSupplier(@RequestHeader(value = Constants.HeaderConstant.HEADER_USER_ID) String userId,
                                       @Valid @RequestBody CreateSupplierRequest request){
        return  BaseResponse.successData(supplierService.createSupplier(userId, request));
    }
    @PutMapping
    public BaseResponse updateSupplier(@RequestHeader(value = Constants.HeaderConstant.HEADER_USER_ID) String userId,
                                       @Valid @RequestBody UpdateSupplierRequest request){
        return BaseResponse.successData(supplierService.updateSupplier(userId, request));
    }

    @GetMapping("{id}")
    public BaseResponse getSupplierById(@RequestHeader(value = Constants.HeaderConstant.HEADER_USER_ID)String userId,
                                        @PathVariable("id") Long id){
        return BaseResponse.successData(supplierService.getSupplierById(id));
    }

    @PostMapping("/getList")
    public BaseResponse getListSupplier(@RequestHeader(value = Constants.HeaderConstant.HEADER_USER_ID)String userId,
                                        @RequestBody GetSupplierListRequest request){
        Page<Supplier> supplierPage = supplierService.getListSupplier(userId, request);
        return BaseResponse.successListData(supplierPage.getContent().stream()
                .map(e -> modelMapper.map(e, SupplierDTO.class))
                .toList(), (int) supplierPage.getTotalElements());
    }

    @DeleteMapping
    public BaseResponse deleteSupplier(@RequestHeader(value = Constants.HeaderConstant.HEADER_USER_ID)String userId,
                                       @Valid @RequestBody DeleteSupplierRequest request){
        List<ErrorDetail> errorDetails = supplierService.deleteSupplier(userId, request);
        if(errorDetails == null){
            return BaseResponse.successData("Xóa nhà cung cấp thành công!");
        }else {
            return BaseResponse.error(ErrorCodeDefs.ERR_VALIDATION,
                    ErrorCodeDefs.getMessage(ErrorCodeDefs.ERR_VALIDATION),
                    errorDetails);
        }
    }
}
