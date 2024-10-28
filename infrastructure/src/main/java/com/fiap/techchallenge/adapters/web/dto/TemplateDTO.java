package com.fiap.techchallenge.adapters.web.dto;

import com.fiap.techchallenge.controller.inputdata.TemplateInputData;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class TemplateDTO {
	private String key;
	private String value;

	public TemplateInputData toInputData(){
		return new TemplateInputData(key, value);
	}
}
