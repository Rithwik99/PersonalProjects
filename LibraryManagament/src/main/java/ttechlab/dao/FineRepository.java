package ttechlab.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import ttechlab.entity.Fine;

public interface FineRepository extends JpaRepository<Fine, Long> {

}
