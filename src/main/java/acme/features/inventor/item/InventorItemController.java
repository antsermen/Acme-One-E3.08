package acme.features.inventor.item;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import acme.entities.Item;
import acme.framework.controllers.AbstractController;
import acme.roles.Inventor;

@Controller
public class InventorItemController extends AbstractController<Inventor, Item>{
	
	//Internal state-----------------------------------------------------------------------------
	
	 @Autowired InventorItemToolListService inventorItemListService;
	 @Autowired InventorItemComponentListService inventorComponentListService;
	 @Autowired InventorItemShowService inventorItemShowService;
	 @Autowired InventorItemCreateService inventorItemCreateService;
	 @Autowired InventorItemUpdateService inventorItemUpdateService;
	 @Autowired InventorItemPublishService inventorItemPublishService;
	 @Autowired InventorItemDeleteService inventorItemDeleteService;
	 
	 //Constructor-------------------------------------------------------------------------------
	 
	 @PostConstruct
	 protected void inialise() {
		 super.addCommand("my-tools", "list", this.inventorItemListService);
		 super.addCommand("show", this.inventorItemShowService);
		 super.addCommand("my-components", "list", this.inventorComponentListService);
		 super.addCommand("create", this.inventorItemCreateService);
		 super.addCommand("update", this.inventorItemUpdateService);
		 super.addCommand("publish","update", this.inventorItemPublishService);
		 super.addCommand("delete", this.inventorItemDeleteService);
	 }
}
