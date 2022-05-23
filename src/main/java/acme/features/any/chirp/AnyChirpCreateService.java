package acme.features.any.chirp;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import SpamDetector.Spam_Detector.SpamDetector;
import acme.entities.Chirp;
import acme.features.inventor.item.InventorItemRepository;
import acme.framework.components.models.Model;
import acme.framework.controllers.Errors;
import acme.framework.controllers.Request;
import acme.framework.roles.Any;
import acme.framework.services.AbstractCreateService;

@Service
public class AnyChirpCreateService implements AbstractCreateService<Any, Chirp>{
	
	// Internal state ======================================================
	
	@Autowired
	protected AnyChirpRepository repository;
	@Autowired
	protected InventorItemRepository systemRepository;
	
	// AbstractCreateService<Any, Chirp> interface =========================
	
	@Override
	public boolean authorise(final Request<Chirp> request) {
		assert request != null;
		
		return true;
	}
	
	@Override
	public Chirp instantiate(final Request<Chirp> request) {
		assert request != null;

		Chirp result;
		
		final Date moment = new Date(System.currentTimeMillis());
		result = new Chirp();
		result.setCreationMoment(moment);
		result.setTitle("");	
		result.setAuthor("");
		result.setBody("");
		
		return result;
	}

	@Override
	public void bind(final Request<Chirp> request, final Chirp entity, final Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;
			
		request.bind(entity, errors, "title", "author", "body", "email");
	}
	
	@Override
	public void validate(final Request<Chirp> request, final Chirp entity, final Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;
		boolean confirmation;

		confirmation = request.getModel().getBoolean("confirmation");
		errors.state(request, confirmation, "confirmation", "javax.validation.constraints.AssertTrue.message");
		if(!errors.hasErrors("title")) {
			errors.state(request, !SpamDetector.spamDetector(entity.getTitle(), this.systemRepository.findSystemConfiguration().getWeakSpamTerms(), 
				this.systemRepository.findSystemConfiguration().getStrongSpamTerms(), 
				this.systemRepository.findSystemConfiguration().getWeakSpamThreshold(),
				this.systemRepository.findSystemConfiguration().getStrongSpamThreshold()), "title", "any.chirp.form.error.title.spam");
		}
		if(!errors.hasErrors("author")) {
			errors.state(request, !SpamDetector.spamDetector(entity.getAuthor(), this.systemRepository.findSystemConfiguration().getWeakSpamTerms(), 
				this.systemRepository.findSystemConfiguration().getStrongSpamTerms(), 
				this.systemRepository.findSystemConfiguration().getWeakSpamThreshold(),
				this.systemRepository.findSystemConfiguration().getStrongSpamThreshold()), "author", "any.chirp.form.error.author.spam");
		}
		if(!errors.hasErrors("body")) {
			errors.state(request, !SpamDetector.spamDetector(entity.getBody(), this.systemRepository.findSystemConfiguration().getWeakSpamTerms(), 
				this.systemRepository.findSystemConfiguration().getStrongSpamTerms(), 
				this.systemRepository.findSystemConfiguration().getWeakSpamThreshold(),
				this.systemRepository.findSystemConfiguration().getStrongSpamThreshold()), "body", "any.chirp.form.error.body.spam");
		}

	}
	
	@Override
	public void unbind(final Request<Chirp> request, final Chirp entity, final Model model) {
		assert request != null;
		assert entity != null;
		assert model != null;

		request.unbind(entity, model, "title", "author", "body", "email");
		model.setAttribute("confirmation", false);
		model.setAttribute("readonly", false);
	}
	
	

	@Override
	public void create(final Request<Chirp> request, final Chirp entity) {
		assert request != null;
		assert entity != null;

		Date moment;

		moment = new Date(System.currentTimeMillis());
		entity.setCreationMoment(moment);;
		this.repository.save(entity);

	}

}
