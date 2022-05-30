package acme.features.inventor.patronage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.Patronage;
import acme.entities.Status;
import acme.framework.components.models.Model;
import acme.framework.controllers.Errors;
import acme.framework.controllers.Request;
import acme.framework.services.AbstractUpdateService;
import acme.roles.Inventor;

@Service
public class InventorPatronageAcceptService implements AbstractUpdateService<Inventor, Patronage>{
			
			// Internal state ========================================================
	
			@Autowired
			protected InventorPatronageRepository repository;
			
			// AbstractListService<Inventor, Patronage> interface ====================
			
			@Override
			public boolean authorise(final Request<Patronage> request) {
				assert request != null;
				
				final Integer id = request.getModel().getInteger("id");
				final Patronage patronage = this.repository.findOnePatronageById(id);
				final Integer id_inv = patronage.getInventor().getId();

				return request.getPrincipal().getActiveRoleId() == id_inv;
			}

			@Override
			public void validate(final Request<Patronage> request, final Patronage entity, final Errors errors) {
				assert request != null;
				assert entity != null;
				assert errors != null;

			}

			@Override
			public Patronage findOne(final Request<Patronage> request) {
				assert request != null;
				
				final Patronage result;
				int id;
						
				id = request.getModel().getInteger("id");
				result = this.repository.findOnePatronageById(id);
						
				return result;}
			


			@Override
			public void unbind(final Request<Patronage> request, final Patronage entity, final Model model) {
				assert request != null;
				assert entity != null;
				assert model != null;

				request.unbind(entity, model);

			}	
@Override
public void bind(final Request<Patronage> request, final Patronage entity, final Errors errors) {
	assert request != null;
	assert entity != null;
	assert errors != null;
	request.bind(entity, errors);

}


@Override
public void update(final Request<Patronage> request, final Patronage entity) {
	assert request != null;
	assert entity != null;
	entity.setStatus(Status.ACCEPTED);
	this.repository.save(entity);


}
}
