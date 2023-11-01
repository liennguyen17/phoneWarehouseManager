package com.ltw.phonewarehousemanager.dto.request.product;

import com.ltw.phonewarehousemanager.constant.Constants;
import com.ltw.phonewarehousemanager.repository.product.CustomProductQuery;
import jakarta.validation.constraints.Min;
import lombok.Data;
import org.hibernate.validator.constraints.Range;

@Data
public class GetProductRequest extends CustomProductQuery.ProductFilterParam {
    @Min(value = 0, message = Constants.ErrorMessageProductValidation.START_SIZE)
    private Integer start = 0;
    @Range(min = 5, max = 50, message = Constants.ErrorMessageProductValidation.LIMIT_SIZE)
    private Integer limit = 10;
    private Boolean getAll = false;
}
