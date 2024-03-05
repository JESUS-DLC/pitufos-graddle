package com.rdz.pitufos.business.service;


import com.rdz.pitufos.domain.dto.request.UsuarioLoginRequestDto;
import com.rdz.pitufos.domain.dto.request.UsuarioRequestDto;
import com.rdz.pitufos.domain.dto.response.JwtResponseDto;
import com.rdz.pitufos.domain.dto.response.UsuarioResponseDto;

public interface IUserService{

    UsuarioResponseDto signUp(UsuarioRequestDto usuarioRequestDto);

    JwtResponseDto signIn(UsuarioLoginRequestDto usuarioLoginRequestDto);
}
