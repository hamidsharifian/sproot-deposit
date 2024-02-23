package com.tosan.repository;

import com.tosan.dto.CustomerFilterDto;
import com.tosan.entity.CustomerType;
import com.tosan.entity.TsCustomer;
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

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@RunWith(SpringRunner.class)
@FixMethodOrder(MethodSorters.DEFAULT)
public class CustomerRepositoryDeleteTest {
    @Autowired
    MyCustomerRepositoryImpl customerRepository;

    @Test
    public void contextLoads() {
        // This test passes if the application context loads without errors
    }

    @Test
    public void testDelete_nonExistingId() throws TosanGeneralException, CustomerHasDepositException {
        int deletedsCount = customerRepository.delete(7l);
        assertThat(deletedsCount).isEqualTo(0);
    }

    @Test
    public void testDelete_successfully() throws TosanGeneralException, CustomerHasDepositException {
        int deletedsCount = customerRepository.delete(2l);
        assertThat(deletedsCount).isEqualTo(1);
    }

    @Test(expected = CustomerHasDepositException.class)
    public void testDelete_hasDependentDeposit() throws TosanGeneralException, CustomerHasDepositException {
        int deletedCount = customerRepository.delete(1l);
    }

}
