package acme.forms;

import java.io.Serializable;
import java.util.Map;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ChimpumDashboard implements Serializable{

	private static final long serialVersionUID = 1L;
	
	Map<String,Double> ratioArtefactsChimpumByCurrency;
	Map<String,Double> averageBudgetByCurrency;
	Map<String,Double> deviationBudgetByCurrency;
	Map<String,Double> maximumBudgetByCurrency;
	Map<String,Double> minimumBudgetByCurrency;

}
