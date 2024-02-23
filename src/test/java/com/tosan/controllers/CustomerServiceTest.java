package com.tosan.controllers;

import com.tosan.dto.CustomerFilterDto;
import com.tosan.entity.TsCustomer;
import com.tosan.entity.CustomerType;
import com.tosan.exceptions.CustomInvalidInputException;
import com.tosan.exceptions.CustomerHasDepositException;
import com.tosan.exceptions.DuplicateNationalCodeException;
import com.tosan.exceptions.TosanGeneralException;
import com.tosan.repository.MyCustomerRepositoryImpl;
import com.tosan.repository.MyDepositRepositoryImpl;
import com.tosan.service.CustomerService;
import org.junit.Test;
import org.junit.runner.RunWith;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Bean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;


@SpringBootTest
@RunWith(SpringRunner.class)
public class CustomerServiceTest {

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

    @Test
    public void findAll() {
        customerService.findAll();
        verify(customerRepository, times(1)).findAll();
    }

    @Test(expected = DuplicateNationalCodeException.class)
    public void updateCustomer() throws DuplicateNationalCodeException, CustomInvalidInputException {
        Long id = 1l;
        TsCustomer customer = new TsCustomer("Ali", "Rezaei", "Reza", "23990", CustomerType.REAL);
        //checkResourceFound(this.customerService.getCustomer(id));
        customerService.updateCustomer(id, customer);
        //verify(customerRepository, times(1)).save(customer);
    }

    @Test
    public void deleteCustomer() throws TosanGeneralException, CustomerHasDepositException {
        //Long
        customerService.deleteCustomer(1l);
        verify(customerRepository, times(1)).delete(1l);
    }

    @Test
    public void findByFilter() {
        CustomerFilterDto customerFilterDto = new CustomerFilterDto();
        customerFilterDto.setFirstName("Hamid");
        customerService.findByFilter(customerFilterDto);
        verify(customerRepository, times(1)).findByFilter(customerFilterDto);
    }

    @Test
    public void activateCustomer() {
        Long id = 27l;
        customerService.activate(id);
    }

    @Test
    public void deactivateCustomer() {
        Long id = 29l;
        customerService.deactivate(id);
    }
}
