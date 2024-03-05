package com.rdz.pitufos.domain.dto.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;

public record UsuarioRequestDto(
        long id,
        @NotEmpty(message = "el nombre no puede estar vacio")
        String name,
        @Email(message = "debes ingresar un email")
        String email,
        @NotEmpty(message = "la contraseña no puede estar vacia")
        String password
        )
{
}
