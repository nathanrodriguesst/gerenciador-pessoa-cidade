package com.vsm.gerenciador_pessoa_cidade.apis.consultar.pessoa;

import com.vsm.gerenciador_pessoa_cidade.entities.Pessoa;
import com.vsm.gerenciador_pessoa_cidade.services.PessoaService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/v1/consultar/pessoa")
public class ConsultarPessoaController {

    @Autowired
    private final PessoaService pessoaService;

    public ConsultarPessoaController(PessoaService pessoaService) {
        this.pessoaService = pessoaService;
    }

    @Operation(summary = "Consultar Pessoa por CPF",
            description = "Esse end-point retorna a pessoa cuja o CPF foi informado")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Pessoa recuperada com sucesso"),
            @ApiResponse(responseCode = "404", description = "Pessoa não encontrada com CPF informado"),
            @ApiResponse(responseCode = "500", description = "Erro interno do servidor")
    })
    @GetMapping("/{cpf}")
    public ResponseEntity<Pessoa> findPessoaByCpf(@PathVariable String cpf) {
        cpf = cpf.replaceAll("[^0-9]", "");
        Pessoa pessoa = pessoaService.findPessoaByCpf(cpf);
        return ResponseEntity.ok(pessoa);
    }
}