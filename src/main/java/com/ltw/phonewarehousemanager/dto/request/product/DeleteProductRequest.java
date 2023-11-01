package com.ltw.phonewarehousemanager.dto.request.product;

import com.ltw.phonewarehousemanager.constant.Constants;
import lombok.Data;
import org.hibernate.validator.constraints.NotEmpty;

import java.util.List;
@Data
public class DeleteProductRequest {
    @NotEmpty(message = Constants.ErrorMessageProductValidation.DELETE_NOT_EMPTY)
    private List<Long> ids;
}
