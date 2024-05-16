package org.my.companyspring.entity.customer.projections;

import org.my.companyspring.entity.order.Order;
import org.springframework.data.rest.core.config.Projection;

/**
 * Projection for {@link org.my.companyspring.entity.order.Order}
 */

@Projection(name = "basic_order", types = {Order.class})
public interface OrderInfo {
    Long getId();

    Integer getQuantity();

    String getStatus();

    String getDescription();

    Integer getDiscount();
}