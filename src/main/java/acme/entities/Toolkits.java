package acme.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.validation.constraints.Min;
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
	@Length(min = 5, max = 101)
	protected String title;
	
	@Column(unique = true)
	@Pattern(regexp = "^[A-Z]{3}-[0-9]{3}(-[A-Z])?$")
	@NotNull
	protected String code;
	
	@NotBlank
	@Length(min = 5, max = 256)
	protected String description;
	
	@NotBlank
	@Length(min = 5, max = 256)
	protected String notes;
	
	@URL
	protected String link;
	
	// Derived attributes -----------------------------------------------------
	
	// Relationships ----------------------------------------------------------
  
   @OneToOne(optional = false, mappedBy = "toolkits", cascade = CascadeType.ALL)
    private Tool tool;
  
   @OneToMany(optional = true, mappedBy = "toolkits", cascade = CascadeType.ALL)
    private List<Component> component;
	
}
