package ttechlab.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import ttechlab.bo.LibrarianService;
import ttechlab.entity.Librarian;

@Controller
public class LibrarianController {

	private LibrarianService librarianService;

	@GetMapping("/get-librarian")
	public ResponseEntity<List<Librarian>> getAllLibrarian() {
		List<Librarian> librarian = librarianService.getAllLibrarians();
		return new ResponseEntity<>(librarian, HttpStatus.OK);
	}

	@PostMapping("/save-librarian")
	public ResponseEntity<Librarian> SaveLibrarian(@RequestBody Librarian librarian) {
		Librarian savedlibrarian = librarianService.saveLibrarian(librarian);
		return new ResponseEntity<>(savedlibrarian, HttpStatus.OK);

	}

	@GetMapping("/get-librarian/{id}")
	public ResponseEntity<Librarian> getByid(@PathVariable Long id) {
		Librarian Librarianbyid = librarianService.getLibrarianById(id);
		if (Librarianbyid != null) {
			return new ResponseEntity<>(Librarianbyid, HttpStatus.OK);
		} else {
			return new ResponseEntity<>(Librarianbyid, HttpStatus.NOT_FOUND);
		}
	}

	@DeleteMapping("/delete-librarian/{id}")
	public ResponseEntity<Librarian> deletebookById(@PathVariable Long id) {
		Librarian deleteById = librarianService.getLibrarianById(id);
		if (deleteById != null) {
			librarianService.deleteLibrarian(id);
			return new ResponseEntity<>(deleteById, HttpStatus.NO_CONTENT);
		} else {
			return new ResponseEntity<>(deleteById, HttpStatus.NOT_FOUND);
		}
	}
}
