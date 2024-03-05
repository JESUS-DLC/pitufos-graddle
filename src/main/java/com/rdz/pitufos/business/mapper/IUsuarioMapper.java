package com.rdz.pitufos.business.mapper;


import com.rdz.pitufos.domain.dto.request.UsuarioRequestDto;
import com.rdz.pitufos.domain.dto.response.UsuarioResponseDto;
import com.rdz.pitufos.domain.entity.UsuarioEntity;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface IUsuarioMapper {

    @Mapping(source = "id",target = "id")
    @Mapping(source = "name",target = "name")
    @Mapping(source = "email",target = "email")
    @Mapping(source = "role.name",target = "role")
    UsuarioResponseDto toUsuarioResponseDto(UsuarioEntity usuarioEntity);


    @InheritInverseConfiguration
    @Mapping(target = "enabled",ignore = true)
    @Mapping(target = "role",ignore = true)
    UsuarioEntity toUsuarioEntity(UsuarioRequestDto usuarioRequestDto);
}
