package com.portability.application.ports.out;

import com.portability.domain.entity.Portability;

import java.util.Optional;
import java.util.UUID;

public interface PortabilityRepository {

    Portability save(Portability portability);

    Optional<Portability> findById(UUID portabilityId);
}
