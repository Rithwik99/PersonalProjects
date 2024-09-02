package ttechlab.bo;

import java.util.List;

import ttechlab.entity.Book;
import ttechlab.entity.Member;
import ttechlab.entity.Reservation;

public interface ReservationService  {
	
	 List<Reservation> getAllReservations();
	 Reservation getReservationById(Long id);
	 Reservation saveReservation(Long mid,Long bid);
	 void deleteReservation(Long id);

}
