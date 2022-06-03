package acme.features.inventor.rotelam;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import acme.framework.controllers.AbstractController;

@Controller
public class InventorRotelamController extends AbstractController{
	
	@Autowired
	protected InventorListRotelamService rotelamListService;
	
	
	@Autowired
	protected void initialise() {
		super.addCommand("list", this.rotelamListService);
	}

}
