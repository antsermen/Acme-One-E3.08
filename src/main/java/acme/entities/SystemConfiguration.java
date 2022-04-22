package acme.entities;

import javax.persistence.Entity;
import javax.validation.constraints.NotBlank;

import acme.framework.entities.AbstractEntity;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class SystemConfiguration extends AbstractEntity{
	
	// Serialisation identifier -----------------------------------------------
	
	protected static final long	serialVersionUID	= 1L;
	
	// Attributes -------------------------------------------------------------
	
	@NotBlank
	protected String systemCurrency;
	
	@NotBlank
	protected String acceptedCurrencies;
	
	@NotBlank
	protected String strongSpamTerms;
	
	protected double strongSpamThreshold;
	
	@NotBlank
	protected String weakSpamTerms;
	
	protected double weakSpamThreshold;

}
