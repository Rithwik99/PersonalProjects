package ttechlab.bo.impl;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.function.Function;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.repository.query.FluentQuery.FetchableFluentQuery;
import org.springframework.stereotype.Service;

import ttechlab.bo.BookService;
import ttechlab.bo.ReservationService;
import ttechlab.dao.BookDao;
import ttechlab.dao.MemberDao;
import ttechlab.dao.ReservationDao;
import ttechlab.entity.Book;
import ttechlab.entity.Member;
import ttechlab.entity.Member;
import ttechlab.entity.Reservation;
@Service
public class ReservationServiceImpl implements ReservationService {

	@Autowired
	private ReservationDao reservationDao;
	@Autowired
	private BookDao bookDao;
	@Autowired
	private MemberDao memberdao;
	@Override
	public List<Reservation> getAllReservations() {
		// TODO Auto-generated method stub
		return reservationDao.findAll();
	}

	@Override
	public Reservation getReservationById(Long id) {
		// TODO Auto-generated method stub
		return reservationDao.findById(id).get();
	}


	

	@Override
	public void deleteReservation(Long id) {
		reservationDao.deleteById(id);
		
	}

	
	public Reservation saveReservation(Long mid, Long bid) {
			
			Member member=memberdao.findById(mid).orElseThrow(()->new RuntimeException("member not foud"));
			Book book=bookDao.findById(bid).orElseThrow(()->new RuntimeException("Bookk not found"));
			Reservation reservation=new Reservation();
			reservation.setMember(member);
			reservation.setBook(book);
			reservation.setReservationDate(LocalDate.now());
			reservation.setStatus("Reserved");
			book.setAvailable(false);
			reservationDao.save(reservation);
			bookDao.save(book);
			
			return reservation;
	}

	
	
	

}