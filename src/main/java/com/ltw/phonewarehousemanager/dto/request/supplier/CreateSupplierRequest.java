package com.ltw.phonewarehousemanager.dto.request.supplier;

import com.ltw.phonewarehousemanager.constant.Constants;
import com.ltw.phonewarehousemanager.domain.validator.phone.PhoneAnnotation;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

import java.util.Set;

@Data
public class CreateSupplierRequest {
    @Size(max = 200, message = Constants.ErrorMessageSupplierValidation.NAME_SIZE)
    @NotBlank(message = Constants.ErrorMessageSupplierValidation.NAME_NOT_BLANK)
    private String name;
    @Size(max = 200, message = Constants.ErrorMessageSupplierValidation.ADDRESS_SIZE)
    @NotBlank(message = Constants.ErrorMessageSupplierValidation.ADDRESS_NOT_BLANK)
    private String address;
    @PhoneAnnotation
    private String phone;
//    private Set<Long> products;
}
