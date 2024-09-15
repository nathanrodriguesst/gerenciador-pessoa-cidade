package com.vsm.gerenciador_pessoa_cidade.repositories;

import com.vsm.gerenciador_pessoa_cidade.entities.Pessoa;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PessoaRepository extends JpaRepository<Pessoa, Long> {
    Pessoa findByCpf(String cpf);
    List<Pessoa> findByNomeContainingOrCpfIgnoreCase(String nome, String cpf);
    boolean existsByCpf(String cpf);
}