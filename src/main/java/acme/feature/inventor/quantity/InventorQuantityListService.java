package acme.feature.inventor.quantity;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;

import acme.entities.Quantity;
import acme.entities.Toolkit;
import acme.features.inventor.item.InventorItemRepository;
import acme.features.inventor.toolkit.InventorToolkitRepository;
import acme.framework.components.models.Model;
import acme.framework.controllers.Request;
import acme.framework.helpers.CollectionHelper;
import acme.framework.services.AbstractListService;
import acme.roles.Inventor;

public class InventorQuantityListService implements AbstractListService<Inventor,Quantity>{
	
	@Autowired
	protected InventorToolkitRepository toolkitRepository;
	
	@Autowired
	protected InventorItemRepository itemRepository;
	
	@Override
	public boolean authorise(final Request<Quantity> request) {
		assert request != null;
		
		final Toolkit toolkit = this.toolkitRepository.findOneToolkitById(request.getModel().getInteger("id"));
		final Integer inventor_id = toolkit.getInventor().getId();
		
		return request.getPrincipal().getActiveRoleId() == inventor_id;
	}
	
	@Override
	public Collection<Quantity> findMany(final Request<Quantity> request) {
		final int toolkitId;
		toolkitId = request.getModel().getInteger("masterId");
		final Collection<Quantity> quantities = this.toolkitRepository.findQuantityByToolkitId(toolkitId);
		
		return quantities;
	}
	
	@Override
	public void unbind(final Request<Quantity> request, final Collection<Quantity> entities, final Model model) {
		assert request != null; 
		assert !CollectionHelper.someNull(entities);
		assert model != null; 
		
		int masterId;
		final Toolkit toolkit;
		
		masterId = request.getModel().getInteger("masterId");
		
		toolkit = this.toolkitRepository.findOneToolkitById(masterId);
		
		final boolean showCreate = (!toolkit.isPublished() && request.isPrincipal(toolkit.getInventor()));
		
		model.setAttribute("masterId", masterId);
		model.setAttribute("showCreate", showCreate);
		 
	}
	
	@Override
	public void unbind(final Request<Quantity> request, final Quantity entity, final Model model) {
		assert request != null; 
		assert entity != null; 
		assert model != null; 
 
		request.unbind(entity, model, "itemsNumber", "item.itemType", "item.name", "item.retailPrice","item.systemRetailPrice"); 
		 
	}

}
