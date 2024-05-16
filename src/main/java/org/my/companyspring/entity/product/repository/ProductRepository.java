package org.my.companyspring.entity.product.repository;


import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import org.my.companyspring.entity.product.Product;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@SecurityRequirement(name = "bearerAuth")
@RepositoryRestResource
public interface ProductRepository extends CrudRepository<Product, Long> {





}
