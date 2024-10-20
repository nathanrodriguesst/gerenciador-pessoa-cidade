package com.vsm.gerenciador_pessoa_cidade.controllers;

import com.vsm.gerenciador_pessoa_cidade.entities.Pessoa;
import com.vsm.gerenciador_pessoa_cidade.dtos.PessoaDTO;
import com.vsm.gerenciador_pessoa_cidade.services.PessoaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("gerenciador/pessoa")
public class PessoaController {

    @Autowired
    private PessoaService pessoaService;

    @PostMapping("/create")
    public ResponseEntity<Pessoa> createPessoa(@RequestBody PessoaDTO pessoaDTO) {
        Pessoa pessoa = pessoaService.createPessoa(pessoaDTO);
        return ResponseEntity.ok(pessoa);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<Pessoa> updatePessoa(@PathVariable Long id, @RequestBody PessoaDTO pessoaDTO) {
        Pessoa updatedPessoa = pessoaService.updatePessoa(id, pessoaDTO);
        return ResponseEntity.ok(updatedPessoa);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Pessoa> deletePessoa(@PathVariable Long id) {
        pessoaService.deletePessoa(id);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/all")
    public ResponseEntity<Page<Pessoa>> findAllPessoas(int page, int size) {
        Page<Pessoa> pessoaList = pessoaService.findAllPessoas(page, size);
        return ResponseEntity.ok(pessoaList);
    }

    @GetMapping("/id/{id}")
    public ResponseEntity<Pessoa> findPessoaById(@PathVariable Long id) {
        Pessoa pessoa = pessoaService.findPessoaById(id);
        return ResponseEntity.ok(pessoa);
    }

    @GetMapping("/search")
    public ResponseEntity<List<Pessoa>> searchPessoa(@RequestParam String query) {
        List<Pessoa> pessoa = pessoaService.searchPessoa(query);
        return ResponseEntity.ok(pessoa);
    }
}