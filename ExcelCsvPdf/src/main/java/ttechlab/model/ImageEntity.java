package ttechlab.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Lob;
import lombok.Data;

@Entity

public class ImageEntity {
	
	@Id
	@GeneratedValue
	private Long id;
	static String name;
	private String type;
	
	@Lob
	@Column(columnDefinition = "LONGBLOB")
	private byte[] imageData;


}
