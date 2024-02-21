package com.tosan;

import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class MainApplication {

    //private final CustomerService customerService;

//    public MainApplication(CustomerService customerService) {
//        this.customerService = customerService;
//    }

    public static void main(String[] args) {
        SpringApplication.run(MainApplication.class, args);
    }

    @Bean
    public ApplicationRunner init() {
        return args -> {

            System.out.println("Fetch all customers ...");
            //List<TsCustomer> allTsCustomers = customerService.findAll();
            //System.out.println("Tocal Count: " + allTsCustomers.size() );

//            System.out.println("\nFetch shopping cart by price ...");
//            Customer sc2 = customerService.byPriceShoppingCart();
//            System.out.println("\nOwner: " + sc2.getOwner() + " Books: " + sc2.getBooks());
        };
    }
}
