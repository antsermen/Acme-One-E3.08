package acme.features.administrator.dashboard;

import java.util.HashMap;
import java.util.Map;

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
		
		final Integer	totalNumberOfComponents;
		final Map<Pair<String, String>, Double> averageRetailPriceOfComponents;
		final Map<Pair<String, String>, Double> deviationRetailPriceOfComponents;
		final Map<Pair<String, String>, Double> minimumRetailPriceOfComponents;
		final Map<Pair<String, String>, Double> maximumRetailPriceOfComponents;
		
		totalNumberOfComponents = this.repository.totalNumberOfComponents();
		
		averageRetailPriceOfComponents = new HashMap<Pair<String, String>, Double>();	
		this.repository.averageRetailPriceOfComponents("EUR").stream()
		.forEach(x->averageRetailPriceOfComponents.put(Pair.of("EUR", x.get(0).toString()),
			Double.parseDouble(x.get(1).toString())));
		this.repository.averageRetailPriceOfComponents("GBP").stream()
		.forEach(x->averageRetailPriceOfComponents.put(Pair.of("GBP", x.get(0).toString()),
			Double.parseDouble(x.get(1).toString())));
		this.repository.averageRetailPriceOfComponents("USD").stream()
		.forEach(x->averageRetailPriceOfComponents.put(Pair.of("USD", x.get(0).toString()),
			Double.parseDouble(x.get(1).toString())));
		
		deviationRetailPriceOfComponents = new HashMap<Pair<String, String>, Double>();	
		this.repository.deviationRetailPriceOfComponents("EUR").stream()
		.forEach(x->deviationRetailPriceOfComponents.put(Pair.of("EUR", x.get(0).toString()),
			Double.parseDouble(x.get(1).toString())));
		this.repository.deviationRetailPriceOfComponents("GBP").stream()
		.forEach(x->deviationRetailPriceOfComponents.put(Pair.of("GBP", x.get(0).toString()),
			Double.parseDouble(x.get(1).toString())));
		this.repository.deviationRetailPriceOfComponents("USD").stream()
		.forEach(x->deviationRetailPriceOfComponents.put(Pair.of("USD", x.get(0).toString()),
			Double.parseDouble(x.get(1).toString())));
		
		minimumRetailPriceOfComponents = new HashMap<Pair<String, String>, Double>();	
		this.repository.minimumRetailPriceOfComponents("EUR").stream()
		.forEach(x->minimumRetailPriceOfComponents.put(Pair.of("EUR", x.get(0).toString()),
			Double.parseDouble(x.get(1).toString())));
		this.repository.minimumRetailPriceOfComponents("GBP").stream()
		.forEach(x->minimumRetailPriceOfComponents.put(Pair.of("GBP", x.get(0).toString()),
			Double.parseDouble(x.get(1).toString())));
		this.repository.minimumRetailPriceOfComponents("USD").stream()
		.forEach(x->minimumRetailPriceOfComponents.put(Pair.of("USD", x.get(0).toString()),
			Double.parseDouble(x.get(1).toString())));
		
		maximumRetailPriceOfComponents = new HashMap<Pair<String, String>, Double>();	
		this.repository.maximumRetailPriceOfComponents("EUR").stream()
		.forEach(x->maximumRetailPriceOfComponents.put(Pair.of("EUR", x.get(0).toString()),
			Double.parseDouble(x.get(1).toString())));
		this.repository.maximumRetailPriceOfComponents("GBP").stream()
		.forEach(x->maximumRetailPriceOfComponents.put(Pair.of("GBP", x.get(0).toString()),
			Double.parseDouble(x.get(1).toString())));
		this.repository.maximumRetailPriceOfComponents("USD").stream()
		.forEach(x->maximumRetailPriceOfComponents.put(Pair.of("USD", x.get(0).toString()),
			Double.parseDouble(x.get(1).toString())));
		
		final Integer totalNumberOfTools;
		final Map<String, Double> averageRetailPriceOfTools;
		final Map<String, Double> deviationRetailPriceOfTools;
		final Map<String, Double> minimumRetailPriceOfTools;
		final Map<String, Double> maximumRetailPriceOfTools;
		
		totalNumberOfTools = this.repository.totalNumberOfTools();
		
		averageRetailPriceOfTools = new HashMap<String, Double>();
		final Double averageEURRetailPriceOfTools =  this.repository.averageRetailPriceOfTools("EUR");
		final Double averageGBPRetailPriceOfTools = this.repository.averageRetailPriceOfTools("GBP");
		final Double averagUSDRetailPriceOfTools = this.repository.averageRetailPriceOfTools("USD");
		averageRetailPriceOfTools.put("EUR", averageEURRetailPriceOfTools);
		averageRetailPriceOfTools.put("GBP", averageGBPRetailPriceOfTools);
		averageRetailPriceOfTools.put("USD", averagUSDRetailPriceOfTools);
		
		deviationRetailPriceOfTools = new HashMap<String, Double>();
		final Double deviationEURRetailPriceOfTools = this.repository.deviationRetailPriceOfTools("EUR");
		final Double deviationGBPRetailPriceOfTools = this.repository.deviationRetailPriceOfTools("GBP");
		final Double deviationUSDRetailPriceOfTools = this.repository.deviationRetailPriceOfTools("USD");
		deviationRetailPriceOfTools.put("EUR", deviationEURRetailPriceOfTools);
		deviationRetailPriceOfTools.put("GBP", deviationGBPRetailPriceOfTools);
		deviationRetailPriceOfTools.put("USD", deviationUSDRetailPriceOfTools);
		
		minimumRetailPriceOfTools = new HashMap<String, Double>();
		final Double minimumEURRetailPriceOfTools = this.repository.minimumRetailPriceOfTools("EUR");
		final Double minimumGBPRetailPriceOfTools = this.repository.minimumRetailPriceOfTools("GBP");
		final Double minimumUSDRetailPriceOfTools = this.repository.minimumRetailPriceOfTools("USD");
		minimumRetailPriceOfTools.put("EUR", minimumEURRetailPriceOfTools);
		minimumRetailPriceOfTools.put("GBP", minimumGBPRetailPriceOfTools);
		minimumRetailPriceOfTools.put("USD", minimumUSDRetailPriceOfTools);
		
		maximumRetailPriceOfTools = new HashMap<String, Double>();
		final Double maximumEURRetailPriceOfTools = this.repository.maximumRetailPriceOfTools("EUR");
		final Double maximumGBPRetailPriceOfTools = this.repository.maximumRetailPriceOfTools("GBP");
		final Double maximumUSDRetailPriceOfTools = this.repository.maximumRetailPriceOfTools("USD");
		maximumRetailPriceOfTools.put("EUR", maximumEURRetailPriceOfTools);
		maximumRetailPriceOfTools.put("GBP", maximumGBPRetailPriceOfTools);
		maximumRetailPriceOfTools.put("USD", maximumUSDRetailPriceOfTools);
		
		Map<Status, Integer> totalNumberOfPatronagesByStatus;
		Map<Status, Double> averageBudgetOfPatronagesByStatus;
		Map<Status, Double> deviationBudgetOfPatronagesByStatus;
		Map<Status, Double> minimumBudgetOfPatronagesByStatus;
		Map<Status, Double> maximumBudgetOfPatronagesByStatus;
		
		
		totalNumberOfPatronagesByStatus = new HashMap<Status, Integer>();
		final Integer totalNumberOfProposedPatronages = this.repository.totalNumberOfPatronagesByStatus(Status.PROPOSED);
		final Integer totalNumberOfAcceptedPatronages = this.repository.totalNumberOfPatronagesByStatus(Status.ACCEPTED);
		final Integer totalNumberOfDeniedPatronages = this.repository.totalNumberOfPatronagesByStatus(Status.DENIED);
		totalNumberOfPatronagesByStatus.put(Status.PROPOSED, totalNumberOfProposedPatronages);
		totalNumberOfPatronagesByStatus.put(Status.ACCEPTED, totalNumberOfAcceptedPatronages);
		totalNumberOfPatronagesByStatus.put(Status.DENIED, totalNumberOfDeniedPatronages);
		
		averageBudgetOfPatronagesByStatus = new HashMap<Status, Double>();
		final Double averageBudgetOfProposedPatronages = this.repository.averageBudgetOfPatronagesByStatus(Status.PROPOSED);
		final Double averageBudgetOfAcceptedPatronages = this.repository.averageBudgetOfPatronagesByStatus(Status.ACCEPTED);
		final Double averageBudgetOfDeniedPatronages = this.repository.averageBudgetOfPatronagesByStatus(Status.DENIED);
		averageBudgetOfPatronagesByStatus.put(Status.PROPOSED, averageBudgetOfProposedPatronages);
		averageBudgetOfPatronagesByStatus.put(Status.ACCEPTED, averageBudgetOfAcceptedPatronages);
		averageBudgetOfPatronagesByStatus.put(Status.DENIED, averageBudgetOfDeniedPatronages);
		
		deviationBudgetOfPatronagesByStatus = new HashMap<Status, Double>();
		final Double deviationBudgetOfProposedPatronages = this.repository.deviationBudgetOfPatronagesByStatus(Status.PROPOSED);
		final Double deviationBudgetOfAcceptedPatronages = this.repository.deviationBudgetOfPatronagesByStatus(Status.ACCEPTED);
		final Double deviationBudgetOfDeniedPatronages = this.repository.deviationBudgetOfPatronagesByStatus(Status.DENIED);
		deviationBudgetOfPatronagesByStatus.put(Status.PROPOSED, deviationBudgetOfProposedPatronages);
		deviationBudgetOfPatronagesByStatus.put(Status.ACCEPTED, deviationBudgetOfAcceptedPatronages);
		deviationBudgetOfPatronagesByStatus.put(Status.DENIED, deviationBudgetOfDeniedPatronages);
		
		minimumBudgetOfPatronagesByStatus = new HashMap<Status, Double>();
		final Double minimumBudgetOfProposedPatronages = this.repository.minimumBudgetOfPatronagesByStatus(Status.PROPOSED);
		final Double minimumBudgetOfAcceptedPatronages = this.repository.minimumBudgetOfPatronagesByStatus(Status.ACCEPTED);
		final Double minimumBudgetOfDeniedPatronages = this.repository.minimumBudgetOfPatronagesByStatus(Status.DENIED);
		minimumBudgetOfPatronagesByStatus.put(Status.PROPOSED, minimumBudgetOfProposedPatronages);
		minimumBudgetOfPatronagesByStatus.put(Status.ACCEPTED, minimumBudgetOfAcceptedPatronages);
		minimumBudgetOfPatronagesByStatus.put(Status.DENIED, minimumBudgetOfDeniedPatronages);
		
		maximumBudgetOfPatronagesByStatus = new HashMap<Status, Double>();
		final Double maximumBudgetOfProposedPatronages = this.repository.maximumBudgetOfPatronagesByStatus(Status.PROPOSED);
		final Double maximumBudgetOfAcceptedPatronages = this.repository.maximumBudgetOfPatronagesByStatus(Status.ACCEPTED);
		final Double maximumBudgetOfDeniedPatronages = this.repository.maximumBudgetOfPatronagesByStatus(Status.DENIED);
		maximumBudgetOfPatronagesByStatus.put(Status.PROPOSED, maximumBudgetOfProposedPatronages);
		maximumBudgetOfPatronagesByStatus.put(Status.ACCEPTED, maximumBudgetOfAcceptedPatronages);
		maximumBudgetOfPatronagesByStatus.put(Status.DENIED, maximumBudgetOfDeniedPatronages);
		
		
		
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
		
		result.setTotalNumberOfPatronagesByStatus(totalNumberOfPatronagesByStatus);
		result.setAverageBudgetOfPatronagesByStatus(averageBudgetOfPatronagesByStatus);
		result.setDeviationBudgetOfPatronagesByStatus(deviationBudgetOfPatronagesByStatus);
		result.setMinimumBudgetOfPatronagesByStatus(minimumBudgetOfPatronagesByStatus);
		result.setMaximumBudgetOfPatronagesByStatus(maximumBudgetOfPatronagesByStatus);
		
		
		return result;
	}

	@Override
	public void unbind(final Request<AdministratorDashboard> request, final AdministratorDashboard entity, final Model model) {
		assert request != null;
		assert entity != null;
		assert model != null;
		
		request.unbind(entity, model,"totalNumberOfComponents","averageRetailPriceOfComponents","deviationRetailPriceOfComponents","minimumRetailPriceOfComponents","maximumRetailPriceOfComponents",
									"totalNumberOfTools","averageRetailPriceOfTools","deviationRetailPriceOfTools","minimumRetailPriceOfTools","maximumRetailPriceOfTools",
									"totalNumberOfPatronagesByStatus","averageBudgetOfPatronagesByStatus","deviationBudgetOfPatronagesByStatus","minimumBudgetOfPatronagesByStatus","maximumBudgetOfPatronagesByStatus");
		
		
		
		
	}

}