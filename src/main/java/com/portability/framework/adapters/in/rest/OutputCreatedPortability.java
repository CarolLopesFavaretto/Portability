package com.portability.framework.adapters.in.rest;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class OutputCreatedPortability implements Serializable {

    private UUID portabilityId;
}
