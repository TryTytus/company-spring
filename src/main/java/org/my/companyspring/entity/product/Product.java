package org.my.companyspring.entity.product;

import org.my.companyspring.entity.order.Order;
import jakarta.annotation.Nullable;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "product")
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;
    @NotNull
    String name;
    @Nullable
    String color;
    @NotNull
    Integer price;
    @NotNull
    Integer count;


    @OneToMany(mappedBy = "product", fetch = FetchType.LAZY)
    private Set<Order> orders = new HashSet<>();

}
