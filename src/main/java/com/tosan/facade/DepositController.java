package com.tosan.facade;

import com.tosan.AbstractRestHandler;
import com.tosan.entity.Deposit;
import com.tosan.entity.TsTransaction;
import com.tosan.exceptions.*;
import com.tosan.service.CustomerService;
import com.tosan.service.DepositService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

@RestController
@RequestMapping(value = "/deposits")
public class DepositController extends AbstractRestHandler {

    @Autowired
    private CustomerService customerService;
    @Autowired
    private DepositService depositService;

    @RequestMapping(value = "/open",
            method = RequestMethod.POST,
            produces = {"application/json", "application/xml"})
    @ResponseStatus(HttpStatus.OK)
    public Deposit openDeposit(@RequestBody Deposit deposit, HttpServletRequest request,
                               HttpServletResponse response) throws DuplicateNationalCodeException, CustomInvalidInputException, DuplicateDepositException, CustomerRequiredException {
        return this.depositService.openDeposit(deposit);
    }


    @RequestMapping(value = "/{id}/balance",
            method = RequestMethod.GET,
            produces = {"application/json", "application/xml"})
    @ResponseStatus(HttpStatus.OK)
    public Long viewBalance(@PathVariable("id") Long depositId, HttpServletRequest request,
                            HttpServletResponse response) {
        return this.depositService.viewBalance(depositId);
    }

    @RequestMapping(value = "/{id}/transactions",
            method = RequestMethod.GET,
            produces = {"application/json", "application/xml"})
    @ResponseStatus(HttpStatus.OK)
    public List<TsTransaction> findTransactions(@PathVariable("id") Long depositId, HttpServletRequest request,
                                                HttpServletResponse response) {
        return this.depositService.findTransactions(depositId);
    }

    @RequestMapping(value = "/{id}/withraw",
            method = RequestMethod.GET,
            produces = {"application/json", "application/xml"})
    @ResponseStatus(HttpStatus.OK)
    public TsTransaction withraw(@PathVariable("id") Long depositId, @RequestParam(name = "amount") Long amount,
                                                HttpServletRequest request, HttpServletResponse response) {
        return depositService.withraw(depositId, amount);
    }

    @RequestMapping(value = "/{id}/deposit",
            method = RequestMethod.GET,
            produces = {"application/json", "application/xml"})
    @ResponseStatus(HttpStatus.OK)
    public TsTransaction deposit(@PathVariable("id") Long depositId, @RequestParam(name = "amount") Long amount,
                                                HttpServletRequest request, HttpServletResponse response) throws InsufficientBalanceException, DepositBlockedException, TransactionFailedException {
        return depositService.deposit(depositId, amount);
    }

    @RequestMapping(value = "/{id}/transfer",
            method = RequestMethod.GET,
            produces = {"application/json", "application/xml"})
    @ResponseStatus(HttpStatus.OK)
    public TsTransaction transfer(@PathVariable("id") Long sourceDepositId,
                        @RequestParam(name = "depositId") Long destinationDepositId,
                        @RequestParam(name = "amount") Long amount,
                                                HttpServletRequest request, HttpServletResponse response) {
        return depositService.transfer(sourceDepositId, destinationDepositId, amount);
    }

    @RequestMapping(value = "/{id}",
            method = RequestMethod.DELETE,
            produces = {"application/json", "application/xml"})
    @ResponseStatus(HttpStatus.OK)
    public Integer delete(@PathVariable("id") Long depositId,
                                                HttpServletRequest request, HttpServletResponse response) {
        return depositService.delete(depositId);
    }

    /*
    @RequestMapping(value = "/{id}",
            method = RequestMethod.GET,
            produces = {"application/json", "application/xml"})
    @ResponseStatus(HttpStatus.OK)
    public
    @ResponseBody
    Customer getCustomer(@PathVariable("id") Long id,
                   HttpServletRequest request, HttpServletResponse response) throws Exception {
        Customer customer = this.customerService.getCustomer(id);
        checkResourceFound(customer);
        return customer;
    }

    @RequestMapping(value = "/all",
            method = RequestMethod.GET,
            produces = {"application/json", "application/xml"})
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    public Page<Customer> findPagedAll(
            @RequestParam(value = "page", required = true, defaultValue = DEFAULT_PAGE_NUM) Integer page,
            @RequestParam(value = "size", required = true, defaultValue = DEFAULT_PAGE_SIZE) Integer size,
            HttpServletRequest request, HttpServletResponse response) {
        return null;//this.customerService.findAll(page, size);
    }
    */
}
