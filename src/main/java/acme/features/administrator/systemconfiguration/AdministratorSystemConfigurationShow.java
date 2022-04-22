package acme.features.administrator.systemconfiguration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.SystemConfiguration;
import acme.framework.components.models.Model;
import acme.framework.controllers.Request;
import acme.framework.roles.Administrator;
import acme.framework.services.AbstractShowService;

@Service
public class AdministratorSystemConfigurationShow implements AbstractShowService<Administrator,SystemConfiguration>{
	
	

		@Autowired
		protected AdministratorSystemConfigurationRepository repository;

		

		@Override
		public boolean authorise(final Request<SystemConfiguration> request) {
			assert request != null;

			return true;
		}

		@Override
		public void unbind(final Request<SystemConfiguration> request, final SystemConfiguration entity, final Model model) {
			assert request != null;
			assert entity != null;
			assert model != null;

			request.unbind(entity, model,"systemCurrency", "acceptedCurrencies", "strongSpamTerms", "strongSpamThreshold", "weakSpamTerms","weakSpamThreshold");
			model.setAttribute("confirmation", false);
			model.setAttribute("readonly", true);
		
		}

		@Override
		public SystemConfiguration findOne(final Request<SystemConfiguration> request) {
			assert request != null;

			SystemConfiguration result;

			result = this.repository.findSystemConfiguration().stream().findFirst().get();

			return result;
		}
}
