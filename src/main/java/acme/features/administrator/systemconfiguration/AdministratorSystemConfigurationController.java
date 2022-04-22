package acme.features.administrator.systemconfiguration;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import acme.entities.SystemConfiguration;
import acme.framework.controllers.AbstractController;
import acme.framework.roles.Administrator;

@Controller
public class AdministratorSystemConfigurationController extends AbstractController<Administrator, SystemConfiguration>{
	
	@Autowired
	protected AdministratorSystemConfigurationShow showService;
	
	@PostConstruct
	protected void initialise() {
		super.addCommand("show", this.showService);
	}
	
	

}
