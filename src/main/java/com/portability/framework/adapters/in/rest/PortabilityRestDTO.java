package com.portability.framework.adapters.in.rest;

import com.portability.domain.entity.enums.TelephoneCompany;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import java.io.Serializable;
import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PortabilityRestDTO implements Serializable {

    private UUID portabilityId;
    @Enumerated(EnumType.STRING)
    private TelephoneCompany source;
    @Enumerated(EnumType.STRING)
    private TelephoneCompany target;
}
