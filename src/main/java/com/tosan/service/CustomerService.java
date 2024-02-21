package com.tosan.service;

import com.tosan.dto.CustomerFilterDto;
import com.tosan.TsCustomer;
import com.tosan.repository.MyCustomerRepositoryImpl;
import com.tosan.repository.MyDepositRepositoryImpl;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CustomerService {
    private final MyCustomerRepositoryImpl customerRepository;
    private final MyDepositRepositoryImpl depositRepository;

    public CustomerService(MyCustomerRepositoryImpl customerRepository, MyDepositRepositoryImpl depositRepository) {
        this.customerRepository = customerRepository;
        this.depositRepository = depositRepository;
    }

    public void createCustomer(TsCustomer tsCustomer) {
        customerRepository.save(tsCustomer);
    }

    public List<TsCustomer> findAll() {
        return customerRepository.findAll();
    }

    public List<TsCustomer> findByStatus(boolean active) {
        return customerRepository.findByStatus(active);
    }

    public void updateCustomer(Long id, TsCustomer tsCustomer) {
        customerRepository.save(tsCustomer);
    }

    public void deleteCustomer(Long id) {
        customerRepository.delete(id);
    }

    public void activate(Long customerId) {
        depositRepository.activate(customerId);
    }

    public void deactivate(Long customerId) {
        depositRepository.deactivate(customerId);
    }

    public List<TsCustomer> findByFilter(CustomerFilterDto filterDto) {
        return customerRepository.findByFilter(filterDto);
    }
}
