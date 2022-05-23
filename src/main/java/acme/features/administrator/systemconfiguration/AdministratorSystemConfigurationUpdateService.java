package acme.features.administrator.systemconfiguration;

import org.assertj.core.util.Arrays;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.SystemConfiguration;
import acme.framework.components.models.Model;
import acme.framework.controllers.Errors;
import acme.framework.controllers.Request;
import acme.framework.roles.Administrator;
import acme.framework.services.AbstractUpdateService;

@Service
public class AdministratorSystemConfigurationUpdateService implements AbstractUpdateService<Administrator, SystemConfiguration>{
	
	// Internal state
	
	@Autowired
	protected AdministratorSystemConfigurationRepository repository;

	// Interface 
	
	@Override
	public boolean authorise(final Request<SystemConfiguration> request) {
		assert request != null;
		
		return true;
	}

	@Override
	public SystemConfiguration findOne(final Request<SystemConfiguration> request) {
		assert request != null; 
		return this.repository.findSystemConfiguration();
	}

	@Override
	public void unbind(final Request<SystemConfiguration> request, final SystemConfiguration entity, final Model model) {
		assert request != null;
		assert entity != null;
		assert model != null;
		
		request.unbind(entity, model,"systemCurrency", "acceptedCurrencies", "strongSpamTerms", "strongSpamThreshold", "weakSpamTerms","weakSpamThreshold");
		model.setAttribute("confirmation", false);
	//	model.setAttribute("readonly", true);
	}

	@Override
	public void bind(final Request<SystemConfiguration> request, final SystemConfiguration entity, final Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;
		
		request.bind(entity, errors,"systemCurrency", "acceptedCurrencies", "strongSpamTerms", "strongSpamThreshold", "weakSpamTerms","weakSpamThreshold");
	}

	@Override
	public void validate(final Request<SystemConfiguration> request, final SystemConfiguration entity, final Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;
		if(!errors.hasErrors("systemCurrency")) {
			final String[] acceptedCurrencies = this.repository.findSystemConfiguration().getAcceptedCurrencies().split(",");
			errors.state(request, Arrays.asList(acceptedCurrencies).contains(entity.getSystemCurrency()), "systemCurrency", "administrator.systemConfiguration.form.error.systemCurrency.acceptedCurrencies");
		}
		
	}

	@Override
	public void update(final Request<SystemConfiguration> request, final SystemConfiguration entity) {
		assert request != null;
		assert entity != null;
		this.repository.save(entity);
	}

	
}
