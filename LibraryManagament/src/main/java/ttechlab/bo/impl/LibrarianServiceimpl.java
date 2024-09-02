package ttechlab.bo.impl;

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

import ttechlab.bo.LibrarianService;
import ttechlab.dao.LibrarianDao;
import ttechlab.entity.Librarian;
@Service
public class LibrarianServiceimpl implements LibrarianService{

	
	@Autowired
	private LibrarianDao librarianDao; 
	@Override
	public List<Librarian> getAllLibrarians() {
		// TODO Auto-generated method stub
		return librarianDao.findAll();
	}

	@Override
	public Librarian getLibrarianById(Long id) {
		// TODO Auto-generated method stub
		return librarianDao.findById(id).get();
	}

	@Override
	public Librarian saveLibrarian(Librarian librarian) {
		// TODO Auto-generated method stub
		return librarianDao.save(librarian);
	}

	@Override
	public void deleteLibrarian(Long id) {
		librarianDao.deleteById(id);
		
	}

}