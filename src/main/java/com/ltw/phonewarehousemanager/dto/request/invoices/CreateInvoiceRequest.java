package com.ltw.phonewarehousemanager.dto.request.invoices;

import com.ltw.phonewarehousemanager.dto.request.invoiceDetails.CreateInvoiceDetailsRequest;
import lombok.Data;

import java.util.List;

@Data
public class CreateInvoiceRequest {
//    private String userId; //nào viết xong bảng user sẽ thêm
    private List<CreateInvoiceDetailsRequest> invoiceDetails;
}
