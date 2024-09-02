package ttechlab.bo;

import java.util.List;

import ttechlab.entity.library;

public interface LibraryService {
	
	List<library> getAllLibraries();
	library getLibraryById(Long id);
	library saveLibrary(library library);
	void deleteLibrary(Long id);
}
