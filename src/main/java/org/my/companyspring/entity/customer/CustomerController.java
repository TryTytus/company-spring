package org.my.companyspring.entity.customer;


import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import lombok.AllArgsConstructor;
import org.my.companyspring.entity.customer.projections.CustomerInfo;
import org.my.companyspring.entity.customer.records.CustomerOrdStatsDTO;
import org.my.companyspring.entity.customer.records.SpentMostDTO;
import org.my.companyspring.entity.customer.repository.CustomCustomerRepository;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/customer")
@SecurityRequirement(name = "bearerAuth")
@AllArgsConstructor
public class CustomerController {

    CustomCustomerRepository customCustomerRepository;

    @GetMapping("/getCustomersWithOrders")
    @ResponseBody
    public List<CustomerInfo> getCustomersWithOrders(int skip)
    {

        return customCustomerRepository.get10CustomersWithOrders(skip);
    }


    @GetMapping("/getWhoSpentTheMost")
    @ResponseBody
    public List<SpentMostDTO> getWhoSpentTheMost()
    {
        return customCustomerRepository.getWhoSpentTheMost();
    }


    @GetMapping("/getCustomerOrderStatistics")
    @Operation(description = "get the total number of orders, total quantity, and average discount for each customer")
    public List<CustomerOrdStatsDTO> getCustomerOrderStatistics()
    {
        return customCustomerRepository.getCustomerOrderStatistics();
    }

}
