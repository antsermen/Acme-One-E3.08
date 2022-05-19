package acme.features.inventor.item;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.Item;
import acme.forms.MoneyExchange;
import acme.framework.components.models.Model;
import acme.framework.controllers.Request;
import acme.framework.datatypes.Money;
import acme.framework.services.AbstractShowService;
import acme.functions.MoneyExchangeFunction;
import acme.roles.Inventor;

@Service
public class InventorItemShowService implements AbstractShowService<Inventor, Item>{

	//Internal State------------------------------------------------------------
	
	@Autowired
	protected InventorItemRepository inventorItemRepository;
	
	//AbstractShow Interface
	
	@Override
	public boolean authorise(final Request<Item> request) {
		assert request != null;
		return request.getPrincipal().getActiveRoleId() == this.inventorItemRepository.findItemById(request.getModel().getInteger("id")).getInventor().getId();
	}

	@Override
	public Item findOne(final Request<Item> request) {
		assert request != null;
		return this.inventorItemRepository.findItemById(request.getModel().getInteger("id"));
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
		targetCurrency = this.inventorItemRepository.findSystemConfiguration().getSystemCurrency();
		exchange=MoneyExchangeFunction.computeMoneyExchange(source, targetCurrency);
		target=exchange.target;
		entity.setSystemRetailPrice(target);
		
		request.unbind(entity, model, "itemType", "name", "code", "technology", "description", "systemRetailPrice", "retailPrice", "link", "published");
	}
	

}
