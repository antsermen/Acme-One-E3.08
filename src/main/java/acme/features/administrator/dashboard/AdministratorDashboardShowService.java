package acme.features.administrator.dashboard;

import java.util.HashMap;
import java.util.Map;

import javax.persistence.Tuple;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.util.Pair;
import org.springframework.stereotype.Service;

import acme.entities.Status;
import acme.forms.AdministratorDashboard;
import acme.framework.components.models.Model;
import acme.framework.controllers.Request;
import acme.framework.roles.Administrator;
import acme.framework.services.AbstractShowService;

@Service
public class AdministratorDashboardShowService implements AbstractShowService<Administrator, AdministratorDashboard>{
	
	@Autowired
	protected AdministratorDashboardRepository repository;

	@Override
	public boolean authorise(final Request<AdministratorDashboard> request) {
		assert request != null;

		return true;
	}

	@Override
	public AdministratorDashboard findOne(final Request<AdministratorDashboard> request) {	
		assert request != null;
		final AdministratorDashboard result;	
		
		final int	totalNumberOfComponents;
		final Map<Pair<String, String>, Double> averageRetailPriceOfComponents;
		final Map<Pair<String, String>, Double> deviationRetailPriceOfComponents;
		final Map<Pair<String, String>, Double> minimumRetailPriceOfComponents;
		final Map<Pair<String, String>, Double> maximumRetailPriceOfComponents;
		
		totalNumberOfComponents = this.repository.totalNumberOfComponents();
		
		averageRetailPriceOfComponents = new HashMap<Pair<String, String>, Double>();	
		deviationRetailPriceOfComponents = new HashMap<Pair<String, String>, Double>();	
		minimumRetailPriceOfComponents = new HashMap<Pair<String, String>, Double>();	
		maximumRetailPriceOfComponents = new HashMap<Pair<String, String>, Double>();
		
		for (final Tuple x: this.repository.indicatorsRetailPriceOfComponents()){
			averageRetailPriceOfComponents.put(Pair.of(x.get(0).toString(), x.get(1).toString()), Double.parseDouble(x.get(2).toString()));
			deviationRetailPriceOfComponents.put(Pair.of(x.get(0).toString(), x.get(1).toString()), Double.parseDouble(x.get(3).toString()));
			minimumRetailPriceOfComponents.put(Pair.of(x.get(0).toString(), x.get(1).toString()), Double.parseDouble(x.get(4).toString()));
			maximumRetailPriceOfComponents.put(Pair.of(x.get(0).toString(), x.get(1).toString()), Double.parseDouble(x.get(5).toString()));
			
		}
			
		final int totalNumberOfTools;
		final Map<String, Double> averageRetailPriceOfTools;
		final Map<String, Double> deviationRetailPriceOfTools;
		final Map<String, Double> minimumRetailPriceOfTools;
		final Map<String, Double> maximumRetailPriceOfTools;
		
		totalNumberOfTools = this.repository.totalNumberOfTools();	
		averageRetailPriceOfTools = new HashMap<String, Double>();
		deviationRetailPriceOfTools = new HashMap<String, Double>();
		minimumRetailPriceOfTools = new HashMap<String, Double>();
		maximumRetailPriceOfTools = new HashMap<String, Double>();
		for (final Tuple x: this.repository.indicatorsRetailPriceOfTools()){
			averageRetailPriceOfTools.put(x.get(0).toString(), Double.parseDouble(x.get(1).toString()));
			deviationRetailPriceOfTools.put(x.get(0).toString(), Double.parseDouble(x.get(2).toString()));
			minimumRetailPriceOfTools.put(x.get(0).toString(), Double.parseDouble(x.get(3).toString()));
			maximumRetailPriceOfTools.put(x.get(0).toString(), Double.parseDouble(x.get(4).toString()));		
		}
		
		
		Map<Status, Integer> totalNumberOfPatronages;
		Map<Pair<String,Status>,Double> averageBudgetOfPatronages;
		Map<Pair<String,Status>,Double> deviationBudgetOfPatronages;
		Map<Pair<String,Status>,Double> minimumBudgetOfPatronages;
		Map<Pair<String,Status>,Double> maximumBudgetOfPatronages;
		
		totalNumberOfPatronages = new HashMap<Status, Integer>();
		averageBudgetOfPatronages = new HashMap<Pair<String,Status>,Double>();
		deviationBudgetOfPatronages = new HashMap<Pair<String,Status>,Double>();
		minimumBudgetOfPatronages = new HashMap<Pair<String,Status>,Double>();
		maximumBudgetOfPatronages = new HashMap<Pair<String,Status>,Double>();
				
		totalNumberOfPatronages.put(Status.ACCEPTED, this.repository.totalNumberOfAcceptedPatronages());
		totalNumberOfPatronages.put(Status.PROPOSED, this.repository.totalNumberOfProposedPatronages());
		totalNumberOfPatronages.put(Status.DENIED, this.repository.totalNumberOfDeniedPatronages());
		
		for(final Tuple x: this.repository.indicatorsBudgetOfAcceptedPatronages()) {
			averageBudgetOfPatronages.put(Pair.of(x.get(0).toString(), Status.ACCEPTED), Double.parseDouble(x.get(1).toString()));
			deviationBudgetOfPatronages.put(Pair.of(x.get(0).toString(), Status.ACCEPTED), Double.parseDouble(x.get(2).toString()));
			minimumBudgetOfPatronages.put(Pair.of(x.get(0).toString(), Status.ACCEPTED), Double.parseDouble(x.get(3).toString()));
			maximumBudgetOfPatronages.put(Pair.of(x.get(0).toString(), Status.ACCEPTED), Double.parseDouble(x.get(4).toString()));
		}
		for(final Tuple x: this.repository.indicatorsBudgetOfProposedPatronages()) {
			averageBudgetOfPatronages.put(Pair.of(x.get(0).toString(), Status.PROPOSED), Double.parseDouble(x.get(1).toString()));
			deviationBudgetOfPatronages.put(Pair.of(x.get(0).toString(), Status.PROPOSED), Double.parseDouble(x.get(2).toString()));
			minimumBudgetOfPatronages.put(Pair.of(x.get(0).toString(), Status.PROPOSED), Double.parseDouble(x.get(3).toString()));
			maximumBudgetOfPatronages.put(Pair.of(x.get(0).toString(), Status.PROPOSED), Double.parseDouble(x.get(4).toString()));
		}
		for(final Tuple x: this.repository.indicatorsBudgetOfDeniedPatronages()) {
			averageBudgetOfPatronages.put(Pair.of(x.get(0).toString(), Status.DENIED), Double.parseDouble(x.get(1).toString()));
			deviationBudgetOfPatronages.put(Pair.of(x.get(0).toString(), Status.DENIED), Double.parseDouble(x.get(2).toString()));
			minimumBudgetOfPatronages.put(Pair.of(x.get(0).toString(), Status.DENIED), Double.parseDouble(x.get(3).toString()));
			maximumBudgetOfPatronages.put(Pair.of(x.get(0).toString(), Status.DENIED), Double.parseDouble(x.get(4).toString()));
		}
				
		result = new AdministratorDashboard();
		
		result.setTotalNumberOfComponents(totalNumberOfComponents);
		result.setAverageRetailPriceOfComponents(averageRetailPriceOfComponents);
		result.setDeviationRetailPriceOfComponents(deviationRetailPriceOfComponents);
		result.setMinimumRetailPriceOfComponents(minimumRetailPriceOfComponents);
		result.setMaximumRetailPriceOfComponents(maximumRetailPriceOfComponents);
		
		result.setTotalNumberOfTools(totalNumberOfTools);
		result.setAverageRetailPriceOfTools(averageRetailPriceOfTools);
		result.setDeviationRetailPriceOfTools(deviationRetailPriceOfTools);
		result.setMinimumRetailPriceOfTools(minimumRetailPriceOfTools);
		result.setMaximumRetailPriceOfTools(maximumRetailPriceOfTools);
		
		result.setTotalNumberOfPatronages(totalNumberOfPatronages);
		result.setAverageBudgetOfPatronages(averageBudgetOfPatronages);
		result.setDeviationBudgetOfPatronages(deviationBudgetOfPatronages);
		result.setMinimumBudgetOfPatronages(minimumBudgetOfPatronages);
		result.setMaximumBudgetOfPatronages(maximumBudgetOfPatronages);
		
		
		return result;
	}

	@Override
	public void unbind(final Request<AdministratorDashboard> request, final AdministratorDashboard entity, final Model model) {
		assert request != null;
		assert entity != null;
		assert model != null;
		
		request.unbind(entity, model,"totalNumberOfComponents","averageRetailPriceOfComponents","deviationRetailPriceOfComponents","minimumRetailPriceOfComponents","maximumRetailPriceOfComponents",
									"totalNumberOfTools","averageRetailPriceOfTools","deviationRetailPriceOfTools","minimumRetailPriceOfTools","maximumRetailPriceOfTools",
									"totalNumberOfPatronages","averageBudgetOfPatronages","deviationBudgetOfPatronages","minimumBudgetOfPatronages","maximumBudgetOfPatronages");	
		
	}

}