package ttechlab.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import ttechlab.bo.BookService;
import ttechlab.bo.MemberService;
import ttechlab.bo.ReservationService;
import ttechlab.entity.Book;
import ttechlab.entity.Member;
import ttechlab.entity.Reservation;

@Controller
public class ReservationController {

	@Autowired
	private ReservationService reservationService;
	@Autowired
	private BookService bookService;
	@Autowired
	private MemberService memberService; 
	
	
	@GetMapping("/get-reservation/{id}")
	public ResponseEntity<Reservation> getReservation(@PathVariable Long id){
		try {
			 Reservation reservation = reservationService.getReservationById(id);
	            return new ResponseEntity<>(reservation, HttpStatus.OK);
			
		}catch (RuntimeException e) {
			return new ResponseEntity<>(null,HttpStatus.NOT_FOUND);
		}
	
				
		}
	
	@PostMapping("/reserve")
	public ResponseEntity<Reservation> getReservation(@RequestParam Long mid,@RequestParam Long bid){
			try {
				Reservation reservation= reservationService.saveReservation(mid, bid);
				return new ResponseEntity<>(reservation,HttpStatus.CREATED);
				
			}catch (RuntimeException e) {
			return new ResponseEntity<>(null,HttpStatus.BAD_REQUEST);
			}
	}
}

