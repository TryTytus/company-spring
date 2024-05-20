package org.my.companyspring.entity.customer.records;

public record CustomerOrdStatsDTO(
        Long customerId,
        String firstName,
        String lastName,
        Long totalOrders,
        Integer totalQuantity,
        Double averageDiscount
) {}
