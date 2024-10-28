package com.fiap.techchallenge.persistence.repositories;

import com.fiap.techchallenge.persistence.entities.TemplateEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TemplateJpaRepository extends JpaRepository<TemplateEntity, Long> {
}
