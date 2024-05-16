package org.my.companyspring.entity.product;


import lombok.RequiredArgsConstructor;
import org.my.companyspring.entity.product.records.HighPricedPrdPerCustDTO;
import org.my.companyspring.entity.product.records.PrdOrdStatsDTO;
import org.my.companyspring.entity.product.records.TopPrdByOrdNumDTO;
import org.my.companyspring.entity.product.repository.CustomProductRepository;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/products")
@RequiredArgsConstructor
public class ProductController {

    private final CustomProductRepository productRepository;

    @GetMapping("/getHighestPricedProductPerCustomer")
    @ResponseBody
    public List<HighPricedPrdPerCustDTO> getHighestPricedProductPerCustomer()
    {
        return productRepository.getHighestPricedProductPerCustomer();
    }



    @GetMapping("/getProductOrderStatistics")
    @ResponseBody
    public List<PrdOrdStatsDTO> getProductOrderStatistics()
    {
        return productRepository.getProductOrderStatistics();
    }

    @GetMapping("/getTopProductsByOrderQuantity")
    @ResponseBody
    public List<TopPrdByOrdNumDTO> getTopProductsByOrderQuantity()
    {
        return productRepository.getTopProductsByOrderQuantity();
    }

}
