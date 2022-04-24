package acme.features.authenticated.systemConfiguration;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.servlet.mvc.AbstractController;

import acme.entities.SystemConfiguration;
import acme.framework.roles.Authenticated;

@Controller
public class AuthenticatedSystemConfigurationController extends AbstractController<Authenticated, SystemConfiguration> {
	
	// Internal state
	
	@Autowired
	protected AuthenticatedSystemConfigurationShowService showService;

	// Constructors 

	@PostConstruct
	protected void initialise() {
		super.addCommand("show", this.showService);

	}

}