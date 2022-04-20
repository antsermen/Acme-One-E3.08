package acme.features.any.tool;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import acme.entities.Item;
import acme.framework.controllers.AbstractController;
import acme.framework.roles.Any;

@Controller
@RequestMapping("/any/tool/")
public class AnyToolController extends AbstractController<Any, Item>{
	
	@Autowired
	protected AnyToolListService	listService;
	@Autowired
	protected AnyToolShowService	showService;
	
	// Constructors -----------------------------------------------------------

	@PostConstruct
	protected void initialise() {
		super.addCommand("list", this.listService);
		super.addCommand("show", this.showService);
	}

}
