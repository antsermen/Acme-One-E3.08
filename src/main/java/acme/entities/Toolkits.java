package acme.entities;

import java.util.Collection;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.URL;

import acme.framework.entities.AbstractEntity;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Toolkits extends AbstractEntity{
  
	// Serialisation identifier -----------------------------------------------
	
	protected static final long serialVersionUID = 1L;
	
	// Attributes -------------------------------------------------------------
  
	@NotBlank
	@Length(min = 5, max = 100)
	protected String title;
	
	@Column(unique = true)
	@Pattern(regexp = "^[A-Z]{3}-[0-9]{3}(-[A-Z])?$")
	@NotNull
	protected String code;
	
	@NotBlank
	@Length(min = 5, max = 255)
	protected String description;
	
	@NotBlank
	@Length(min = 5, max = 255)
	protected String notes;
	
	@URL
	protected String link;
	
	// Derived attributes -----------------------------------------------------
	
	// Relationships ----------------------------------------------------------
  
	
	@Valid
	@OneToOne(optional=false)
	protected Tool tool;
  
	
	@Valid
	@OneToMany()
	protected Collection<Component> component;
	
}
