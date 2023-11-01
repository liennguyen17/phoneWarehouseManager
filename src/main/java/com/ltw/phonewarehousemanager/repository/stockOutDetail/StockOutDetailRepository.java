package com.ltw.phonewarehousemanager.repository.stockOutDetail;

import com.ltw.phonewarehousemanager.dto.model.StockOutDetail;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface StockOutDetailRepository extends JpaRepository<StockOutDetail, Long>, JpaSpecificationExecutor<StockOutDetail> {
    Optional<StockOutDetail> findById(Long id);
}
