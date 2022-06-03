package acme.features.inventor.chimpum;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.Chimpum;
import acme.framework.components.models.Model;
import acme.framework.controllers.Request;
import acme.framework.services.AbstractShowService;
import acme.roles.Inventor;

@Service
public class InventorChimpumShowService implements AbstractShowService<Inventor, Chimpum>{

	//Internal State------------------------------------------------------------
	
	@Autowired
	protected InventorChimpumRepository inventorChimpumRepository;
	
	//AbstractShow Interface
	
	@Override
	public boolean authorise(final Request<Chimpum> request) {
		assert request != null;
		
		return request.getPrincipal().getActiveRoleId()==this.inventorChimpumRepository.findChimpumById(request.getModel().getInteger("id")).getItem().getInventor().getId();
	}

	@Override
	public Chimpum findOne(final Request<Chimpum> request) {
		assert request != null;
		return this.inventorChimpumRepository.findChimpumById(request.getModel().getInteger("id"));
	}

	@Override
	public void unbind(final Request<Chimpum> request, final Chimpum entity, final Model model) {
		assert request != null; 
		assert entity != null;
		assert model != null;
		model.setAttribute("itemCode", entity.getItem().getCode());
		request.unbind(entity, model, "pattern", "creationMoment", "title", "description", "initialPeriod", "finalPeriod", "budget", "link");
	}
	

}
