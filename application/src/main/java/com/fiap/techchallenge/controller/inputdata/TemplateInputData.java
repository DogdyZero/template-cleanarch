package com.fiap.techchallenge.controller.inputdata;

import com.fiap.techchallenge.controller.interfaces.InputData;

public class TemplateInputData implements InputData {
	private String key;
	private String value;

	public TemplateInputData(String key, String value) {
		this.key = key != null ? key.trim() : null;
		this.value = value != null ? value.trim() : null;
	}

	public String getKey() {
		return key;
	}

	public String getValue() {
		return value;
	}
}
