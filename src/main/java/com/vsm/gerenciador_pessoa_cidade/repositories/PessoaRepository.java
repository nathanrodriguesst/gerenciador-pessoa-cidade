package com.vsm.gerenciador_pessoa_cidade.repositories;

import com.vsm.gerenciador_pessoa_cidade.entities.Pessoa;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PessoaRepository extends JpaRepository<Pessoa, Long> {
    Pessoa findByCpf(String cpf);
    boolean existsByCpf(String cpf);
    @Query(value = "select p.* from pessoas p \n" +
            "inner join cidades c on p.cidade_id = c.id \n" +
            "where p.bairro like concat('%', :query, '%') \n" +
            "or p.cep like if(:sanitizedQuery <> '', (concat('%', :sanitizedQuery, '%')), null)  \n" +
            "or p.cpf like if(:sanitizedQuery <> '', (concat('%', :sanitizedQuery, '%')), null) \n" +
            "or p.email like concat('%', :query, '%')  \n" +
            "or p.endereco like concat('%', :query, '%') \n" +
            "or p.nome like concat('%', :query, '%') \n" +
            "or p.numero like concat('%', :query, '%') \n" +
            "or p.telefone like if(:sanitizedQuery <> '', (concat('%', :sanitizedQuery, '%')), null) \n" +
            "or c.nome like concat('%', :query, '%');", nativeQuery = true)
    List<Pessoa> searchPessoa(String query, String sanitizedQuery);
}