package acme.features.inventor.quantity;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.Item;
import acme.entities.ItemType;
import acme.entities.Quantity;
import acme.entities.Toolkit;
import acme.features.inventor.toolkit.InventorToolkitRepository;
import acme.framework.components.models.Model;
import acme.framework.controllers.Errors;
import acme.framework.controllers.Request;
import acme.framework.services.AbstractUpdateService;
import acme.roles.Inventor;
@Service
public class InventorQuantityUpdateService implements AbstractUpdateService<Inventor,Quantity>{
	
	@Autowired
	protected InventorToolkitRepository toolkitRepository;
	
	@Override
	public boolean authorise(final Request<Quantity> request) {
		assert request != null;
		
		boolean result;
		int masterId;
		Toolkit toolkit;

		masterId = request.getModel().getInteger("masterId");
		toolkit = this.toolkitRepository.findOneToolkitById(masterId);
		result = (toolkit != null && !toolkit.isPublished() && request.isPrincipal(toolkit.getInventor()));
		return result;
	}

	@Override
	public void bind(final Request<Quantity> request, final Quantity entity, final Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;

		request.bind(entity, errors, "number"); 
		
	}

	@Override
	public void unbind(final Request<Quantity> request, final Quantity entity, final Model model) {
		assert request != null;
		assert entity != null;
		assert model != null;

		model.setAttribute("toolkitPublished", entity.getToolkit().isPublished());
		
		request.unbind(entity, model, "number", "item.itemType", "item.name", "item.code", "item.technology",
			"item.description", "item.retailPrice","item.systemRetailPrice" ,"item.link","item.published");
		
	}

	@Override
	public Quantity findOne(final Request<Quantity> request) {
		assert request != null;
		
		Quantity result;
		int id;
		id = request.getModel().getInteger("id");
		result = this.toolkitRepository.findOneQuantityById(id);
		return result;
	}

 
	@Override 
	public void validate(final Request<Quantity> request, final Quantity entity, final Errors errors) { 
		assert request != null; 
		assert entity != null; 
		assert errors != null; 
		
		final Item selectedItem = entity.getItem();
		
		if(selectedItem.getItemType().equals(ItemType.TOOL)) {
			errors.state(request, entity.getItemsNumber() == 1, "number", "inventor.quantity.form.error.toolkit-one-quantity-tool");
		}
		
		
		
		
	} 

	@Override
	public void update(final Request<Quantity> request, final Quantity entity) {
		assert request != null;
		assert entity != null;
		
		this.toolkitRepository.save(entity);
		
	}

}
