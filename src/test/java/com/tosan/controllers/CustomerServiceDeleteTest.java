
package com.tosan.controllers;

import com.tosan.dto.CustomerFilterDto;
import com.tosan.exceptions.CustomerHasDepositException;
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
public class CustomerServiceDeleteTest {

    @Autowired
    private CustomerService customerService;

    @Autowired
    private MyCustomerRepositoryImpl customerRepository;

    @Test
    public void deleteCustomer() throws TosanGeneralException, CustomerHasDepositException {
        //Long
        customerService.deleteCustomer(1l);
        verify(customerRepository, times(1)).delete(1l);
    }

}
