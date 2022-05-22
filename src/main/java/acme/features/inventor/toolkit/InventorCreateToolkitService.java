package acme.features.inventor.toolkit;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import SpamDetector.Spam_Detector.SpamDetector;
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
		result.setInventor(inventor);
		result.setPublished(false);
		result.setCode("");
		result.setDescription("");
		result.setLink("");
		result.setTitle("");
		result.setNotes("");

		return result;
	}
	
	@Override
	public void bind(final Request<Toolkit> request, final Toolkit entity, final Errors errors) {
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

			existing = this.repository.findOneToolkitByCode(entity.getCode());
			errors.state(request, existing == null || existing.getId() == entity.getId(), "code", "inventor.toolkit.form.error.code.duplicated");
		}
		if(!errors.hasErrors("title")) {
			errors.state(request, !SpamDetector.spamDetector(entity.getTitle(), this.repository.findSystemConfiguration().getWeakSpamTerms(), 
				this.repository.findSystemConfiguration().getStrongSpamTerms(), 
				this.repository.findSystemConfiguration().getWeakSpamThreshold(),
				this.repository.findSystemConfiguration().getStrongSpamThreshold()), "title", "inventor.toolkit.form.error.title.spam");
		}
		if(!errors.hasErrors("description")) {
			errors.state(request, !SpamDetector.spamDetector(entity.getDescription(), this.repository.findSystemConfiguration().getWeakSpamTerms(), 
				this.repository.findSystemConfiguration().getStrongSpamTerms(), 
				this.repository.findSystemConfiguration().getWeakSpamThreshold(),
				this.repository.findSystemConfiguration().getStrongSpamThreshold()), "description", "inventor.toolkit.form.error.description.spam");
		}
		if(!errors.hasErrors("legalStuff")) {
			errors.state(request, !SpamDetector.spamDetector(entity.getNotes(), this.repository.findSystemConfiguration().getWeakSpamTerms(), 
				this.repository.findSystemConfiguration().getStrongSpamTerms(), 
				this.repository.findSystemConfiguration().getWeakSpamThreshold(),
				this.repository.findSystemConfiguration().getStrongSpamThreshold()), "notes", "inventor.toolkit.form.error.notes.spam");
		}

	}
	
	@Override
	public void unbind(final Request<Toolkit> request, final Toolkit entity, final Model model) {
		assert request != null;
		assert entity != null;
		assert model != null;
		
		request.unbind(entity, model, "code", "title", "description", "notes", "link","published");
	}
	
	@Override
	public void create(final Request<Toolkit> request, final Toolkit entity) {
		assert request != null;
		assert entity != null;
		
		entity.setPublished(false);
		this.repository.save(entity);
	}


}
