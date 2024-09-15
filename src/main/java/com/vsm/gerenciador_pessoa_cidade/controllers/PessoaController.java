package com.vsm.gerenciador_pessoa_cidade.controllers;

import com.vsm.gerenciador_pessoa_cidade.entities.Pessoa;
import com.vsm.gerenciador_pessoa_cidade.dtos.PessoaDTO;
import com.vsm.gerenciador_pessoa_cidade.services.PessoaService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("gerenciador/pessoa")
public class PessoaController {

    @Autowired
    private PessoaService pessoaService;


    @Operation(summary = "Criar Nova Pessoa", description = "Cria uma nova pessoa")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Pessoa criada com sucesso"),
            @ApiResponse(responseCode = "400", description = "Campo obrigatório não preenchido"),
            @ApiResponse(responseCode = "500", description = "Erro interno do servidor")
    })
    @PostMapping("/create")
    public ResponseEntity<Pessoa> createPessoa(@RequestBody PessoaDTO pessoaDTO) {
        Pessoa pessoa = pessoaService.createPessoa(pessoaDTO);
        return ResponseEntity.ok(pessoa);
    }

    @Operation(summary = "Atualizar Pessoa", description = "Atualiza uma pessoa, indicada pelo ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Pessoa atualizada com sucesso"),
            @ApiResponse(responseCode = "400", description = "Campo obrigatório não foi preenchido"),
            @ApiResponse(responseCode = "404", description = "Pessoa não encontrada com ID informado"),
            @ApiResponse(responseCode = "500", description = "Erro interno do servidor")
    })
    @PutMapping("/update/{id}")
    public ResponseEntity<Pessoa> updatePessoa(@PathVariable Long id, @RequestBody PessoaDTO pessoaDTO) {
        Pessoa updatedPessoa = pessoaService.updatePessoa(id, pessoaDTO);
        return ResponseEntity.ok(updatedPessoa);
    }

    @Operation(summary = "Deletar Pessoa", description = "Deleta uma pessoa, indicada pelo ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Pessoa deletada com sucesso"),
            @ApiResponse(responseCode = "500", description = "Erro interno do servidor")
    })
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Pessoa> deletePessoa(@PathVariable Long id) {
        pessoaService.deletePessoa(id);
        return ResponseEntity.ok().build();
    }

    @Operation(summary = "Listar Pessoas",
            description = "Lista todas as pessoas contidas no banco")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Pessoas recuperadas com sucesso"),
            @ApiResponse(responseCode = "500", description = "Erro interno do servidor")
    })
    @GetMapping("/all")
    public ResponseEntity<List<Pessoa>> findAllPessoas() {
        return ResponseEntity.ok(pessoaService.findAllPessoas());
    }

    @Operation(summary = "Recuperar Pessoa por ID",
            description = "Recupera pessoa com o ID informado")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Pessoa recuperada com sucesso"),
            @ApiResponse(responseCode = "404", description = "Pessoa não encontrada com ID informado"),
            @ApiResponse(responseCode = "500", description = "Erro interno do servidor")
    })
    @GetMapping("/id/{id}")
    public ResponseEntity<Pessoa> findPessoaById(@PathVariable Long id) {
        Pessoa pessoa = pessoaService.findPessoaById(id);
        return ResponseEntity.ok(pessoa);
    }

    @Operation(summary = "Pesquisar Pessoa por ID, Nome ou CPF",
            description = "Pesquisa pessoas por ID, Nome ou CPF, retornando uma lista")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Pessoas recuperadas com sucesso"),
            @ApiResponse(responseCode = "500", description = "Erro interno do servidor")
    })
    @GetMapping("/search")
    public ResponseEntity<List<Pessoa>> searchPessoa(@RequestParam String query) {
        List<Pessoa> pessoa = pessoaService.searchPessoaByIdOrNomeOrCpf(query);
        return ResponseEntity.ok(pessoa);
    }
}