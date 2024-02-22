package com.tosan;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.Id;


@Entity
@Getter
@Setter
//@Builder
public class Employee {

    @Id
    private int id;
    private String login;
    private String password;

    public Employee() {

    }

    // standard getters and setters
}
