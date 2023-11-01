package com.ltw.phonewarehousemanager.controller;

import com.ltw.phonewarehousemanager.constant.Constants;
import com.ltw.phonewarehousemanager.dto.entity.InvoiceDetailDTO;
import com.ltw.phonewarehousemanager.dto.model.InvoiceDetail;
import com.ltw.phonewarehousemanager.dto.request.invoiceDetails.CreateInvoiceDetailsRequest;
import com.ltw.phonewarehousemanager.dto.request.invoiceDetails.GetInvoiceDetailsListRequest;
import com.ltw.phonewarehousemanager.dto.response.BaseItemResponse;
import com.ltw.phonewarehousemanager.dto.response.BaseListResponse;
import com.ltw.phonewarehousemanager.dto.response.BaseResponse;
import com.ltw.phonewarehousemanager.service.invoiceDetail.InvoiceDetailService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/admin/v1/invoice-detail")
@RequiredArgsConstructor
public class InvoiceDetailController {
    private final InvoiceDetailService invoiceDetailService;
    private final ModelMapper modelMapper;

    @PostMapping
    public BaseItemResponse<InvoiceDetailDTO> createInvoiceDetail(@RequestHeader(value = Constants.HeaderConstant.HEADER_USER_ID) String userId,
                                                                  @Valid @RequestBody CreateInvoiceDetailsRequest request){
        return BaseResponse.successData(invoiceDetailService.createInvoiceDetail(userId, request));
    }

    @PostMapping("/getList")
    public BaseListResponse<InvoiceDetailDTO> getListInvoiceDetail(@RequestHeader(value = Constants.HeaderConstant.HEADER_USER_ID)String userId,
                                                                   @Valid @RequestBody GetInvoiceDetailsListRequest request){
        Page<InvoiceDetail> invoiceDetailPage = invoiceDetailService.getListInvoiceDetail(userId, request);
        return BaseResponse.successListData(invoiceDetailPage.getContent().stream()
                .map(e -> modelMapper.map(e, InvoiceDetailDTO.class))
                .toList(), (int) invoiceDetailPage.getTotalElements());
    }
}
