package com.portability.framework.out;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.portability.domain.entity.Portability;
import com.portability.framework.adapters.out.producer.KafkaServiceImp;
import com.portability.framework.config.PostgreSQLContainerTest;
import org.jeasy.random.EasyRandom;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.runner.RunWith;
import org.mockito.BDDMockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.junit.jupiter.MockitoSettings;
import org.mockito.quality.Strictness;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.test.context.junit4.SpringRunner;

import static org.mockito.ArgumentMatchers.any;

@RunWith(SpringRunner.class)
@ExtendWith(MockitoExtension.class)
@MockitoSettings(strictness = Strictness.LENIENT)
class KafkaServiceTest extends PostgreSQLContainerTest {

    EasyRandom easy = new EasyRandom();

    @MockBean
    KafkaTemplate<String, String> kafkaTemplate;

    @Autowired
    private KafkaServiceImp kafkaService;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    @DisplayName("KafkaService - eventPortability")
    void portabilityControllerNewPortability() {
        Portability portabilityEvent = easy.nextObject(Portability.class);

        try {
            BDDMockito.given(kafkaTemplate.send(any(), any())).willReturn(null);
            BDDMockito.doNothing().when(kafkaTemplate).flush();

            kafkaService.eventPortability(portabilityEvent);
            BDDMockito.verify(kafkaTemplate, BDDMockito.times(1)).send(any(), any());
        } catch (Exception e) {
            Assertions.fail(e.getMessage(), e);
        }
    }
}
