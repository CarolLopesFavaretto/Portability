package com.portability.framework.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.portability.application.ports.out.KafkaService;
import com.portability.application.ports.out.PortabilityRepository;
import com.portability.domain.entity.Address;
import com.portability.domain.entity.Line;
import com.portability.domain.entity.Portability;
import com.portability.domain.entity.User;
import com.portability.domain.entity.enums.Status;
import com.portability.domain.entity.enums.TelephoneCompany;
import com.portability.framework.adapters.in.dtos.InputPortabilityDTO;
import com.portability.framework.adapters.in.dtos.PortabilityDTO;
import com.portability.framework.adapters.in.dtos.UserDTO;
import com.portability.framework.config.PostgreSQLContainerTest;
import com.portability.framework.exceptions.handler.ErrorMessage;
import org.apache.kafka.common.errors.ResourceNotFoundException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.time.LocalDate;
import java.util.UUID;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@AutoConfigureMockMvc
class PortabilityControllerTest extends PostgreSQLContainerTest {

    @Autowired
    private PortabilityRepository repository;

    @Autowired
    private ObjectMapper objectMapper;

    @MockBean
    private KafkaService service;

    @Autowired
    private MockMvc mvc;

    @Test
    void shouldFindPortabilityById() throws Exception {
        Portability portability = getPortability();
        Portability save = repository.save(portability);

            mvc.perform(MockMvcRequestBuilders
                            .get("/portability/{portabilityId}", save.getPortabilityId().toString())
                            .accept(MediaType.APPLICATION_JSON))
                    .andExpect(status().isOk())
                    .andExpect(mvcResult -> {
                        String contentAsString = mvcResult.getResponse().getContentAsString();
                        Portability resp = objectMapper.readValue(contentAsString, Portability.class);
                        assertThat(resp.getPortabilityStatus()).isEqualTo(portability.getPortabilityStatus());
                        assertThat(resp.getSource()).isEqualTo(portability.getSource());
                        assertThat(resp.getTarget()).isEqualTo(portability.getTarget());
                        assertThat(resp.getUser().getName()).isEqualTo(portability.getUser().getName());
                        assertThat(resp.getUser().getDocumentNumber()).isEqualTo(portability.getUser().getDocumentNumber());
                        assertThat(resp.getUser().getDateOfBirth()).isEqualTo(portability.getUser().getDateOfBirth());
                    });
    }

    private Portability getPortability() {
        Portability portability = Portability.builder()
                .portabilityStatus(Status.PORTADO)
                .source(TelephoneCompany.CLARO)
                .target(TelephoneCompany.NEXTEL)
                .user(User.builder()
                        .name("Caroline")
                        .documentNumber("41246532809")
                        .dateOfBirth(LocalDate.parse("1999-11-26"))

                        .address(Address.builder()
                                .city("Osasco")
                                .country("São Paulo")
                                .number("5")
                                .street("Av Paulista")
                                .stateOrRegion("São Paulo").build())
                        .line(Line.builder()
                                .number(Integer.valueOf("1155663322")).build()).build()).build();
        return portability;
    }

    @Test
    void shouldCreatedPortability() throws Exception {

        InputPortabilityDTO portability = getInputPortabilityDTO();

        mvc.perform(MockMvcRequestBuilders
                        .post("/portability")
                        .content(objectMapper.writeValueAsString(portability))
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated())
                .andExpect(mvcResult -> {
                    String contentAsString = mvcResult.getResponse().getContentAsString();
                    Portability response = objectMapper.readValue(contentAsString, Portability.class);
                    assertThat(response.getPortabilityId()).isNotNull();
                });

    }

    private InputPortabilityDTO getInputPortabilityDTO() {
        InputPortabilityDTO portability = InputPortabilityDTO.builder()
                .portability(PortabilityDTO.builder()
                        .source(TelephoneCompany.CLARO)
                        .target(TelephoneCompany.NEXTEL).build())
                .user(UserDTO.builder().name("Caroline")
                        .documentNumber("41246532809")
                        .dateOfBirth(LocalDate.parse("1999-11-26"))
                        .address(Address.builder()
                                .city("Osasco")
                                .country("São Paulo")
                                .number("5")
                                .street("Av Paulista")
                                .stateOrRegion("São Paulo").build())
                        .line(Line.builder()
                                .number(Integer.valueOf("1155663322")).build()).build()).build();
        return portability;
    }

    @Test
    void shouldUpdatedPortability() throws Exception {

        Portability save = repository.save(getPortability());

        mvc.perform(MockMvcRequestBuilders
                        .put("/portability/{portabilityId}", save.getPortabilityId())
                        .content(objectMapper.writeValueAsString(Status.PORTADO))
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(mvcResult -> {
                    String contentAsString = mvcResult.getResponse().getContentAsString();
                    assertThat(contentAsString).isEqualTo("Portabilidade concluida, status: " + Status.PORTADO + "!");
                });
    }

    @Test
    void shouldFindPortabilityByIdNotFound() throws Exception {
        Portability portability = getPortability();
        Portability save = repository.save(portability);

        mvc.perform(MockMvcRequestBuilders
                        .get("/portability/{portabilityId}", UUID.randomUUID().toString())
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound())
                .andExpect(mvcResult -> {
                    String contentAsString = mvcResult.getResponse().getContentAsString();
                    ErrorMessage errorMessage = objectMapper.readValue(contentAsString, ErrorMessage.class);
                    assertThat(errorMessage.getMessage()).isEqualTo("Portabilidade nao encontrada");
                });
    }
}
