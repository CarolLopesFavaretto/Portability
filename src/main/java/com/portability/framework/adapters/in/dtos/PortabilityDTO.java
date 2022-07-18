package com.portability.framework.adapters.in.dtos;

import com.portability.domain.entity.enums.TelephoneCompany;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PortabilityDTO implements Serializable {

    private TelephoneCompany source;
    private TelephoneCompany target;
}
