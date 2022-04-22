package acme.forms;

import java.io.Serializable;
import java.util.Map;

import org.springframework.data.util.Pair;

import acme.entities.Status;
import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
public class AdministratorDashboard implements Serializable {
	
	// Serialisation identifier -----------------------------------------------
	
	protected static final long	serialVersionUID = 1L;
	
	// Attributes -------------------------------------------------------------	
	
	int totalNumberOfComponents;
	Map<Pair<String,String>,Double> averageRetailPriceOfComponents;
	Map<Pair<String,String>,Double> deviationRetailPriceOfComponents;
	Map<Pair<String,String>,Double> minimumRetailPriceOfComponents;
	Map<Pair<String,String>,Double> maximumRetailPriceOfComponents;
	
	int totalNumberOfTools;
	Map<String,Double> averageRetailPriceOfTools;
	Map<String,Double> deviationRetailPriceOfTools;
	Map<String,Double> minimumRetailPriceOfTools;
	Map<String,Double> maximumRetailPriceOfTools;
	
	Map<Status, Integer> totalNumberOfPatronages;
	Map<Pair<String,Status>,Double> averageBudgetOfPatronages;
	Map<Pair<String,Status>,Double> deviationBudgetOfPatronages;
	Map<Pair<String,Status>,Double> minimumBudgetOfPatronages;
	Map<Pair<String,Status>,Double> maximumBudgetOfPatronages;
	
	

	
	// Derived attributes -----------------------------------------------------
	
	// Relationships ----------------------------------------------------------
}
