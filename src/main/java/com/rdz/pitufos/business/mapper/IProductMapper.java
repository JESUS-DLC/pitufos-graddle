package com.rdz.pitufos.business.mapper;


import com.rdz.pitufos.domain.dto.request.ProductRequestDto;
import com.rdz.pitufos.domain.dto.response.ProductResponseDto;
import com.rdz.pitufos.domain.entity.ProductEntity;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

/**
 * Mapper que transforma objetos de ProductoPojo a ProductoEntity
 */
@Mapper(componentModel = "spring")
public interface IProductMapper {

    @Mapping(source = "id",target = "id")
    @Mapping(source = "name",target = "name")
    @Mapping(source = "quantity",target = "quantity")
    @Mapping(source = "price",target = "price")
    @Mapping(source = "image",target = "image")
    @Mapping(source = "stock",target = "stock")
    @Mapping(source = "status",target = "status")
    @Mapping(source = "unit.id", target = "unit")
    ProductResponseDto toProductResponseDto(ProductEntity productEntity);

    @InheritInverseConfiguration
    @Mapping(target = "unit",ignore = true)
    @Mapping(target = "image",ignore = true)
    ProductEntity toProductEntity(ProductRequestDto productRequestDto);

    List<ProductResponseDto> toProductResponseDto(List<ProductEntity> productEntity);
}
