package ttechlab.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import ttechlab.entity.PurchaseTransaction;

public interface PurchaseTransactionDao extends JpaRepository<PurchaseTransaction, Long>  {

}
