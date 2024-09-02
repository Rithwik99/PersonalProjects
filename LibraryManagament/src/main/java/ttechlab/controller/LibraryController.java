package ttechlab.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import ttechlab.bo.LibraryService;
import ttechlab.entity.library;

@Controller
public class LibraryController {

	
	@Autowired
	private LibraryService libraryService;
	
	@GetMapping("/get-library")
	public ResponseEntity<List<library>> getAllLibrarian() {
		List<library> libr = libraryService.getAllLibraries();
		return new ResponseEntity<>(libr, HttpStatus.OK);
	}

	@PostMapping("/save-library")
	public ResponseEntity<library> SaveLibrarian(@RequestBody library libr) {
		library savedlibrary = libraryService.saveLibrary(libr);
		return new ResponseEntity<>(savedlibrary, HttpStatus.OK);

	}

	@GetMapping("/get-library/{id}")
	public ResponseEntity<library> getByid(@PathVariable Long id) {
		library librarybyid = libraryService.getLibraryById(id);
		if (librarybyid != null) {
			return new ResponseEntity<>(librarybyid, HttpStatus.OK);
		} else {
			return new ResponseEntity<>(librarybyid, HttpStatus.NOT_FOUND);
		}
	}

	@DeleteMapping("/delete-library/{id}")
	public ResponseEntity<library> deletebookById(@PathVariable Long id) {
		library deleteById = libraryService.getLibraryById(id);
		if (deleteById != null) {
			libraryService.deleteLibrary(id);
			return new ResponseEntity<>(deleteById, HttpStatus.NO_CONTENT);
		} else {
			return new ResponseEntity<>(deleteById, HttpStatus.NOT_FOUND);
		}
	}
}
