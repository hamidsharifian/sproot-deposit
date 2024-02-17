package com.tosan.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.PositiveOrZero;
import java.io.Serializable;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "hamid_transaction")
//@DataCache(enabled = false)
//@DiscriminatorColumn(name="customer_type", discriminatorType = DiscriminatorType.INTEGER)
//@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@Getter
@Setter
public class TsTransaction implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    //@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "TsCustomerSequenceGenerator")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "cid")
    private Long cid;

    @Column(name = "txdate")
    private Date txdate;
    @Column(name = "amount")
    private Long amount;
    @Column(name = "succeed")
    private Boolean succeed;
    @Column(name = "tracking_code")
    private String TrackingCode;

    @Column(name = "description")
    private String description;

    @Enumerated(EnumType.STRING)
    @Column(name = "transaction_type")
    private TransactionType transactionType;

    @Column(name = "source_id")
    private Long sourceId;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "source_id", insertable = false, updatable = false, referencedColumnName = "did")
    private Deposit source;

    @Column(name = "destination_id")
    private Long destinationId;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "destination_id", insertable = false, updatable = false, referencedColumnName = "did")
    private Deposit destination;

    public void setSourceId(Long sourceId) {
        if(sourceId == null) {
            this.source = null;
        }
        this.sourceId = sourceId;
    }

    public void setSource(Deposit source) {
        if(source != null) {
            this.sourceId = source.getDid();
        }
        this.source = source;
    }

    public void setDestinationId(Long destinationId) {
        if(destinationId == null) {
            this.destination = null;
        }
        this.destinationId = destinationId;
    }

    public void setDestination(Deposit destination) {
        if(destination != null) {
            this.destinationId = destination.getDid();
        }
        this.destination = destination;
    }
}
