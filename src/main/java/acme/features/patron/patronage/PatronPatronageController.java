package acme.features.patron.patronage;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import acme.entities.Patronage;
import acme.framework.controllers.AbstractController;
import acme.roles.Patron;

@Controller
public class PatronPatronageController extends AbstractController<Patron, Patronage> {

	// Internal state ---------------------------------------------------------

	@Autowired PatronPatronageShowService showService;
	@Autowired PatronPatronageListService listService;
	@Autowired PatronPatronageUpdateService updateService;
	@Autowired PatronPatronagePublishService publishService;
	@Autowired PatronPatronageCreateService createService;
	@Autowired PatronPatronageDeleteService deleteService;
	
	// Constructors -----------------------------------------------------------


	@PostConstruct
	protected void initialise() {
		super.addCommand("show", this.showService);
		super.addCommand("list", this.listService);
		super.addCommand("update", this.updateService);
		super.addCommand("publish","update", this.publishService);
		super.addCommand("create", this.createService);
		super.addCommand("delete", this.deleteService);
	}
}
	

