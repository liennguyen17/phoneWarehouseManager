package com.ltw.phonewarehousemanager.service.stockInDetail;

import com.ltw.phonewarehousemanager.constant.Constants;
import com.ltw.phonewarehousemanager.dto.entity.StockInDetailDTO;
import com.ltw.phonewarehousemanager.dto.model.Product;
import com.ltw.phonewarehousemanager.dto.model.StockInDetail;
import com.ltw.phonewarehousemanager.dto.request.StockInDetail.CreateStockInDetailRequest;
import com.ltw.phonewarehousemanager.dto.request.StockInDetail.GetStockInDetailListRequest;
import com.ltw.phonewarehousemanager.dto.request.StockInDetail.UpdateStockInDetailRequest;
import com.ltw.phonewarehousemanager.exception.DataNotFoundException;
import com.ltw.phonewarehousemanager.repository.stockInDetail.StockInDetailRepository;
import com.ltw.phonewarehousemanager.repository.product.ProductRepository;
import com.ltw.phonewarehousemanager.utils.DateUtils;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.mapstruct.factory.Mappers;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.text.ParseException;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class StockInDetailService {
    private final StockInDetailRepository stockInDetailRepository;
    private final StockInDetailUpdateMapper stockInDetailUpdateMapper;
    private final ModelMapper modelMapper;
    private final ProductRepository productRepository;

    private final StockDetailMapper stockDetailMapper;

    @Transactional
    public StockInDetailDTO createStockInDetail(String userId, CreateStockInDetailRequest request) throws ParseException {
//        try{
//            StockInDetail stockInDetail = modelMapper.map(request, StockInDetail.class);
////            stockInDetail.setStockInDate((Date) DateUtils.convertDateFromString(request.getStockInDate(), Constants.DateTimeFormatConstant.DATE_FORMAT));
//            stockInDetail.setCreatedBy(userId);
//            stockInDetail.setCreatedTime(new Timestamp(System.currentTimeMillis()));
//            checkProduct(stockInDetail, request.getProduct());
//            return modelMapper.map(stockInDetailRepository.saveAndFlush(stockInDetail), StockInDetailDTO.class);
//        }catch (DataIntegrityViolationException e){
//            throw new BaseValidateException(Constants.ErrorMessageStockInDetailValidate.ERROR_CREAT);
//        }


        Product product = productRepository.findById(request.getProduct()).orElseThrow(()->new DataNotFoundException(Constants.ErrorMessageProductValidation.NOT_FIND_PRODUCT_BY_ID + request.getProduct()));
        StockInDetail stockInDetail = new StockInDetail();
        stockDetailMapper.fromRequest(request, stockInDetail);
        //        StockInDetail stockInDetail = StockInDetail.builder()
//                .price(request.getPrice())
//                .quantityIn(request.getQuantityIn())
//                .stockInDate(DateUtils.convertDateFromString(request.getStockInDate(), Constants.DateTimeFormatConstant.DATE_FORMAT))
//                .createdBy(userId)
//                .createdTime(new Timestamp(System.currentTimeMillis()))
//                .product(product)
//
//                .build();
//        checkProduct(stockInDetail, request.getProduct());
        return modelMapper.map(stockInDetailRepository.saveAndFlush(stockInDetail), StockInDetailDTO.class);

    }

    @Transactional
    public StockInDetailDTO updateStockInDetail(String userId, UpdateStockInDetailRequest request) throws ParseException {
        try {
            Optional<StockInDetail> stockInDetail = stockInDetailRepository.findById(request.getId());
            if (!stockInDetail.isPresent()) {
                throw new DataNotFoundException(Constants.ErrorMessageStockInDetailValidate.NOT_FIND_STOCK_IN_DATE_BY_ID + request.getId());
            }
//        StockInDetailUpdateDTO updateDTO = modelMapper.map(request, StockInDetailUpdateDTO.class);
//        StockInDetail updateValue = stockInDetail.get();
//        stockInDetailUpdateMapper.updateStockInDetailFromDto(updateDTO, updateValue);
//        updateValue.setModifiedBy(userId);
//        updateValue.setModifiedTime(new Timestamp(System.currentTimeMillis()));
//        checkProduct(updateValue, request.getProduct());

//        return modelMapper.map(stockInDetailRepository.saveAndFlush(updateValue),StockInDetailDTO.class);
            if (stockInDetail.isPresent()) {
                StockInDetail stock = stockInDetail.get();
                stock.setStockInDate(DateUtils.convertDateFromString(request.getStockInDate(), Constants.DateTimeFormatConstant.DATE_FORMAT));
                stock.setPrice(request.getPrice());
                stock.setQuantityIn(request.getQuantityIn());
                stock.setModifiedBy(userId);
                stock.setModifiedTime(new Timestamp(System.currentTimeMillis()));
                if (request.getProduct() != null) {
                    Product product = productRepository.findById(request.getProduct())
                            .orElseThrow(() -> new DataNotFoundException(Constants.ErrorMessageProductValidation.NOT_FIND_PRODUCT_BY_ID + request.getProduct()));
                    stock.setProduct(product);
                }
                return modelMapper.map(stockInDetailRepository.saveAndFlush(stock), StockInDetailDTO.class);
            }
        } catch (Exception e) {
            throw new DataNotFoundException(Constants.ErrorMessageStockInDetailValidate.ERROR_UPDATE);
        }
        throw new DataNotFoundException(Constants.ErrorMessageStockInDetailValidate.ERROR_UPDATE);
    }

    public StockInDetail updateQuantityIn(Long quantityIn, Long stockInDetailId){
        Optional<StockInDetail> optionalStockInDetail = stockInDetailRepository.findById(stockInDetailId);
        if(optionalStockInDetail.isPresent()){
            StockInDetail stockInDetail = optionalStockInDetail.get();
            stockInDetail.setQuantityIn(quantityIn);
            stockInDetailRepository.saveAndFlush(stockInDetail);
            return stockInDetail;
        }else {
            throw new DataNotFoundException(Constants.ErrorMessageStockInDetailValidate.STOCK_ERROR + stockInDetailId);
        }

    }

    public StockInDetailDTO getStockInDetailById(Long id){
        Optional<StockInDetail> stockInDetail = stockInDetailRepository.findById(id);
        if(!stockInDetail.isPresent()){
            throw new DataNotFoundException(Constants.ErrorMessageStockInDetailValidate.NOT_FIND_STOCK_IN_DATE_BY_ID + id);
        }
        return modelMapper.map(stockInDetail.get(), StockInDetailDTO.class);
    }

    public Page<StockInDetail> getListStock(String userId, GetStockInDetailListRequest request){
        if(request.getGetAll()){
            request.setLimit((int) stockInDetailRepository.count());
        }
        return stockInDetailRepository.findAll(PageRequest.of(request.getStart(), request.getLimit()));
    }

    private StockInDetail checkProduct(StockInDetail stockInDetail, Long productId){
        Optional<Product> product = productRepository.findById(productId);
        if(product.isPresent()){
            stockInDetail.setProduct(product.get());
            return stockInDetailRepository.saveAndFlush(stockInDetail);
        }
        else {
            throw new DataNotFoundException(Constants.ErrorMessageProductValidation.NOT_FIND_PRODUCT_BY_ID + productId);
        }
    }




}
