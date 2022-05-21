package acme.features.inventor.patronage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.Patronage;
import acme.framework.components.models.Model;
import acme.framework.controllers.Request;
import acme.framework.services.AbstractShowService;
import acme.roles.Inventor;
import acme.roles.Patron;

@Service
public class InventorShowPatronageService implements AbstractShowService<Inventor, Patronage>{
			
			// Internal state ======================================================
	
			@Autowired
			protected InventorPatronageRepository repository;
					
			// AbstractShowService<Inventor, Patronage> interface ==================
					
			@Override
			public boolean authorise(final Request<Patronage> request) {
				assert request != null;
						
				return request.getPrincipal().getActiveRoleId() == this.repository.findOnePatronageById(request.getModel().getInteger("id")).getInventor().getId();
			}
					
			@Override
			public Patronage findOne(final Request<Patronage> request) {
				assert request != null;
					
				final Patronage result;
				int id;
						
				id = request.getModel().getInteger("id");
				result = this.repository.findOnePatronageById(id);
						
				return result;
			}
					
			@Override
			public void unbind(final Request<Patronage> request, final Patronage entity, final Model model) {
				assert request != null;
				assert entity != null;
				assert model != null;
				
				Patron patron;
				patron = entity.getPatron();
				
				model.setAttribute("patronName", patron.getIdentity().getName());
				model.setAttribute("patronSurname", patron.getIdentity().getSurname());
				model.setAttribute("patronCompany", patron.getCompany());
				model.setAttribute("patronStatement", patron.getStatement());
				model.setAttribute("patronInfo", patron.getInfo());
				request.unbind(entity, model, "status", "legalStuff", "budget", "deadline", "info");
				model.setAttribute("confirmation", false);
				model.setAttribute("readonly", true);
			}
			
}
