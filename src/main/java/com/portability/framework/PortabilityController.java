package com.portability.framework;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.portability.application.ports.in.PortabilityService;
import com.portability.domain.entity.Portability;
import com.portability.framework.adapters.in.dtos.InputPortabilityDTO;
import com.portability.framework.adapters.in.dtos.UpdatedPortabilityStatusDTO;
import com.portability.framework.adapters.in.rest.OutputCreatedPortability;
import com.portability.framework.exceptions.ResourceNotFoundException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.UUID;

@Slf4j
@RestController
@RequestMapping("/portability")
public class PortabilityController {

    @Autowired
    private PortabilityService service;

    @GetMapping("/{portabilityId}")
    public ResponseEntity<Portability> findById(@PathVariable UUID portabilityId) {
        var optional = service.findByID(portabilityId).orElseThrow(()
                -> new ResourceNotFoundException("Portabilidade não encontrada"));
        return ResponseEntity.ok().body(optional);
    }

    @PostMapping
    public ResponseEntity<OutputCreatedPortability> created(@Valid @RequestBody InputPortabilityDTO portabilityDTO) throws JsonProcessingException {
        Portability portability = service.created(portabilityDTO);
        log.info("Portabilidade criada com sucesso: {}", portability.getPortabilityId());
        return ResponseEntity.status(HttpStatus.CREATED).body(new OutputCreatedPortability(portability.getPortabilityId()));
    }

    @PutMapping("/{portabilityId}")
    public ResponseEntity<?> putPortability(@PathVariable UUID portabilityId, @RequestBody UpdatedPortabilityStatusDTO status) {
        service.updatedPortability(portabilityId, status);
        log.info("Callback recebido com sucesso, status: {}", status.getStatus());
        return ResponseEntity.ok().body("Portabilidade concluída, status: " + status.getStatus() + "!");
    }
}
