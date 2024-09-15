package com.vsm.gerenciador_pessoa_cidade.controllers;

import com.vsm.gerenciador_pessoa_cidade.entities.Cidade;
import com.vsm.gerenciador_pessoa_cidade.dtos.CidadeDTO;
import com.vsm.gerenciador_pessoa_cidade.services.CidadeService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("gerenciador/cidade")
public class CidadeController {

    @Autowired
    private CidadeService cidadeService;

    @Operation(summary = "Criar Cidade", description = "Cria uma nova cidade")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Cidade criada com sucesso"),
            @ApiResponse(responseCode = "400", description = "Campo obrigatório não preenchido"),
            @ApiResponse(responseCode = "500", description = "Erro interno do servidor")
    })
    @PostMapping("/create")
    public ResponseEntity<Cidade> createCidade(@RequestBody CidadeDTO cidadeDTO) {
        Cidade cidade = cidadeService.createCidade(cidadeDTO);
        return ResponseEntity.ok(cidade);
    }

    @Operation(summary = "Atualizar Cidade", description = "Atualiza uma cidade, indicada pelo ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Cidade atualizada com sucesso"),
            @ApiResponse(responseCode = "400", description = "Campo obrigatório não foi preenchido"),
            @ApiResponse(responseCode = "404", description = "Cidade não encontrada com ID informado"),
            @ApiResponse(responseCode = "500", description = "Erro interno do servidor")
    })
    @PutMapping("/update/{id}")
    public ResponseEntity<Cidade> updateCidade(@PathVariable Long id, @RequestBody CidadeDTO cidadeDTO) {
        Cidade cidade = cidadeService.updateCidade(id, cidadeDTO);
        return ResponseEntity.ok(cidade);
    }

    @Operation(summary = "Deletar Cidade", description = "Deleta uma cidade, indicada pelo ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Cidade deletada com sucesso"),
            @ApiResponse(responseCode = "500", description = "Erro interno do servidor")
    })
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Cidade> deleteCidade(@PathVariable Long id) {
        cidadeService.deleteCidade(id);
        return ResponseEntity.ok().build();
    }


    @Operation(summary = "Listar Cidades",
            description = "Lista todas as cidades contidas no banco")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Cidades recuperadas com sucesso"),
            @ApiResponse(responseCode = "500", description = "Erro interno do servidor")
    })
    @GetMapping("/all")
    public ResponseEntity<List<Cidade>> findAllCidades() {
        List<Cidade> cidadeList = cidadeService.getAllCidades();
        return ResponseEntity.ok(cidadeList);
    }


    @Operation(summary = "Recuperar Cidade por ID",
            description = "Recupera cidade com o ID informado")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Cidade recuperada com sucesso"),
            @ApiResponse(responseCode = "404", description = "Cidade não encontrada com ID informado"),
            @ApiResponse(responseCode = "500", description = "Erro interno do servidor")
    })
    @GetMapping("/id/{id}")
    public ResponseEntity<Cidade> findCidadeById(@PathVariable Long id) {
        Cidade cidade = cidadeService.findCidadeById(id);
        return ResponseEntity.ok(cidade);
    }

    @Operation(summary = "Pesquisar Cidade por ID ou Nome",
            description = "Pesquisa cidades por ID ou Nome, retornando uma lista")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Cidades recuperadas com sucesso"),
            @ApiResponse(responseCode = "500", description = "Erro interno do servidor")
    })
    @GetMapping("/search")
    public ResponseEntity<List<Cidade>> searchCidade(@RequestParam String query) {
        List<Cidade> cidade = cidadeService.searchCidadeByIdOrNomeOrUf(query);
        return ResponseEntity.ok(cidade);
    }
}