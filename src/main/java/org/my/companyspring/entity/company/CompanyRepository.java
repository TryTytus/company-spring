package org.my.companyspring.entity.company;

import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;


@SecurityRequirement(name = "bearerAuth")
@RepositoryRestResource
public interface CompanyRepository extends CrudRepository<Company, Long> {


}
