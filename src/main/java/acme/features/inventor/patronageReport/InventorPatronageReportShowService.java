package acme.features.inventor.patronageReport;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.PatronageReport;
import acme.framework.components.models.Model;
import acme.framework.controllers.Request;
import acme.framework.services.AbstractShowService;
import acme.roles.Inventor;

@Service
public class InventorPatronageReportShowService implements AbstractShowService<Inventor, PatronageReport>{

	// Internal state

	@Autowired
	protected InventorPatronageReportRepository repository;

	// Interface 

	@Override
	public boolean authorise(final Request<PatronageReport> request) {
		assert request != null;
		final PatronageReport pr = this.repository.findOnePatronageReportById(request.getModel().getInteger("id"));
		final int inventor_id = pr.getPatronage().getInventor().getId();
		return request.getPrincipal().getActiveRoleId() == inventor_id;
	}

	@Override
	public PatronageReport findOne(final Request<PatronageReport> request) {
		assert request != null;

		PatronageReport result;
		int id;

		id = request.getModel().getInteger("id");
		result = this.repository.findOnePatronageReportById(id);

		return result;
	}

	@Override
	public void unbind(final Request<PatronageReport> request, final PatronageReport entity, final Model model) {
		assert request != null;
		assert entity != null;
		assert model != null;
		model.setAttribute("patronage", entity.getPatronage().getCode());
		model.setAttribute("patronages", this.repository.findManyPatronagesByInventorId(request.getPrincipal().getActiveRoleId(),true));
		final String sn = "0000" + entity.getId();
		entity.setSerialNumber(sn.substring(sn.length()-4));
		entity.setSequenceNumber(entity.getPatronage().getCode() + ":" + entity.getSerialNumber());		
		request.unbind(entity, model, "creationMoment", "memorandum", "link", "serialNumber", "sequenceNumber");

	}


}
