package com.tosan.service;

import com.tosan.entity.Deposit;
import com.tosan.entity.TsTransaction;
import com.tosan.repository.MyCustomerRepositoryImpl;
import com.tosan.repository.MyDepositRepositoryImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DepositService {
    private final MyDepositRepositoryImpl depositRepository;
    private final MyCustomerRepositoryImpl customerRepository;

    @Autowired
    CustomerService customerService;

    public DepositService(MyDepositRepositoryImpl depositRepository, MyCustomerRepositoryImpl customerRepository) {
        this.depositRepository = depositRepository;
        this.customerRepository = customerRepository;
    }

    public List<Deposit> findDeposits(Long customerId) {
        return depositRepository.findDeposits(customerId);
    }

    public Deposit openDeposit(Deposit deposit) {
        if(deposit.getOwner() == null) {
            throw new RuntimeException("Owner is not set for the requested open deposit!");
        }
        if(deposit.getOwner().getCid() == null) {
            customerService.createCustomer(deposit.getOwner());
        }
        depositRepository.saveDeposit(deposit);
        return deposit;
    }

    public Long viewBalance(Long depositId) {
        return depositRepository.viewBalance(depositId);
    }

    public List<TsTransaction> findTransactions(Long depositId) {
        return depositRepository.findTransactions(depositId);
    }

    public TsTransaction withraw(Long depositId, Long amount) {
        return depositRepository.withraw(depositId, amount);
    }

    public TsTransaction deposit(Long depositId, Long amount) {
        return depositRepository.deposit(depositId, amount);
    }

    public TsTransaction transfer(Long sourceDepositId, Long destinationDepositId, Long amount) {
        return depositRepository.transfer(sourceDepositId, destinationDepositId, amount);
    }

    public Integer delete(Long depositId) {
        return depositRepository.delete(depositId);
    }
}
