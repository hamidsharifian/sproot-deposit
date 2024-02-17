package com.tosan.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;
import javax.persistence.*;
import javax.validation.constraints.NotBlank;

@Entity
@Table(name = "hamid_customer")
//@DataCache(enabled = false)
//@DiscriminatorColumn(name="customer_type", discriminatorType = DiscriminatorType.INTEGER)
//@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@Getter
@Setter
public class TsCustomer implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    //@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "TsCustomerSequenceGenerator")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "cid")
    private Long cid;

    @Column(name = "customer_code")
    private String customerCode;

    @Column(name = "first_name")
    private String firstName;
    @Column(name = "last_name")
    private String lastName;
    @Column(name = "father_name")
    private String fatherName;
    @Column(name = "national_code")
    private String nationalCode;

    //@Size(min = 1)
    @Column(name = "address")
    private String address;
    //@Size(min = 1)
    @Column(name = "phone")
    private String phone;
    //@Email
    @Column(name = "email")
    private String email;
    //@Size(max = 10, min = 7)
    @Column(name = "postal_code")
    private String postalCode;
    @Column(name = "ACTIVE")
    private Boolean active;
    @Enumerated(EnumType.STRING)
    @Column(name = "customer_type")
    private CustomerType customerType;

    @JsonIgnore
    @OneToMany(mappedBy = "owner")
    private Set<Deposit> deposits = new HashSet<>();

    // LAZY is default
    //@ElementCollection(fetch = FetchType.LAZY)
    //@CollectionTable(name = "shopping_cart_books",
    //        joinColumns = @JoinColumn(name = "shopping_cart_id"))
    //private List<Address> books = new ArrayList<>();

}
