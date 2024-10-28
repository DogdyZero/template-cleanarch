package com.fiap.techchallenge.adapters.web;

import com.fiap.techchallenge.adapters.web.dto.TemplateDTO;
import com.fiap.techchallenge.controller.interfaces.TemplateController;
import com.fiap.techchallenge.model.Template;
import com.fiap.techchallenge.presenters.modelview.TemplateModelView;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/templates")
@Validated
@RequiredArgsConstructor
public class TemplateApi {
	private final TemplateController controller;

	@PostMapping
	@Operation(
			summary = "Create a new template",
			description = "Create a new template.",
			responses = {
					@ApiResponse(
							responseCode = "200",
							description = "Success to create new template.",
							content = @Content(
									mediaType = "application/json",
									schema = @Schema(implementation = TemplateDTO.class),
									examples = @ExampleObject(
											name = "Example of template",
											value = "{\"key\": \"123\", \"value\": \"Some value\"}"
									)
							)
					),
					@ApiResponse(
							responseCode = "400",
							description = "Wrong request body."
					)
			},
			requestBody = @io.swagger.v3.oas.annotations.parameters.RequestBody(
					content = @Content(
							mediaType = "application/json",
							schema = @Schema(implementation = Template.class),
							examples = @ExampleObject(
									name = "Example of template",
									value = "{\"key\": \"123\", \"value\": \"Some value\"}"
							)
					)
			)
	)
	public ResponseEntity<TemplateModelView> create(@Valid @RequestBody TemplateDTO dto) {
		TemplateModelView modelView = controller.create(dto.toInputData());
		return ResponseEntity.status(201).body(modelView);
	}

}
