package acme.entities;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.OneToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Past;
import javax.validation.constraints.Pattern;

import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.URL;

import acme.framework.datatypes.Money;
import acme.framework.entities.AbstractEntity;

public class Rotelam extends AbstractEntity{
	
	protected static final long serialVersionUID = 1L;
	
	@Column(unique=true)
	@NotBlank
	@Pattern(regexp= "^[0-9]{2}:[0-9]{2}:[0-9]{4}$" )
	protected String code;
	
	@Temporal(TemporalType.TIMESTAMP)
	@Past
	@NotNull
	protected Date creationMoment;
	
	@NotBlank
	@Length(min=1,max=101)
	protected String subject;
	
	@NotBlank
	@Length(min=1,max=256)
	protected String explanation;
	
	@NotNull
	protected Date period;
	
	@NotBlank
	protected Money expenditure;
	
	@URL
	protected String moreInfo;
	
	@OneToOne()
	@NotNull
	@Valid
	protected Item items;

}
