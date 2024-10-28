package com.fiap.techchallenge.adapters.repositories;

import com.fiap.techchallenge.model.Template;
import com.fiap.techchallenge.persistence.repositories.TemplateJpaRepository;
import com.fiap.techchallenge.repository.TemplateRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class TemplateRepositoryImpl implements TemplateRepository {

	private final TemplateJpaRepository repository;
	@Override
	public Template create(Template template) {
		return null;
	}

	@Override
	public Optional<Template> findByKey(String key) {
		return Optional.empty();
	}

	@Override
	public Optional<List<Template>> findAll() {
		return Optional.empty();
	}

	@Override
	public Template update(String key, Template template) {
		return null;
	}

	@Override
	public Optional<Template> remove(String key) {
		return Optional.empty();
	}
}
