package com.tosan.repository;

import com.tosan.dto.CustomerFilterDto;
import com.tosan.entity.CustomerType;
import com.tosan.entity.TsCustomer;
import org.junit.Test;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

public class CustomerRepositoryOthersTest {
    MyCustomerRepositoryImpl customerRepository;

    @Test
    public void testUpdate() {
        TsCustomer testModel = new TsCustomer("Hamid", "Sharifian", "Sharif", "99994", CustomerType.REAL);
        customerRepository.update(testModel);
        System.out.println("INFO: testUpdate(): Id of the inserted Customer" + testModel.getCid());
        //verify()
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
