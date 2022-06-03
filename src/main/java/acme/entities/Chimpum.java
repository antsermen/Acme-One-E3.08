package acme.entities;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Past;

import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.URL;

import acme.framework.datatypes.Money;
import acme.framework.entities.AbstractEntity;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Chimpum extends AbstractEntity {

	private static final long serialVersionUID = 1L;
	
	//Derived
	@NotNull
	protected String pattern;
	
	@Temporal(TemporalType.TIMESTAMP)
	@Past
	protected Date creationMoment;
	
	@NotBlank
	@Length(min = 1, max = 100)
	protected String title;
	
	@NotBlank
	@Length(min = 1, max = 255)
	protected String description;
	
	@NotNull
	@Temporal(TemporalType.TIMESTAMP)
	protected Date initialPeriod;
	
	@NotNull
	@Temporal(TemporalType.TIMESTAMP)
	protected Date finalPeriod;
	
	@Valid
	@NotNull
	protected Money budget;
	
	@URL
	protected String link;
	
	@Valid
	@NotNull
	@ManyToOne(optional=false)
	protected Item item;

}
