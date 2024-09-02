package ttechlab.dao;



import org.springframework.data.jpa.repository.JpaRepository;

import ttechlab.entity.Book;

public interface BookDao extends JpaRepository<Book, Long>{

}
