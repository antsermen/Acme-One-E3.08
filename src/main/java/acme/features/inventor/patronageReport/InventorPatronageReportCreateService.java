package acme.features.inventor.patronageReport;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.PatronageReport;
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

	// AbstractListService<Inventor, PatronageReport> interface ---------------------------

	@Override
	public boolean authorise(final Request<PatronageReport> request) {
		assert request != null;
		return true;
	}

	@Override
	public PatronageReport instantiate(final Request<PatronageReport> request) {
		final PatronageReport result = new PatronageReport();
		result.setCreationMoment(new Date());
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
	}

	@Override
	public void unbind(final Request<PatronageReport> request, final PatronageReport entity, final Model model) {
		assert request != null;
		assert entity != null;
		assert model != null;
		
		request.unbind(entity, model,"memorandum","link");
		model.setAttribute("confirm", "false");
		model.setAttribute("patronageCode", this.repository.findPatronageById(request.getModel().getInteger("id")).getCode());
		model.setAttribute("patronageId", request.getModel().getInteger("id"));
	}	

	@Override
	public void create(final Request<PatronageReport> request, final PatronageReport entity) {
		assert request != null;
		assert entity != null;
		entity.setCreationMoment(new Date());
		//final String sn = "0000" + entity.getId();
		//entity.setSerialNumber(sn.substring(sn.length()-4));
		//entity.setSequenceNumber(entity.getPatronage().getCode() + ":" + entity.getSerialNumber());	
		entity.setSequenceNumber("sdfsf");
		entity.setSerialNumber("sfs");
		entity.setPatronage(this.repository.findPatronageById(request.getModel().getInteger("id")));
		this.repository.save(entity);

	}

} 