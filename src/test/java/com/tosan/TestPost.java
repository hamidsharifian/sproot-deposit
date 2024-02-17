package com.tosan;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.tosan.entity.TsCustomer;
import com.tosan.facade.CustomerController;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.http.MediaType.APPLICATION_JSON_UTF8;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc

//@WebMvcTest(CustomerController.class)
public class TestPost {
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private CustomerController service;

    @Test
    public void testInsertObject() throws Exception {
        String url = "/customers/create";
        TsCustomer nwTsCustomer = new TsCustomer();
//        nwTsCustomer.setActive(true);
        nwTsCustomer.setFirstName("Ali");
        nwTsCustomer.setLastName("Ebrahimi");
        //... more
        ObjectMapper mapper = new ObjectMapper();
        mapper.configure(SerializationFeature.WRAP_ROOT_VALUE, false);
        ObjectWriter ow = mapper.writer().withDefaultPrettyPrinter();
        String requestJson = ow.writeValueAsString(nwTsCustomer);

        mockMvc.perform(post(url).contentType(APPLICATION_JSON_UTF8)
                .content(requestJson))
                .andExpect(status().isCreated());
    }


    @Test
    public void testFindAll() throws Exception {
        String url = "/customers/all";

        mockMvc.perform(get(url))
                .andExpect(status().isOk());
                //.andExpect();
    }
}
