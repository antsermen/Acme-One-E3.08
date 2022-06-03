package acme.features.inventor.rotelam;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.Item;
import acme.entities.Rotelam;
import acme.framework.components.models.Model;
import acme.framework.controllers.Request;
import acme.framework.services.AbstractShowService;
import acme.roles.Inventor;

@Service
public class InventorShowRotelamService implements AbstractShowService<Inventor,Rotelam>{
	
	
	@Autowired
	protected InventorRotelamRepository repository;
	
	@Override
	public boolean authorise(final Request<Rotelam> request) {
		assert request != null;
		
		final boolean result;
		int id;
		Item item;
		
		id = request.getModel().getInteger("masterId");
		item= this.repository.findItemById(id);
		result=(item != null && request.isPrincipal(item.getInventor()));
		
		return result;

	}
	
	
	@Override
	public Rotelam findOne(final Request<Rotelam> request) {
		assert request != null;

		Rotelam result;
		int id;

		id = request.getModel().getInteger("id");
		result = this.repository.findRotelamById(id);

		return result;
	}

	@Override
	public void unbind(final Request<Rotelam> request, final Rotelam entity, final Model model) {
		assert request != null;
		assert entity != null;
		assert model != null;

		request.unbind(entity, model, "code", "creationMoment", "subject", "explanation", "period", "expenditure", "moreInfo");
	}
	
	

}
