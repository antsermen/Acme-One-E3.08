package acme.features.inventor.quantity;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import acme.entities.Item;
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
		
		String itemName;
		Item item;
		
		itemName =  request.getModel().getAttribute("artifact.name").toString();
		item = this.itemRepository.findItemByName(itemName);
		

		entity.setItem(item);
		request.bind(entity, errors, "itemsNumber", "item.name");
		
	}
	


	@Override
	public void unbind(final Request<Quantity> request, final Quantity entity, final Model model) {
		assert request != null;
		assert entity != null;
		assert model != null;		
		
		List<Item> publishedItems;	
		publishedItems = this.itemRepository.findPublishedItems();
		
		request.unbind(entity, model, "amount", "artifact.name");
		model.setAttribute("toolkitId", request.getModel().getAttribute("toolkitId"));
		model.setAttribute("items", publishedItems);
		model.setAttribute("published", entity.getToolkit().isPublished());
		
		
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
		
		if(entity.getItem()==null) {
			
			errors.state(request, entity.getItem()!=null, "itemId", "inventor.quantity.form.error.null-item");
			
		} else {
			final Item selectedItem = this.itemRepository.findItemById(Integer.valueOf(request.getModel().getAttribute("itemId").toString()));
			 if(selectedItem.getItemType().equals(ItemType.TOOL)) {
				errors.state(request, entity.getItemsNumber() == 1, "number", "inventor.quantity.form.error.toolkit-one-quantity-tool");
			}
			
			
		}
		

	}



	@Override
	public void create(final Request<Quantity> request, final Quantity entity) {
		assert request != null;
		assert entity != null;	

		
		this.toolkitRepository.save(entity);
	}

}
