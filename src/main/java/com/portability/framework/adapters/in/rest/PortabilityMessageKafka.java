package com.portability.framework.adapters.in.rest;

import com.portability.domain.entity.enums.TelephoneCompany;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class PortabilityMessageKafka {

    private UUID portabilityId;
    private TelephoneCompany source;
    private TelephoneCompany target;


}
