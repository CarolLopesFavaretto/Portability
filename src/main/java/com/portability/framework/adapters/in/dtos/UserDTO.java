package com.portability.framework.adapters.in.dtos;

import com.portability.domain.entity.Address;
import com.portability.domain.entity.Line;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserDTO implements Serializable {

    private Line line;
    private Address address;
    private LocalDate dateOfBirth;
    private String documentNumber;
    private String name;
}
