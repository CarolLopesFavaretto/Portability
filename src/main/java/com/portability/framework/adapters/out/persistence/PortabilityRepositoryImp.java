package com.portability.framework.adapters.out.persistence;

import com.portability.application.ports.out.PortabilityRepository;
import com.portability.domain.entity.Portability;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Optional;
import java.util.UUID;

@Component
@RequiredArgsConstructor
public class PortabilityRepositoryImp implements PortabilityRepository {

    private final PortabilityRepositoryJPA repositoryJPA;

    @Override
    public Portability save(Portability portability) {
        return repositoryJPA.save(portability);
    }

    @Override

    public Optional<Portability> findById(UUID portabilityId) {
        return repositoryJPA.findById(portabilityId);
    }
}
