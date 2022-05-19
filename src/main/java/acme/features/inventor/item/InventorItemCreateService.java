package acme.features.inventor.item;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.Item;
import acme.forms.MoneyExchange;
import acme.framework.components.models.Model;
import acme.framework.controllers.Errors;
import acme.framework.controllers.Request;
import acme.framework.datatypes.Money;
import acme.framework.services.AbstractCreateService;
import acme.functions.MoneyExchangeFunction;
import acme.roles.Inventor;

@Service
public class InventorItemCreateService implements AbstractCreateService<Inventor, Item>{
	
	@Autowired InventorItemRepository inventorItemRepository;

	@Override
	public boolean authorise(final Request<Item> request) {
		assert request != null;
		return true;
	}

	@Override
	public void bind(final Request<Item> request, final Item entity, final Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;
		
		request.bind(entity, errors, "itemType", "name", "code", "technology", "description", "retailPrice", "link");
	}

	@Override
	public void unbind(final Request<Item> request, final Item entity, final Model model) {
		assert request != null;
		assert entity != null;
		assert model != null;
		
		request.unbind(entity, model, "itemType", "name", "code", "technology", "description", "retailPrice", "link");
	}

	@Override
	public Item instantiate(final Request<Item> request) {

		final Item result = new Item();
		
		result.setInventor(this.inventorItemRepository.findInventorById(request.getPrincipal().getActiveRoleId()));
		result.setPublished(false);
		result.setCode("");
		result.setDescription("");
		result.setLink("");
		result.setTechnology("");
		
		return result;
	}

	@Override
	public void validate(final Request<Item> request, final Item entity, final Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;

		if(!errors.hasErrors("code")) {
			final Item i = this.inventorItemRepository.findItemByCode(entity.getCode());
			errors.state(request,i == null ||  i.getCode()==entity.getCode(),"code", "inventor.item.form.error.code.duplicated");
		}

	}

	@Override
	public void create(final Request<Item> request, final Item entity) {
		assert request != null;
		assert entity != null;
		Money source, target;
		String targetCurrency;
		MoneyExchange exchange;
		source = entity.getRetailPrice();
		targetCurrency = this.inventorItemRepository.findSystemConfiguration().getSystemCurrency();
		exchange=MoneyExchangeFunction.computeMoneyExchange(source, targetCurrency);
		target=exchange.target;
		entity.setSystemRetailPrice(target);

		this.inventorItemRepository.save(entity);
		
	}

}
