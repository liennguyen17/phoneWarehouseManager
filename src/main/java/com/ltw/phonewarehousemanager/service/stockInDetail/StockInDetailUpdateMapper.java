package com.ltw.phonewarehousemanager.service.stockInDetail;

import com.ltw.phonewarehousemanager.dto.entity.StockInDetailUpdateDTO;
import com.ltw.phonewarehousemanager.dto.model.StockInDetail;
import org.mapstruct.*;

@Mapper(componentModel = "spring")
public interface StockInDetailUpdateMapper {
    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    @Mappings({
            @Mapping(target = "createdTime", ignore = true),
            @Mapping(target = "createdBy", ignore = true),
            @Mapping(target = "modifiedBy", ignore = true),
            @Mapping(target = "modifiedTime", ignore = true)
    })
    void updateStockInDetailFromDto(StockInDetailUpdateDTO dto, @MappingTarget StockInDetail entity);
}
