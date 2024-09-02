package ttechlab.bo;

import java.util.List;

import ttechlab.entity.Book;

public interface BookService {
	
	List<Book> getAllBooks();
	Book getBookById(Long id);
	 Book saveBook(Book book);
	 void deleteBook(Long id);

}
