package com.ltw.phonewarehousemanager.service.stockOutDetail;

import com.ltw.phonewarehousemanager.constant.Constants;
import com.ltw.phonewarehousemanager.dto.entity.StockOutDetailDTO;
import com.ltw.phonewarehousemanager.dto.model.Product;
import com.ltw.phonewarehousemanager.dto.model.StockOutDetail;
import com.ltw.phonewarehousemanager.dto.request.StockOutDetail.CreateStockOutDetailRequest;
import com.ltw.phonewarehousemanager.dto.request.StockOutDetail.GetStockOutDetailListRequest;
import com.ltw.phonewarehousemanager.dto.request.invoiceDetails.CreateInvoiceDetailsRequest;
import com.ltw.phonewarehousemanager.exception.DataNotFoundException;
import com.ltw.phonewarehousemanager.repository.product.ProductRepository;
import com.ltw.phonewarehousemanager.repository.stockOutDetail.StockOutDetailRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.sql.Timestamp;

@Service
@RequiredArgsConstructor
public class StockOutDetailService {
    private final StockOutDetailRepository stockOutDetailRepository;
    private final ModelMapper modelMapper;
    private final ProductRepository productRepository;

    @Transactional
    public StockOutDetailDTO createStockOutDetail(String userId, CreateInvoiceDetailsRequest requestInvoiceDetails){
        Product product = productRepository.findById(requestInvoiceDetails.getProduct()).orElseThrow(()->new DataNotFoundException(Constants.ErrorMessageProductValidation.NOT_FIND_PRODUCT_BY_ID + requestInvoiceDetails.getProduct()));
        StockOutDetail stockOutDetail = StockOutDetail.builder()
                .price(requestInvoiceDetails.getUnitPrice())
                .quantityOut(requestInvoiceDetails.getQuantity())
                .stockOutDate(new Timestamp(System.currentTimeMillis()))
                .createdBy(userId)
                .product(product)
                .build();

        return modelMapper.map(stockOutDetailRepository.saveAndFlush(stockOutDetail),StockOutDetailDTO.class);
    }

    public Page<StockOutDetail> getListStock(String userId, GetStockOutDetailListRequest request){
        if(request.getGetAll()){
            request.setLimit((int) stockOutDetailRepository.count());
        }
        return stockOutDetailRepository.findAll(PageRequest.of(request.getStart(), request.getLimit()));
    }
}
