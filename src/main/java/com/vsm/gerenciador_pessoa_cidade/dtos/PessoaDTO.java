package com.vsm.gerenciador_pessoa_cidade.dtos;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PessoaDTO {
    private Long id;
    private String nome;
    private String cpf;
    private String endereco;
    private String numero;
    private String bairro;
    private String cep;
    private int cidadeId;
    private String telefone;
    private String email;
}
