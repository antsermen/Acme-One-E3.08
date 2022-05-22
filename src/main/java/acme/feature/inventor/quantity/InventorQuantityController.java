package acme.feature.inventor.quantity;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;

import acme.entities.Quantity;
import acme.framework.controllers.AbstractController;
import acme.roles.Inventor;

public class InventorQuantityController extends AbstractController<Inventor, Quantity>{
	
	@Autowired
	protected InventorQuantityListService quantityListService;
	
	@Autowired
	protected InventorQuantityShowService quantityShowService;
	
	@Autowired
	protected InventorQuantityCreateService quantityCreateService;
	
	@Autowired
	protected InventorQuantityDeleteService quantityDeleteService;
	
	@Autowired
	protected InventorQuantityUpdateService quantityUpdateService;
	
	
	@PostConstruct
	protected void initialise() {
		super.addCommand("list", this.quantityListService);
		super.addCommand("show", this.quantityShowService);
		super.addCommand("create", this.quantityCreateService);
		super.addCommand("delete", this.quantityDeleteService);
		super.addCommand("update", this.quantityUpdateService);
		

					
	}

}
