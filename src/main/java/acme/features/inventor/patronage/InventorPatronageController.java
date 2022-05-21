package acme.features.inventor.patronage;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import acme.entities.Patronage;
import acme.framework.controllers.AbstractController;
import acme.roles.Inventor;

@Controller
@RequestMapping("/inventor/patronage/")
public class InventorPatronageController extends AbstractController<Inventor, Patronage>{
		// Internal state ---------------------------------------------------------
	
		@Autowired
		protected InventorListPatronageService listPatronageService;	
		@Autowired
		protected InventorShowPatronageService showPatronageService;
		@Autowired
		protected InventorPatronageAcceptService acceptPatronageService;
	
		@Autowired
		protected InventorPatronageDeniedService deniedPatronageService;
		
		
		// Constructors -----------------------------------------------------------


		@PostConstruct
		protected void initialise() {
			super.addCommand("list", this.listPatronageService);
			super.addCommand("show", this.showPatronageService);
			super.addCommand("denied","update", this.deniedPatronageService);
			super.addCommand("accept","update", this.acceptPatronageService);


		}
}
