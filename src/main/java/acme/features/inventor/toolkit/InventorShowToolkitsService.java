package acme.features.inventor.toolkit;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.Toolkit;
import acme.framework.components.models.Model;
import acme.framework.controllers.Request;
import acme.framework.services.AbstractShowService;
import acme.roles.Inventor;

@Service
public class InventorShowToolkitsService implements AbstractShowService<Inventor, Toolkit>{
			// Internal state ======================================================
	
			@Autowired
			protected InventorToolkitRepository repository;
				
			// AbstractShowService<Inventor, Toolkit> interface ==========
				
			@Override
			public boolean authorise(final Request<Toolkit> request) {
				assert request != null;
					
				return true;
			}
				
			@Override
			public Toolkit findOne(final Request<Toolkit> request) {
				assert request != null;
					
				final Toolkit result;
				int id;
					
				id = request.getModel().getInteger("id");
				result = this.repository.findOneToolkitById(id);
					
				return result;
			}
				
			@Override
			public void unbind(final Request<Toolkit> request, final Toolkit entity, final Model model) {
				assert request != null;
				assert entity != null;
				assert model != null;
					
				int id;
				Double toolkitPrice;
				
				id = request.getModel().getInteger("id");
				toolkitPrice = this.repository.calculateToolkitPrice(id);
				
				model.setAttribute("toolkitPrice", toolkitPrice);
				request.unbind(entity, model, "title", "description", "notes", "link");
				model.setAttribute("confirmation", false);
				model.setAttribute("readonly", true);
			}
}
