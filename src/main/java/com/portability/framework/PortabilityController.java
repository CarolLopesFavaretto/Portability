package com.portability.framework;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.portability.application.ports.in.PortabilityService;
import com.portability.domain.entity.Portability;
import com.portability.domain.entity.enums.Status;
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
    public ResponseEntity<Portability> findById(@PathVariable String portabilityId) {
        var optional = service.findByID(UUID.fromString(portabilityId)).orElseThrow(()
                -> new ResourceNotFoundException("Portabilidade nao encontrada"));
        return ResponseEntity.ok().body(optional);
    }

    @PostMapping
    public ResponseEntity<OutputCreatedPortability> created(@Valid @RequestBody InputPortabilityDTO portabilityDTO) throws JsonProcessingException {
        Portability portability = service.created(portabilityDTO);
        log.info("Portabilidade criada com sucesso: {}", portability.getPortabilityId());
        return ResponseEntity.status(HttpStatus.CREATED).body(new OutputCreatedPortability(portability.getPortabilityId()));
    }

    @PutMapping("/{portabilityId}")
    public ResponseEntity<String> putPortability(@PathVariable UUID portabilityId, @RequestBody Status status) {
        service.updatedPortability(portabilityId, status);
        log.info("Callback recebido com sucesso, status: {}", status);
        return ResponseEntity.ok().body("Portabilidade concluida, status: " + status + "!");
    }
}
