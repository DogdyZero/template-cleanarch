package com.fiap.techchallenge.controller.mappers;

import com.fiap.techchallenge.controller.inputdata.TemplateInputData;
import com.fiap.techchallenge.model.Template;

public class TemplateMapper {
	public static Template toDomain(TemplateInputData inputData) {
		return new Template(inputData.getKey(), inputData.getValue());
	}
}
