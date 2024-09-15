package com.vsm.gerenciador_pessoa_cidade.dtos;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CidadeDTO {
    private Long id;
    private String nome;
    private String uf;
    private int ibge;
}
