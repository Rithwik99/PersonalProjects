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

import ttechlab.bo.LibraryService;
import ttechlab.dao.LibrarianDao;
import ttechlab.dao.Librarydao;
import ttechlab.entity.library;
@Service
public class LibraryServiceimpl implements LibraryService{

	@Autowired
	private Librarydao librarydao;
	@Override
	public List<library> getAllLibraries() {
		// TODO Auto-generated method stub
		return librarydao.findAll();
	}

	@Override
	public library getLibraryById(Long id) {
		// TODO Auto-generated method stub
		return librarydao.findById(id).get();
	}

	@Override
	public library saveLibrary(library library) {
		// TODO Auto-generated method stub
		return librarydao.save(library);
	}

	@Override
	public void deleteLibrary(Long id) {
		librarydao.deleteById(id);
		
	}

}