package com.portability.framework.adapters.in.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;
import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AddressDTO implements Serializable {

    @NotNull(message = "field cannot be null")
    private String street;
    @NotNull(message = "field cannot be null")
    private String number;
    @NotNull(message = "field cannot be null")
    private String city;
    @NotNull(message = "field cannot be null")
    private String country;
    @NotNull(message = "field cannot be null")
    private String stateOrRegion;
}
