package com.fiap.techchallenge.controller.interfaces;

import com.fiap.techchallenge.controller.inputdata.TemplateInputData;
import com.fiap.techchallenge.presenters.modelview.TemplateModelView;

import java.util.List;
import java.util.Optional;

public interface TemplateController {
	TemplateModelView create(TemplateInputData templateInputData);

	TemplateModelView update(String key, TemplateInputData templateInputData);

	List<TemplateModelView> find(String param);

	Optional<TemplateModelView> findByKey(String key);

	void remove(String key);
}
