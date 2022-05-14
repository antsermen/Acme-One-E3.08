package acme.features.inventor.toolkit;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.Toolkit;
import acme.framework.components.models.Model;
import acme.framework.controllers.Errors;
import acme.framework.controllers.Request;
import acme.framework.services.AbstractCreateService;
import acme.roles.Inventor;

@Service
public class InventorCreateToolkitService implements AbstractCreateService<Inventor,Toolkit>{
	
	@Autowired
	protected InventorToolkitRepository repository;
	
	@Override
	 public boolean authorise(final Request<Toolkit> request) {
		assert request != null;

		return true;
	}
	
	@Override
	public Toolkit instantiate(final Request<Toolkit> request) {
		assert request != null;

		Toolkit result;
		Inventor inventor;

		inventor = this.repository.findOneEmployerById(request.getPrincipal().getActiveRoleId());
		result = new Toolkit();
		result.setPublished(true);;
		result.setInventor(inventor);

		return result;
	}
	
	@Override
	public void bind(final Request<Toolkit> request, final Toolkit entity, final Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;
		
		request.bind(entity, errors, "reference", "title", "deadline", "salary", "score", "moreInfo", "description");
	}
	
	@Override
	public void validate(final Request<Toolkit> request, final Toolkit entity, final Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;

		if (!errors.hasErrors("code")) {
			Toolkit existing;

			existing = this.repository.findOneToolkitByCode(entity.getCode());
			errors.state(request, existing == null || existing.getId() == entity.getId(), "code", "employer.job.form.error.duplicated");
		}
	}
	
	@Override
	public void unbind(final Request<Toolkit> request, final Toolkit entity, final Model model) {
		assert request != null;
		assert entity != null;
		assert model != null;
		
		request.unbind(entity, model, "code", "title", "description", "notes", "link", "isPublished");
	}
	
	@Override
	public void create(final Request<Toolkit> request, final Toolkit entity) {
		assert request != null;
		assert entity != null;

		this.repository.save(entity);
	}


}
