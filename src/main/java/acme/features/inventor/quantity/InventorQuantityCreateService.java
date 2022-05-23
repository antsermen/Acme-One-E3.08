package acme.features.inventor.quantity;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.ItemType;
import acme.entities.Quantity;
import acme.entities.Toolkit;
import acme.features.inventor.item.InventorItemRepository;
import acme.features.inventor.toolkit.InventorToolkitRepository;
import acme.framework.components.models.Model;
import acme.framework.controllers.Errors;
import acme.framework.controllers.Request;
import acme.framework.services.AbstractCreateService;
import acme.roles.Inventor;
@Service
public class InventorQuantityCreateService implements AbstractCreateService<Inventor,Quantity>{
	
	@Autowired
	protected InventorToolkitRepository toolkitRepository;

	
	@Autowired
	protected InventorItemRepository itemRepository;
	
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
		
		if(this.itemRepository.findAssignableItemsToToolkit(entity.getToolkit().getId()).isEmpty()) {
			request.bind(entity, errors, "itemsNumber");
		} else {
			
			entity.setItem(this.itemRepository.findItemById(Integer.valueOf(request.getModel().getAttribute("itemId").toString())));
			request.bind(entity, errors, "itemsNumber","itemId");
			
		}
		
	}
	


	@Override
	public void unbind(final Request<Quantity> request, final Quantity entity, final Model model) {
		assert request != null;
		assert entity != null;
		assert model != null;		
		
		final int masterId = Integer.parseInt((String) request.getModel().getAttribute("masterId"));
		model.setAttribute("masterId", masterId);
		model.setAttribute("items", this.itemRepository.findAssignableItemsToToolkit(masterId));
		request.unbind(entity, model, "itemsNumber");
		
		
	}
	


	@Override
	public Quantity instantiate(final Request<Quantity> request) {
		assert request != null;
		
		Quantity quantity;
		quantity= new Quantity();
		
		final Toolkit toolkit = this.toolkitRepository.findOneToolkitById(request.getModel().getInteger("masterId"));
		
		quantity.setToolkit(toolkit);
		
		return quantity;
	}

	@Override
	public void validate(final Request<Quantity> request, final Quantity entity, final Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;
		
		if(entity.getItemsNumber()==null) {
			errors.state(request, entity.getItem()!=null, "itemId", "inventor.quantity.form.error.null-item");
		}
		if(entity.getItem().getItemType() == ItemType.TOOL) {
			errors.state(request, entity.getItemsNumber()<=1, "*", "inventor.quantity.form.error.only-1-type-of-tool-allowed");
		}
		
	}
	



	@Override
	public void create(final Request<Quantity> request, final Quantity entity) {
		assert request != null;
		assert entity != null;	
		
		this.toolkitRepository.save(entity);
	}

}
