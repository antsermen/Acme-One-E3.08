package acme.features.inventor.item;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import SpamDetector.Spam_Detector.SpamDetector;
import acme.entities.Item;
import acme.framework.components.models.Model;
import acme.framework.controllers.Errors;
import acme.framework.controllers.Request;
import acme.framework.services.AbstractUpdateService;
import acme.roles.Inventor;

@Service
public class InventorItemUpdateService implements AbstractUpdateService<Inventor, Item> {
	
	@Autowired
	protected InventorItemRepository inventorItemRepository;

	@Override
	public boolean authorise(final Request<Item> request) {
		assert request != null;
		
		final Item item = this.inventorItemRepository.findItemById(request.getModel().getInteger("id"));
		final boolean published = item.isPublished();
		final Integer owner_id = item.getInventor().getId();
		
		return request.getPrincipal().getActiveRoleId() == owner_id && !published;
	}

	@Override
	public void bind(final Request<Item> request, final Item entity, final Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;
		
		request.bind(entity, errors, "itemType", "name", "code", "technology", "description", "retailPrice", "link");
	}

	@Override
	public void unbind(final Request<Item> request, final Item entity, final Model model) {
		assert request != null;
		assert entity != null;
		assert model != null;

		request.unbind(entity, model, "itemType", "name", "code", "technology", "description", "retailPrice", "link", "published");

	}

	@Override
	public Item findOne(final Request<Item> request) {
		assert request != null;
		return this.inventorItemRepository.findItemById(request.getModel().getInteger("id"));
	}

	@Override
	public void validate(final Request<Item> request, final Item entity, final Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;
		
		if(!errors.hasErrors("code")) {
			final Item duplicated = this.inventorItemRepository.findItemByCode(entity.getCode());
			errors.state(request, duplicated == null || duplicated.getId()==entity.getId(),"code", "inventor.item.form.error.code.duplicated");
			final Item modified = this.inventorItemRepository.findItemById(entity.getId());
			errors.state(request, modified.getCode().equals(entity.getCode()), "code", "inventor.item.form.error.code.modified");
		}
		if(!errors.hasErrors("retailPrice")) {
			errors.state(request, entity.getRetailPrice().getAmount() > 0, "retailPrice", "inventor.item.form.error.retailPrice.negative");
		}
		if(!errors.hasErrors("name")) {
			errors.state(request, !SpamDetector.spamDetector(entity.getName(), this.inventorItemRepository.findSystemConfiguration().getWeakSpamTerms(), 
				this.inventorItemRepository.findSystemConfiguration().getStrongSpamTerms(), 
				this.inventorItemRepository.findSystemConfiguration().getWeakSpamThreshold(),
				this.inventorItemRepository.findSystemConfiguration().getStrongSpamThreshold()), "name", "inventor.item.form.error.name.spam");
		}
		if(!errors.hasErrors("technology")) {
			errors.state(request, !SpamDetector.spamDetector(entity.getTechnology(), this.inventorItemRepository.findSystemConfiguration().getWeakSpamTerms(), 
				this.inventorItemRepository.findSystemConfiguration().getStrongSpamTerms(), 
				this.inventorItemRepository.findSystemConfiguration().getWeakSpamThreshold(),
				this.inventorItemRepository.findSystemConfiguration().getStrongSpamThreshold()), "technology", "inventor.item.form.error.technology.spam");
		}
		if(!errors.hasErrors("description")) {
			errors.state(request, !SpamDetector.spamDetector(entity.getDescription(), this.inventorItemRepository.findSystemConfiguration().getWeakSpamTerms(), 
				this.inventorItemRepository.findSystemConfiguration().getStrongSpamTerms(), 
				this.inventorItemRepository.findSystemConfiguration().getWeakSpamThreshold(),
				this.inventorItemRepository.findSystemConfiguration().getStrongSpamThreshold()), "description", "inventor.item.form.error.description.spam");
		}

	}

	@Override
	public void update(final Request<Item> request, final Item entity) {
		assert request != null;
		assert entity != null;

		this.inventorItemRepository.save(entity);
	}
	
}
