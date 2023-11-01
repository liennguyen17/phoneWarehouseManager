package com.ltw.phonewarehousemanager.service.product;

import com.ltw.phonewarehousemanager.dto.entity.ProductUpdateValueDTO;
import com.ltw.phonewarehousemanager.dto.model.Product;
import org.mapstruct.*;

@Mapper(componentModel = "spring")
public interface ProductUpdateMapper {
    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    @Mappings({
            @Mapping(target = "createdTime", ignore = true),
            @Mapping(target = "createdBy", ignore = true),
            @Mapping(target = "modifiedBy", ignore = true),
            @Mapping(target = "modifiedTime", ignore = true)
    })
    void updateProductFromDto(ProductUpdateValueDTO dto, @MappingTarget Product entity);
}
