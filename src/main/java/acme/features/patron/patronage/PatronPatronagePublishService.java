package acme.features.patron.patronage;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.commons.lang3.time.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import SpamDetector.Spam_Detector.SpamDetector;
import acme.entities.Patronage;
import acme.features.inventor.item.InventorItemRepository;
import acme.framework.components.models.Model;
import acme.framework.controllers.Errors;
import acme.framework.controllers.Request;
import acme.framework.services.AbstractUpdateService;
import acme.roles.Patron;

@Service
public class PatronPatronagePublishService implements AbstractUpdateService<Patron, Patronage>{

	@Autowired protected PatronPatronageRepository patronPatronageRepository;
	@Autowired protected InventorItemRepository inventorItemRepository;
	
	@Override
	public boolean authorise(final Request<Patronage> request) {
		assert request != null;
		
		final Patronage patronage = this.patronPatronageRepository.findOnePatronageById(request.getModel().getInteger("id"));
		final boolean published = patronage.isPublished();
		final Integer owner_id = patronage.getPatron().getId();
		
		return request.getPrincipal().getActiveRoleId() == owner_id && !published;
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

		request.unbind(entity, model, "status", "code", "legalStuff", "budget", "creationDate", "startDate", "deadline", "info", "inventor", "published");
		model.setAttribute("inventor", entity.getInventor().getUserAccount().getUsername());
		model.setAttribute("patron", entity.getPatron().getUserAccount().getUsername());
		model.setAttribute("inventors", this.inventorItemRepository.findAllInventors());

	}

	@Override
	public Patronage findOne(final Request<Patronage> request) {
		assert request != null;
		return this.patronPatronageRepository.findOnePatronageById(request.getModel().getInteger("id"));
	}

	@Override
	public void validate(final Request<Patronage> request, final Patronage entity, final Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;
		
		if(!errors.hasErrors("code")) {
			final Patronage i = this.patronPatronageRepository.findOnePatronageByCode((entity.getCode()));
			errors.state(request, i == null || i.getId()==entity.getId(),"code", "patron.patronage.form.error.code.duplicated");
		}
		if(!errors.hasErrors("legalStuff")) {
			errors.state(request, !SpamDetector.spamDetector(entity.getLegalStuff(), this.inventorItemRepository.findSystemConfiguration().getWeakSpamTerms(), 
				this.inventorItemRepository.findSystemConfiguration().getStrongSpamTerms(), 
				this.inventorItemRepository.findSystemConfiguration().getWeakSpamThreshold(),
				this.inventorItemRepository.findSystemConfiguration().getStrongSpamThreshold()), "legalStuff", "patron.patronage.form.legalStuff.error.spam");
		}

		if(!errors.hasErrors("startDate")) {
			final Date minStartDate = DateUtils.addMonths(entity.getCreationDate(), 1);
			errors.state(request, entity.getStartDate().after(minStartDate), "startDate", "patron.patronage.form.error.startDate");

		}
		if(!errors.hasErrors("deadline")) {
			final Date minDeadline = DateUtils.addMonths(entity.getStartDate(), 1);
			errors.state(request, entity.getDeadline().after(minDeadline), "deadline", "patron.patronage.form.error.deadline");
		}
		if(!errors.hasErrors("budget")) {
			errors.state(request, entity.getBudget().getAmount()>0, "budget", "patron.patronage.form.error.budget.negative");
			final String[] acceptedCurrencies = this.inventorItemRepository.findSystemConfiguration().getAcceptedCurrencies().split(",");
			final List<String> acceptedCurrenciesList = new ArrayList<>();
			for(final String ac : acceptedCurrencies) {
				acceptedCurrenciesList.add(ac);
			}
			errors.state(request, acceptedCurrenciesList.contains(entity.getBudget().getCurrency()), "budget", "patron.patronage.form.error.budget.acceptedCurrencies");
		}
	}

	@Override
	public void update(final Request<Patronage> request, final Patronage entity) {
		assert request != null;
		assert entity != null;
		
		entity.setPublished(true);
		this.patronPatronageRepository.save(entity);
	}

}
