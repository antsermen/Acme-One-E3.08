package acme.features.any.toolkits;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.Item;
import acme.entities.Toolkit;
import acme.forms.MoneyExchange;
import acme.framework.components.models.Model;
import acme.framework.controllers.Request;
import acme.framework.datatypes.Money;
import acme.framework.roles.Any;
import acme.framework.services.AbstractShowService;
import acme.functions.MoneyExchangeFunction;

@Service
public class AnyToolkitShowService implements AbstractShowService<Any,Toolkit>{
	
	@Autowired
	protected AnyToolkitRepository repository;

	// AbstractShowService<Anonymous, Job> interface --------------------------

	@Override
	public boolean authorise(final Request<Toolkit> request) {
		assert request != null;

		return true;
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
			
		int id;
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
		request.unbind(entity, model, "title", "description", "notes", "link", "code");
		model.setAttribute("confirmation", false);
		model.setAttribute("readonly", true);
	}
}
