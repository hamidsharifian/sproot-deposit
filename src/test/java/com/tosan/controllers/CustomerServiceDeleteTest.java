
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

import static org.assertj.core.api.Assertions.assertThat;
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
    public void testDelete_nonExistingId() throws TosanGeneralException, CustomerHasDepositException {
        int deletedsCount = customerService.deleteCustomer(7l);
        assertThat(deletedsCount).isEqualTo(0);
    }

    @Test(expected = CustomerHasDepositException.class)
    public void testDelete_hasDependentDeposit() throws TosanGeneralException, CustomerHasDepositException {
        customerService.deleteCustomer(1l);
    }

    @Test
    public void testDelete_successfully() throws TosanGeneralException, CustomerHasDepositException {
        int deletedsCount = customerService.deleteCustomer(2l);
        assertThat(deletedsCount).isEqualTo(1);
    }

}
