package com.rdz.pitufos.domain.dto.response;

import java.math.BigDecimal;

public record BillDetailResponseDto(
        long id,
        long product,
        String productName,
        long productPrice,
        long bill,
        int quantity,
        BigDecimal total
) {
}
