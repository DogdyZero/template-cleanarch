package com.fiap.techchallenge.controller;

import com.fiap.techchallenge.controller.inputdata.TemplateInputData;
import com.fiap.techchallenge.controller.interfaces.TemplateController;
import com.fiap.techchallenge.controller.mappers.TemplateMapper;
import com.fiap.techchallenge.model.Template;
import com.fiap.techchallenge.presenters.interfaces.ApplicationPresenter;
import com.fiap.techchallenge.presenters.modelview.TemplateModelView;
import com.fiap.techchallenge.usecase.TemplateUsecase;

import java.util.List;
import java.util.Optional;

public class TemplateControllerImpl implements TemplateController {
	private final TemplateUsecase usecase;
	private final ApplicationPresenter<TemplateModelView> presenter;

	public TemplateControllerImpl(TemplateUsecase usecase,
			ApplicationPresenter<TemplateModelView> presenter) {
		this.usecase = usecase;
		this.presenter = presenter;
	}

	@Override
	public TemplateModelView create(TemplateInputData inputData) {
		Template template = TemplateMapper.toDomain(inputData);
		Template newTemplate = usecase.create(template);
		return presenter.toModelView(newTemplate);
	}

	@Override
	public TemplateModelView update(String key, TemplateInputData templateInputData) {
		return null;
	}

	@Override
	public List<TemplateModelView> find(String param) {
		return List.of();
	}

	@Override
	public Optional<TemplateModelView> findByKey(String key) {
		return Optional.empty();
	}

	@Override
	public void remove(String key) {

	}
}
