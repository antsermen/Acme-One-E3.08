package acme.features.any.item;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.Item;
import acme.features.authenticated.systemConfiguration.AuthenticatedSystemConfigurationRepository;
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
	@Autowired
	protected AuthenticatedSystemConfigurationRepository systemConfigurationRepository;

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
		targetCurrency = this.systemConfigurationRepository.findSystemConfiguration().getSystemCurrency();
		exchange=MoneyExchangeFunction.computeMoneyExchange(source, targetCurrency);
		if (exchange == null) {
			model.setAttribute("moneyExchange", null);
		} else {
			target = exchange.getTarget();
			model.setAttribute("moneyExchange", target);
		}
		request.unbind(entity, model, "name", "code", "technology", "description", "retailPrice","link");
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
