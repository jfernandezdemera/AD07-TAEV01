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

import taev01.app.domain.Coche;
import taev01.app.repository.CocheRepository;

@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
@RequestMapping("api/coches")
public class CocheController {

	@Autowired
	CocheRepository cocheRepository;

	// LISTADO
	@GetMapping({ "/", "" })
	@ResponseStatus(HttpStatus.OK)
	public List<Coche> index() {
		return cocheRepository.findAll();
	}

	// CONSULTAS
	@GetMapping("/id={id}")
	public ResponseEntity<Coche> getId(@PathVariable("id") Integer id) {
		Coche coche = cocheRepository.findById(id).orElse(null);
		if (coche == null) {
			return new ResponseEntity<Coche>(HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<Coche>(coche, HttpStatus.OK);
	}

	@GetMapping("/marca={marca}")
	public ResponseEntity<List<Coche>> getMarca(@PathVariable("marca") String marca) {
		List<Coche> coches = cocheRepository.findByMarca(marca);
		if (coches.size() == 0) {
			return new ResponseEntity<List<Coche>>(HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<List<Coche>>(coches, HttpStatus.OK);
	}

	// CREACION - ACTUALIZACION
	@PostMapping({ "/", "" })
	@ResponseStatus(HttpStatus.CREATED)
	public Coche create(@RequestBody Coche coche) {
		return cocheRepository.save(coche);
	}

	// ACTUALIZACION
	@PutMapping("/{id}")
	@ResponseStatus(HttpStatus.CREATED)
	public Coche update(@RequestBody Coche coche, @PathVariable("id") Integer id) {
		Coche tempCoche = cocheRepository.findById(id).orElse(null);

		tempCoche.setColor(coche.getColor());
		tempCoche.setMarca(coche.getMarca());
		tempCoche.setMatricula(coche.getMatricula());
		tempCoche.setModelo(coche.getModelo());
		tempCoche.setPrecio(coche.getPrecio());
		tempCoche.setRevisiones(coche.getRevisiones());

		return cocheRepository.save(tempCoche);
	}

	// ELIMINACION
	@DeleteMapping("/{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void delete(@PathVariable("id") Integer id) {
		cocheRepository.deleteById(id);
	}
}
