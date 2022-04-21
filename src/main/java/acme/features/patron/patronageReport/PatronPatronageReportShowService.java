package acme.features.patron.patronageReport;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.PatronageReport;
import acme.framework.components.models.Model;
import acme.framework.controllers.Request;
import acme.framework.services.AbstractShowService;
import acme.roles.Patron;

@Service
public class PatronPatronageReportShowService implements AbstractShowService<Patron, PatronageReport>{
	
	@Autowired
	protected PatronPatronageReportRepository repository;

	@Override
	public boolean authorise(final Request<PatronageReport> request) {
		assert request != null;

		boolean result;
		int patronageId;
		PatronageReport patronageR;
		
		patronageId = request.getModel().getInteger("id");
		patronageR = this.repository.findOnePatronageReportById(patronageId);
		result = patronageR.getPatronage().getId() == request.getPrincipal().getActiveRoleId();

		return result;
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

		request.unbind(entity, model, "sequenceNumber", "creationMoment");
	}
	
	
}
