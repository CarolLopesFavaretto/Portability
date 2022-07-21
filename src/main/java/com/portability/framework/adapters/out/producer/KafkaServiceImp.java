package com.portability.framework.adapters.out.producer;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.portability.application.ports.out.KafkaService;
import com.portability.domain.entity.Portability;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class KafkaServiceImp implements KafkaService {

    @Value("${topic.name.producer}")
    private String topicName;

    @Autowired
    private KafkaTemplate<String, String> message;

    @Autowired
    private ObjectMapper objectMapper;

    @Override
    public void eventPortability(Portability event) throws JsonProcessingException {
        String messageJson = objectMapper.writeValueAsString(event);
        log.info("Payload enviado: {}", messageJson);
        message.send(topicName, messageJson);
    }
}
