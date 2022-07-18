package com.portability.framework.adapters.out.persistence;

import com.portability.domain.entity.Portability;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface PortabilityRepositoryJPA extends JpaRepository<Portability, UUID> {
}
