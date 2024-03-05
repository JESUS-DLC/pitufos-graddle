package com.rdz.pitufos.domain.dto.request;

import jakarta.validation.constraints.NotEmpty;

public record UnitRequestDto(
        long id,
        @NotEmpty(message = "el nombre no puede estar vacio")
        String name
) {
}
