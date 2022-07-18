package com.portability.framework;

import com.portability.application.ports.in.PortabilityService;
import com.portability.domain.entity.Portability;
import com.portability.framework.adapters.in.dtos.InputPortabilityDTO;
import com.portability.framework.adapters.in.dtos.UpdatedPortabilityStatusDTO;
import com.portability.framework.adapters.in.rest.OutputCreatedPortability;
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

    private final PortabilityService service;

    public PortabilityController(PortabilityService service) {
        this.service = service;
    }

    @PostMapping
    public ResponseEntity<OutputCreatedPortability> created(@RequestBody InputPortabilityDTO portabilityDTO){
        Portability portability = service.created(portabilityDTO);
        log.info("Portabilidade criada com sucesso: {}", portability.getPortabilityId());
        return ResponseEntity.status(HttpStatus.CREATED).body( new OutputCreatedPortability(portability.getPortabilityId()));
    }

    @PutMapping("/{portabilityId}")
    public ResponseEntity<?> putPortability(@PathVariable UUID portabilityId, @RequestBody UpdatedPortabilityStatusDTO status){
        service.updatedPortability(portabilityId,status);
        log.info("Callback recebido com sucesso, status: {}", status.getStatus());
        return ResponseEntity.ok().body("Portabilidade concluída, status: " + status.getStatus()+ "!");
    }
}
