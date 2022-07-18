package com.portability.application.ports.in;

import com.portability.domain.entity.Portability;
import com.portability.framework.adapters.in.dtos.InputPortabilityDTO;
import com.portability.framework.adapters.in.dtos.UpdatedPortabilityStatusDTO;
import org.springframework.stereotype.Service;

import java.util.UUID;
@Service
public interface PortabilityService {

    Portability created(InputPortabilityDTO portabilityDTO);

    void updatedPortability(UUID portabilityId, UpdatedPortabilityStatusDTO status);
}
