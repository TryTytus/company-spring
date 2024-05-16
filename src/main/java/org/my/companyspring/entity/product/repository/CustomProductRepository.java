package org.my.companyspring.entity.product.repository;


import jakarta.persistence.EntityManager;
import jakarta.persistence.criteria.*;
import lombok.RequiredArgsConstructor;
import org.my.companyspring.entity.customer.Customer;
import org.my.companyspring.entity.product.Product;
import org.my.companyspring.entity.product.records.HighPricedPrdPerCustDTO;
import org.my.companyspring.entity.product.records.PrdOrdStatsDTO;
import org.my.companyspring.entity.product.records.TopPrdByOrdNumDTO;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class CustomProductRepository {

    private final EntityManager em;
    public List<HighPricedPrdPerCustDTO> getHighestPricedProductPerCustomer() {

        CriteriaBuilder cb = em.getCriteriaBuilder();
        var query = cb.createQuery(HighPricedPrdPerCustDTO.class);
        Root<Customer> customer = query.from(Customer.class);
        Join<Customer, Order> order = customer.join("orders");
        Join<Order, Product> product = order.join("product");

        Subquery<Integer> subquery = query.subquery(Integer.class);
        Root<Order> subOrder = subquery.from(Order.class);
        Join<Order, Product> subProduct = subOrder.join("product");

        subquery.select(cb.max(subProduct.get("price")))
                .where(cb.equal(subOrder.get("customer"), customer));

        query.multiselect(
                customer.get("id"),
                customer.get("firstName"),
                customer.get("lastName"),
                product.get("id"),
                product.get("name"),
                product.get("price")
        ).where(cb.equal(product.get("price"), subquery));

        return em.createQuery(query).getResultList();
    }

    public List<PrdOrdStatsDTO> getProductOrderStatistics() {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        var query = cb.createQuery(PrdOrdStatsDTO.class);
        Root<Product> product = query.from(Product.class);
        Join<Product, Order> order = product.join("orders");

        query.multiselect(
                product.get("name"),
                cb.avg(order.get("quantity")),
                cb.count(order.get("id"))
        ).groupBy(
                product.get("name")
        );

        return em.createQuery(query).getResultList();
    }

    public List<TopPrdByOrdNumDTO> getTopProductsByOrderQuantity() {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        var query = cb.createQuery(TopPrdByOrdNumDTO.class);
        Root<Product> product = query.from(Product.class);
        Join<Product, Order> order = product.join("orders");

        query.multiselect(
                product.get("id"),
                product.get("name"),
                cb.sum(order.get("quantity"))
        ).groupBy(
                product.get("id"),
                product.get("name")
        ).orderBy(
                cb.desc(cb.sum(order.get("quantity")))
        );

        return em.createQuery(query)
                .setMaxResults(3)
                .getResultList();
    }

}
