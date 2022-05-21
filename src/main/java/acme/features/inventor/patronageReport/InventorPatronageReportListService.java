package acme.features.inventor.patronageReport;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.PatronageReport;
import acme.framework.components.models.Model;
import acme.framework.controllers.Request;
import acme.framework.entities.Principal;
import acme.framework.services.AbstractListService;
import acme.framework.helpers.CollectionHelper;
import acme.roles.Inventor;


@Service
public class InventorPatronageReportListService implements AbstractListService<Inventor, PatronageReport> {

	// Internal state ---------------------------------------------------------

	@Autowired
	protected InventorPatronageReportRepository repository;

	// AbstractListService<Administrator, PatronageReport> interface --------------


	@Override
	public boolean authorise(final Request<PatronageReport> request) {
		assert request != null;

		return true;
	}

	@Override
	public Collection<PatronageReport> findMany(final Request<PatronageReport> request) {
		assert request != null;

		final Collection<PatronageReport> result;
		final Principal principal = request.getPrincipal();
		result = this.repository.findManyPatronageReportByInventorId(principal.getActiveRoleId());


		return result;
	}
	
	@Override
	public void unbind(final Request<PatronageReport> request, final Collection<PatronageReport> entities, final Model model) {
		assert request != null;
		assert !CollectionHelper.someNull(entities);
		assert model != null;


		model.setAttribute("patronageId", request.getModel().getInteger("patronageId"));

	}

	@Override
	public void unbind(final Request<PatronageReport> request, final PatronageReport entity, final Model model) {
		assert request != null;
		assert entity != null;
		assert model != null;


	request.unbind(entity, model,"creationMoment", "memorandum");
	}

} 
