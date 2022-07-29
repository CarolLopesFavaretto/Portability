package com.portability.framework.adapters.in.rest;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PortabilityPublish {

    private String number;
    private String documentNumber;
    private PortabilityMessageKafka portability;
}
