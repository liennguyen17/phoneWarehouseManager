package com.ltw.phonewarehousemanager.controller;

import com.ltw.phonewarehousemanager.constant.Constants;
import com.ltw.phonewarehousemanager.dto.entity.StockInDetailDTO;
import com.ltw.phonewarehousemanager.dto.model.StockInDetail;
import com.ltw.phonewarehousemanager.dto.request.StockInDetail.CreateStockInDetailRequest;
import com.ltw.phonewarehousemanager.dto.request.StockInDetail.GetStockInDetailListRequest;
import com.ltw.phonewarehousemanager.dto.request.StockInDetail.UpdateStockInDetailRequest;
import com.ltw.phonewarehousemanager.dto.response.BaseItemResponse;
import com.ltw.phonewarehousemanager.dto.response.BaseResponse;
import com.ltw.phonewarehousemanager.service.stockInDetail.StockInDetailService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/admin/v1/stocks-in")
@RequiredArgsConstructor
public class StockInDetailController {
    private final StockInDetailService stockInDetailService;
    private final ModelMapper modelMapper;

    @PostMapping
    public BaseItemResponse<StockInDetailDTO> createStockInDetail(@RequestHeader(value = Constants.HeaderConstant.HEADER_USER_ID) String userId,
                                                                  @Valid @RequestBody CreateStockInDetailRequest request) throws ParseException {
        return BaseResponse.successData(stockInDetailService.createStockInDetail(userId, request));
    }

    @PutMapping
    public BaseItemResponse<StockInDetailDTO> updateStockInDetail(@RequestHeader(value = Constants.HeaderConstant.HEADER_USER_ID) String userId,
                                                                  @Valid @RequestBody UpdateStockInDetailRequest request) throws ParseException {
        return BaseResponse.successData(stockInDetailService.updateStockInDetail(userId, request));
    }

    @GetMapping("{id}")
    public BaseResponse getStockInDetailById(@RequestHeader(value = Constants.HeaderConstant.HEADER_USER_ID) String userId,
                                             @PathVariable("id") Long id) {
        return BaseResponse.successData(stockInDetailService.getStockInDetailById(id));
    }

    @PostMapping("/getList")
    public BaseResponse getListStockInDetail(@RequestHeader (value = Constants.HeaderConstant.HEADER_USER_ID)String userId,
                                             @RequestBody GetStockInDetailListRequest request){
        Page<StockInDetail> stockInDetailPage = stockInDetailService.getListStock(userId, request);
        return BaseResponse.successListData(stockInDetailPage.getContent().stream()
                .map(e -> modelMapper.map(e, StockInDetailDTO.class))
                .toList(), (int) stockInDetailPage.getTotalElements());
    }
}
