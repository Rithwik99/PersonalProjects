package ttechlab.bo;

import java.util.List;

import ttechlab.entity.BorrowTransaction;

public interface BorrowTransactionService {
	List<BorrowTransaction> getAllBorrowTransaction();
	BorrowTransaction getBorrowTransactionById(Long id);
	BorrowTransaction saveBorrowTransaction(BorrowTransaction borrowTransaction);
	void deleteBorrowTransaction(Long id);

}
