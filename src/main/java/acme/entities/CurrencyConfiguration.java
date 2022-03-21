package acme.entities;

import javax.persistence.Entity;
import javax.validation.constraints.NotBlank;

import acme.framework.entities.AbstractEntity;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class CurrencyConfiguration extends AbstractEntity {
	
	// Serialisation identifier -----------------------------------------------

	protected static final long		serialVersionUID	= 1L;

	// Attributes -------------------------------------------------------------
		
	@NotBlank
	protected String defaultCurrency;
	
	@NotBlank
	protected String acceptedCurrencies;
	
	// Derived attributes -----------------------------------------------------
	
	// Relationships ----------------------------------------------------------
	
	
}
