package com.portability.framework.adapters.in.dtos;

import com.portability.domain.entity.enums.Status;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;
import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UpdatedPortabilityStatusDTO implements Serializable {

    @NotNull(message = "field cannot be null")
    private Status status;
}
