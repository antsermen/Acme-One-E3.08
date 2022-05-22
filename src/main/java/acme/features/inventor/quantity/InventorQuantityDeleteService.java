package acme.features.inventor.quantity;

import org.springframework.beans.factory.annotation.Autowired;

import acme.entities.Quantity;
import acme.entities.Toolkit;
import acme.features.inventor.toolkit.InventorToolkitRepository;
import acme.framework.components.models.Model;
import acme.framework.controllers.Errors;
import acme.framework.controllers.Request;
import acme.framework.services.AbstractDeleteService;
import acme.roles.Inventor;

public class InventorQuantityDeleteService implements AbstractDeleteService<Inventor,Quantity>{
	
	
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

		request.bind(entity, errors, "itemsNumber"); 
		
	}

	@Override
	public void unbind(final Request<Quantity> request, final Quantity entity, final Model model) {
		assert request != null;
		assert entity != null;
		assert model != null;

		request.unbind(entity, model, "number", "item.itemType", "item.name", "item.code", "item.technology", "item.description", "item.retailPrice", "item.link","item.systemRetailPrice","item.published"); 
		
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
	}

	@Override
	public void delete(final Request<Quantity> request, final Quantity entity) {
		assert request != null;
		assert entity != null;
		
		
		this.toolkitRepository.delete(entity);
	}

}
