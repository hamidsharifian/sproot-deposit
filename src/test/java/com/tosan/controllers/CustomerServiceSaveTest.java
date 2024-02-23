package com.tosan.controllers;

import com.tosan.entity.TsCustomer;
import com.tosan.entity.CustomerType;
import com.tosan.exceptions.CustomInvalidInputException;
import com.tosan.exceptions.DuplicateNationalCodeException;
import com.tosan.repository.MyCustomerRepositoryImpl;
import com.tosan.service.CustomerService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;


@SpringBootTest
@RunWith(SpringRunner.class)
public class CustomerServiceSaveTest {

    @Autowired
    private CustomerService customerService;

    @Autowired
    private MyCustomerRepositoryImpl customerRepository;

    TsCustomer customer;

    @Before
    public void beforeClass() {
        customer = new TsCustomer();
        customer.setFirstName("Hamid");
        customer.setLastName("Sharifian");
        customer.setCustomerType(CustomerType.REAL);
        customer.setCustomerCode("924212");
    }

    @Test
    public void contextLoads() {
        // This test passes if the application context loads without errors
    }

    @Test(expected = CustomInvalidInputException.class)
    public void creeteCustomer_customInvalidInputException() throws DuplicateNationalCodeException, CustomInvalidInputException {
        customerService.createCustomer(customer);
    }

    @Test(expected = DuplicateNationalCodeException.class)
    public void createCustomer_duplicationNationalCode() throws DuplicateNationalCodeException, CustomInvalidInputException {
        customer.setNationalCode("12345");
        customerService.createCustomer(customer);
    }

    @Test
    public void createCustomer_successfully() throws DuplicateNationalCodeException, CustomInvalidInputException {
        customer.setNationalCode("22345");
        customerService.createCustomer(customer);
    }

//    @Test
//    public void testSave() throws DuplicateNationalCodeException, CustomInvalidInputException {
//        TsCustomer customer = new TsCustomer();
//        customer.setFirstName("Hamid");
//        customer.setLastName("Sharifian");
//        customer.setNationalCode("93412");
//        customer.setCustomerType(CustomerType.REAL);
//        customer.setCustomerCode("924212");
//        customerRepository.save(customer);
//    }



}
