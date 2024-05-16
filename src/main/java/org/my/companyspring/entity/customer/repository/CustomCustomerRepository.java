package org.my.companyspring.entity.customer.repository;


import org.my.companyspring.entity.customer.Customer;
import org.my.companyspring.entity.customer.projections.CustomerInfo;
import org.my.companyspring.entity.customer.records.CustomerOrdStatsDTO;
import org.my.companyspring.entity.customer.records.SpentMostDTO;
import  org.my.companyspring.entity.order.Order;
import org.my.companyspring.entity.product.Product;
import jakarta.persistence.EntityManager;
import jakarta.persistence.criteria.*;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@AllArgsConstructor

public class CustomCustomerRepository {
    private EntityManager em;

    /**
     * Find customers who have placed orders with a quantity
     * greater than the average order quantity
     */

    public List<Customer> getGreaterThanAvg()
    {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Customer> query = cb.createQuery(Customer.class);


        Root<Customer> root = query.from(Customer.class);
        Join<Customer, Order> join = root.join("orders", JoinType.INNER);


        Subquery<Double> subQuery = query.subquery(Double.class);
        Root<Order> sbRoot = subQuery.from(Order.class);

        subQuery.select(cb.avg(sbRoot.get("quantity")));

        query.select(root);

        query.where(cb.greaterThan(join.get("quantity"), subQuery));
        query.orderBy(cb.asc(root.get("id")));

        return em.createQuery(query).getResultList();
    }

    public List<CustomerInfo> get10CustomersWithOrders(int skip)
    {
        return em.createQuery("SELECT c FROM Customer c JOIN FETCH c.orders",
                        CustomerInfo.class)
                .setFirstResult(skip)
                .setMaxResults(10).getResultList();
    }


    public List<SpentMostDTO> getWhoSpentTheMost() {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<SpentMostDTO> query = cb.createQuery(SpentMostDTO.class);

        Root<Customer> customerRoot = query.from(Customer.class);
        Join<Customer, Order> orderJoin = customerRoot.join("orders");
        Join<Order, Product> productJoin = orderJoin.join("product");

        Expression<Long> totalSpent = cb.sum(
                cb.prod(
                        orderJoin.get("quantity"),
                        productJoin.get("price")
                ));

        query.multiselect(
                customerRoot.get("firstName"),
                customerRoot.get("lastName"),
                totalSpent.alias("totalSpent")
        );

        query.groupBy(customerRoot.get("firstName"), customerRoot.get("lastName"));
        query.orderBy(cb.desc(totalSpent));

        return em.createQuery(query)
                .setMaxResults(10)
                .getResultList();
    }

    public List<CustomerOrdStatsDTO> getCustomerOrderStatistics() {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<CustomerOrdStatsDTO> query = cb.createQuery(CustomerOrdStatsDTO.class);
        Root<Customer> customer = query.from(Customer.class);
        Join<Customer, Order> order = customer.join("orders", JoinType.LEFT);

        query.multiselect(
                customer.get("id"),
                customer.get("firstName"),
                customer.get("lastName"),
                cb.count(order.get("id")),
                cb.sum(order.get("quantity")),
                cb.avg(order.get("discount"))
        ).groupBy(
                customer.get("id"),
                customer.get("firstName"),
                customer.get("lastName")
        );

        return em.createQuery(query).getResultList();
    }


}