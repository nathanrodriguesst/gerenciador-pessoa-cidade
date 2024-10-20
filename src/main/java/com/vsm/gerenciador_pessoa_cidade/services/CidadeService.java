package com.vsm.gerenciador_pessoa_cidade.services;

import com.vsm.gerenciador_pessoa_cidade.exceptions.ResourceInUseException;
import com.vsm.gerenciador_pessoa_cidade.exceptions.FieldNotEmptyException;
import com.vsm.gerenciador_pessoa_cidade.exceptions.ResourceNotFoundException;
import com.vsm.gerenciador_pessoa_cidade.repositories.CidadeRepository;
import com.vsm.gerenciador_pessoa_cidade.entities.Cidade;
import com.vsm.gerenciador_pessoa_cidade.dtos.CidadeDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CidadeService {

    @Autowired
    private CidadeRepository cidadeRepository;

    public Cidade createCidade(CidadeDTO cidadeDTO) {
        Cidade cidade = new Cidade();

        if (cidadeDTO.getNome().isEmpty() || cidadeDTO.getUf().isEmpty() || cidadeDTO.getIbge() == 0) {
            throw new FieldNotEmptyException("Por favor, preencha todos os campos.");
        }

        mapCidadeDTOToEntity(cidadeDTO, cidade);

        return cidadeRepository.save(cidade);
    }

    public List<Cidade> findAllCidades() {
        return cidadeRepository.findAll();
    }

    public Cidade findCidadeById(Long id) {
        return cidadeRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Cidade não encontrada com ID " + id));
    }

    public Page<Cidade> searchCidade(String query, int page, int size) {
        PageRequest pageable = PageRequest.of(page, size);
        return this.cidadeRepository.searchCidade(query, pageable);
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

        cidadeRepository.deleteById(id);
    }

    private void mapCidadeDTOToEntity(CidadeDTO cidadeDTO, Cidade cidade) {
        cidade.setNome(cidadeDTO.getNome());
        cidade.setUf(cidadeDTO.getUf());
        cidade.setIbge(cidadeDTO.getIbge());
    }
}