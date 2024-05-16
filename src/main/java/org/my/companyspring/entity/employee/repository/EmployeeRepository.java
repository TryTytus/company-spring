package org.my.companyspring.entity.employee.repository;


import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import org.my.companyspring.entity.employee.Employee;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;


@SecurityRequirement(name = "bearerAuth")
@RepositoryRestResource
public interface EmployeeRepository extends
        CrudRepository<Employee, Long>,
        PagingAndSortingRepository<Employee, Long>
{



}
