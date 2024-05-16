package org.my.companyspring.entity.employee;


import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import lombok.RequiredArgsConstructor;
import org.my.companyspring.entity.employee.records.EmpOrderNum;
import org.my.companyspring.entity.employee.records.EmpPrdNum;
import org.my.companyspring.entity.employee.repository.CustomEmployeeRepository;
import org.springframework.data.rest.webmvc.RepositoryRestController;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@SecurityRequirement(name = "bearerAuth")
@RestController
@RequiredArgsConstructor
@RequestMapping("/employees")
public class EmployeeController {


    private final CustomEmployeeRepository employeeCustomRepository;

    @GetMapping("/byOrderNum")
    public List<EmpOrderNum> byOrderNum()
    {
        return employeeCustomRepository.getEmployeeByOrderNum();
    }

    @GetMapping("/byProductNum")
    public List<EmpPrdNum> byProductNum()
    {
        return employeeCustomRepository.getEmployeeByProductsNum();
    }

}
