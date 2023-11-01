//package com.ltw.phonewarehousemanager.service.product;
//
//import com.ltw.phonewarehousemanager.dto.entity.ProductUpdateValueDTO;
//import com.ltw.phonewarehousemanager.dto.model.Product;
//import org.springframework.stereotype.Component;
//
//@Component
//public class ProductUpdateMapperImpl implements ProductUpdateMapper{
//    @Override
//    public void updateProductFromDto(ProductUpdateValueDTO dto, Product entity) {
//        if(dto == null){
//            return;
//        }
//
//        entity.setId(dto.getId());
//        if(dto.getName() != null){
//            entity.setName(dto.getName());
//        }
//        if(dto.getDescription() != null){
//            entity.setDescription(dto.getDescription());
//        }
//    }
//}
