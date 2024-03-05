package com.rdz.pitufos.presentation.controller;


import com.rdz.pitufos.business.service.IUserService;
import com.rdz.pitufos.domain.dto.request.UsuarioLoginRequestDto;
import com.rdz.pitufos.domain.dto.request.UsuarioRequestDto;
import com.rdz.pitufos.domain.dto.response.JwtResponseDto;
import com.rdz.pitufos.domain.dto.response.UsuarioResponseDto;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/auth")
public class    UserController {

    private final IUserService iUserService;

    @PostMapping("/signin")
    ResponseEntity<JwtResponseDto> signIn(@Valid @RequestBody UsuarioLoginRequestDto usuarioLoginRequestDto){
        return ResponseEntity.status(HttpStatus.OK).body(iUserService.signIn(usuarioLoginRequestDto));
    }

    @PostMapping("/signup")
    ResponseEntity<UsuarioResponseDto> signUp(@Valid @RequestBody UsuarioRequestDto usuarioRequestDto){
        return ResponseEntity.status(HttpStatus.OK).body(iUserService.signUp(usuarioRequestDto));
    }
}
