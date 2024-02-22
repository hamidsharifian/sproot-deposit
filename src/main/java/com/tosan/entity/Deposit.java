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
    @Column(name = "deposit_number", unique = true)
    private String depositNumber;
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
    @Column(name = "owner_id")
    private Long ownerId;

    @NotNull
    @ManyToOne(cascade = {CascadeType.DETACH, CascadeType.MERGE, CascadeType.REFRESH})
    @JoinColumn(name="owner_id", referencedColumnName = "cid", updatable = false, insertable = false)
    private TsCustomer owner;

    public void setOwnerId(Long ownerId) {
        if(ownerId == null) {
            this.ownerId = null;
        }
        this.ownerId = ownerId;
    }

    public void setOwner(TsCustomer owner) {
        if(owner != null) {
            this.ownerId = owner.getCid();
        }
        this.owner = owner;
    }

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
