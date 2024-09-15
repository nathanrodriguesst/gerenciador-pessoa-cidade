package com.vsm.gerenciador_pessoa_cidade.repositories;

import com.vsm.gerenciador_pessoa_cidade.entities.Cidade;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CidadeRepository extends JpaRepository<Cidade, Long> {
    List<Cidade> findByNomeContainingOrUfIgnoreCase(String nome, String uf);
    @Query(value = "select c.id, c.nome, c.uf, c.ibge\n" +
            "from cidades c\n" +
            "inner join pessoas p on c.id = p.cidade_id\n" +
            "where c.id = :id " +
            "limit 1", nativeQuery = true)
    Cidade checkIfCidadeIsInUse(Long id);
    @Query(value = "select * from cidades c where c.ibge = :ibge", nativeQuery = true)
    Cidade findByIbge(int ibge);
}