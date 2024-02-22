package com.tosan.controllers;

import com.tosan.entity.Deposit;
import com.tosan.entity.DepositStatus;
import com.tosan.entity.DepositType;
import com.tosan.entity.TsTransaction;
import com.tosan.facade.CustomerController;
import com.tosan.repository.MyCustomerRepositoryImpl;
import com.tosan.repository.MyDepositRepositoryImpl;
import com.tosan.service.CustomerService;
import com.tosan.service.DepositService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.internal.verification.api.VerificationData;
import org.mockito.verification.VerificationMode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpStatus;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.*;

@RunWith(SpringRunner.class)
//@ContextConfiguration(classes = TestConfig.class)
public class DepositServiceTest {

    @TestConfiguration
    static class CustomerServiceImplTestContextConfiguration {

        @MockBean
        private MyCustomerRepositoryImpl myCustomerRepository;

        @MockBean
        private MyDepositRepositoryImpl myDepositRepository;

        @MockBean
        private CustomerService customerService;

        @Bean
        public DepositService depositService() {
            Deposit deposit = new Deposit();
            deposit.setDepositType(DepositType.ONGOING);
            deposit.setDid(1l);
            deposit.setBalance(1250l);
            deposit.setDepositNumber("23451");
            List<Deposit> depositList = new ArrayList<>();
            depositList.add(deposit);
            when(myDepositRepository.findDeposits(1l)).thenReturn(depositList);
            return new DepositService(myDepositRepository, myCustomerRepository);
        }
    }

    @Autowired
    private DepositService depositService;

    @Autowired
    private MyDepositRepositoryImpl depositRepository;

    @Test
    public void findDeposits() {
        Long id = 1l;
        List<Deposit> deposits = depositService.findDeposits(id);
    }

    @Test
    public void openDeposit() {
        Deposit deposit = new Deposit();
        deposit.setDepositNumber("123456");
        deposit.setDepositStatus(DepositStatus.OPEN);
        deposit.setCurrency("USD");
        deposit.setDepositType(DepositType.ONGOING);
        Deposit opened = this.depositService.openDeposit(deposit);
        System.out.println(opened.toString());
    }

    @Test
    public void viewBalance() {
        Long depositId = 2l;
        Long aLong = this.depositService.viewBalance(depositId);
        verify(depositRepository, times(1)).viewBalance(depositId);
        verify(depositRepository, new VerificationMode() {
            @Override
            public void verify(VerificationData verificationData) {

            }

            @Override
            public VerificationMode description(String s) {
                return null;
            }
        }).viewBalance(depositId);
    }

    @Test
    public void findTransactions() {
        Long depositId = 3l;
        List<TsTransaction> transactions = this.depositService.findTransactions(depositId);
        verify(depositService, times(1)).findTransactions(depositId);
    }

    @Test
    public void withraw() {
        Long depositId = 2l;
        Long amount = 1000l;
        depositService.withraw(depositId, amount);
        verify(depositRepository, times(1)).withraw(depositId, amount);
    }

    @Test
    public void deposit() {
        Long depositId = 0l;
        Long amount = 0l;
        depositService.deposit(depositId, amount);
        verify(depositRepository, times(1)).deposit(depositId, amount);

    }

    @Test
    public void transfer() {
        Long sourceDepositId = 1l;
        Long destinationDepositId = 2l;
        Long amount = 500l;
        depositService.transfer(sourceDepositId, destinationDepositId, amount);
        verify(depositRepository, times(1)).transfer(sourceDepositId, destinationDepositId, amount);
    }

    @Test
    public void delete() {
        Long depositId = 2l;
        depositService.delete(depositId);
        verify(depositRepository, times(1)).delete(depositId);
    }

    public void setDepositService(DepositService depositService) {
        this.depositService = depositService;
    }

    public DepositService getDepositService() {
        return depositService;
    }

}


