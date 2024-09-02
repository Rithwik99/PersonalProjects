package ttechlab.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import ttechlab.entity.Reservation;

public interface ReservationDao extends JpaRepository<Reservation, Long>{

}
