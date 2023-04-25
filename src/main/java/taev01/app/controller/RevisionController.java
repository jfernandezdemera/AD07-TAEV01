package taev01.app.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import taev01.app.domain.Revision;
import taev01.app.repository.RevisionRepository;

@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
@RequestMapping("api/revisiones")
public class RevisionController {

	@Autowired
	RevisionRepository revisionRepository;

	// LISTADO
	@GetMapping({ "/", "" })
	@ResponseStatus(HttpStatus.OK)
	public List<Revision> index() {
		return revisionRepository.findAll();
	}

	// CONSULTAS
	@GetMapping("/id={id}")
	public ResponseEntity<Revision> getId(@PathVariable("id") Integer id) {
		Revision revision = revisionRepository.findById(id).orElse(null);
		if (revision == null) {
			return new ResponseEntity<Revision>(HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<Revision>(revision, HttpStatus.OK);
	}

	@GetMapping("/cocheid={cocheid}")
	public ResponseEntity<List<Revision>> getMarca(@PathVariable("cocheid") Integer cocheid) {
		List<Revision> revisiones = revisionRepository.findByCocheId(cocheid);
		if (revisiones.size() == 0) {
			return new ResponseEntity<List<Revision>>(HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<List<Revision>>(revisiones, HttpStatus.OK);
	}

	// CREACION - ACTUALIZACION
	@PostMapping({ "/", "" })
	@ResponseStatus(HttpStatus.CREATED)
	public Revision create(@RequestBody Revision revision) {
		return revisionRepository.save(revision);
	}

	// ACTUALIZACION
	@PutMapping("/{id}")
	@ResponseStatus(HttpStatus.CREATED)
	public Revision update(@RequestBody Revision revision, @PathVariable("id") Integer id) {
		Revision tempRevision = revisionRepository.findById(id).orElse(null);

		tempRevision.setAceite(revision.getAceite());
		tempRevision.setFiltro(revision.getFiltro());
		tempRevision.setFrenos(revision.getFrenos());
		tempRevision.setCoche(revision.getCoche());

		return revisionRepository.save(tempRevision);
	}

	// ELIMINACION
	@DeleteMapping("/{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void delete(@PathVariable("id") Integer id) {
		revisionRepository.deleteById(id);
	}
}
