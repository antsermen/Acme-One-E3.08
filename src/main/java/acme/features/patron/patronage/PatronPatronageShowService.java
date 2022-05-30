package acme.features.patron.patronage;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.Patronage;
import acme.features.inventor.item.InventorItemRepository;
import acme.forms.MoneyExchange;
import acme.framework.components.models.Model;
import acme.framework.controllers.Request;
import acme.framework.datatypes.Money;
import acme.framework.services.AbstractShowService;
import acme.functions.MoneyExchangeFunction;
import acme.roles.Patron;

@Service
public class PatronPatronageShowService implements AbstractShowService<Patron, Patronage>{
	
	@Autowired
	protected PatronPatronageRepository repository;
	@Autowired
	protected InventorItemRepository inventorItemRepository;

	@Override
	public boolean authorise(final Request<Patronage> request) {
		assert request != null;

		boolean result;
		int patronageId;
		Patronage patronage;
		
		patronageId = request.getModel().getInteger("id");
		patronage = this.repository.findOnePatronageById(patronageId);
		result = patronage.getPatron().getId() == request.getPrincipal().getActiveRoleId();

		return result;
	}

	@Override
	public Patronage findOne(final Request<Patronage> request) {
		assert request != null;

		Patronage result;
		int id;

		id = request.getModel().getInteger("id");
		result = this.repository.findOnePatronageById(id);

		return result;
	}

	@Override
	public void unbind(final Request<Patronage> request, final Patronage entity, final Model model) {
		assert request != null;
		assert entity != null;
		assert model != null;
		model.setAttribute("inventor", entity.getInventor().getUserAccount().getUsername());
		model.setAttribute("patron", entity.getPatron().getUserAccount().getUsername());
		model.setAttribute("inventors", this.inventorItemRepository.findAllInventors());
		Money source, target;
		String targetCurrency;
		MoneyExchange exchange;
		source = entity.getBudget();
		targetCurrency = this.repository.findSystemConfiguration().getSystemCurrency();
		exchange=MoneyExchangeFunction.computeMoneyExchange(source, targetCurrency);
		target=exchange.target;
		entity.setSystemBudget(target);
		
		request.unbind(entity, model, "status", "code", "legalStuff", "budget", "systemBudget", "creationDate", "startDate", "deadline", "info", "published");
		model.setAttribute("confirmation", false);
	}
	
	
}
