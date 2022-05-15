package acme.features.inventor.toolkit;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.Toolkit;
import acme.framework.components.models.Model;
import acme.framework.controllers.Errors;
import acme.framework.controllers.Request;
import acme.framework.services.AbstractUpdateService;
import acme.roles.Inventor;


@Service
public class InventorUpdateToolkitService implements AbstractUpdateService<Inventor,Toolkit>{
	
	@Autowired
	InventorToolkitRepository repository;
	
	@Override
	public boolean authorise(final Request<Toolkit> request) {
		assert request != null;

		final Toolkit toolkit = this.repository.findOneToolkitById(request.getModel().getInteger("id"));
		final boolean published = toolkit.isPublished();
		final Integer inventor_id = toolkit.getInventor().getId();
		
		return request.getPrincipal().getActiveRoleId() == inventor_id && !published;
		
	}

	@Override
	public void bind(final Request<Toolkit> request, final Toolkit entity, final Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;
		
		request.bind(entity, errors, "code", "technology", "description", "retailPrice", "link", "published");
	}

	@Override
	public void unbind(final Request<Toolkit> request, final Toolkit entity, final Model model) {
		assert request != null;
		assert entity != null;
		assert model != null;

		request.unbind(entity, model, "code", "technology", "description", "retailPrice", "link", "published");

	}

	@Override
	public Toolkit findOne(final Request<Toolkit> request) {
		assert request != null;
		return this.repository.findOneToolkitById(request.getModel().getInteger("id"));
	}

	@Override
	public void validate(final Request<Toolkit> request, final Toolkit entity, final Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;
		
		if(!errors.hasErrors("code")) {
			final Toolkit i = this.repository.findOneToolkitByCode(entity.getCode());
			errors.state(request, i == null || i.getId()==entity.getId(),"code", "inventor.item.form.error.code.duplicated");
		}

	}

	@Override
	public void update(final Request<Toolkit> request, final Toolkit entity) {
		assert request != null;
		assert entity != null;

		this.repository.save(entity);
	}

}
