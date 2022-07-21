package com.portability.application.services;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.portability.application.ports.in.PortabilityService;
import com.portability.application.ports.out.KafkaService;
import com.portability.application.ports.out.PortabilityRepository;
import com.portability.domain.entity.Portability;
import com.portability.domain.entity.User;
import com.portability.domain.entity.enums.Status;
import com.portability.framework.adapters.in.dtos.InputPortabilityDTO;
import com.portability.framework.adapters.in.dtos.UpdatedPortabilityStatusDTO;
import com.portability.framework.exceptions.ResourceNotFoundException;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Optional;
import java.util.UUID;

public class PortabilityServiceImp implements PortabilityService {

    @Autowired
    private PortabilityRepository repository;

    @Autowired
    private KafkaService kafkaService;

    @Autowired
    private ModelMapper mapper;


    @Override
    public Portability created(InputPortabilityDTO portabilityDTO) throws JsonProcessingException {
        Portability portability = Portability.builder()
                .user(mapper.map(portabilityDTO.getUser(), User.class))
                .portabilityStatus(Status.PROCESSANDO_PORTABILIDADE)
                .source(portabilityDTO.getPortability().getSource())
                .target(portabilityDTO.getPortability().getTarget())
                .build();
        portability = repository.save(portability);
        kafkaService.eventPortability(portability);
        return portability;
    }

    @Override
    public Optional<Portability> findByID(UUID portabilityId) {
        return repository.findById(portabilityId);
    }

    @Override
    public void updatedPortability(UUID portabilityId, UpdatedPortabilityStatusDTO status) {
        var entity = repository.findById(portabilityId).orElseThrow(() ->
                new ResourceNotFoundException("Portabilidade não encontrada"));

        entity.setPortabilityStatus(status.getStatus());
        repository.save(entity);
    }
}
