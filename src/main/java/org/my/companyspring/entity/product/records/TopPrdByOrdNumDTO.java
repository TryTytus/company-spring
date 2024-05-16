package org.my.companyspring.entity.product.records;

public record TopPrdByOrdNumDTO(
        Long productId,
        String productName,
        Long totalQuantity
) {}