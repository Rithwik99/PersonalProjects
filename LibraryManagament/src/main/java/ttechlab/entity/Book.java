package ttechlab.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;

@Entity
@Data
public class Book {
	
	 	@Id
	    @GeneratedValue(strategy = GenerationType.IDENTITY)
	    private Long id;
	 	
	    private String isbn;
	    private String title;
	    private String author;
	    private String publisher;
	    private int yearOfPublication;
	    private String edition;
	    private String genre;
	    private String shelfLocation;
	    private boolean isAvailable;

}
