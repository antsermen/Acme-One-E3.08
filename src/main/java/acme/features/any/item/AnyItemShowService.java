package acme.features.any.item;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.Item;
import acme.forms.MoneyExchange;
import acme.framework.components.models.Model;
import acme.framework.controllers.Request;
import acme.framework.datatypes.Money;
import acme.framework.roles.Any;
import acme.framework.services.AbstractShowService;
import acme.functions.MoneyExchangeFunction;

@Service
public class AnyItemShowService implements AbstractShowService<Any,Item>{
	
	@Autowired
	protected AnyItemRepository repository;

	// AbstractShowService<Anonymous, Job> interface --------------------------

	@Override
	public boolean authorise(final Request<Item> request) {
		assert request != null;

		return true;
	}

	@Override
	public void unbind(final Request<Item> request, final Item entity, final Model model) {
		assert request != null;
		assert entity != null;
		assert model != null;
		Money source, target;
		String targetCurrency;
		MoneyExchange exchange;
		source = entity.getRetailPrice();
		targetCurrency = this.repository.findSystemConfiguration().getSystemCurrency();
		exchange=MoneyExchangeFunction.computeMoneyExchange(source, targetCurrency);
		target=exchange.target;
		entity.setSystemRetailPrice(target);
		request.unbind(entity, model, "name", "code", "technology", "description", "retailPrice", "systemRetailPrice","link");
	}

	@Override
	public Item findOne(final Request<Item> request) {
		assert request != null;

		Item result;
		Integer id;

		id = request.getModel().getInteger("id");
		result = this.repository.findItemById(id);

		return result;
	}
}
