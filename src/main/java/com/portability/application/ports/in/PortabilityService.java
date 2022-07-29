package com.portability.application.ports.in;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.portability.domain.entity.Portability;
import com.portability.domain.entity.enums.Status;
import com.portability.framework.adapters.in.dtos.InputPortabilityDTO;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Service
public interface PortabilityService {

    Portability created(InputPortabilityDTO portabilityDTO) throws JsonProcessingException;

    Optional<Portability> findByID (UUID portabilityId);

    void updatedPortability(UUID portabilityId, Status status);
}
