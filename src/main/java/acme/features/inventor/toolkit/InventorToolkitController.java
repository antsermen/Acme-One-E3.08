package acme.features.inventor.toolkit;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import acme.entities.Toolkit;
import acme.framework.controllers.AbstractController;
import acme.roles.Inventor;

@Controller
@RequestMapping("/inventor/toolkit/")
public class InventorToolkitController extends AbstractController<Inventor, Toolkit>{
	
		// Internal state ---------------------------------------------------------
		
		@Autowired
		protected InventorListToolkitsService listToolkitsService;
		
		@Autowired
		protected InventorShowToolkitsService showToolkitsService;

		// Constructors -----------------------------------------------------------


		@PostConstruct
		protected void initialise() {
			super.addCommand("list-recent", "list", this.listToolkitsService);
			super.addCommand("show", this.showToolkitsService);
		}
}
