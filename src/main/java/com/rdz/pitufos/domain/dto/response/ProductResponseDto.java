package com.rdz.pitufos.domain.dto.response;

import java.math.BigDecimal;

public record ProductResponseDto(
        long id,
        String name,
        Integer quantity,
        long unit,
        BigDecimal price,
        String image,
        Integer stock,
        Boolean status
) {
}
