package com.tosan.facade;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.nio.charset.StandardCharsets;

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

    @Autowired
    Translator translator;

    @GetMapping(value = "/hello", produces = "text/plain; charset=UTF-8")
    @ResponseStatus(HttpStatus.OK)
    public void hello(HttpServletRequest request, HttpServletResponse response) {
        try {
            //response.getOutputStream().print("Hello, World!");
            //response.getOutputStream().write(Translator.toLocale("greeting").getBytes(StandardCharsets.UTF_8));
            response.getOutputStream().write("سلام".getBytes(StandardCharsets.UTF_8));
            response.setHeader("Content-Type", "text/plain;charset=utf-8");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
