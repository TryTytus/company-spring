package org.my.companyspring.entity.order;

import org.my.companyspring.entity.customer.Customer;
import org.my.companyspring.entity.employee.Employee;
import org.my.companyspring.entity.product.Product;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.annotation.Nullable;
import jakarta.persistence.*;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.*;

import java.util.HashSet;
import java.util.Set;


@Entity // order is reserved word in SQL !!!
@Table(name = "_order_")
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor

public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;


    @NotNull
    @Min(value = 1)
    @Max(value = 20)
    private Integer quantity;

    @NotNull
    @Size(max = 20)
    private String status;

    @Nullable
    private String description;

    @Min(value = 1)
    @Max(value = 30)
    @Nullable
    private Integer discount;


    @ManyToOne()
    @JoinColumn(
            name = "customer_id",
            referencedColumnName = "id",
            foreignKey = @ForeignKey(name = "customer_order")
    )
    @JsonIgnore
    private Customer customer;

    @ManyToOne()
    @JoinColumn(
            name = "product_id",
            referencedColumnName = "id"
    )
    @JsonIgnore
    private Product product;


    @ManyToMany(mappedBy = "orders")
    @JsonIgnore
    private Set<Employee> employees = new HashSet<>();

}
