package com.api.mkdata.domain.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ClienteFiltroDTO {
    private String nome;
    private Boolean ativo;
}
