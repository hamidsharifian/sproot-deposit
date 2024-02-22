package com.tosan.repository;

import com.tosan.entity.TsCustomer;
import com.tosan.dto.CustomerFilterDto;
import com.tosan.entity.CustomerType;
import com.tosan.exceptions.CustomInvalidInputException;
import com.tosan.exceptions.DuplicateNationalCodeException;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.*;

import static org.mockito.Mockito.verify;
import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@RunWith(SpringRunner.class)
@FixMethodOrder(MethodSorters.DEFAULT)
public class CustomerRepositorySaveTest {
    @Autowired
    MyCustomerRepositoryImpl customerRepository;

    CustomerMockRule mockRule = new CustomerMockRule();

    @Test
    public void contextLoads() {
        // This test passes if the application context loads without errors
    }
    
    @Test
    public void testSave() throws DuplicateNationalCodeException, CustomInvalidInputException {
        TsCustomer customer = new TsCustomer();
        customer.setFirstName("Hamid");
        customer.setLastName("Sharifian");
        customer.setNationalCode("93412");
        customer.setCustomerType(CustomerType.REAL);
        customer.setCustomerCode("924212");
        customerRepository.save(customer);
    }
    @Test(expected = DuplicateNationalCodeException.class)
    public void testSave_duplicationNationalCode() throws DuplicateNationalCodeException, CustomInvalidInputException {
        TsCustomer customer = new TsCustomer();
        customer.setFirstName("Hamid");
        customer.setLastName("Sharifian");
        //customer.setNationalCode("12345");
        customer.setCustomerType(CustomerType.REAL);
        customer.setCustomerCode("924212");
        customerRepository.save(customer);
    }

    @Test(expected = CustomInvalidInputException.class)
    public void testSave_customInvalidInputException() throws DuplicateNationalCodeException, CustomInvalidInputException {
        TsCustomer customer = new TsCustomer();
        customer.setFirstName("Hamid");
        customer.setLastName("Sharifian");
        customer.setNationalCode("12348");
        customer.setCustomerType(CustomerType.REAL);
        customer.setCustomerCode("924212");
        customerRepository.save(customer);
    }

    @Test
    public void testUpdate() {
        TsCustomer testModel = new TsCustomer("Hamid", "Sharifian", "Sharif", "99994", CustomerType.REAL);
        customerRepository.update(testModel);
        System.out.println("INFO: testUpdate(): Id of the inserted Customer" + testModel.getCid());
        //verify()
    }

    @Test
    public void testDelete() {
        int deletedsCount = customerRepository.delete(7l);

        System.out.println("INFO: testDelete(): " + deletedsCount + " records where deleted successfully, We assume it is well tested!");
    }

    @Test
    public void testFind() {
        List<TsCustomer> all = customerRepository.findAll();
        List<TsCustomer> activeCustomers = customerRepository.findByStatus(true);
        List<TsCustomer> deactivateduCustomer = customerRepository.findByStatus(false);

        assertThat(all.size()).isGreaterThan(activeCustomers.size() + deactivateduCustomer.size());
    }


    @Test
    public void findByFilterTest() {
        CustomerFilterDto filterDto = new CustomerFilterDto();
        filterDto.setFirstName("Hamid");
        List<TsCustomer> found = customerRepository.findByFilter(filterDto);

        Optional<TsCustomer> hamid = found.stream().filter(f -> !f.getFirstName().equals("Hamid")).findAny();
        assertThat(hamid).isEmpty();
    }
    
//    @Test
//    void      delete_hlEntityExists_deleteEntity() {
//given(hlRepository.existsById(hlEntity.getId())).willReturn(true);
//willDoNothing().given(hlRepository).delete(hlEntity);
//
//hlService.delete(hlEntity);
//
//verify(hlRepository,      times(1)).delete(hlEntity);
//    }
//
//    @Test
//    void      delete_hlEntityDoesNotExists_hlNotFoundExceptionThrown() {
//given(hlRepository.existsById(anyLong())).willReturn(false);
//
//hlService.delete(hlEntity);
//
//verify(hlRepository,      times(0)).delete(any(HLEntity.class));
//    }



}
