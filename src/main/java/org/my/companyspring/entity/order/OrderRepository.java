package org.my.companyspring.entity.order;

import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@SecurityRequirement(name = "bearerAuth")
@RepositoryRestResource
public interface OrderRepository extends
        CrudRepository<Order, Long>,
        PagingAndSortingRepository<Order, Long>
{

}
