package com.tosan.facade;

import com.tosan.AbstractRestHandler;
import com.tosan.dto.CustomerFilterDto;
import com.tosan.entity.Deposit;
import com.tosan.entity.TsCustomer;
import com.tosan.service.CustomerService;
import com.tosan.service.DepositService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

@RestController
@RequestMapping(value = "/customers")
public class CustomerController extends AbstractRestHandler {

    @Autowired
    private CustomerService customerService;
    @Autowired
    private DepositService depositService;

    @RequestMapping(value = "/create",
            method = RequestMethod.POST,
            consumes = {"application/json", "application/xml"},
            produces = {"application/json", "application/xml"})
    @ResponseStatus(HttpStatus.CREATED)
    public void createCustomer(@RequestBody TsCustomer tsCustomer,
                            HttpServletRequest request, HttpServletResponse response) {
        this.customerService.createCustomer(tsCustomer);
        response.setHeader("Location", request.getRequestURL().append("/").append(tsCustomer.getCid()).toString());
    }

    @RequestMapping(value = "/all",
            method = RequestMethod.GET,
            produces = {"application/json", "application/xml"})
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    public List<TsCustomer> findAll(HttpServletRequest request, HttpServletResponse response) {
        return this.customerService.findAll();
    }

    @RequestMapping(value = "/{id}",
            method = RequestMethod.PUT,
            consumes = {"application/json", "application/xml"},
            produces = {"application/json", "application/xml"})
    @ResponseStatus(HttpStatus.OK)
    public void updateCustomer(@PathVariable("id") Long id, @RequestBody TsCustomer tsCustomer,
                               HttpServletRequest request, HttpServletResponse response) {
        //checkResourceFound(this.customerService.getCustomer(id));
        this.customerService.updateCustomer(id, tsCustomer);
    }

    @RequestMapping(value = "/{id}",
            method = RequestMethod.DELETE,
            produces = {"application/json", "application/xml"})
    @ResponseStatus(HttpStatus.OK)
    public void deleteCustomer(@PathVariable("id") Long id, HttpServletRequest request,
                               HttpServletResponse response) {
        this.customerService.deleteCustomer(id);
    }

    @RequestMapping(value = "/filter",
            method = RequestMethod.POST,
            produces = {"application/json", "application/xml"})
    @ResponseStatus(HttpStatus.OK)
    public List<TsCustomer> findByFilter(@RequestBody(required = false) CustomerFilterDto customerFilterDto, HttpServletRequest request,
                             HttpServletResponse response) {
        return this.customerService.findByFilter(customerFilterDto);
    }

    @RequestMapping(value = "/{id}/deposits",
            method = RequestMethod.GET,
            produces = {"application/json", "application/xml"})
    @ResponseStatus(HttpStatus.OK)
    public List<Deposit> findDeposits(@PathVariable("id") Long id, HttpServletRequest request,
                                      HttpServletResponse response) {
        return this.depositService.findDeposits(id);
    }

    @RequestMapping(value = "/{id}/activate",
            method = RequestMethod.GET,
            produces = {"application/json", "application/xml"})
    @ResponseStatus(HttpStatus.OK)
    public void activateCustomer(@PathVariable("id") Long id, HttpServletRequest request,
                                      HttpServletResponse response) {
        this.customerService.activate(id);
    }

    @RequestMapping(value = "/{id}/deactivate",
            method = RequestMethod.GET,
            produces = {"application/json", "application/xml"})
    @ResponseStatus(HttpStatus.OK)
    public void deactivateCustomer(@PathVariable("id") Long id, HttpServletRequest request,
                                      HttpServletResponse response) {
        this.customerService.deactivate(id);
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
