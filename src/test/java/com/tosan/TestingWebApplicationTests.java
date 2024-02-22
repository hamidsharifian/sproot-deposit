package com.tosan;

import com.tosan.facade.CustomerController;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.test.context.junit4.SpringRunner;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;

//@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@SpringBootTest(classes = MainApplication.class,
		webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@RunWith(SpringRunner.class)

public class TestingWebApplicationTests {

	@LocalServerPort
	private int port;

	@Autowired
	private CustomerController controller;

	@Autowired
	private TestRestTemplate restTemplate;



	@Test
	public void contextLoads() throws Exception {
		assertThat(controller).isNotNull();
	}

	@Test
	public void greetingShouldReturnDefaultMessage() throws Exception {
		assertThat(this.restTemplate.getForObject("http://localhost:" + port + "/test/hello",
				String.class)).contains("Hello, World");
	}

}