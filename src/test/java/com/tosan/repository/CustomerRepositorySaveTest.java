package com.tosan.repository;

import com.tosan.entity.TsCustomer;
import com.tosan.dto.CustomerFilterDto;
import com.tosan.entity.CustomerType;
import com.tosan.exceptions.CustomInvalidInputException;
import com.tosan.exceptions.CustomerHasDepositException;
import com.tosan.exceptions.DuplicateNationalCodeException;
import com.tosan.exceptions.TosanGeneralException;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.*;

import static org.mockito.Mockito.verify;
import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@RunWith(SpringRunner.class)
@FixMethodOrder(MethodSorters.DEFAULT)
public class CustomerRepositorySaveTest {
    @Autowired
    MyCustomerRepositoryImpl customerRepository;

    CustomerMockRule mockRule = new CustomerMockRule();

    @Test
    public void contextLoads() {
        // This test passes if the application context loads without errors
    }
    
    @Test
    public void testSave() throws DuplicateNationalCodeException, CustomInvalidInputException {
        TsCustomer customer = new TsCustomer();
        customer.setFirstName("Hamid");
        customer.setLastName("Sharifian");
        customer.setNationalCode("93412");
        customer.setCustomerType(CustomerType.REAL);
        customer.setCustomerCode("924212");
        customerRepository.save(customer);
    }
    @Test(expected = DuplicateNationalCodeException.class)
    public void testSave_duplicationNationalCode() throws DuplicateNationalCodeException, CustomInvalidInputException {
        TsCustomer customer = new TsCustomer();
        customer.setFirstName("Hamid");
        customer.setLastName("Sharifian");
        //customer.setNationalCode("12345");
        customer.setCustomerType(CustomerType.REAL);
        customer.setCustomerCode("924212");
        customerRepository.save(customer);
    }

    @Test(expected = CustomInvalidInputException.class)
    public void testSave_customInvalidInputException() throws DuplicateNationalCodeException, CustomInvalidInputException {
        TsCustomer customer = new TsCustomer();
        customer.setFirstName("Hamid");
        customer.setLastName("Sharifian");
        customer.setNationalCode("12348");
        customer.setCustomerType(CustomerType.REAL);
        customer.setCustomerCode("924212");
        customerRepository.save(customer);
    }




}
