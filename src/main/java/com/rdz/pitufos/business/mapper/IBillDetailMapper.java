package com.rdz.pitufos.business.mapper;

import com.rdz.pitufos.domain.dto.request.BillDetailRequestDto;
import com.rdz.pitufos.domain.dto.response.BillDetailResponseDto;
import com.rdz.pitufos.domain.entity.BillDetailEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface IBillDetailMapper {
    @Mapping(source = "id",target = "id")
    @Mapping(source = "product.id",target = "product")
    @Mapping(source = "product.name",target = "productName")
    @Mapping(source = "product.price",target = "productPrice")
    @Mapping(source = "bill.id",target = "bill")
    @Mapping(source = "quantity",target = "quantity")
    @Mapping(source = "total",target = "total")
    BillDetailResponseDto toBillDetailResponseDto(BillDetailEntity billDetailEntity);

    @Mapping(target = "product",ignore = true)
    @Mapping(target = "bill",ignore = true)
    @Mapping(target = "total",ignore = true)
    @Mapping(target = "price",ignore = true)
    BillDetailEntity toBillDetailEntity(BillDetailRequestDto billDetailRequestDto);
}
