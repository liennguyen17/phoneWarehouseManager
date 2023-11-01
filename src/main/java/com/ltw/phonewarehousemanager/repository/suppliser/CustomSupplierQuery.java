package com.ltw.phonewarehousemanager.repository.suppliser;

import com.ltw.phonewarehousemanager.dto.model.Supplier;
import com.ltw.phonewarehousemanager.utils.CriteriaBuilderUtils;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.apache.logging.log4j.util.Strings;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import jakarta.persistence.criteria.Predicate;

public class CustomSupplierQuery {
    private CustomSupplierQuery() {
    }

    @Data
    @NoArgsConstructor
    public static class SupplierFilterParam {
        private String keywords;
        private Set<Long> products;
        private List<String> createdBy;
        private String sortField;
    }

    public static Specification<Supplier> getFilterSupplier(SupplierFilterParam param) {
        return ((root, query, criteriaBuilder) -> {
            List<Predicate> predicates = new ArrayList<>();
            if (!Strings.isEmpty(param.getKeywords())) {
                predicates.add(CriteriaBuilderUtils.createPredicateForSearchInsensitve(root, criteriaBuilder, param.getKeywords(), "name", "address", "phone"));
            }
            if (!CollectionUtils.isEmpty(param.createdBy)) {
                predicates.add(root.get("createdBy").in(param.createdBy));
            }

//            if(param.sortField != null && !param.sortField.equals("")){
//                if(param.sortField.equals(Constants.SortType.DESC)){
//                    query.orderBy(criteriaBuilder.desc(root.get(param.sortField)));
//                }
//                if(param.)
//            }
            return criteriaBuilder.and(predicates.toArray(new jakarta.persistence.criteria.Predicate[0]));
        });
    }
}
