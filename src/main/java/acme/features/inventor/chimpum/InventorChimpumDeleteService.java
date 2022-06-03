package acme.features.inventor.chimpum;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.Chimpum;
import acme.framework.components.models.Model;
import acme.framework.controllers.Errors;
import acme.framework.controllers.Request;
import acme.framework.services.AbstractDeleteService;
import acme.roles.Inventor;

@Service
public class InventorChimpumDeleteService implements AbstractDeleteService<Inventor, Chimpum>{

	@Autowired
	protected InventorChimpumRepository inventorChimpumRepository;
	
	@Override
	public boolean authorise(final Request<Chimpum> request) {
		assert request != null;
		return request.getPrincipal().getActiveRoleId()==this.inventorChimpumRepository.findChimpumById(request.getModel().getInteger("id")).getItem().getInventor().getId();
	}

	@Override
	public void bind(final Request<Chimpum> request, final Chimpum entity, final Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;
		
		request.bind(entity, errors, "pattern", "creationMoment", "title", "description", "initialPeriod", "finalPeriod", "budget", "link");
	}

	@Override
	public void unbind(final Request<Chimpum> request, final Chimpum entity, final Model model) {
		assert request != null;
		assert entity != null;
		assert model != null;

		request.unbind(entity, model, "pattern", "creationMoment", "title", "description", "initialPeriod", "finalPeriod", "budget", "link");

	}

	@Override
	public Chimpum findOne(final Request<Chimpum> request) {
		assert request != null;
		return this.inventorChimpumRepository.findChimpumById(request.getModel().getInteger("id"));
	}

	@Override
	public void validate(final Request<Chimpum> request, final Chimpum entity, final Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;
		
	}

	@Override
	public void delete(final Request<Chimpum> request, final Chimpum entity) {
		assert request != null;
		assert entity != null;
	
		this.inventorChimpumRepository.delete(entity);
	}

}
