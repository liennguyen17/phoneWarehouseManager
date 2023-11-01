//package com.ltw.phonewarehousemanager.service.stockInDetail;
//
//import com.ltw.phonewarehousemanager.dto.entity.StockInDetailUpdateDTO;
//import com.ltw.phonewarehousemanager.dto.model.StockInDetail;
//import org.springframework.stereotype.Component;
//
//@Component
//public class StockInDetailUpdateMapperImpl implements StockInDetailUpdateMapper{
//    @Override
//    public void updateStockInDetailFromDto(StockInDetailUpdateDTO dto, StockInDetail entity) {
//        if(dto == null){
//            return;
//        }
//
//        entity.setId(dto.getId());
//        if(dto.getQuantityIn() != null){
//            entity.setQuantityIn(dto.getQuantityIn());
//
//        }
//        if(dto.getPrice() != null){
//            entity.setPrice(dto.getPrice());
//        }
//    }
//}
