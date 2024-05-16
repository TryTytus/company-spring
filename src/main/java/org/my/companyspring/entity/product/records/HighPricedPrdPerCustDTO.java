package org.my.companyspring.entity.product.records;

public record HighPricedPrdPerCustDTO(
        Long customerId,
        String firstName,
        String lastName,
        Long productId,
        String productName,
        Integer productPrice
) {}
