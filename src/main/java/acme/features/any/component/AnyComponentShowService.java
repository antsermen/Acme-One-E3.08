/*
 * AnyJobShowService.java
 *
 * Copyright (C) 2012-2022 Rafael Corchuelo.
 *
 * In keeping with the traditional purpose of furthering education and research, it is
 * the policy of the copyright owner to permit non-commercial use and redistribution of
 * this software. It has been tested carefully, but it is not guaranteed for any particular
 * purposes. The copyright owner does not offer any warranties or representations, nor do
 * they accept any liabilities with respect to them.
 */

package acme.features.any.component;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.Component;
import acme.framework.components.models.Model;
import acme.framework.controllers.Request;
import acme.framework.roles.Any;
import acme.framework.services.AbstractShowService;

@Service
public class AnyComponentShowService implements AbstractShowService<Any, Component> {

	// Internal state ---------------------------------------------------------

	@Autowired
	protected AnyComponentRepository repository;

	// AbstractShowService<Anonymous, Job> interface --------------------------

	@Override
	public boolean authorise(final Request<Component> request) {
		assert request != null;

		return true;
	}

	@Override
	public void unbind(final Request<Component> request, final Component entity, final Model model) {
		assert request != null;
		assert entity != null;
		assert model != null;

		request.unbind(entity, model, "name", "code", "technology", "description", "retailPrice","link");
	}

	@Override
	public Component findOne(final Request<Component> request) {
		assert request != null;

		Component result;
		int id;

		id = request.getModel().getInteger("id");
		result = this.repository.findComponentById(id);

		return result;
	}

}
