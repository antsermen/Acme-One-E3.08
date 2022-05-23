package acme.features.inventor.quantity;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.Item;
import acme.entities.ItemType;
import acme.entities.Quantity;
import acme.entities.Toolkit;
import acme.features.inventor.item.InventorItemRepository;
import acme.features.inventor.toolkit.InventorToolkitRepository;
import acme.framework.components.models.Model;
import acme.framework.controllers.Request;
import acme.framework.services.AbstractShowService;
import acme.roles.Inventor;
@Service
public class InventorQuantityShowService implements AbstractShowService<Inventor,Quantity>{
	
	@Autowired
	protected InventorToolkitRepository toolkitRepository;
	
	@Autowired
	protected InventorItemRepository itemRepository;
	
	@Override
	public boolean authorise(final Request<Quantity> request) {
		assert request != null;
		
		final Quantity quantity = this.toolkitRepository.findOneQuantityById(request.getModel().getInteger("id"));
		final Toolkit toolkit = this.toolkitRepository.findOneToolkitById(quantity.getToolkit().getId());
		final Integer inventor_id = toolkit.getInventor().getId();
		
		return request.getPrincipal().getActiveRoleId() == inventor_id;

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
	public void unbind(final Request<Quantity> request, final Quantity entity, final Model model) {
		assert request != null;
		assert entity != null;
		assert model != null;
		
		final Item item = this.itemRepository.findItemByQuantityId(entity.getId());
		
		request.unbind(entity, model, "itemsNumber", "item.name");
		model.setAttribute("item.name", item.getName());
		model.setAttribute("item.code", item.getCode() );
		model.setAttribute("item.itemType",item.getItemType());
		model.setAttribute("item.technology",item.getTechnology());
		model.setAttribute("item.retailPrice", item.getRetailPrice());
		model.setAttribute("item.systemRetailPrice", item.getSystemRetailPrice());
		model.setAttribute("item.description", item.getDescription());
		model.setAttribute("item.link", item.getLink());
		model.setAttribute("toolkitPublished", entity.getToolkit().isPublished());
		model.setAttribute("isTool", item.getItemType()==ItemType.TOOL);

	}

}
