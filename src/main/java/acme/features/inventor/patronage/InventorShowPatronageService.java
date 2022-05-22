package acme.features.inventor.patronage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.Patronage;
import acme.forms.MoneyExchange;
import acme.framework.components.models.Model;
import acme.framework.controllers.Request;
import acme.framework.datatypes.Money;
import acme.framework.services.AbstractShowService;
import acme.functions.MoneyExchangeFunction;
import acme.roles.Inventor;
import acme.roles.Patron;

@Service
public class InventorShowPatronageService implements AbstractShowService<Inventor, Patronage>{
			
			// Internal state ======================================================
	
			@Autowired
			protected InventorPatronageRepository repository;
					
			// AbstractShowService<Inventor, Patronage> interface ==================
					
			@Override
			public boolean authorise(final Request<Patronage> request) {
				assert request != null;
				final Patronage patronage = this.repository.findOnePatronageById(request.getModel().getInteger("id"));
				return request.getPrincipal().getActiveRoleId() == patronage.getInventor().getId() && patronage.isPublished()==true;
			}
					
			@Override
			public Patronage findOne(final Request<Patronage> request) {
				assert request != null;
					
				final Patronage result;
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
				Money source, target;
				String targetCurrency;
				MoneyExchange exchange;
				source = entity.getBudget();
				targetCurrency = this.repository.findSystemConfiguration().getSystemCurrency();
				exchange=MoneyExchangeFunction.computeMoneyExchange(source, targetCurrency);
				target=exchange.target;
				entity.setSystemBudget(target);
				
				Patron patron;
				patron = entity.getPatron();
				model.setAttribute("patronageId", request.getModel().getInteger("id"));
				model.setAttribute("patronName", patron.getIdentity().getName());
				model.setAttribute("patronSurname", patron.getIdentity().getSurname());
				model.setAttribute("patronCompany", patron.getCompany());
				model.setAttribute("patronStatement", patron.getStatement());
				model.setAttribute("patronInfo", patron.getInfo());
				request.unbind(entity, model, "status", "legalStuff", "budget", "systemBudget", "deadline", "info");
				model.setAttribute("confirmation", false);
				model.setAttribute("readonly", true);
			}
			
			
}
