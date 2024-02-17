package com.tosan.repository;

import com.tosan.entity.Deposit;
import com.tosan.entity.DepositStatus;
import com.tosan.entity.TransactionType;
import com.tosan.entity.TsTransaction;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.Date;
import java.util.List;
import java.util.Random;

@Repository
public class MyDepositRepositoryImpl {

    @PersistenceContext
    EntityManager em;
    @Transactional(readOnly = true)
    public List<Deposit> findDeposits(Long customerId) {
        String queryStr = "SELECT c from Deposit c where c.owner.cid = :owner";
        Query query = em.createQuery(queryStr, Deposit.class);
        query.setParameter("owner", customerId);
        return query.getResultList();
    }

    @Transactional
    public Deposit saveDeposit(Deposit deposit) {
        em.persist(deposit);
        return deposit;
    }

    @Transactional
    public Integer delete(Long depositId) {
        String queryStr = "DELETE from Deposit d where d.did = :did";
        Query query = em.createQuery(queryStr);
        query.setParameter("did", depositId);
        return query.executeUpdate();
    }

    @Transactional
    public void activate(Long customerId) {
        String queryStr = "UPDATE TsCustomer c set c.active = true where c.cid = :cid";
        Query query = em.createQuery(queryStr);
        query.setParameter("cid", customerId);
        query.executeUpdate();
    }

    @Transactional
    public void deactivate(Long customerId) {
        String queryStr = "UPDATE TsCustomer c set c.active = false where c.cid = :cid";
        Query query = em.createQuery(queryStr);
        query.setParameter("cid", customerId);
        query.executeUpdate();
    }

    @Transactional
    public Long viewBalance(Long depositId) {
        String queryStr = "SELECT d.balance from Deposit d where d.did = :did";
        Query query = em.createQuery(queryStr, Long.class);
        query.setParameter("did", depositId);
        try {
            return (Long) query.getSingleResult();
        } catch(Exception e) {
            return null;
        }
    }

    @Transactional
    public List<TsTransaction> findTransactions(Long depositId) {
        String queryStr = "SELECT t from TsTransaction t where t.source.did = :did or t.destination = :did ";
        Query query = em.createQuery(queryStr, TsTransaction.class);
        query.setParameter("did", depositId);
        return (List<TsTransaction>) query.getResultList();
    }

    @Transactional
    public TsTransaction withraw(Long depositId, Long amount) {
        TsTransaction tsTransaction = new TsTransaction();
        Deposit deposit = em.find(Deposit.class, depositId);
        if(deposit.getDepositStatus() != DepositStatus.OPEN && deposit.getDepositStatus() != DepositStatus.DEPOSIT_BLOCKED) {
            tsTransaction.setSucceed(false);
            tsTransaction.setDescription("Deposit is blocked!");
        } else if(deposit.getBalance() < amount) {
            tsTransaction.setSucceed(false);
            tsTransaction.setDescription("Deposit's balance is insufficient!");
        } else {
            String queryStr = "update Deposit d set d.balance = d.balance - :amount where d.did = :did ";
            //" and d.balance >= :amount and (d.depositStatus = :open_status or d.depositStatus = :withraw_blocked_status)";
            Query query = em.createQuery(queryStr);
            query.setParameter("did", depositId);
            query.setParameter("amount", amount);
            int count = query.executeUpdate();
            tsTransaction.setSucceed(count > 0);
            tsTransaction.setDescription(count > 0 ? "transaction succeeded!": "transaction failed!");
        }

        tsTransaction.setSourceId(depositId);
        tsTransaction.setAmount(amount);
        tsTransaction.setTransactionType(TransactionType.WITHRAW);
        tsTransaction.setTxdate(new Date());
        tsTransaction.setTrackingCode(String.valueOf(new Random(depositId).nextLong()));
        em.persist(tsTransaction);
        return tsTransaction;
    }

