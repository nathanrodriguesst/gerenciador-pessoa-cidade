package com.vsm.gerenciador_pessoa_cidade.controllers;

import com.vsm.gerenciador_pessoa_cidade.entities.Cidade;
import com.vsm.gerenciador_pessoa_cidade.dtos.CidadeDTO;
import com.vsm.gerenciador_pessoa_cidade.services.CidadeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("gerenciador/cidade")
public class CidadeController {

    @Autowired
    private CidadeService cidadeService;

    @PostMapping("/create")
    public ResponseEntity<Cidade> createCidade(@RequestBody CidadeDTO cidadeDTO) {
        Cidade cidade = cidadeService.createCidade(cidadeDTO);
        return ResponseEntity.ok(cidade);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<Cidade> updateCidade(@PathVariable Long id, @RequestBody CidadeDTO cidadeDTO) {
        Cidade cidade = cidadeService.updateCidade(id, cidadeDTO);
        return ResponseEntity.ok(cidade);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Cidade> deleteCidade(@PathVariable Long id) {
        cidadeService.deleteCidade(id);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/id/{id}")
    public ResponseEntity<Cidade> findCidadeById(@PathVariable Long id) {
        Cidade cidade = cidadeService.findCidadeById(id);
        return ResponseEntity.ok(cidade);
    }

    @GetMapping("/search")
    public ResponseEntity<Page<Cidade>> searchCidade(@RequestParam String query, int page, int size) {
        Page<Cidade> cidade = (Page<Cidade>) cidadeService.searchCidade(query, page, size);
        return ResponseEntity.ok(cidade);
    }

    @GetMapping("/all")
    public ResponseEntity<List<Cidade>> findAll() {
        List<Cidade> cidades = cidadeService.findAllCidades();
        return ResponseEntity.ok(cidades);
    }
}