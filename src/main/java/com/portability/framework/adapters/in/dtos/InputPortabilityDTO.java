package com.portability.framework.adapters.in.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class InputPortabilityDTO implements Serializable {

    private UserDTO user;
    private PortabilityDTO portability;
}
