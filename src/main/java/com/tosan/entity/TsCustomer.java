package com.tosan.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.NaturalId;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;
import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlTransient;
import javax.xml.bind.annotation.XmlType;

@Entity
@Table(name = "hamid_customer", uniqueConstraints = {
        @UniqueConstraint(name = "national_code_uk", columnNames = {"national_code"}),
        @UniqueConstraint(name = "customer_code_uk", columnNames = {"customer_code"})

})
//@DataCache(enabled = false)
//@DiscriminatorColumn(name="customer_type", discriminatorType = DiscriminatorType.INTEGER)
//@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
//@SequenceGenerator(name = "TsCustomerSequenceGenerator", sequenceName = "CUSTOMERS_SEQ", allocationSize = 1)
@XmlType(name = "tsCustomer", namespace = "http://www.tosan.com/hamid")
@XmlAccessorType(XmlAccessType.FIELD)
@Getter
@Setter
public class TsCustomer implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    //@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "TsCustomerSequenceGenerator")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "cid")
    private Long cid;

    public TsCustomer() {

    }


    @Column(name = "customer_code", nullable = false, unique = true)
    //@Unique
    private String customerCode;

    @NotNull(message = "firstName is required")
    @Size(min = 1, message = "firstName must not be blank")
    @Column(name = "first_name", nullable = false)
    private String firstName;
//    @NotBlank @NotEmpty
    @Size(min = 1, message = "lastName must not be blank")
    @NotNull(message = "lastName is required")
    @Column(name = "last_name", nullable = false)
    private String lastName;
    @Column(name = "father_name")
    private String fatherName;
    @NaturalId
    @NotNull(message = "nationalCode is required")
    @Size(min = 5, max = 5, message = "nationalCode must be exactly 5")
    @Column(name = "national_code", unique = true, nullable = false)
    private String nationalCode;

    //@Size(min = 1)
    @Column(name = "address")
    private String address;
    //@Size(min = 1)
    @Column(name = "phone")
    private String phone;
    //@Email(message = "Email is not in correct format")
    @Column(name = "email")
    private String email;
    //@Size(max = 10, min = 7)
    @Column(name = "postal_code")
    private String postalCode;
    @Column(name = "ACTIVE")
    private Boolean active;
    @NotNull(message = "Specify the customer type, options are: LEGAL or REAL.")
    @Enumerated(EnumType.STRING)
    @Column(name = "customer_type", nullable = false)
    private CustomerType customerType;

    @XmlTransient
    @JsonIgnore
    @OneToMany(mappedBy = "owner") //cascade = {CascadeType.DETACH, CascadeType.MERGE, CascadeType.REFRESH}
    private Set<Deposit> deposits = new HashSet<>();

    public TsCustomer(String firstName, String lastName, String fatherName, String nationalCode, CustomerType customerType) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.fatherName = fatherName;
        this.nationalCode = nationalCode;
        this.address = address;
        this.customerType = customerType;
    }

    // LAZY is default
    //@ElementCollection(fetch = FetchType.LAZY)
    //@CollectionTable(name = "shopping_cart_books",
    //        joinColumns = @JoinColumn(name = "shopping_cart_id"))
    //private List<Address> books = new ArrayList<>();

}
