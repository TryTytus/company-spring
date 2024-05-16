package org.my.companyspring.entity.employee.repository;

import jakarta.persistence.EntityManager;
import jakarta.persistence.criteria.*;

import lombok.RequiredArgsConstructor;
import org.my.companyspring.entity.employee.Employee;
import org.my.companyspring.entity.employee.records.EmpOrderNum;
import org.my.companyspring.entity.employee.records.EmpPrdNum;
import org.my.companyspring.entity.order.Order;

import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class CustomEmployeeRepository {

    private final EntityManager em;
     public List<EmpOrderNum> getEmployeeByOrderNum() {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<EmpOrderNum> cq = cb.createQuery(EmpOrderNum.class);
        Root<Employee> root = cq.from(Employee.class);

        cq.multiselect(
                root.get("id"),
                root.get("firstName"),
                cb.count(root.join("orders", JoinType.LEFT))
        );

        cq.groupBy(root.get("id"), root.get("firstName"));
        cq.orderBy(cb.asc(cb.count(root.join("orders", JoinType.LEFT))));


        return em.createQuery(cq).getResultList();
    }


    public List<EmpPrdNum> getEmployeeByProductsNum() {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<EmpPrdNum> cq = cb.createQuery(EmpPrdNum.class);

        Root<Employee> root = cq.from(Employee.class);
        Join<Employee, Order> join = root.join("orders", JoinType.LEFT);

        cq.multiselect(
                root.get("id"),
                root.get("firstName"),
                cb.sum(join.get("quantity"))
        );

        cq.groupBy(root.get("id"), root.get("firstName"));
        cq.orderBy(cb.desc(cb.sum(join.get("quantity"))));

        return em.createQuery(cq).getResultList();
    }

}


