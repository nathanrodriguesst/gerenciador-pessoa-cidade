package com.vsm.gerenciador_pessoa_cidade.entities;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Endereco {
    private String logradouro;
    private String bairro;
    private int ibge;
    private Cidade cidade;
}