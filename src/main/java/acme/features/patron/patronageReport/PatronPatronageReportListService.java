package acme.features.patron.patronageReport;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.PatronageReport;
import acme.framework.components.models.Model;
import acme.framework.controllers.Request;
import acme.framework.services.AbstractListService;
import acme.roles.Patron;

@Service
public class PatronPatronageReportListService implements AbstractListService<Patron, PatronageReport> {
	
	@Autowired
	protected PatronPatronageReportRepository repository;
	
	@Override
	public boolean authorise(final Request<PatronageReport> request) {
		assert request != null;

		return true;
	}

	@Override
	public Collection<PatronageReport> findMany(final Request<PatronageReport> request) {
		assert request != null;

		final Collection<PatronageReport> result;
		int principal;

		principal = request.getModel().getInteger("patronageId");
		
		result = this.repository.findManyPatronagesReportByPatronId(principal);
		//Collection<PatronageReport> a = new ArrayList<>();
		return result;
	}

	@Override
	public void unbind(final Request<PatronageReport> request, final PatronageReport entity, final Model model) {
		assert request != null;
		assert entity != null;
		assert model != null;
		final String name = entity.getPatronage().getPatron().getIdentity().getFullName();
		request.unbind(entity, model,"sequenceNumber","creationMoment",name);
	}
}
