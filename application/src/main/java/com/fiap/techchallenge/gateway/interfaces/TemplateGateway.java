package com.fiap.techchallenge.gateway.interfaces;

import com.fiap.techchallenge.model.Template;

import java.util.List;
import java.util.Optional;

public interface TemplateGateway {
	Template create(Template template);

	Optional<Template> findByKey(String key);

	Optional<List<Template>> findAll();

	Template update(String key, Template template);

	Optional<Template> remove(String key);
}
