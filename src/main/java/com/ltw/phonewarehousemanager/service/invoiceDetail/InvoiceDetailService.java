package com.ltw.phonewarehousemanager.service.invoiceDetail;

import com.ltw.phonewarehousemanager.constant.Constants;
import com.ltw.phonewarehousemanager.dto.entity.InvoiceDetailDTO;
import com.ltw.phonewarehousemanager.dto.model.InvoiceDetail;
import com.ltw.phonewarehousemanager.dto.model.Product;
import com.ltw.phonewarehousemanager.dto.model.StockInDetail;
import com.ltw.phonewarehousemanager.dto.request.invoiceDetails.CreateInvoiceDetailsRequest;
import com.ltw.phonewarehousemanager.dto.request.invoiceDetails.GetInvoiceDetailsListRequest;
import com.ltw.phonewarehousemanager.exception.DataNotFoundException;
import com.ltw.phonewarehousemanager.repository.stockInDetail.StockInDetailRepository;
import com.ltw.phonewarehousemanager.repository.invoiceDetail.InvoiceDetailRepository;
import com.ltw.phonewarehousemanager.repository.product.ProductRepository;
import com.ltw.phonewarehousemanager.service.product.ProductService;
import com.ltw.phonewarehousemanager.service.stockInDetail.StockInDetailService;
import com.ltw.phonewarehousemanager.service.stockOutDetail.StockOutDetailService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class InvoiceDetailService {
    private final StockInDetailService stockInDetailService;
    private final StockInDetailRepository stockInDetailRepository;
    private final ProductService productService;
    private final ProductRepository productRepository;
    private final InvoiceDetailRepository invoiceDetailRepository;
    private final StockOutDetailService stockOutDetailService;
    private final ModelMapper modelMapper;
    public InvoiceDetailDTO createInvoiceDetail(String userId, CreateInvoiceDetailsRequest request){
        validateProductAndQuantity(request,userId);
        Product product = productRepository.findProductById(request.getProduct());
        BigDecimal totalAmount =  calculateTotalAmount(request.getUnitPrice(), request.getQuantity());

        InvoiceDetail invoiceDetail = modelMapper.map(request, InvoiceDetail.class);
        invoiceDetail.setTotalAmount(totalAmount);
        invoiceDetail.setCreatedBy(userId);
        invoiceDetail.setProduct(product);
        invoiceDetail.setCreatedTime(new Timestamp(System.currentTimeMillis()));

//        Product product = productRepository.findProductById(request.getProduct());
//        StockInDetail stockInDetail = stockInDetailRepository.findByProduct(product);
//        Long requestedQuantity = request.getQuantity();
//        Long availableQuantity = stockInDetail.getQuantityIn();
//        if(requestedQuantity > availableQuantity){
//            throw new RuntimeException("Số lượng sản phẩm không đủ số lượng tồn kho.");
//        }
//        Long quantityIn = availableQuantity - requestedQuantity;
//        stockInDetailService.updateQuantityIn(quantityIn, stockInDetail.getId());

        updateQuantityIn(request);

        stockOutDetailService.createStockOutDetail(userId, request);

        return modelMapper.map(invoiceDetailRepository.saveAndFlush(invoiceDetail), InvoiceDetailDTO.class);


    }

    public InvoiceDetail createDetailInvoice(String userId, CreateInvoiceDetailsRequest request){
        validateProductAndQuantity(request,userId);
        Product product = productRepository.findProductById(request.getProduct());
        BigDecimal totalAmount =  calculateTotalAmount(request.getUnitPrice(), request.getQuantity());

        InvoiceDetail invoiceDetail = modelMapper.map(request, InvoiceDetail.class);
        invoiceDetail.setTotalAmount(totalAmount);
        invoiceDetail.setCreatedBy(userId);
        invoiceDetail.setProduct(product);
        invoiceDetail.setCreatedTime(new Timestamp(System.currentTimeMillis()));
        updateQuantityIn(request);

        stockOutDetailService.createStockOutDetail(userId, request);
        return invoiceDetail;
    }

    public void updateQuantityIn(CreateInvoiceDetailsRequest request){
        Product product = productRepository.findProductById(request.getProduct());
        StockInDetail stockInDetail = stockInDetailRepository.findByProduct(product);
        Long requestedQuantity = request.getQuantity();
        Long availableQuantity = stockInDetail.getQuantityIn();
        if(requestedQuantity > availableQuantity){
            throw new DataNotFoundException("Sản phẩm (id: "+product.getId()+")" + product.getName() +" có: "+ Constants.ErrorMessageProductValidation.ERROR_QUANTITY + "(kho: " + availableQuantity + " <" + " số lượng mua: " + requestedQuantity+")");
        }
        Long quantityIn = availableQuantity - requestedQuantity;
        stockInDetailService.updateQuantityIn(quantityIn, stockInDetail.getId());
    }

    public Page<InvoiceDetail> getListInvoiceDetail(String userId, GetInvoiceDetailsListRequest request){
        if(request.getGetAll()){
            request.setLimit((int) invoiceDetailRepository.count());
        }
        return invoiceDetailRepository.findAll(PageRequest.of(request.getStart(), request.getLimit()));
    }


    private DataNotFoundException validateProductAndQuantity(CreateInvoiceDetailsRequest request, String userId){
        Optional<Product> product = productRepository.findById(request.getProduct());
        if(product == null){
            return new DataNotFoundException(Constants.ErrorMessageProductValidation.NOT_FIND_PRODUCT_BY_ID + request.getProduct());
        }
        Optional<StockInDetail> stockInDetail = stockInDetailRepository.findByProduct(product);
        if(!stockInDetail.isPresent() || stockInDetail.get().getQuantityIn() < request.getQuantity()){
            return new DataNotFoundException(Constants.ErrorMessageProductValidation.ERROR_QUANTITY);
        }
        return null;
    }

    private BigDecimal calculateTotalAmount(BigDecimal unitPrice, Long quantity){
        return unitPrice.multiply(new BigDecimal(quantity));
    }


}
