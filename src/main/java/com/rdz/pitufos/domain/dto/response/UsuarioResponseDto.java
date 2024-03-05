package com.rdz.pitufos.domain.dto.response;

public record UsuarioResponseDto(
        long id,
        String name,
        String email,
        String role
        )
{
}
