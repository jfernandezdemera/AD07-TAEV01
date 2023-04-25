package taev01.app.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import taev01.app.domain.Revision;

public interface RevisionRepository extends JpaRepository<Revision, Integer> {

	List<Revision> findByCocheId(Integer coche_id);
}
