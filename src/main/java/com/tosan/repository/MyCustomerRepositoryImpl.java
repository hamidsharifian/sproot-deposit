package com.tosan.repository;


import com.tosan.TsCustomer;
import com.tosan.dto.CustomerFilterDto;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.List;

@Repository
//@DataJpaTest
public class MyCustomerRepositoryImpl { //} implements MyCustomerRepository {

    @PersistenceContext
    EntityManager em;

    public void customMethod() {
        System.out.println("customMethod");
    }

    @Transactional
    public TsCustomer save(TsCustomer customer) {
        //em.merge(customer);
        em.persist(customer);
        return customer;
    }

    @Transactional
    public TsCustomer update(TsCustomer customer) {
        return em.merge(customer);
    }

    @Transactional
    public int delete(Long cid) {
        String queryStr = "DELETE from TsCustomer c where c.cid = ?1";
        Query query = em.createQuery(queryStr);
        query.setParameter(1, cid);
        return query.executeUpdate();
    }

    @Transactional(readOnly = true)
    public List<TsCustomer> findByStatus(boolean active) {
        String queryStr = "SELECT c from TsCustomer c where c.active = ?1";
        Query query = em.createQuery(queryStr, TsCustomer.class);
        query.setParameter(1, active);
        return query.getResultList();
    }
    @Transactional(readOnly = true)
    public List<TsCustomer> findAll() {
        String queryStr = "SELECT c from TsCustomer c";
        Query query = em.createQuery(queryStr, TsCustomer.class);
        return query.getResultList();
    }

    @Transactional
    public List<TsCustomer> findByFilter(CustomerFilterDto filterDto) {
        String queryStr = "SELECT c from TsCustomer c where " +
                " (:firstname is null or c.firstName = :firstname) and " +
                " (:lastname is null or c.lastName = :lastname) and " +
                " (:nationalcode is null or c.nationalCode = :nationalcode) ";
        Query query = em.createQuery(queryStr, TsCustomer.class);
        query.setParameter("firstname", filterDto.getFirstName());
        query.setParameter("lastname", filterDto.getLastName());
        query.setParameter("nationalcode", filterDto.getNationalCode());
        return query.getResultList();
    }



}

