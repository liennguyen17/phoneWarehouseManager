package com.ltw.phonewarehousemanager.repository.product;

import com.ltw.phonewarehousemanager.constant.Constants;
import com.ltw.phonewarehousemanager.dto.model.Product;
import com.ltw.phonewarehousemanager.dto.model.Supplier;
import com.ltw.phonewarehousemanager.utils.CriteriaBuilderUtils;
import jakarta.persistence.criteria.Join;
import jakarta.persistence.criteria.Predicate;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.apache.logging.log4j.util.Strings;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class CustomProductQuery {
    private CustomProductQuery(){}

    @Data
    @NoArgsConstructor
    public static class ProductFilterParam{
        private String keywords;
        private Long supplier;
        private List<String> createBy;
        private String sortField;
        private String sortType;
    }

    public static Specification<Product> getFilterProduct(ProductFilterParam param){
        return ((root, query, criteriaBuilder) -> {
            List<Predicate> predicates = new ArrayList<>();
            if(!Strings.isEmpty(param.getKeywords())){
                predicates.add(
                        CriteriaBuilderUtils.createPredicateForSearchInsensitve(root, criteriaBuilder, param.getKeywords(),
                                "name", "description")
                );
            }
//            if(param.getSupplier().describeConstable().isEmpty()){
//                Join<Product, Supplier> supplierJoin = root.joinList("supplier");
//                predicates.add(supplierJoin.get("id").in(param.getSupplier()));
//            }
            if(param.sortField != null && !param.sortField.equals("")){
                if(param.sortType.equals(Constants.SortType.DESC) || param.sortType.equals("")){
                    query.orderBy(criteriaBuilder.desc(root.get(param.sortField)));
                }
                if(param.sortType.equals(Constants.SortType.ASC)){
                    query.orderBy(criteriaBuilder.asc(root.get(param.sortField)));
                }
            }else {
                query.orderBy(criteriaBuilder.desc(root.get("id")));
            }
            return  criteriaBuilder.and(predicates.toArray(new Predicate[0]));
        });
    }
}
