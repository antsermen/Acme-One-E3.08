package acme.features.inventor.chimpum;

import java.util.Date;

import org.apache.commons.lang3.time.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.Chimpum;
import acme.framework.components.models.Model;
import acme.framework.controllers.Errors;
import acme.framework.controllers.Request;
import acme.framework.services.AbstractUpdateService;
import acme.roles.Inventor;

@Service
public class InventorChimpumUpdateService implements AbstractUpdateService<Inventor, Chimpum> {
	
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

		model.setAttribute("itemCode", entity.getItem().getCode());
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
		if(!errors.hasErrors("initialPeriod")) {
			final Date minStartDate = DateUtils.addMonths(entity.getCreationMoment(), 1);
			errors.state(request, entity.getInitialPeriod().after(minStartDate), "initialPeriod", "inventor.chimpum.form.error.initialPeriod");
		}
		if(!errors.hasErrors("finalPeriod")) {
			final Date minEndDate = DateUtils.addWeeks(entity.getInitialPeriod(), 1);
			errors.state(request, entity.getFinalPeriod().after(minEndDate), "finalPeriod", "inventor.chimpum.form.error.finalPeriod");
		}
		if(!errors.hasErrors("budget")) {
			errors.state(request, entity.getBudget().getAmount()>0, "budget", "inventor.chimpum.form.error.budget.negative");
		}
	}

	@Override
	public void update(final Request<Chimpum> request, final Chimpum entity) {
		assert request != null;
		assert entity != null;
		this.inventorChimpumRepository.save(entity);
	}
	
}
