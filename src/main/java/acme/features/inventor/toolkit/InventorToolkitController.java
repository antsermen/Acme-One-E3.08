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
		
		@Autowired
		protected InventorToolkitPublishService publishToolkitService;
		
		@Autowired
		protected InventorCreateToolkitService createToolkitService;
		
		@Autowired
		protected InventorUpdateToolkitService updateToolkitService;
		
		@Autowired
		protected InventorDeleteToolkitService deleteToolkitService;
		// Constructors -----------------------------------------------------------


		@PostConstruct
		protected void initialise() {
			super.addCommand("list", "list", this.listToolkitsService);
			super.addCommand("show", this.showToolkitsService);
			super.addCommand("create", this.createToolkitService);
			super.addCommand("update", this.updateToolkitService);
			super.addCommand("delete", this.deleteToolkitService);
			
			super.addCommand("publish", "update", this.publishToolkitService);
		}
}
