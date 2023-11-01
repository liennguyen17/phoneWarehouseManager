//package com.ltw.phonewarehousemanager.service.supplier;
//
//import com.ltw.phonewarehousemanager.dto.model.Supplier;
//import com.ltw.phonewarehousemanager.dto.request.supplier.UpdateSupplierRequest;
//import org.springframework.stereotype.Component;
//
//@Component
//public class SupplierUpdateMapperImpl implements SupplierUpdateMapper{
//    @Override
//    public void updateSupplierFromDto(UpdateSupplierRequest dto, Supplier entity) {
//        if(dto == null){
//            return;
//        }
//        if(dto.getId() != null){
//            entity.setId(dto.getId());
//        }
//        if(dto.getName() != null){
//            entity.setName(dto.getName());
//        }
//        if(dto.getPhone() != null){
//            entity.setPhone(dto.getPhone());
//        }
//        if(dto.getAddress() != null){
//            entity.setAddress(dto.getAddress());
//        }
//    }
//}
