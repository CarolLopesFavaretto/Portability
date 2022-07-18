package com.portability.framework.adapters.in.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AddressDTO implements Serializable {

    private String street;
    private String number;
    private String city;
    private String country;
    private String stateOrRegion;
}
