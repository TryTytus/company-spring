package org.my.companyspring.entity.customer.repository;



import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import org.my.companyspring.entity.customer.Customer;
import org.my.companyspring.entity.customer.projections.CustomerInfo;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@SecurityRequirement(name = "bearerAuth")
@RepositoryRestResource
public interface CustomerRepository extends
        CrudRepository<Customer, Long>,
        PagingAndSortingRepository<Customer, Long>
{

    CustomerInfo getByEmail(String email);
    CustomerInfo searchFirstByLastNameContains(String search);



}
