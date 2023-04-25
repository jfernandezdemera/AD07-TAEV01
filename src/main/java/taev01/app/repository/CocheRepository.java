package taev01.app.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import taev01.app.domain.Coche;

public interface CocheRepository extends JpaRepository<Coche, Integer> {

	List<Coche> findByMarca(String marca);	
}
