package acme.entities;


import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.URL;

import acme.framework.datatypes.Money;
import acme.framework.entities.AbstractEntity;
import acme.roles.Inventor;
import acme.roles.Patron;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter

public class Patronage extends AbstractEntity{
	
		// Serialisation identifier -----------------------------------------------
	
		protected static final long	serialVersionUID = 1L;
		
		// Attributes -------------------------------------------------------------
	
		protected Status status;
	
		@Pattern(regexp="^[A-Z]{3}-[0-9]{3}(-[A-Z])?$")
		@Column(unique=true)
		protected String code;
	
		@NotBlank
		@Length(min = 1, max = 255)
		protected String legalStuff;
		
		@Valid
		@NotNull
		protected Money budget;
		
		@Temporal(TemporalType.TIMESTAMP)
		protected Date creationDate;
		
		@NotNull
		@Temporal(TemporalType.TIMESTAMP)
		protected Date startDate;
		
		@NotNull
		@Temporal(TemporalType.TIMESTAMP)
		protected Date deadline;
	
		@URL
		protected String info;
		
		protected boolean published;
	
		// Derived attributes -----------------------------------------------------
		
		
		
		// Relationships ----------------------------------------------------------
		 
		@NotNull
		@Valid
		@ManyToOne(optional=false)
		protected Patron patron;
		
		@NotNull
		@Valid
		@ManyToOne(optional=false)
		protected Inventor inventor;
		 
		 
}
