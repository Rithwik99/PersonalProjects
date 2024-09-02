package ttechlab.bo.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ttechlab.bo.BookService;
import ttechlab.dao.BookDao;
import ttechlab.entity.Book;

@Service
public class BookServiceImpl implements BookService{
	
	@Autowired
	private BookDao bookDao;

	@Override
	public List<Book> getAllBooks() {
		return bookDao.findAll();
	}

	@Override
	public Book getBookById(Long id) {
		return bookDao.findById(id).get();
	}

	@Override
	public Book saveBook(Book book) {
		return bookDao.save(book);
	}

	@Override
	public void deleteBook(Long id) {
		bookDao.deleteById(id);
		
	}

}
