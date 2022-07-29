package com.portability.framework.adapters.config;

import com.portability.domain.entity.Portability;
import com.portability.framework.adapters.in.rest.PortabilityMessageKafka;
import com.portability.framework.adapters.in.rest.PortabilityPublish;
import org.springframework.stereotype.Component;

@Component
public class PortabilityMapper {

    public PortabilityPublish objMessage(Portability request) {
        PortabilityPublish kafka = new PortabilityPublish();
        kafka.setNumber(request.getUser().getLine().getNumber().toString());
        kafka.setDocumentNumber(request.getUser().getDocumentNumber());
        kafka.setPortability(portabilityDTO(request));
        return kafka;
    }

    public PortabilityMessageKafka portabilityDTO(Portability request) {
        PortabilityMessageKafka portability = new PortabilityMessageKafka();
        portability.setPortabilityId(request.getPortabilityId());
        portability.setSource(request.getSource());
        portability.setTarget(request.getTarget());
        return portability;
    }
}
