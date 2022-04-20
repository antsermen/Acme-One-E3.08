package acme.features.inventor.item;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.Item;
import acme.framework.components.models.Model;
import acme.framework.controllers.Request;
import acme.framework.services.AbstractShowService;
import acme.roles.Inventor;

@Service
public class InventorItemShowService implements AbstractShowService<Inventor, Item>{

	//Internal State------------------------------------------------------------
	
	@Autowired
	protected InventorItemRepository InventorItemRepository;
	
	//AbstractShow Interface
	
	@Override
	public boolean authorise(final Request<Item> request) {
		assert request != null;
		return request.getPrincipal().getActiveRoleId() == this.InventorItemRepository.findItemById(request.getModel().getInteger("id")).getInventor().getId();
	}

	@Override
	public Item findOne(final Request<Item> request) {
		assert request != null;
		return this.InventorItemRepository.findItemById(request.getModel().getInteger("id"));
	}

	@Override
	public void unbind(final Request<Item> request, final Item entity, final Model model) {
		assert request != null; 
		assert entity != null;
		assert model != null;
		
		request.unbind(entity, model, "itemType", "name", "code", "technology", "description", "retailPrice", "link");
	}
	

}
