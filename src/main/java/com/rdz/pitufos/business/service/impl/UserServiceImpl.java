package com.rdz.pitufos.business.service.impl;

import com.rdz.pitufos.business.exception.EmailExistException;
import com.rdz.pitufos.business.exception.ResourceNotFoundExcpetion;
import com.rdz.pitufos.business.mapper.IUsuarioMapper;
import com.rdz.pitufos.business.service.IUserService;
import com.rdz.pitufos.domain.dto.request.UsuarioLoginRequestDto;
import com.rdz.pitufos.domain.dto.request.UsuarioRequestDto;
import com.rdz.pitufos.domain.dto.response.JwtResponseDto;
import com.rdz.pitufos.domain.dto.response.UsuarioResponseDto;
import com.rdz.pitufos.domain.entity.RoleEntity;
import com.rdz.pitufos.domain.entity.UsuarioEntity;
import com.rdz.pitufos.repository.IRoleRepository;
import com.rdz.pitufos.repository.IUsuarioRepository;
import com.rdz.pitufos.security.JwtService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;


@Service
@RequiredArgsConstructor
public class UserServiceImpl implements IUserService {

    private final IUsuarioRepository iUsuarioRepository;
    private final IUsuarioMapper iUsuarioMapper;
    private final IRoleRepository iRoleRepository;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;
    private final JwtService jwtService;


    public UsuarioResponseDto signUp(UsuarioRequestDto usuarioRequestDto){
        if(iUsuarioRepository.existsByEmailIgnoreCase(usuarioRequestDto.email())) throw new EmailExistException();
        UsuarioEntity usuarioEntity = iUsuarioMapper.toUsuarioEntity(usuarioRequestDto);
        usuarioEntity.setPassword(passwordEncoder.encode(usuarioEntity.getPassword()));
        usuarioEntity.setEnabled(true);
        RoleEntity role = iRoleRepository.findById(1L).orElseThrow(ResourceNotFoundExcpetion::new);
        usuarioEntity.setRole(role);
        return iUsuarioMapper.toUsuarioResponseDto( iUsuarioRepository.save(usuarioEntity));
    }

    @Override
    public JwtResponseDto signIn(UsuarioLoginRequestDto usuarioLoginRequestDto) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        usuarioLoginRequestDto.email(),
                        usuarioLoginRequestDto.password()));
        UserDetails userDetails = iUsuarioRepository.findByEmail(usuarioLoginRequestDto.email()).orElseThrow(ResourceNotFoundExcpetion::new);
        String token = jwtService.generateToken(userDetails);
        return new JwtResponseDto(token);
    }

    private void authenticate(String username,String password){
        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username,password));
        }catch (DisabledException exception){
            throw new DisabledException("disabled account");
        }catch (BadCredentialsException exception){
            throw new BadCredentialsException("bad credentials");
        }
    }
}
