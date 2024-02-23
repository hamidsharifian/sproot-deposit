package com.tosan.controllers;

import com.tosan.entity.Deposit;
import com.tosan.exceptions.DepositBlockedException;
import com.tosan.exceptions.InsufficientBalanceException;
import com.tosan.exceptions.TransactionFailedException;
import com.tosan.repository.MyDepositRepositoryImpl;
import com.tosan.service.DepositService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@SpringBootTest
@RunWith(SpringRunner.class)
public class DeposiServiceDepositTest {

    @Autowired
    private DepositService depositService;

    @Autowired
    private MyDepositRepositoryImpl depositRepository;

    @Test(expected = InsufficientBalanceException.class)
    public void deposit_insufficient() throws InsufficientBalanceException, DepositBlockedException, TransactionFailedException {
        Long depositId = 1l;
        Long amount = 1000l;
        depositService.deposit(depositId, amount);
    }

    @Test(expected = DepositBlockedException.class)
    public void deposit_blocked() throws InsufficientBalanceException, DepositBlockedException, TransactionFailedException {
        Long depositId = 3l;
        Long amount = 1000l;
        depositService.deposit(depositId, amount);
    }

}
