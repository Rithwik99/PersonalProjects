package ttechlab.bo;

import java.util.List;

import ttechlab.entity.Librarian;

public interface LibrarianService {

	
	List<Librarian> getAllLibrarians();
	Librarian getLibrarianById(Long id);
	Librarian saveLibrarian(Librarian librarian);
	void deleteLibrarian(Long id);
}
