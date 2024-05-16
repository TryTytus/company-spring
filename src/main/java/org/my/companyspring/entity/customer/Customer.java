package org.my.companyspring.entity.customer;


import org.my.companyspring.entity.order.Order;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "customer")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@NamedEntityGraph(
        name = "Customer.orders",
        attributeNodes = { @NamedAttributeNode("orders") }
)
public class Customer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    private String firstName;
    @NotNull
    private String lastName;
    @NotNull
    @Column(unique = true)
    private String email;
    @NotNull
    private String phone;
    @NotNull
    private Character sex;

    @NotNull
    private String city;
    @NotNull
    private String street;
    @NotNull
    private String streetNumber;
    @NotNull
    private String postCode;


    @OneToMany(mappedBy = "customer", cascade = CascadeType.ALL)
    @JsonManagedReference
    Set<Order> orders = new HashSet<>();

}
