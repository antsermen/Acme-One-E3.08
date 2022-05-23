package acme.features.administrator.announcement;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import SpamDetector.Spam_Detector.SpamDetector;
import acme.entities.Announcement;
import acme.features.inventor.item.InventorItemRepository;
import acme.framework.components.models.Model;
import acme.framework.controllers.Errors;
import acme.framework.controllers.Request;
import acme.framework.roles.Administrator;
import acme.framework.services.AbstractCreateService;

@Service
public class AdministratorAnnouncementCreateService implements AbstractCreateService<Administrator, Announcement> {
	
	@Autowired
	protected AdministratorAnnouncementRepository repository;
	@Autowired
	protected InventorItemRepository systemRepository;
	
	@Override
	public boolean authorise(final Request<Announcement> request) {
		assert request != null;
		return true;
	}

	@Override
	public void bind(final Request<Announcement> request, final Announcement entity, final Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;
		
		request.bind(entity, errors, "title", "body", "critical", "link");
	}

	@Override
	public void unbind(final Request<Announcement> request, final Announcement entity, final Model model) {
		assert request != null;
		assert entity != null;
		assert model != null;

		request.unbind(entity, model, "title", "body", "critical", "link");
		model.setAttribute("confirmation", false);
		model.setAttribute("readonly", false);
		
	}

	@Override
	public Announcement instantiate(final Request<Announcement> request) {
		assert request != null;

		Announcement result;
		final Date moment = new Date(System.currentTimeMillis() - 1);
		result = new Announcement();
		result.setCreationMoment(moment);
		result.setTitle("");	
		result.setBody("");
		result.setLink("");
		result.setCritical(false);

		return result;
	}

	@Override
	public void validate(final Request<Announcement> request, final Announcement entity, final Errors errors) {
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
				this.systemRepository.findSystemConfiguration().getStrongSpamThreshold()), "title", "administrator.announcement.form.error.title.spam");
		}
		if(!errors.hasErrors("body")) {
			errors.state(request, !SpamDetector.spamDetector(entity.getBody(), this.systemRepository.findSystemConfiguration().getWeakSpamTerms(), 
				this.systemRepository.findSystemConfiguration().getStrongSpamTerms(), 
				this.systemRepository.findSystemConfiguration().getWeakSpamThreshold(),
				this.systemRepository.findSystemConfiguration().getStrongSpamThreshold()), "body", "administrator.announcement.form.error.body.spam");
		}

		
	}

	@Override
	public void create(final Request<Announcement> request, final Announcement entity) {
		assert request != null;
		assert entity != null;

		Date moment;

		moment = new Date(System.currentTimeMillis() - 1);
		entity.setCreationMoment(moment);;
		this.repository.save(entity);
		
	}
	

	


}
