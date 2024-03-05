package com.rdz.pitufos.domain.dto.request;

import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class BillRequestDto{
        private long id;
        @NotEmpty(message = "el nombre no puede estar vacio")
        private String name;
        private Boolean status;
}
