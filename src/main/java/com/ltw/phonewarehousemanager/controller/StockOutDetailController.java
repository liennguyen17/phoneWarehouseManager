package com.ltw.phonewarehousemanager.controller;

import com.ltw.phonewarehousemanager.constant.Constants;
import com.ltw.phonewarehousemanager.dto.entity.StockOutDetailDTO;
import com.ltw.phonewarehousemanager.dto.model.StockOutDetail;
import com.ltw.phonewarehousemanager.dto.request.StockOutDetail.GetStockOutDetailListRequest;
import com.ltw.phonewarehousemanager.dto.response.BaseResponse;
import com.ltw.phonewarehousemanager.repository.stockOutDetail.StockOutDetailRepository;
import com.ltw.phonewarehousemanager.service.stockOutDetail.StockOutDetailService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/admin/v1/stocks-out")
@RequiredArgsConstructor
public class StockOutDetailController {
    private final StockOutDetailService stockOutDetailService;
    private final ModelMapper modelMapper;

    @PostMapping("/getList")
    public BaseResponse getListStockOutDetail(@RequestHeader (value = Constants.HeaderConstant.HEADER_USER_ID) String userId,
                                              @RequestBody GetStockOutDetailListRequest request){
        Page<StockOutDetail> stockOutDetailPage = stockOutDetailService.getListStock(userId,request);
        return BaseResponse.successListData(stockOutDetailPage.getContent().stream()
                .map(e -> modelMapper.map(e, StockOutDetailDTO.class))
                .toList(), (int) stockOutDetailPage.getTotalElements());
    }
}
