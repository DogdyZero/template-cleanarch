package com.fiap.techchallenge.usecase;

import com.fiap.techchallenge.gateway.interfaces.TemplateGateway;
import com.fiap.techchallenge.model.Template;

import java.util.List;
import java.util.Optional;

public class TemplateUsecaseImpl implements TemplateUsecase {
	private final TemplateGateway templateGateway;

	public TemplateUsecaseImpl(TemplateGateway templateGateway) {
		this.templateGateway = templateGateway;
	}

	@Override
	public Template create(Template template) {
		return templateGateway.create(template);
	}

	@Override
	public Optional<Template> findByKey(String key) {
		return templateGateway.findByKey(key);
	}

	@Override
	public Optional<List<Template>> findAll() {
		return templateGateway.findAll();
	}

	@Override
	public Template update(String key, Template template) {
		return templateGateway.update(key, template);
	}

	@Override
	public Optional<Template> remove(String key) {
		return templateGateway.remove(key);
	}
}
