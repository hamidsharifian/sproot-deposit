package com.tosan.facade;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@RestController
@RequestMapping(value = "/test")
public class TestController {
//    @RequestMapping(value = "/create",
//            method = RequestMethod.POST,
//            consumes = {"application/json", "application/xml"},
//            produces = {"application/json", "application/xml"})
//    @ResponseStatus(HttpStatus.CREATED)
//    public void createCustomer(@RequestBody Customer customer,
//                               HttpServletRequest request, HttpServletResponse response) {
//        Customer createdCustomer = this.customerService.createCustomer(customer);
//        response.setHeader("Location", request.getRequestURL().append("/").append(createdCustomer.getId()).toString());
//    }

    @GetMapping(value = "/hello")
    @ResponseStatus(HttpStatus.OK)
    public void createCustomer(HttpServletRequest request, HttpServletResponse response) {
        try {
            response.getOutputStream().print("Hello, World!");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
