package com.rdz.pitufos.domain.dto.request;

import jakarta.validation.constraints.NotEmpty;

public record UsuarioLoginRequestDto(

        @NotEmpty(message = "debes ingresar un nombre de usuario")
        String email,
        @NotEmpty(message = "debes ingresar una contrasñea")
        String password
) {
}
