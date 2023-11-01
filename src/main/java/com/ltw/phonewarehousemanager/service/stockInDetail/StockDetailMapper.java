package com.ltw.phonewarehousemanager.service.stockInDetail;

import com.ltw.phonewarehousemanager.constant.Constants;
import com.ltw.phonewarehousemanager.dto.entity.StockInDetailUpdateDTO;
import com.ltw.phonewarehousemanager.dto.model.StockInDetail;
import com.ltw.phonewarehousemanager.dto.request.StockInDetail.CreateStockInDetailRequest;
import com.ltw.phonewarehousemanager.utils.DateUtils;
import org.mapstruct.*;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

@Mapper(componentModel = "spring")
public interface StockDetailMapper {

    @Named("convertDateFromString")
    default Date convertDateFromString(String dateStr) throws ParseException {
        return DateUtils.convertDateFromString(dateStr, Constants.DateTimeFormatConstant.DATE_FORMAT);
    }
    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    @Mappings({
            @Mapping(target = "stockInDate", source = "stockInDate", qualifiedByName = "convertDateFromString"),
            @Mapping(target = "product", ignore = true),
            @Mapping(target = "warehouse", ignore = true)
    })
    void fromRequest(CreateStockInDetailRequest request, @MappingTarget StockInDetail entity);
}
