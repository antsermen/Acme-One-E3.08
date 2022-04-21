package acme.features.any.item;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import acme.entities.Item;
import acme.framework.controllers.AbstractController;
import acme.framework.roles.Any;

@Controller
public class AnyItemController extends AbstractController<Any,Item>{
	
	@Autowired
	protected AnyItemToolListService toolListService;
	@Autowired
	protected AnyItemComponentListService componentListService;
	@Autowired
	protected AnyItemShowService itemShowService;
	
	
	
	@PostConstruct
	protected void initialise() {
		super.addCommand("tools", "list", this.toolListService);
		super.addCommand("components", "list", this.componentListService);
		super.addCommand("show", this.itemShowService);
	}

}
