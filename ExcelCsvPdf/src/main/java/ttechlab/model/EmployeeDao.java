package ttechlab.model;

import org.springframework.data.jpa.repository.JpaRepository;

public interface EmployeeDao extends JpaRepository<Employee, Long> {

	

	boolean existsByEmail(String email);
	

	

	

}
