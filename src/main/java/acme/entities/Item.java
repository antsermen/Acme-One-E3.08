package acme.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.validation.Valid;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.URL;

import acme.framework.datatypes.Money;
import acme.framework.entities.AbstractEntity;
import acme.roles.Inventor;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Item extends AbstractEntity{
	
		// Serialisation identifier -----------------------------------------------
	
		protected static final long serialVersionUID = 1L;
		
		// Attributes -------------------------------------------------------------

		@NotBlank
		protected ItemType itemType;
		
		@NotBlank
		@Length(min = 5, max = 100)
		protected String name;
		
		@Column(unique = true)
		@Pattern(regexp = "^[A-Z]{3}-[0-9]{3}(-[A-Z])?$")
		@NotBlank
		protected String code;
		
		@NotBlank
		@Length(min = 5, max = 100)
		protected String technology;
		
		@NotBlank
		@Length(min = 5, max = 255)
		protected String description;
		
		@NotNull
		@Min(0)
		protected Money retailPrice;
		
		@URL
		protected String link;
		
		
		// Derived attributes -----------------------------------------------------
		
		// Relationships ----------------------------------------------------------
		
		@NotNull
		@Valid
		@ManyToOne()
		protected Inventor inventor;
}
