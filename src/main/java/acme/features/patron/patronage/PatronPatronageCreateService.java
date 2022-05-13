package acme.features.patron.patronage;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.Patronage;
import acme.entities.Status;
import acme.features.inventor.item.InventorItemRepository;
import acme.framework.components.models.Model;
import acme.framework.controllers.Errors;
import acme.framework.controllers.Request;
import acme.framework.services.AbstractCreateService;
import acme.roles.Patron;

@Service
public class PatronPatronageCreateService implements AbstractCreateService<Patron, Patronage>{

	@Autowired
	protected InventorItemRepository inventorItemRepository;
	@Autowired
	protected PatronPatronageRepository patronPatronageRepository;

	@Override
	public boolean authorise(final Request<Patronage> request) {
		assert request != null;
		return true;
	}

	@Override
	public void bind(final Request<Patronage> request, final Patronage entity, final Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;
		
		request.bind(entity, errors, "status", "code", "legalStuff", "budget", "creationDate", "startDate", "deadline", "info");
	}

	@Override
	public void unbind(final Request<Patronage> request, final Patronage entity, final Model model) {
		assert request != null;
		assert entity != null;
		assert model != null;
		
		request.unbind(entity, model, "status", "code", "legalStuff", "budget", "creationDate", "startDate", "deadline", "info");
		model.setAttribute("inventors", this.inventorItemRepository.findAllInventors());
	}

	@Override
	public Patronage instantiate(final Request<Patronage> request) {
		
		final Patronage result = new Patronage();
		
		result.setStatus(Status.PROPOSED);
		result.setCode("");
		result.setLegalStuff("");
		result.setCreationDate(new Date());
		result.setInfo("");
		result.setPatron(this.patronPatronageRepository.findOnePatronById(request.getPrincipal().getActiveRoleId()));

		
		return result;
	}

	@Override
	public void validate(final Request<Patronage> request, final Patronage entity, final Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;
		System.out.println("validate1");
		if(!errors.hasErrors("code")) {
			final Patronage i = this.patronPatronageRepository.findOnePatronageByCode((entity.getCode()));
			System.out.println("asdasd");
			errors.state(request, i == null || i.getId()==entity.getId(),"code", "inventor.item.form.error.code.duplicated");
		}
		System.out.println("validate2");
	}

	@Override
	public void create(final Request<Patronage> request, final Patronage entity) {
		System.out.println("create0");
		assert request != null;
		assert entity != null;
		
		System.out.println("create1");
		entity.setPublished(false);
//		entity.setInventor(this.inventorItemRepository.findInventorById(8));
		entity.setPatron(this.patronPatronageRepository.findOnePatronById(request.getPrincipal().getActiveRoleId()));
		
//		entity.setInventor(this.patronPatronageRepository);
		
		this.patronPatronageRepository.save(entity);
	}
	
	

}
