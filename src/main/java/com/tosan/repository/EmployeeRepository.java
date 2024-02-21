package com.tosan.repository;

import com.tosan.Employee;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Repository
public class EmployeeRepository {
    @PersistenceContext
    EntityManager em;


    @Transactional
    public Employee save(Employee employee) {
        em.persist(employee);
        return employee;
    }

    public void setEm(EntityManager em) {
        this.em = em;
    }
}