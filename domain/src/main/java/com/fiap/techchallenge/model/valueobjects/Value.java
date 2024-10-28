package com.fiap.techchallenge.model.valueobjects;

import com.fiap.techchallenge.exceptions.TemplateException;
import lombok.Getter;
import org.springframework.util.StringUtils;

@Getter
public class Value {
	private String value;

	public Value(String value){
		if(StringUtils.containsWhitespace(value))
			throw new TemplateException("Not allowed empty value!");
		this.value = value;
	}
}
