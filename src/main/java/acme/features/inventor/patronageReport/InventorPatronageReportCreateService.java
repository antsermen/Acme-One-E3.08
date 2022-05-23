package acme.features.inventor.patronageReport;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import SpamDetector.Spam_Detector.SpamDetector;
import acme.entities.PatronageReport;
import acme.features.inventor.item.InventorItemRepository;
import acme.framework.components.models.Model;
import acme.framework.controllers.Errors;
import acme.framework.controllers.Request;
import acme.framework.services.AbstractCreateService;
import acme.roles.Inventor;

@Service
public class InventorPatronageReportCreateService implements AbstractCreateService<Inventor, PatronageReport> {

	// Internal state -------------------------------------------------------------------

	@Autowired
	protected InventorPatronageReportRepository repository;
	@Autowired
	protected InventorItemRepository inventorItemRepository;

	// AbstractListService<Inventor, PatronageReport> interface ---------------------------

	@Override
	public boolean authorise(final Request<PatronageReport> request) {
		assert request != null;
		return true;
	}

	@Override
	public PatronageReport instantiate(final Request<PatronageReport> request) {
		final PatronageReport result = new PatronageReport();
		result.setMemorandum("");
		result.setLink("");
		return result;
	}

	@Override
	public void bind(final Request<PatronageReport> request, final PatronageReport entity, final Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;
		entity.setPatronage(this.repository.findPatronageByCode(request.getModel().getAttribute("patronageCode").toString()));
		entity.setVersion(0);
		entity.setCreationMoment(new Date());
		final String sn = "0000" + String.valueOf(request.getModel().getInteger("id"));		
		entity.setSerialNumber(sn.substring(sn.length()-4));
		entity.setSequenceNumber(entity.getPatronage().getCode() + ":" + entity.getSerialNumber());	

		
		request.bind(entity, errors,"memorandum","link");
	}

	@Override
	public void validate(final Request<PatronageReport> request, final PatronageReport entity, final Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;
		if(!errors.hasErrors("confirm")) {
			final Boolean isConfirmed = request.getModel().getBoolean("confirm");
			errors.state(request, isConfirmed, "confirm", "javax.validation.constraints.AssertTrue.message");
		}
		if(!errors.hasErrors("memorandum")) {
			errors.state(request, !SpamDetector.spamDetector(entity.getMemorandum(), this.inventorItemRepository.findSystemConfiguration().getWeakSpamTerms(), 
				this.inventorItemRepository.findSystemConfiguration().getStrongSpamTerms(), 
				this.inventorItemRepository.findSystemConfiguration().getWeakSpamThreshold(),
				this.inventorItemRepository.findSystemConfiguration().getStrongSpamThreshold()), "memorandum", "inventor.patronage-report.form.error.memorandum.spam");
		}

	}

	@Override
	public void unbind(final Request<PatronageReport> request, final PatronageReport entity, final Model model) {
		assert request != null;
		assert entity != null;
		assert model != null;
		
		request.unbind(entity, model,"memorandum","link", "version", "sequenceNumber", "serialNumber");
		model.setAttribute("confirm", "false");
		model.setAttribute("id", request.getModel().getInteger("id"));
		model.setAttribute("patronageCode", this.repository.findPatronageById(request.getModel().getInteger("id")).getCode());
		model.setAttribute("patronageId", request.getModel().getInteger("id"));


	}	

	@Override
	public void create(final Request<PatronageReport> request, final PatronageReport entity) {
		assert request != null;
		assert entity != null;
		this.repository.save(entity);

	}

} 