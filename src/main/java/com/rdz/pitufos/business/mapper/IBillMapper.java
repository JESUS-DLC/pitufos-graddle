package com.rdz.pitufos.business.mapper;


import com.rdz.pitufos.domain.dto.request.BillRequestDto;
import com.rdz.pitufos.domain.dto.response.BillResponseDto;
import com.rdz.pitufos.domain.entity.BillEntity;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface IBillMapper {

    @Mapping(source = "id",target = "id")
    @Mapping(source = "name",target = "name")
    @Mapping(source = "date",target = "date")
    @Mapping(source = "total",target = "total")
    @Mapping(source = "status",target = "status")
    BillResponseDto toBillResponseDto(BillEntity billEntity);

    @InheritInverseConfiguration
    @Mapping(target = "date",ignore = true)
    @Mapping(target = "status",ignore = true)
    @Mapping(target = "total",ignore = true)
    BillEntity toBillEntity(BillRequestDto billRequestDto);

    List<BillResponseDto> toBillResponseDto(List<BillEntity> billEntities);
}
