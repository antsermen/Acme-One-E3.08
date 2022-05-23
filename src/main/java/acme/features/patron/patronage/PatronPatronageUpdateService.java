package acme.features.patron.patronage;

import java.util.Date;

import org.apache.commons.lang3.time.DateUtils;
import org.assertj.core.util.Arrays;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import SpamDetector.Spam_Detector.SpamDetector;
import acme.entities.Patronage;
import acme.features.inventor.item.InventorItemRepository;
import acme.forms.MoneyExchange;
import acme.framework.components.models.Model;
import acme.framework.controllers.Errors;
import acme.framework.controllers.Request;
import acme.framework.datatypes.Money;
import acme.framework.services.AbstractUpdateService;
import acme.functions.MoneyExchangeFunction;
import acme.roles.Patron;

@Service
public class PatronPatronageUpdateService implements AbstractUpdateService<Patron, Patronage>{

	@Autowired
	protected PatronPatronageRepository patronPatronageRepository;
	@Autowired
	protected InventorItemRepository inventorItemRepository;
	
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
		entity.setInventor(this.inventorItemRepository.findInventorByUsername(request.getModel().getAttribute("inventor").toString()));
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
			final Patronage duplicated = this.patronPatronageRepository.findOnePatronageByCode((entity.getCode()));
			errors.state(request, duplicated == null || duplicated.getId()==entity.getId(),"code", "patron.patronage.form.error.code.duplicated");
			final Patronage modified = this.patronPatronageRepository.findOnePatronageById((entity.getId()));
			errors.state(request, modified.getCode().equals(entity.getCode()),"code", "patron.patronage.form.error.code.modified");
		}
		if(!errors.hasErrors("legalStuff")) {
			errors.state(request, !SpamDetector.spamDetector(entity.getLegalStuff(), this.inventorItemRepository.findSystemConfiguration().getWeakSpamTerms(), 
				this.inventorItemRepository.findSystemConfiguration().getStrongSpamTerms(), 
				this.inventorItemRepository.findSystemConfiguration().getWeakSpamThreshold(),
				this.inventorItemRepository.findSystemConfiguration().getStrongSpamThreshold()), "legalStuff", "inventor.item.form.error.name.spam");
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
			errors.state(request, Arrays.asList(acceptedCurrencies).contains(entity.getBudget().getCurrency()), "budget", "patron.patronage.form.error.budget.acceptedCurrencies");
		}
	}

	@Override
	public void update(final Request<Patronage> request, final Patronage entity) {
		assert request != null;
		assert entity != null;	
		
		Money source, target;
		String targetCurrency;
		MoneyExchange exchange;
		source = entity.getBudget();
		targetCurrency = this.inventorItemRepository.findSystemConfiguration().getSystemCurrency();
		exchange=MoneyExchangeFunction.computeMoneyExchange(source, targetCurrency);
		target=exchange.target;
		entity.setSystemBudget(target);

		this.patronPatronageRepository.save(entity);
	}

}
