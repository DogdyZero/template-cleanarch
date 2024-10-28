package com.fiap.techchallenge.gateway;

import com.fiap.techchallenge.gateway.interfaces.TemplateGateway;
import com.fiap.techchallenge.model.Template;
import com.fiap.techchallenge.repository.TemplateRepository;

import java.util.List;
import java.util.Optional;

public class TemplateGatewayImpl implements TemplateGateway {
	private final TemplateRepository repository;

	public TemplateGatewayImpl(TemplateRepository repository) {
		this.repository = repository;
	}

	@Override
	public Template create(Template template) {
		return repository.create(template);
	}

	@Override
	public Optional<Template> findByKey(String key) {
		return repository.findByKey(key);
	}

	@Override
	public Optional<List<Template>> findAll() {
		return repository.findAll();
	}

	@Override
	public Template update(String key, Template template) {
		return repository.update(key, template);
	}

	@Override
	public Optional<Template> remove(String key) {
		return repository.remove(key);
	}
}
