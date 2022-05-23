package acme.features.patron.dashboard;

import java.util.EnumMap;
import java.util.HashMap;
import java.util.Map;

import javax.persistence.Tuple;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.util.Pair;
import org.springframework.stereotype.Service;

import acme.entities.Status;
import acme.forms.PatronDashboard;
import acme.framework.components.models.Model;
import acme.framework.controllers.Request;
import acme.framework.entities.Principal;
import acme.framework.services.AbstractShowService;
import acme.roles.Patron;

@Service
public class PatronDashboardShowService implements AbstractShowService<Patron, PatronDashboard>{
	
	@Autowired
	protected PatronDashboardRepository repository;

	@Override
	public boolean authorise(final Request<PatronDashboard> request) {
		assert request != null;

		return true;
	}

	@Override
	public PatronDashboard findOne(final Request<PatronDashboard> request) {	
		assert request != null;
		final PatronDashboard result;
		Principal principal;

		principal = request.getPrincipal();
		
		final int id = principal.getActiveRoleId();
		
		Map<Status, Integer> totalNumberOfPatronages;
		Map<Pair<String,Status>,Double> averageBudgetOfPatronages;
		Map<Pair<String,Status>,Double> deviationBudgetOfPatronages;
		Map<Pair<String,Status>,Double> minimumBudgetOfPatronages;
		Map<Pair<String,Status>,Double> maximumBudgetOfPatronages;
		
		totalNumberOfPatronages = new EnumMap<Status, Integer>(Status.class);
		averageBudgetOfPatronages = new HashMap<Pair<String,Status>,Double>();
		deviationBudgetOfPatronages = new HashMap<Pair<String,Status>,Double>();
		minimumBudgetOfPatronages = new HashMap<Pair<String,Status>,Double>();
		maximumBudgetOfPatronages = new HashMap<Pair<String,Status>,Double>();
				
		totalNumberOfPatronages.put(Status.ACCEPTED, this.repository.totalNumberOfAcceptedPatronages(id));
		totalNumberOfPatronages.put(Status.PROPOSED, this.repository.totalNumberOfProposedPatronages(id));
		totalNumberOfPatronages.put(Status.DENIED, this.repository.totalNumberOfDeniedPatronages(id));
		
		for(final Tuple x: this.repository.indicatorsBudgetOfAcceptedPatronages(id)) {
			averageBudgetOfPatronages.put(Pair.of(x.get(0).toString(), Status.ACCEPTED), Double.parseDouble(x.get(1).toString()));
			deviationBudgetOfPatronages.put(Pair.of(x.get(0).toString(), Status.ACCEPTED), Double.parseDouble(x.get(2).toString()));
			minimumBudgetOfPatronages.put(Pair.of(x.get(0).toString(), Status.ACCEPTED), Double.parseDouble(x.get(3).toString()));
			maximumBudgetOfPatronages.put(Pair.of(x.get(0).toString(), Status.ACCEPTED), Double.parseDouble(x.get(4).toString()));
		}
		for(final Tuple x: this.repository.indicatorsBudgetOfProposedPatronages(id)) {
			averageBudgetOfPatronages.put(Pair.of(x.get(0).toString(), Status.PROPOSED), Double.parseDouble(x.get(1).toString()));
			deviationBudgetOfPatronages.put(Pair.of(x.get(0).toString(), Status.PROPOSED), Double.parseDouble(x.get(2).toString()));
			minimumBudgetOfPatronages.put(Pair.of(x.get(0).toString(), Status.PROPOSED), Double.parseDouble(x.get(3).toString()));
			maximumBudgetOfPatronages.put(Pair.of(x.get(0).toString(), Status.PROPOSED), Double.parseDouble(x.get(4).toString()));
		}
		for(final Tuple x: this.repository.indicatorsBudgetOfDeniedPatronages(id)) {
			averageBudgetOfPatronages.put(Pair.of(x.get(0).toString(), Status.DENIED), Double.parseDouble(x.get(1).toString()));
			deviationBudgetOfPatronages.put(Pair.of(x.get(0).toString(), Status.DENIED), Double.parseDouble(x.get(2).toString()));
			minimumBudgetOfPatronages.put(Pair.of(x.get(0).toString(), Status.DENIED), Double.parseDouble(x.get(3).toString()));
			maximumBudgetOfPatronages.put(Pair.of(x.get(0).toString(), Status.DENIED), Double.parseDouble(x.get(4).toString()));
		}
		result = new PatronDashboard();
		
		
		
		result.setTotalNumberOfPatronages(totalNumberOfPatronages);
		
		result.setAverageBudgetOfPatronages(averageBudgetOfPatronages);
		result.setDeviationBudgetOfPatronages(deviationBudgetOfPatronages);
		result.setMinimumBudgetOfPatronages(minimumBudgetOfPatronages);
		result.setMaximumBudgetOfPatronages(maximumBudgetOfPatronages);
		
		
		return result;
	}

	@Override
	public void unbind(final Request<PatronDashboard> request, final PatronDashboard entity, final Model model) {
		assert request != null;
		assert entity != null;
		assert model != null;
		
		request.unbind(entity, model,
									"totalNumberOfPatronages","averageBudgetOfPatronages","deviationBudgetOfPatronages","minimumBudgetOfPatronages","maximumBudgetOfPatronages");	
		
	}

}
