package com.vsm.gerenciador_pessoa_cidade.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Table(name="pessoas")
public class Pessoa {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Size(min = 3, max = 30)
    @Column(nullable = false, length = 30)
    private String nome;

    @Size(min = 11, max = 14)
    @Column(nullable = false, length = 14)
    private String cpf;

    @Size(max = 50)
    @Column(length = 50)
    private String endereco;

    @Size(max = 10)
    @Column(length = 10)
    private String numero;

    @Size(max = 50)
    @Column(length = 50)
    private String bairro;

    @Size(max = 8)
    @Column(length = 8)
    private String cep;

    @ManyToOne
    @JoinColumn( name = "cidade_id")
    private Cidade cidade;

    @Size(max = 15)
    @Column(length = 15)
    private String telefone;

    @Size(max = 50)
    @Column(length = 50)
    private String email;
}
