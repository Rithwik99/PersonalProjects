//package ttechlab.bo.impl;
//
//import java.util.List;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Service;
//
//import ttechlab.bo.ReturnTransactionService;
//import ttechlab.dao.ReturnTransactiondao;
//import ttechlab.entity.ReturnTransaction;
//@Service
//public class ReturnTransactionimpl implements ReturnTransactionService{
//
//	@Autowired
//	private ReturnTransactiondao returnTransactiondao;
//	
//	@Override
//	public List<ReturnTransaction> getAllReservations() {
//		// TODO Auto-generated method stub
//		return returnTransactiondao.findAll();
//	}
//
//	@Override
//	public ReturnTransaction getReturnTransactionById(Long id) {
//		// TODO Auto-generated method stub
//		return returnTransactiondao.findById(id).get();
//	}
//
//	@Override
//	public ReturnTransaction saveReturnTransaction(ReturnTransaction returnTransaction) {
//		// TODO Auto-generated method stub
//		return returnTransactiondao.save(returnTransaction);
//	}
//
//	@Override
//	public void deleteReturnTransaction(Long id) {
//		returnTransactiondao.deleteById(id);
//		
//	}
//
//}
