package com.portability.application.ports.out;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.portability.domain.entity.Portability;
import org.springframework.stereotype.Service;

@Service
public interface KafkaService {

    public void eventPortability(Portability event) throws JsonProcessingException;
}
