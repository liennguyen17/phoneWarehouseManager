package com.ltw.phonewarehousemanager.service.supplier;

import com.ltw.phonewarehousemanager.dto.entity.SupplierDTO;
import com.ltw.phonewarehousemanager.dto.model.Supplier;
import com.ltw.phonewarehousemanager.dto.request.supplier.CreateSupplierRequest;
import com.ltw.phonewarehousemanager.dto.request.supplier.DeleteSupplierRequest;
import com.ltw.phonewarehousemanager.dto.request.supplier.GetSupplierListRequest;
import com.ltw.phonewarehousemanager.dto.request.supplier.UpdateSupplierRequest;
import com.ltw.phonewarehousemanager.dto.response.ErrorDetail;
import org.springframework.data.domain.Page;
import java.util.List;

public interface SupplierService {
    SupplierDTO createSupplier(String userId, CreateSupplierRequest request);
    SupplierDTO updateSupplier(String userId, UpdateSupplierRequest request);
    List<ErrorDetail> deleteSupplier(String userId, DeleteSupplierRequest request);
    SupplierDTO getSupplierById(Long id);
    Page<Supplier> getListSupplier(String userId, GetSupplierListRequest request);
}
