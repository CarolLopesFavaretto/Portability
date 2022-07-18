package com.portability.framework.adapters.in.dtos;

import com.portability.domain.entity.enums.PortabilityStatus;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UpdatedPortabilityStatusDTO implements Serializable {

    private PortabilityStatus status;
}
