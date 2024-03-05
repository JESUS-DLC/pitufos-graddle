package com.rdz.pitufos.domain.dto.request;

import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

@AllArgsConstructor
@NoArgsConstructor
@Getter @Setter
public class ProductRequestDto{
        private long id;
        @NotEmpty(message = "el nombre no puede estar vacion}")
        private String name;
        private Integer quantity;
       private Long unit;
        private BigDecimal price;
        private Integer stock;
        private Boolean status;
}
