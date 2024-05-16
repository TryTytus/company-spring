package org.my.companyspring.entity.product.records;

public record PrdOrdStatsDTO(
        String productName,
        Double averageQuantity,
        Long totalOrders
) {}
