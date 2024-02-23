package com.tosan.repository;


import com.tosan.dto.CustomerFilterDto;
import com.tosan.entity.TsCustomer;
import com.tosan.exceptions.CustomInvalidInputException;
import com.tosan.exceptions.CustomerHasDepositException;
import com.tosan.exceptions.DuplicateNationalCodeException;
import com.tosan.exceptions.TosanGeneralException;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceException;
import javax.persistence.Query;
import javax.validation.ConstraintViolationException;
import java.rmi.server.ExportException;
import java.sql.SQLIntegrityConstraintViolationException;
import java.util.List;
import java.util.stream.Collectors;

@Repository
//@DataJpaTest
public class MyCustomerRepositoryImpl { //} implements MyCustomerRepository {

    @PersistenceContext
    EntityManager em;

    public void customMethod() {
        System.out.println("customMethod");
    }

    @Transactional(rollbackFor = {DuplicateNationalCodeException.class, CustomInvalidInputException.class})
    public TsCustomer save(TsCustomer customer) throws DuplicateNationalCodeException, CustomInvalidInputException {
        //em.merge(customer);
        try {
            em.persist(customer);
        } catch(PersistenceException ex) {
            if(ex.getCause() instanceof org.hibernate.exception.ConstraintViolationException) {
                ((org.hibernate.exception.ConstraintViolationException) ex.getCause()).getConstraintName();//constraintViolationException.getConstraintName();
                throw new DuplicateNationalCodeException("There's a user with this nationalCode.");
            }
            System.out.println(ex.getMessage());
        } catch(ConstraintViolationException ex) {
            List<String> messages = ex.getConstraintViolations().stream().map(cv ->
                    cv.getPropertyPath().toString() + " " + cv.getMessage()
            ).collect(Collectors.toList());
            throw new CustomInvalidInputException("Some parameters are invalid", messages);
        }
        return customer;
    }

    @Transactional
    public TsCustomer update(TsCustomer customer) {
        return em.merge(customer);
    }

    @Transactional(rollbackFor = {CustomerHasDepositException.class, TosanGeneralException.class})
    public int delete(Long cid) throws CustomerHasDepositException, TosanGeneralException {
        try {
            String queryStr = "DELETE from TsCustomer c where c.cid = ?1";
            Query query = em.createQuery(queryStr);
            query.setParameter(1, cid);
            return query.executeUpdate();
        } catch (Exception pex) {
            try {
                org.hibernate.exception.ConstraintViolationException cause =
                        (org.hibernate.exception.ConstraintViolationException) pex.getCause();
                SQLIntegrityConstraintViolationException sqlEx = (SQLIntegrityConstraintViolationException) cause.getSQLException();
                if (sqlEx.getMessage().contains("Cannot delete or update")) {
                    throw new CustomerHasDepositException("Customers who have deposit cannot be deleted");
                }
            } catch(CustomerHasDepositException chde) {
                throw chde;
            } catch(Exception ex) { }
            throw new TosanGeneralException("An error occured, contact the administrator");
        }
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

