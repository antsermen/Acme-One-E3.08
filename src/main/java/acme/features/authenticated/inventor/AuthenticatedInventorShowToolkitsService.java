package acme.features.authenticated.inventor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.Toolkit;
import acme.framework.components.models.Model;
import acme.framework.controllers.Request;
import acme.framework.roles.Authenticated;
import acme.framework.services.AbstractShowService;
import acme.roles.Inventor;

@Service
public class AuthenticatedInventorShowToolkitsService implements AbstractShowService<Authenticated, Inventor>{
	
			// Internal state ======================================================
	
			@Autowired
			protected AuthenticatedInventorRepository repository;
			
			// AbstractShowService<Authenticated, Inventor> interface ==========
			
			@Override
			public boolean authorise(final Request<Inventor> request) {
				assert request != null;
				
				return true;
			}
			
			@Override
			public Toolkit findOne(final Request<Inventor> request) {
				assert request != null;
				
				final Toolkit result;
				int id;
				
				id = request.getModel().getInteger("id");
				//result = this.repository.findOneToolkitByInventorId(id);
				
				return result;
			}
			
			@Override
			public void unbind(final Request<Inventor> request, final Toolkit entity, final Model model) {
				assert request != null;
				assert entity != null;
				assert model != null;
				
				request.unbind(entity, model, "code", "title", "description", "notes", "link", "price");
				model.setAttribute("confirmation", false);
				model.setAttribute("readonly", true);
			}
}
