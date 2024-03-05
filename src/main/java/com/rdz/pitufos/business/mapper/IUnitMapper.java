package com.rdz.pitufos.business.mapper;


import com.rdz.pitufos.domain.dto.request.UnitRequestDto;
import com.rdz.pitufos.domain.entity.UnitEntity;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface IUnitMapper {

    @Mapping(source = "id",target = "id")
    @Mapping(source = "name",target = "name")
    UnitRequestDto toUnitRequestDto(UnitEntity unitEntity);

    @InheritInverseConfiguration
    @Mapping(target = "products",ignore = true)
    UnitEntity toUnitEntity(UnitRequestDto unitRequestDto);

    List<UnitRequestDto> toUnitRequestDto(List<UnitEntity> unitEntities);
}
