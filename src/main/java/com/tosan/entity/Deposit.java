package com.tosan.entity;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.ColumnDefault;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;
import java.io.Serializable;
import java.util.Date;

@Getter
@Setter
@XmlType(name = "deposit", namespace = "http://www.tosan.com/hamid")
@XmlAccessorType(XmlAccessType.FIELD)
@Entity
@Table(name = "hamid_deposit")
public class Deposit implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "did")
    private Long did;

    @ColumnDefault(value = "0")
    private Long balance;
    @Column(name = "deposit_number")
    private Long depositNumber;
    private String currency;
    @Enumerated(EnumType.STRING)
    @Column(name = "deposit_type")
    private DepositType depositType;
    @Enumerated(EnumType.STRING)
    @Column(name = "deposit_status")
    private DepositStatus depositStatus;
    @Column(name = "start_date")
    private Date startDate;
    @Column(name = "end_date")
    private Date endDate;

    @NotNull
    @ManyToOne
    @JoinColumn(name="owner_id", referencedColumnName = "cid")
    private TsCustomer owner;

    @Override
    public boolean equals(Object obj) {
               
        if (this == obj) {
            return true;
        }
        
        if (obj == null) {
            return false;
        }
        
        if (getClass() != obj.getClass()) {
            return false;
        }
        
        return did != null && did.equals(((Deposit) obj).did);
    }

    @Override
    public int hashCode() {
        return 2021;
    }

    @Override
    public String toString() {
        return "Deposit{" + "id=" + did + ", balance=" + balance +  '}';
    }    
}
