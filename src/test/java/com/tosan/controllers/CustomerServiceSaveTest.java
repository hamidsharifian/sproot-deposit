package com.tosan.controllers;

import com.tosan.dto.CustomerFilterDto;
import com.tosan.entity.TsCustomer;
import com.tosan.entity.CustomerType;
import com.tosan.exceptions.CustomInvalidInputException;
import com.tosan.exceptions.CustomerHasDepositException;
import com.tosan.exceptions.DuplicateNationalCodeException;
import com.tosan.exceptions.TosanGeneralException;
import com.tosan.repository.MyCustomerRepositoryImpl;
import com.tosan.service.CustomerService;
import org.junit.Test;
import org.junit.runner.RunWith;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;


@SpringBootTest
@RunWith(SpringRunner.class)
public class CustomerServiceSaveTest {

    @Autowired
    private CustomerService customerService;

    @Autowired
    private MyCustomerRepositoryImpl customerRepository;

    @Test(expected = CustomInvalidInputException.class)
    public void createCustomer() throws DuplicateNationalCodeException, CustomInvalidInputException {
        TsCustomer customer = new TsCustomer();
        customer.setFirstName("Hamid");
        customerService.createCustomer(customer);
        //verify(customerRepository, times(1)).save(customer);
    }

    @Test(expected = DuplicateNationalCodeException.class)
    public void updateCustomer() throws DuplicateNationalCodeException, CustomInvalidInputException {
        Long id = 1l;
        TsCustomer customer = new TsCustomer("Ali", "Rezaei", "Reza", "23990", CustomerType.REAL);
        //checkResourceFound(this.customerService.getCustomer(id));
        customerService.updateCustomer(id, customer);
        //verify(customerRepository, times(1)).save(customer);
    }

}
