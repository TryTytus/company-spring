package org.my.companyspring.faker;

import org.my.companyspring.entity.company.Company;
import org.my.companyspring.entity.company.CompanyRepository;
import org.my.companyspring.entity.customer.Customer;
import org.my.companyspring.entity.customer.repository.CustomerRepository;
import org.my.companyspring.entity.employee.Employee;
import org.my.companyspring.entity.employee.repository.EmployeeRepository;
import org.my.companyspring.entity.order.Order;
import org.my.companyspring.entity.product.Product;
import org.my.companyspring.entity.product.repository.ProductRepository;
import com.github.javafaker.Faker;
import lombok.AllArgsConstructor;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import java.util.*;


@AllArgsConstructor
@Component
public class FakeDataOnStartup implements ApplicationRunner {

    private EmployeeRepository employeeRepository;
    private CompanyRepository companyRepository;
    private ProductRepository productRepository;
    private CustomerRepository customerRepository;


    @Override
    public void run(ApplicationArguments args) throws Exception {
        Faker faker = new Faker();

        List<Company> companies = new ArrayList<>();

        for (int i = 0; i < 20; i++) {
            companies.add(
                    Company.builder()
                            .name(faker.company().name())
                            .industry(faker.company().industry())
                            .country(faker.address().country())
                            .size(faker.number().numberBetween(20, 150))
                            .build()
            );
        }

        companyRepository.saveAll(companies);

        ArrayList<Customer> customers = new ArrayList<>();

        for (int i = 0; i < 20; i++) {

            customers.add(Customer.builder()
                    .firstName(faker.name().firstName())
                    .lastName(faker.name().lastName())
                    .email(faker.internet().emailAddress())
                    .city(faker.address().city())
                    .phone(faker.phoneNumber().phoneNumber())
                    .sex((faker.bool().bool()) ? 'M' : 'F')
                    .postCode(faker.address().zipCode())
                    .street(faker.address().streetName())
                    .streetNumber(faker.address().streetAddressNumber())
                    .build());
        }

        Iterable<Customer> savedCustomers = customerRepository.saveAll(customers);
        Iterator<Customer> customerIterator = savedCustomers.iterator();


        List<Product> products = new ArrayList<>();

        for (int i = 0; i < 100; i++) {
            products.add(
                    Product.builder()
                            .name(faker.commerce().productName())
                            .color(faker.color().name())
                            .price(faker.number().numberBetween(1_0000, 1000_000))
                            .count(faker.number().numberBetween(5, 200))
                            .build()
            );
        }

        Iterable<Product> productIterable =  productRepository.saveAll(products);
        Iterator<Product> productIterator = productIterable.iterator();



        List<Employee> employees = new ArrayList<>();

        for (int i = 0; i < 30; i++) {

            employees.add(
                    Employee.builder()
                            .firstName(faker.name().firstName())
                            .lastName(faker.name().lastName())
                            .email(faker.internet().emailAddress())
                            .address(faker.address().streetAddress())
                            .profession(faker.company().profession())
                            .number(faker.phoneNumber().phoneNumber())
                            .build()
            );
        }




        for (Employee employee : employees) {
            employee.setOrders(new HashSet<>());


            for (int i = 0; i < faker.number().numberBetween(1, 3); i++) {
                if (!customerIterator.hasNext())
                    customerIterator = savedCustomers.iterator();
                if (!productIterator.hasNext())
                    productIterator = productIterable.iterator();

                employee.getOrders().add(
                        Order.builder()
                                .quantity(faker.number().numberBetween(1, 20))
                                .status(faker.options().option("READY", "PROCESSED", "CONFIRMED"))
                                .description(String.join(" ", faker.lorem().words(4)))
                                .discount(faker.number().numberBetween(10, 30))
                                .customer(customerIterator.next())
                                .product(productIterator.next())
                                .build()
                );
            }

        }
        employeeRepository.saveAll(employees);


    }
}
