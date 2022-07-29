package com.portability.framework.adapters.in.dtos;

import com.portability.domain.entity.Address;
import com.portability.domain.entity.Line;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserDTO implements Serializable {

    private Line line;
    private Address address;
    @NotNull(message = "field cannot be null")
    private LocalDate dateOfBirth;
    @NotNull(message = "field cannot be null")
    private String documentNumber;
    @NotNull(message = "field cannot be null")
    private String name;
}
