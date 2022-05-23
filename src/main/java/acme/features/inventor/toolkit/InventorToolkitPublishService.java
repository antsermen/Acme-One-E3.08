package acme.features.inventor.toolkit;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import SpamDetector.Spam_Detector.SpamDetector;
import acme.entities.Toolkit;
import acme.framework.components.models.Model;
import acme.framework.controllers.Errors;
import acme.framework.controllers.Request;
import acme.framework.services.AbstractUpdateService;
import acme.roles.Inventor;

@Service
public class InventorToolkitPublishService implements AbstractUpdateService<Inventor,Toolkit>{
	
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
		
		request.bind(entity, errors,"code","title", "description", "notes", "link","published");
	}
	
	@Override
	public void unbind(final Request<Toolkit> request, final Toolkit entity, final Model model) {
		assert request != null;
		assert entity != null;
		assert model != null;

		request.unbind(entity, model, "code","title", "description", "notes", "link","published");

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
	public void update(final Request<Toolkit> request, final Toolkit entity) {
		assert request != null;
		assert entity != null;

		entity.setPublished(true);
		this.repository.save(entity);
	}

}
