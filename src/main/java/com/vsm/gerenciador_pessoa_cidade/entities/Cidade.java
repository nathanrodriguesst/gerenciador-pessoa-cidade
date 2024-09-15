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
@Table(name = "cidades")
public class Cidade {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 50)
    @Size(min = 3, max = 50)
    private String nome;

    @Column(nullable = false, length = 2)
    @Size(max = 2)
    private String uf;

    @Column(nullable = false)
    private int ibge;
}