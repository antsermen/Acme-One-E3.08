package acme.features.inventor.toolkit;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.Item;
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
				
			
				final Collection<Item> items = this.repository.findItemsFromToolkitId(id);
				
				model.setAttribute("toolkitPrice", toolkitPrice);
				model.setAttribute("items", items);
				for (final Item i: items) {
					Money source, target;
					String targetCurrency;
					MoneyExchange exchange;
					source = i.getRetailPrice();
					targetCurrency = this.repository.findSystemConfiguration().getSystemCurrency();
					exchange=MoneyExchangeFunction.computeMoneyExchange(source, targetCurrency);
					target=exchange.target;
					i.setSystemRetailPrice(target);
					model.setAttribute("itemName", i.getName());
					model.setAttribute("itemType", i.getItemType());
					model.setAttribute("itemDescription", i.getDescription());
					model.setAttribute("itemRetailPrice", i.getRetailPrice());
					model.setAttribute("itemSystemRetailPrice", i.getSystemRetailPrice());
				}
				toolkitPrice.setAmount(this.repository.calculateToolkitPrice(id));
				toolkitPrice.setCurrency(this.repository.findSystemConfiguration().getSystemCurrency());
				request.unbind(entity, model,"code","title", "description", "notes", "link","published");
			}
}
