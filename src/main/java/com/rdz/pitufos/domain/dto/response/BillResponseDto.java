package com.rdz.pitufos.domain.dto.response;

import java.math.BigDecimal;
import java.time.LocalDate;

public record BillResponseDto(
        long id,
        String name,
        boolean status,
        BigDecimal total,
        LocalDate date
) {
}
