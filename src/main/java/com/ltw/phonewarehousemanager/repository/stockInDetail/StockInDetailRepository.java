package com.ltw.phonewarehousemanager.repository.stockInDetail;

import com.ltw.phonewarehousemanager.dto.model.Product;
import com.ltw.phonewarehousemanager.dto.model.StockInDetail;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface StockInDetailRepository extends JpaRepository<StockInDetail, Long>, JpaSpecificationExecutor<StockInDetail> {
    Optional<StockInDetail> findById(Long id);
    StockInDetail findByProduct(Product product);
    Optional<StockInDetail> findByProduct(Optional<Product> product);
}
