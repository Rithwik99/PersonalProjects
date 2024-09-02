package ttechlab.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import ttechlab.entity.BorrowTransaction;

public interface BorrowTransactionDao extends JpaRepository<BorrowTransaction, Long> {

}
