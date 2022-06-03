package acme.features.inventor.chimpum;

import java.util.Date;

import org.apache.commons.lang3.time.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.Chimpum;
import acme.framework.components.models.Model;
import acme.framework.controllers.Errors;
import acme.framework.controllers.Request;
import acme.framework.services.AbstractCreateService;
import acme.roles.Inventor;

@Service
public class InventorChimpumCreateService implements AbstractCreateService<Inventor, Chimpum>{
	
	@Autowired InventorChimpumRepository inventorChimpumRepository;

	@Override
	public boolean authorise(final Request<Chimpum> request) {
		assert request != null;
		return true;

	}

	@Override
	public void bind(final Request<Chimpum> request, final Chimpum entity, final Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;
		entity.setItem(this.inventorChimpumRepository.findItemByCode(request.getModel().getAttribute("itemCode").toString()));
		request.bind(entity, errors, "pattern", "creationMoment", "title", "description", "initialPeriod", "finalPeriod", "budget", "link");
		
	}

	@Override
	public void unbind(final Request<Chimpum> request, final Chimpum entity, final Model model) {
		assert request != null;
		assert entity != null;
		assert model != null;
		model.setAttribute("itemCode", this.inventorChimpumRepository.findItemById(request.getModel().getInteger("id")).getCode());	
		request.unbind(entity, model, "pattern", "creationMoment", "title", "description", "initialPeriod", "finalPeriod", "budget", "link");
		
	}

	@Override
	public Chimpum instantiate(final Request<Chimpum> request) {

		final Chimpum result = new Chimpum();
		final Date now = new Date();
		result.setCreationMoment(now);
		result.setPattern("");
		result.setTitle("");
		result.setDescription("");
		return result;
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
	public void create(final Request<Chimpum> request, final Chimpum entity) {
		assert request != null;
		assert entity != null;
		this.inventorChimpumRepository.save(entity);
		
	}

}
