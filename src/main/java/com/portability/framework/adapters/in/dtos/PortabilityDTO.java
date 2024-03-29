package com.portability.framework.adapters.in.dtos;

import com.portability.domain.entity.enums.TelephoneCompany;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;
import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class PortabilityDTO implements Serializable {

    @NotNull(message = "field cannot be null")
    private TelephoneCompany source;
    @NotNull(message = "field cannot be null")
    private TelephoneCompany target;
}
