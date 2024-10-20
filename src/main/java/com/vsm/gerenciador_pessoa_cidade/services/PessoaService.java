package com.vsm.gerenciador_pessoa_cidade.services;

import com.vsm.gerenciador_pessoa_cidade.exceptions.DuplicateResourceException;
import com.vsm.gerenciador_pessoa_cidade.exceptions.FieldNotEmptyException;
import com.vsm.gerenciador_pessoa_cidade.exceptions.ResourceNotFoundException;
import com.vsm.gerenciador_pessoa_cidade.repositories.CidadeRepository;
import com.vsm.gerenciador_pessoa_cidade.repositories.PessoaRepository;
import com.vsm.gerenciador_pessoa_cidade.entities.Cidade;
import com.vsm.gerenciador_pessoa_cidade.entities.Pessoa;
import com.vsm.gerenciador_pessoa_cidade.dtos.PessoaDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class PessoaService {

    @Autowired
    PessoaRepository pessoaRepository;

    @Autowired
    CidadeRepository cidadeRepository;

    public Pessoa createPessoa(PessoaDTO pessoaDTO) {
        Pessoa pessoa = new Pessoa();
        String cpf = sanitizeString(pessoaDTO.getCpf());

        if (pessoaDTO.getNome().isEmpty() || cpf.isEmpty()) {
            throw new FieldNotEmptyException("Os campos Nome e CPF são obrigatórios.");
        }

        if (pessoaRepository.existsByCpf(cpf)) {
            throw new DuplicateResourceException("CPF já cadastrado: " + pessoaDTO.getCpf());
        }

        mapPessoaDTOToEntity(pessoaDTO, pessoa);

        return pessoaRepository.save(pessoa);
    }

    public Page<Pessoa> findAllPessoas(int page, int size) {
        PageRequest pageable = PageRequest.of(page, size);
        return pessoaRepository.findAll(pageable);
    }

    public Pessoa findPessoaById(Long id) {
        return pessoaRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Pessoa não encontrada com ID " + id));
    }

    public Pessoa findPessoaByCpf(String cpf) {
        return Optional.ofNullable(pessoaRepository.findByCpf(cpf))
                .orElseThrow(() -> new ResourceNotFoundException("Pessoa não encontrada com o CPF " + cpf));
    }

    public List<Pessoa> searchPessoa(String query) {
        List<Pessoa> results = new ArrayList<>();

        try {
            Long id = Long.valueOf(query);
            pessoaRepository.findById(id).ifPresent(results::add);
        } catch (NumberFormatException e) {
            // Continua a pesquisa se não for ID
        }

        results.addAll(pessoaRepository.searchPessoa(query, sanitizeString(query)));

        List<Pessoa> newResults = new ArrayList<>();
        for (Pessoa p : results) {
           if (!newResults.contains(p)) {
               newResults.add(p);
           }
        }

        return newResults;
    }

    public Pessoa updatePessoa(Long id, PessoaDTO pessoaDTO) {
        Pessoa pessoa = pessoaRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Pessoa não encontrada com ID " + id));

        mapPessoaDTOToEntity(pessoaDTO, pessoa);
        return pessoaRepository.save(pessoa);
    }

    public void deletePessoa(Long id) {
        pessoaRepository.deleteById(id);
    }

    private String sanitizeString(String cpf) {
        return cpf.replaceAll("[^0-9]", "");
    }

    private void mapPessoaDTOToEntity(PessoaDTO pessoaDTO, Pessoa pessoa) {
        pessoa.setNome(pessoaDTO.getNome());
        pessoa.setCpf(sanitizeString(pessoaDTO.getCpf()));
        pessoa.setEndereco(pessoaDTO.getEndereco());
        pessoa.setNumero(pessoaDTO.getNumero());
        pessoa.setBairro(pessoaDTO.getBairro());
        pessoa.setCep(pessoaDTO.getCep());
        pessoa.setCidade(prepareCidade((long) pessoaDTO.getCidadeId()));
        pessoa.setTelefone(sanitizeString(pessoaDTO.getTelefone()));
        pessoa.setEmail(pessoaDTO.getEmail());
    }

    private Cidade prepareCidade(Long cidadeId) {
        if (cidadeId == null) {
            return null;
        }

        return cidadeRepository.findById(cidadeId)
                .orElseThrow(() -> new ResourceNotFoundException("Cidade não encontrada com ID " + cidadeId));
    }
}