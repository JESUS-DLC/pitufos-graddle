package com.rdz.pitufos.domain.dto.request;

import java.util.List;

public record ListBillDetailRequestDto(
        List<BillDetailRequestDto> products
) {
}
