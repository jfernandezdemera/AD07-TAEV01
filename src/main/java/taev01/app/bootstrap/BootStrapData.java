
package taev01.app.bootstrap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import jakarta.transaction.Transactional;
import taev01.app.domain.Coche;
import taev01.app.domain.Revision;
import taev01.app.repository.CocheRepository;
import taev01.app.repository.RevisionRepository;

@Component
public class BootStrapData implements CommandLineRunner {

	@Autowired
	CocheRepository cocheRepository;

	@Autowired
	RevisionRepository revisionRepository;

	@Transactional

	@Override
	public void run(String... args) throws Exception {

		Coche coche = new Coche();
		coche.setColor("blanco");
		coche.setMarca("Renault");
		coche.setModelo("Laguna");
		coche.setPrecio(30.000);
		coche.setMatricula("1234-MMM");

		Revision revision = new Revision();
		revision.setAceite(1);
		revision.setFiltro(1);
		revision.setFrenos(1);
		revision.setCoche(coche);

		cocheRepository.save(coche);
		revisionRepository.save(revision);
		
		Coche coche1 = new Coche();
		coche1.setColor("marron");
		coche1.setMarca("Fiat");
		coche1.setModelo("Tipo");
		coche1.setPrecio(15.000);
		coche1.setMatricula("1111-BBB");

		Revision revision1 = new Revision();
		revision1.setAceite(0);
		revision1.setFiltro(1);
		revision1.setFrenos(0);
		revision1.setCoche(coche1);

		cocheRepository.save(coche1);
		revisionRepository.save(revision1);
		

	}
}