    @Transactional
    public TsTransaction deposit(Long depositId, Long amount) {
        TsTransaction tsTransaction = new TsTransaction();
        Deposit deposit = em.find(Deposit.class, depositId);
        if(deposit.getDepositStatus() != DepositStatus.OPEN && deposit.getDepositStatus() != DepositStatus.WITHRAW_BLOCKED) {
            tsTransaction.setSucceed(false);
            tsTransaction.setDescription("Deposit is blocked!");
        } else {
            String queryStr = "update Deposit d set d.balance = d.balance + :amount where d.did = :did ";
            //" and d.balance >= :amount and (d.depositStatus = :open_status or d.depositStatus = :withraw_blocked_status)";
            Query query = em.createQuery(queryStr);
            query.setParameter("did", depositId);
            query.setParameter("amount", amount);
            int count = query.executeUpdate();
            tsTransaction.setSucceed(count > 0);
            tsTransaction.setDescription(count > 0 ? "transaction succeeded!": "transaction failed!");
        }

        tsTransaction.setSourceId(depositId);
        tsTransaction.setAmount(amount);
        tsTransaction.setTransactionType(TransactionType.DEPOSIT);
        tsTransaction.setTxdate(new Date());
        tsTransaction.setTrackingCode(String.valueOf(new Random(depositId).nextLong()));
        em.persist(tsTransaction);
        return tsTransaction;
    }

    @Transactional
    public TsTransaction transfer(Long sourceDepositId, Long destinationDepositId, Long amount) {
        TsTransaction tsTransaction = new TsTransaction();
        Deposit sourceDeposit = em.find(Deposit.class, sourceDepositId);
        Deposit destinationDeposit = em.find(Deposit.class, destinationDepositId);
        if(sourceDeposit.getDepositStatus() != DepositStatus.OPEN && sourceDeposit.getDepositStatus() != DepositStatus.DEPOSIT_BLOCKED) {
            tsTransaction.setSucceed(false);
            tsTransaction.setDescription("Source deposit is blocked!");
        } else if(destinationDeposit.getDepositStatus() != DepositStatus.OPEN && destinationDeposit.getDepositStatus() != DepositStatus.WITHRAW_BLOCKED) {
            tsTransaction.setSucceed(false);
            tsTransaction.setDescription("Destination deposit is blocked!");
        } else if(sourceDeposit.getBalance() < amount) {
            tsTransaction.setSucceed(false);
            tsTransaction.setDescription("Source deposit's balance is insufficient!");
        } else {
            String withrawQueryStr = "update Deposit d set d.balance = d.balance - :amount where d.did = :sourceDid ";
            Query withrawQuery = em.createQuery(withrawQueryStr);
            withrawQuery.setParameter("sourceDid", sourceDepositId);
            withrawQuery.setParameter("amount", amount);
            int withrawCount = withrawQuery.executeUpdate();

            String depositQueryStr = "update Deposit d set d.balance = d.balance + :amount where d.did = :destinationDid ";
            Query depositQuery = em.createQuery(depositQueryStr);
            depositQuery.setParameter("destinationDid", destinationDepositId);
            depositQuery.setParameter("amount", amount);
            int depositCount = depositQuery.executeUpdate();

            boolean wholeSucceed = depositCount == 1 && withrawCount == 1;

            tsTransaction.setSucceed(wholeSucceed);
            tsTransaction.setDescription(wholeSucceed ? "transaction succeeded!": "transaction failed!");
        }

        tsTransaction.setSourceId(sourceDepositId);
        tsTransaction.setDestinationId(destinationDepositId);
        tsTransaction.setAmount(amount);
        tsTransaction.setTransactionType(TransactionType.TRANSFER);
        tsTransaction.setTxdate(new Date());
        tsTransaction.setTrackingCode(String.valueOf(new Random(sourceDepositId+destinationDepositId).nextLong()));
        em.persist(tsTransaction);
        return tsTransaction;
    }
}
