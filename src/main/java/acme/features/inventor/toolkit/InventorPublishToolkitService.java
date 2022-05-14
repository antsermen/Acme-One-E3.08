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
public class InventorPublishToolkitService implements AbstractUpdateService<Inventor,Toolkit>{
	
	@Autowired
	InventorToolkitRepository toolkitRepository;
	
	@Override
	public boolean authorise(final Request<Toolkit> request) {
		assert request != null;
		
		boolean result;
		int toolkitId;
		Toolkit toolkit;
		Inventor inventor;
		
		toolkitId= request.getModel().getInteger("id");
		toolkit= this.toolkitRepository.findOneToolkitById(toolkitId);
		inventor= toolkit.getInventor();
		result= toolkit.isPublished() && request.isPrincipal(inventor);
		
		return result;
	}
	
	@Override
	public Toolkit findOne(final Request<Toolkit> request) {
		assert request != null;
		
		Toolkit result;
		int id;
		
		id=request.getModel().getInteger("id");
		result= this.toolkitRepository.findOneToolkitById(id);
		
		return result;
	}
	
	@Override
	public void bind(final Request<Toolkit> request,final Toolkit entity,final Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;
		
		request.bind(entity, errors, "code", "title", "description", "notes", "link");

	}
	
	@Override
	public void validate(final Request<Toolkit> request, final Toolkit entity, final Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;

		if (!errors.hasErrors("code")) {
			Toolkit existing;

			existing = this.toolkitRepository.findOneToolkitByCode(entity.getCode());
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
	public void update(final Request<Toolkit> request, final Toolkit entity) {
		assert request != null;
		assert entity != null;

		entity.setPublished(false);;
		this.toolkitRepository.save(entity);
	}

}
