package com.vsm.gerenciador_pessoa_cidade.services;

import com.vsm.gerenciador_pessoa_cidade.exceptions.ResourceInUseException;
import com.vsm.gerenciador_pessoa_cidade.exceptions.FieldNotEmptyException;
import com.vsm.gerenciador_pessoa_cidade.exceptions.ResourceNotFoundException;
import com.vsm.gerenciador_pessoa_cidade.repositories.CidadeRepository;
import com.vsm.gerenciador_pessoa_cidade.entities.Cidade;
import com.vsm.gerenciador_pessoa_cidade.dtos.CidadeDTO;
import com.vsm.gerenciador_pessoa_cidade.repositories.PessoaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CidadeService {

    @Autowired
    private CidadeRepository cidadeRepository;

    @Autowired
    private PessoaRepository pessoaRepository;

    public Cidade createCidade(CidadeDTO cidadeDTO) {
        Cidade cidade = new Cidade();

        if (cidadeDTO.getNome().isEmpty() || cidadeDTO.getUf().isEmpty() || cidadeDTO.getIbge() == 0) {
            throw new FieldNotEmptyException("Por favor, preencha todos os campos.");
        }

        mapCidadeDTOToEntity(cidadeDTO, cidade);

        return cidadeRepository.save(cidade);
    }

    public List<Cidade> getAllCidades() {
        return cidadeRepository.findAll();
    }

    public Cidade findCidadeById(Long id) {
        return cidadeRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Cidade não encontrada com ID " + id));
    }

    public List<Cidade> searchCidadeByIdOrNomeOrUf(String query) {
        List<Cidade> results = new ArrayList<>();

        try {
            Long id = Long.valueOf(query);
            cidadeRepository.findById(id).ifPresent(results::add);
        } catch (NumberFormatException e) {
            // Continua a pesquisa se não for ID
        }

        results.addAll(cidadeRepository.findByNomeContainingOrUfIgnoreCase(query, query));

        return results;
    }

    public Cidade findCidadeByIbge(int ibge) {
        Cidade cidade = cidadeRepository.findByIbge(ibge);

        if (cidade == null) {
            throw new ResourceNotFoundException("Cidade não encontrada");
        }

        return cidade;
    }

    public Cidade updateCidade(Long id, CidadeDTO cidadeDTO) {
        Cidade cidade = cidadeRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Cidade não encontrada com ID " + id));

        mapCidadeDTOToEntity(cidadeDTO, cidade);
        return cidadeRepository.save(cidade);
    }

    public void deleteCidade(Long id) {
        Cidade pessoaUsingCidade = cidadeRepository.checkIfCidadeIsInUse(id);

        if (pessoaUsingCidade != null) {
            throw new ResourceInUseException("Cidade já está sendo utilizada em um ou mais cadastros de pessoa");
        }

        cidadeRepository.deleteById((long) id);
    }

    private void mapCidadeDTOToEntity(CidadeDTO cidadeDTO, Cidade cidade) {
        cidade.setNome(cidadeDTO.getNome());
        cidade.setUf(cidadeDTO.getUf());
        cidade.setIbge(cidadeDTO.getIbge());
    }
}