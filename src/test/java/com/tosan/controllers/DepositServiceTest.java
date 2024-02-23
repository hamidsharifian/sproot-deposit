package com.tosan.controllers;

import com.tosan.entity.*;
import com.tosan.exceptions.*;
import com.tosan.repository.MyCustomerRepositoryImpl;
import com.tosan.repository.MyDepositRepositoryImpl;
import com.tosan.service.CustomerService;
import com.tosan.service.DepositService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;

@SpringBootTest
@RunWith(SpringRunner.class)
//@ContextConfiguration(classes = TestConfig.class)
public class DepositServiceTest {

    public Deposit deposit;

    @Autowired
    private DepositService depositService;

    @Autowired
    private MyDepositRepositoryImpl depositRepository;

    @Before
    public void xyz() {
        deposit = new Deposit();
        deposit.setDepositNumber("993456");
        deposit.setDepositStatus(DepositStatus.OPEN);
        deposit.setCurrency(Currency.USD);
        deposit.setDepositType(DepositType.LONGTERM);
    }

    @Test
    public void findDeposits() {
        Long id = 1l;
        List<Deposit> deposits = depositService.findDeposits(id);
        assertThat(deposits.size()).isEqualTo(2);
    }

    @Test(expected = CustomerRequiredException.class)
    public void openDeposit_customerNotSet() throws DuplicateNationalCodeException, CustomInvalidInputException, DuplicateDepositException, CustomerRequiredException {
        deposit.setDepositNumber("423456");
        Deposit opened = this.depositService.openDeposit(deposit);
        System.out.println(opened.toString());
    }

    @Test(expected = CustomInvalidInputException.class)
    public void openDeposit_duplicateDepositNumber() throws DuplicateNationalCodeException, CustomInvalidInputException, DuplicateDepositException, CustomerRequiredException {
        deposit.setDepositNumber("123456");
        deposit.setOwnerId(2l);
        Deposit opened = this.depositService.openDeposit(deposit);
        System.out.println(opened.toString());
    }
    @Test
    public void openDeposit_successfully() throws DuplicateNationalCodeException, CustomInvalidInputException, DuplicateDepositException, CustomerRequiredException {
        deposit.setDepositNumber("223456");
        deposit.setOwnerId(2l);
        Deposit opened = this.depositService.openDeposit(deposit);
        System.out.println(opened.toString());
    }

    @Test
    public void viewBalance() {
        Long depositId = 2l;
        Long aLong = this.depositService.viewBalance(depositId);
        verify(depositRepository, times(1)).viewBalance(depositId);
    }

    @Test
    public void findTransactions() {
        Long depositId = 3l;
        List<TsTransaction> transactions = this.depositService.findTransactions(depositId);
    }

    @Test
    public void withraw() {
        Long depositId = 2l;
        Long amount = 1000l;
        depositService.withraw(depositId, amount);
        verify(depositRepository, times(1)).withraw(depositId, amount);
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

}


