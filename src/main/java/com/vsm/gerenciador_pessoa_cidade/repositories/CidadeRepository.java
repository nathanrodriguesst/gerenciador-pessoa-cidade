package com.vsm.gerenciador_pessoa_cidade.repositories;

import com.vsm.gerenciador_pessoa_cidade.entities.Cidade;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CidadeRepository extends JpaRepository<Cidade, Long> {
    // Essa query garante que o input será um número antes de tentar buscar por ID
    @Query(value =
            "select c.id, c.nome, c.uf, c.ibge from cidades c \n" +
            "where " +
            "c.ibge = :query \n" +
            "or (IF(:query REGEXP '^[0-9]+$', c.id = CAST(:query AS SIGNED), FALSE)) \n" +
            "or c.nome like concat('%', :query, '%') \n" +
            "or c.uf = :query;"
            , nativeQuery = true)
    Page<Cidade> searchCidade(String query, Pageable pageable);

    // Essa query apoia o metodo de deleção para validar se já há uma Pessoa utilizando a cidade.
    @Query(value = "select c.id, c.nome, c.uf, c.ibge \n" +
            "from cidades c \n" +
            "inner join pessoas p on c.id = p.cidade_id \n" +
            "where c.id = :id \n" +
            "limit 1", nativeQuery = true)
    Cidade checkIfCidadeIsInUse(Long id);

    // Utilizado para integração com VIACep
    @Query(value = "select * from cidades c where c.ibge = :ibge", nativeQuery = true)
    Cidade findByIbge(int ibge);
}