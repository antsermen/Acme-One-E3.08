package acme.features.inventor.chimpum;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import acme.entities.Chimpum;
import acme.framework.controllers.AbstractController;
import acme.roles.Inventor;

@Controller
public class InventorChimpumController extends AbstractController<Inventor, Chimpum>{
	
	//Internal state-----------------------------------------------------------------------------
	
	 @Autowired InventorChimpumListService inventorChimpumListService;
	 @Autowired InventorChimpumShowService inventorChimpumShowService;
	 @Autowired InventorChimpumCreateService inventorChimpumCreateService;
	 @Autowired InventorChimpumUpdateService inventorChimpumUpdateService;
	 @Autowired InventorChimpumDeleteService inventorChimpumDeleteService;
	 
	 //Constructor-------------------------------------------------------------------------------
	 
	 @PostConstruct
	 protected void inialise() {
		 super.addCommand("list", this.inventorChimpumListService);
		 super.addCommand("show", this.inventorChimpumShowService);
		 super.addCommand("create", this.inventorChimpumCreateService);
		 super.addCommand("update", this.inventorChimpumUpdateService);
		 super.addCommand("delete", this.inventorChimpumDeleteService);
	 }
}
