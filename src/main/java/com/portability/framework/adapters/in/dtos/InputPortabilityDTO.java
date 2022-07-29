package com.portability.framework.adapters.in.dtos;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class InputPortabilityDTO implements Serializable {

    private UserDTO user;
    private PortabilityDTO portability;


}
