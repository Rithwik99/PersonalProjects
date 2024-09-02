package ttechlab.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.Data;

@Entity
@Data
public class Employee {

	@Id
	@GeneratedValue
	private Long id;
	private String firstname;
	private String email;
	private String lastname;
	private Long phoneNo;
}
