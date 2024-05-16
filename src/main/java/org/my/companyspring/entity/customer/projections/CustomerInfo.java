package org.my.companyspring.entity.customer.projections;

import org.my.companyspring.entity.customer.Customer;
import org.springframework.data.rest.core.config.Projection;

import java.util.Set;

/**
 * Projection for {@link Customer}
 */
@Projection(name = "customer_with_orders", types = {Customer.class})
public interface CustomerInfo {
    Long getId();

    String getFirstName();

    String getLastName();

    String getEmail();

    String getPhone();

    Character getSex();

    String getCity();

    String getStreet();

    String getStreetNumber();

    String getPostCode();

    Set<OrderInfo> getOrders();
}