package com.fiap.techchallenge.config;

import com.fiap.techchallenge.controller.TemplateControllerImpl;
import com.fiap.techchallenge.controller.interfaces.TemplateController;
import com.fiap.techchallenge.gateway.TemplateGatewayImpl;
import com.fiap.techchallenge.gateway.interfaces.TemplateGateway;
import com.fiap.techchallenge.presenters.TemplatePresenter;
import com.fiap.techchallenge.presenters.interfaces.ApplicationPresenter;
import com.fiap.techchallenge.presenters.modelview.TemplateModelView;
import com.fiap.techchallenge.repository.TemplateRepository;
import com.fiap.techchallenge.usecase.TemplateUsecase;
import com.fiap.techchallenge.usecase.TemplateUsecaseImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class TemplateConfiguration {
	@Bean
	public TemplateGateway gateway(TemplateRepository repository) {
		return new TemplateGatewayImpl(repository);
	}

	@Bean
	public TemplateUsecase usecase(TemplateGateway gateway) {
		return new TemplateUsecaseImpl(gateway);
	}

	@Bean
	public ApplicationPresenter<TemplateModelView> presenter() {
		return new TemplatePresenter();
	}

	@Bean
	public TemplateController controller(TemplateUsecase usecase,
			ApplicationPresenter<TemplateModelView> presenter) {
		return new TemplateControllerImpl(usecase, presenter);
	}
}
