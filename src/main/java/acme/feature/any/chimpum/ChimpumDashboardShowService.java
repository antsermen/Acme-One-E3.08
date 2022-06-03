package acme.feature.any.chimpum;

import java.util.HashMap;
import java.util.Map;

import javax.persistence.Tuple;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.forms.ChimpumDashboard;
import acme.framework.components.models.Model;
import acme.framework.controllers.Request;
import acme.framework.roles.Any;
import acme.framework.services.AbstractShowService;

@Service
public class ChimpumDashboardShowService implements AbstractShowService<Any, ChimpumDashboard>{
	
	@Autowired
	protected ChimpumDashboardRepository repository;

	@Override
	public boolean authorise(final Request<ChimpumDashboard> request) {
		assert request != null;
		return true;
	}

	@Override
	public ChimpumDashboard findOne(final Request<ChimpumDashboard> request) {
		assert request != null;
		final ChimpumDashboard result = new ChimpumDashboard();
		final Map<String,Double> ratioArtefactsChimpumByCurrency = new HashMap<String, Double>();
		final Map<String,Double> averageBudgetByCurrency = new HashMap<String, Double>();
		final Map<String,Double> deviationBudgetByCurrency = new HashMap<String, Double>();
		final Map<String,Double> maximumBudgetByCurrency = new HashMap<String, Double>();
		final Map<String,Double> minimumBudgetByCurrency = new HashMap<String, Double>();
		this.repository.ratioBudgetChimpumByCurrency();
		for (final Tuple x : this.repository.ratioBudgetChimpumByCurrency()) {
			ratioArtefactsChimpumByCurrency.put(x.get(0).toString(), Double.parseDouble(x.get(1).toString()));
		}
		for (final Tuple x : this.repository.indicatorsBudgetChimpumByCurrency()) {
			averageBudgetByCurrency.put(x.get(0).toString(), Double.parseDouble(x.get(1).toString()));
			deviationBudgetByCurrency.put(x.get(0).toString(), Double.parseDouble(x.get(2).toString()));
			maximumBudgetByCurrency.put(x.get(0).toString(), Double.parseDouble(x.get(3).toString()));
			minimumBudgetByCurrency.put(x.get(0).toString(), Double.parseDouble(x.get(4).toString()));
		}
		result.setAverageBudgetByCurrency(averageBudgetByCurrency);
		result.setDeviationBudgetByCurrency(deviationBudgetByCurrency);
		result.setMaximumBudgetByCurrency(maximumBudgetByCurrency);
		result.setMinimumBudgetByCurrency(minimumBudgetByCurrency);
		result.setRatioArtefactsChimpumByCurrency(ratioArtefactsChimpumByCurrency);
		return result;
	}

	@Override
	public void unbind(final Request<ChimpumDashboard> request, final ChimpumDashboard entity, final Model model) {
		assert request != null;
		assert entity != null;
		assert model != null;
		request.unbind(entity, model, "ratioArtefactsChimpumByCurrency","averageBudgetByCurrency","deviationBudgetByCurrency","maximumBudgetByCurrency","minimumBudgetByCurrency");
		
	}



}