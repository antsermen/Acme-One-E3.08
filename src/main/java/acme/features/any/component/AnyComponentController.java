package acme.features.any.component;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import acme.entities.Component;
import acme.framework.controllers.AbstractController;
import acme.framework.roles.Any;

@Controller
public class AnyComponentController extends AbstractController<Any, Component>{
	@Autowired
	protected AnyComponentListService	listService;
	@Autowired
	protected AnyComponentShowService showService;
	
	// Constructors -----------------------------------------------------------


	@PostConstruct
	protected void initialise() {
		super.addCommand("list", this.listService);
		super.addCommand("show", this.showService);
	}

}
