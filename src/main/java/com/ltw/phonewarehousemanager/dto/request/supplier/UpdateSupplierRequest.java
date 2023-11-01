package com.ltw.phonewarehousemanager.dto.request.supplier;

import com.ltw.phonewarehousemanager.constant.Constants;
import com.ltw.phonewarehousemanager.domain.validator.phone.PhoneAnnotation;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;
import org.aspectj.bridge.IMessage;

import java.util.Set;
@Data
public class UpdateSupplierRequest {
    @NotNull
    private Long id;
    @Size(max = 200, message = Constants.ErrorMessageSupplierValidation.NAME_SIZE)
    @Size(min = 1, message = Constants.ErrorMessageSupplierValidation.NAME_NOT_BLANK)
    private String name;
    @Size(max = 100, message = Constants.ErrorMessageSupplierValidation.ADDRESS_SIZE)
    @NotBlank(message = Constants.ErrorMessageSupplierValidation.ADDRESS_NOT_BLANK)
    private String address;
    @PhoneAnnotation
    private String phone;
//    private Set<Long> product;
}
