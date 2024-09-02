package ttechlab.bo.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ttechlab.bo.BorrowTransactionService;
import ttechlab.dao.BorrowTransactionDao;
import ttechlab.entity.BorrowTransaction;
@Service
public class BorrowTransactionServiceimpl implements BorrowTransactionService{

	@Autowired
	private BorrowTransactionDao borrowTransactioDao;
	@Override
	public List<BorrowTransaction> getAllBorrowTransaction() {
		// TODO Auto-generated method stub
		return borrowTransactioDao.findAll();
	}

	@Override
	public BorrowTransaction getBorrowTransactionById(Long id) {
		// TODO Auto-generated method stub
		return borrowTransactioDao.findById(id).get();
	}

	@Override
	public BorrowTransaction saveBorrowTransaction(BorrowTransaction borrowTransaction) {
		// TODO Auto-generated method stub
		return borrowTransactioDao.save(borrowTransaction);
	}

	@Override
	public void deleteBorrowTransaction(Long id) {
		borrowTransactioDao.deleteById(id);
		
	}

}