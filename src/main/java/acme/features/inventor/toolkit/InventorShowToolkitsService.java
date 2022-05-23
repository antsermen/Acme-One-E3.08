package acme.features.inventor.toolkit;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.Toolkit;
import acme.forms.MoneyExchange;
import acme.framework.components.models.Model;
import acme.framework.controllers.Request;
import acme.framework.datatypes.Money;
import acme.framework.services.AbstractShowService;
import acme.functions.MoneyExchangeFunction;
import acme.roles.Inventor;

@Service
public class InventorShowToolkitsService implements AbstractShowService<Inventor, Toolkit>{
	
			// Internal state ======================================================
	
			@Autowired
			protected InventorToolkitRepository repository;
				
			// AbstractShowService<Inventor, Toolkit> interface ==========
				
			@Override
			public boolean authorise(final Request<Toolkit> request) {
				assert request != null;
				return request.getPrincipal().getActiveRoleId() == this.repository.findOneToolkitById(request.getModel().getInteger("id")).getInventor().getId();
			}
				
			@Override
			public Toolkit findOne(final Request<Toolkit> request) {
				assert request != null;
					
				final Toolkit result;
				int id;
					
				id = request.getModel().getInteger("id");
				result = this.repository.findOneToolkitById(id);
					
				return result;
			}
				
			@Override
			public void unbind(final Request<Toolkit> request, final Toolkit entity, final Model model) {
				assert request != null;
				assert entity != null;
				assert model != null;
					
				final int id;
				final Money toolkitPrice = new Money();	
				
				id = request.getModel().getInteger("id");
        
				final Double rp= this.repository.calculateToolkitPrice(id);
				if(rp==null) {
					toolkitPrice.setAmount(0.);
				}else {
					toolkitPrice.setAmount(rp);
				}
				toolkitPrice.setCurrency(this.repository.findSystemConfiguration().getSystemCurrency());
						
				model.setAttribute("toolkitPrice", toolkitPrice);

				request.unbind(entity, model,"code","title", "description", "notes", "link","published");
			}
}
