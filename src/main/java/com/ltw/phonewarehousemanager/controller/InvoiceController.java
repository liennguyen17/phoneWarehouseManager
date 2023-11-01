package com.ltw.phonewarehousemanager.controller;

import com.ltw.phonewarehousemanager.constant.Constants;
import com.ltw.phonewarehousemanager.dto.entity.InvoiceDTO;
import com.ltw.phonewarehousemanager.dto.request.invoiceDetails.CreateInvoiceDetailsRequest;
import com.ltw.phonewarehousemanager.dto.request.invoices.CreateInvoiceRequest;
import com.ltw.phonewarehousemanager.dto.response.BaseItemResponse;
import com.ltw.phonewarehousemanager.dto.response.BaseResponse;
import com.ltw.phonewarehousemanager.service.invoice.InvoiceService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;

@RestController
@RequestMapping("/admin/v1/invoices")
@RequiredArgsConstructor
public class InvoiceController {
    private final InvoiceService invoiceService;

    @PostMapping
    public BaseItemResponse<InvoiceDTO> createInvoice(@RequestHeader(value = Constants.HeaderConstant.HEADER_USER_ID) String userId,
                                                      @Valid @RequestBody CreateInvoiceRequest request) throws ParseException {
        return BaseResponse.successData(invoiceService.createInvoice(userId, request));
    }
}
