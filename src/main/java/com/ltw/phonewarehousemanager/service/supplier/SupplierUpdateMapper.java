package com.ltw.phonewarehousemanager.service.supplier;
import com.ltw.phonewarehousemanager.dto.model.Supplier;
import com.ltw.phonewarehousemanager.dto.request.supplier.UpdateSupplierRequest;
import org.mapstruct.*;
@Mapper(componentModel = "spring")
public interface SupplierUpdateMapper {
    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    @Mappings({
            @Mapping(target = "createdTime", ignore = true),
            @Mapping(target = "createdBy", ignore = true),
            @Mapping(target = "modifiedBy", ignore = true),
            @Mapping(target = "modifiedTime", ignore = true)
    })
    void updateSupplierFromDto(UpdateSupplierRequest dto, @MappingTarget Supplier entity);
}
