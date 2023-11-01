package com.ltw.phonewarehousemanager.service.invoice;

import com.ltw.phonewarehousemanager.constant.Constants;
import com.ltw.phonewarehousemanager.dto.entity.InvoiceDTO;
import com.ltw.phonewarehousemanager.dto.model.Invoice;
import com.ltw.phonewarehousemanager.dto.model.InvoiceDetail;
import com.ltw.phonewarehousemanager.dto.request.invoiceDetails.CreateInvoiceDetailsRequest;
import com.ltw.phonewarehousemanager.dto.request.invoices.CreateInvoiceRequest;
import com.ltw.phonewarehousemanager.repository.invoice.InvoiceRepository;
import com.ltw.phonewarehousemanager.service.invoiceDetail.InvoiceDetailService;
import com.ltw.phonewarehousemanager.utils.DateUtils;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
@RequiredArgsConstructor
public class InvoiceService {
    private final InvoiceRepository invoiceRepository;
    private final InvoiceDetailService invoiceDetailService;
    private final ModelMapper modelMapper;

    @Transactional
    public InvoiceDTO createInvoice(String userId, CreateInvoiceRequest request) throws ParseException {
        //nào viết xong bảng user thì kiểm tra và truyền user vào

        List<InvoiceDetail> invoiceDetailList = new ArrayList<>();
        for(CreateInvoiceDetailsRequest request1 : request.getInvoiceDetails()){
            InvoiceDetail invoiceDetail = invoiceDetailService.createDetailInvoice(userId, request1);
            invoiceDetailList.add(invoiceDetail);
        }

        BigDecimal totalAmount = BigDecimal.ZERO;
        for(InvoiceDetail invoiceDetail : invoiceDetailList){
            totalAmount = totalAmount.add(invoiceDetail.getTotalAmount());
        }

        Invoice invoice = Invoice.builder()
                .invoiceDetails(new HashSet<>(invoiceDetailList) )
                .totalAmount(totalAmount)
                .createdBy(userId)
                .createdTime(new Timestamp(System.currentTimeMillis()))
                .build();

        return modelMapper.map(invoiceRepository.saveAndFlush(invoice), InvoiceDTO.class);
    }
}
