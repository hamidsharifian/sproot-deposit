package com.tosan.repository;

import com.tosan.TsCustomer;
import org.junit.rules.TestRule;
import org.junit.runner.Description;
import org.junit.runners.model.Statement;

import java.util.Random;

public final class CustomerMockRule implements TestRule {
    public TsCustomer customer;

    @Override public Statement apply(final Statement base,
                                     final Description description) {
        return new Statement() {
            @Override public void evaluate() throws Throwable {
                customer = new TsCustomer();
                customer.setFirstName("TestFirst");
                customer.setLastName("TestLast");
                customer.setNationalCode("1" + new Random(9999).nextInt());
                try {
                    base.evaluate(); // This will run the test.
                } finally {
                    //nothing!
                }
            }
        };
    }
}
