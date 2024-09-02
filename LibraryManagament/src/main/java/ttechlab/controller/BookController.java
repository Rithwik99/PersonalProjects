package ttechlab.controller;

import java.util.List;

import org.apache.coyote.http11.Http11InputBuffer;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import ttechlab.bo.BookService;
import ttechlab.entity.Book;

@RestController
@CrossOrigin(origins = "http://localhost:3001")
public class BookController {

	private BookService bookService;
	
	
	@GetMapping("/get-books")
	public ResponseEntity<List<Book>>getAllBooks(){
		List<Book>books=bookService.getAllBooks();
		return new ResponseEntity<>(books,HttpStatus.OK);
	}
	
	@PostMapping("/save-books")
	public ResponseEntity<Book>SaveBooks(@RequestBody Book book) {
		System.out.println("# book"+book.toString());
		Book savedBook= bookService.saveBook(book);
		return new ResponseEntity<>(savedBook,HttpStatus.OK);
		
	}
	
	@GetMapping("/get-books/{id}")
	public ResponseEntity<Book> getByid(@PathVariable Long id){
		Book booksbyid=bookService.getBookById(id);
		if(booksbyid !=null) {
			return new ResponseEntity<>(booksbyid,HttpStatus.OK);
		}else {
				return new ResponseEntity<>(booksbyid,HttpStatus.NOT_FOUND);
			}
		}
	@DeleteMapping("/delete-books/{id}")
	public ResponseEntity<Book> deletebookById(@PathVariable Long id){
		Book deleteById=bookService.getBookById(id);
		if(deleteById !=null) {
			bookService.deleteBook(id);
			return new ResponseEntity<>(deleteById,HttpStatus.NO_CONTENT);
		}else {
			return new ResponseEntity<>(deleteById,HttpStatus.NOT_FOUND);
		}
	}
}
