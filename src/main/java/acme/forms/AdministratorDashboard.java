package acme.forms;

import java.io.Serializable;

import acme.framework.datatypes.Money;
import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
public class AdministratorDashboard implements Serializable {
	
	// Serialisation identifier -----------------------------------------------
	
	protected static final long	serialVersionUID = 1L;
	
	// Attributes -------------------------------------------------------------	
	
	Integer totalNumberOfComponents;
	Money averageRetailPriceOfComponents;
	Money deviationRetailPriceOfComponents;
	Money minimumRetailPriceOfComponents;
	Money maximumRetailPriceOfComponents;
	
	Integer totalNumberOfTools;
	Money averageRetailPriceOfTools;
	Money deviationRetailPriceOfTools;
	Money minimumRetailPriceOfTools;
	Money maximumRetailPriceOfTools;
	
	Integer totalNumberOfProposedPatronages;
	Money averageRetailPriceOfProposedPatronages;
	Money deviationRetailPriceOfProposedPatronages;
	Money minimumRetailPriceOfProposedPatronages;
	Money maximumRetailPriceOfProposedPatronages;
	
	Integer totalNumberOfAcceptedPatronages;
	Money averageRetailPriceOfAcceptedPatronages;
	Money deviationRetailPriceOfAcceptedPatronages;
	Money minimumRetailPriceOfAcceptedPatronages;
	Money maximumRetailPriceOfAcceptedPatronages;
	
	Integer totalNumberOfDeniedPatronages;
	Money averageRetailPriceOfDeniedPatronages;
	Money deviationRetailPriceOfDeniedPatronages;
	Money minimumRetailPriceOfDeniedPatronages;
	Money maximumRetailPriceOfDeniedPatronages;
	
	// Derived attributes -----------------------------------------------------
	
	// Relationships ----------------------------------------------------------
}
