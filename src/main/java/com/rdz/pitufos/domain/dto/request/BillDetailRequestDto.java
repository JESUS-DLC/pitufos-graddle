package com.rdz.pitufos.domain.dto.request;

public record BillDetailRequestDto(
        long id,
        long product,
        int quantity
) {
}
