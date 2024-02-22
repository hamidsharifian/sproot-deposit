package com.tosan.repository;

import com.tosan.Employee;
import com.tosan.dto.CustomerFilterDto;
import com.tosan.entity.CustomerType;
import com.tosan.entity.TsCustomer;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.jdbc.EmbeddedDatabaseConnection;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@DataJpaTest
@AutoConfigureTestDatabase(connection = EmbeddedDatabaseConnection.H2)
//@TestPropertySource(properties = {
//        "spring.jpa.hibernate.ddl-auto=none",
//        "spring.datasource.url=jdbc:mysql://localhost:3306/tosan",
//        "spring.datasource.username=root",
//        "spring.datasource.password=",
//        "spring.datasource.platform=mysql",
//        "spring.jpa.properties.hibernate.dialect=org.hibernate.dialect.MySQL8Dialect"
//})
public class MyCustomerRepositoryImplTests {

    @TestConfiguration
    static class CustomerServiceImplTestContextConfiguration {
        @Autowired
        TestEntityManager entityManager;

        @Bean
        public EmployeeRepository employeeRepository() {
            EmployeeRepository employeeRepository = new EmployeeRepository();
            employeeRepository.setEm(entityManager.getEntityManager());
            return employeeRepository;
        }

        @Bean
        public MyCustomerRepositoryImpl customerRepository() {
            return new MyCustomerRepositoryImpl();
        }
    }






}