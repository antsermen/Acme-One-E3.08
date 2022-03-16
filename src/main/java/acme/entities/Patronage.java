package acme.entities;


import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.Valid;
import javax.validation.constraints.Future;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Past;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Positive;

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
	
		@NotNull
		@Pattern(regexp="^[A-Z]{3}-[0-9]{3}(-[A-Z])?$", message="default.error.conversion")
		@Column(unique=true)
		protected String code;
	
		@NotBlank
		@Length(max=255)
		protected String legalStuff;
	
		@NotBlank
		@Positive
		protected Money budget;
	
		@Past
		@Temporal(TemporalType.DATE)
		protected LocalDate	creationDate;
	
		@Future
		@Temporal(TemporalType.DATE)
		protected LocalDate startDate;
	
		@Future
		@Temporal(TemporalType.DATE)
		protected LocalDate	endDate;
	
		@URL
		protected String info;
	
		// Derived attributes -----------------------------------------------------
	 

		 protected Long period() {
			 
			return ChronoUnit.DAYS.between(this.startDate, this.endDate);
			 
		 }
	
	
	
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
