package acme.entities;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.validation.Valid;
import javax.validation.constraints.Min;

import acme.framework.entities.AbstractEntity;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Quantity extends AbstractEntity{
	
	// Serialisation identifier -----------------------------------------------
	
	protected static final long serialVersionUID = 1L;
		
	// Attributes -------------------------------------------------------------
	
	@Min(1)
	protected Integer itemsNumber;
	
	// Derived attributes -----------------------------------------------------
	
	// Relationships ----------------------------------------------------------
	

	@Valid
	@ManyToOne(optional = false)
	protected Item item;
	
	@Valid
	@ManyToOne(optional = false)
	protected Toolkit toolkit;
}
