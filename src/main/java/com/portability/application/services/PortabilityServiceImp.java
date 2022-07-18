package com.portability.application.services;

import com.portability.application.ports.in.PortabilityService;
import com.portability.application.ports.out.PortabilityRepository;
import com.portability.domain.entity.Portability;
import com.portability.domain.entity.User;
import com.portability.domain.entity.enums.PortabilityStatus;
import com.portability.framework.adapters.in.dtos.InputPortabilityDTO;
import com.portability.framework.adapters.in.dtos.UpdatedPortabilityStatusDTO;
import com.portability.framework.exceptions.ResourceNotFoundException;
import org.modelmapper.ModelMapper;


import java.util.UUID;

public class PortabilityServiceImp implements PortabilityService {


    private final PortabilityRepository repository;

    private final ModelMapper mapper;

    public PortabilityServiceImp(PortabilityRepository repository, ModelMapper mapper) {
        this.repository = repository;
        this.mapper = mapper;
    }

    @Override
    public Portability created(InputPortabilityDTO portabilityDTO) {
        Portability portability = Portability.builder()
                .user(mapper.map(portabilityDTO.getUser(), User.class))
                .portabilityStatus(PortabilityStatus.PROCESSANDO_PORTABILIDADE)
                .source(portabilityDTO.getPortability().getSource())
                .target(portabilityDTO.getPortability().getTarget())
                .build();
        return repository.save(portability);
    }

    @Override
    public void updatedPortability(UUID portabilityId, UpdatedPortabilityStatusDTO status) {
        var entity = repository.findById(portabilityId);
        Portability portability = entity.orElseThrow(() -> new ResourceNotFoundException("Portabilidade não encontrada"));
        portability.setPortabilityStatus(portability.getPortabilityStatus());
        repository.save(portability);
    }
}
