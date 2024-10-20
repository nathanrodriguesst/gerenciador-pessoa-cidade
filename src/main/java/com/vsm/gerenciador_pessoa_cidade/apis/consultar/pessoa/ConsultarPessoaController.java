package com.vsm.gerenciador_pessoa_cidade.apis.consultar.pessoa;

import com.vsm.gerenciador_pessoa_cidade.entities.Pessoa;
import com.vsm.gerenciador_pessoa_cidade.services.PessoaService;

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

    @GetMapping("/{cpf}")
    public ResponseEntity<Pessoa> findPessoaByCpf(@PathVariable String cpf) {
        cpf = cpf.replaceAll("[^0-9]", "");
        Pessoa pessoa = pessoaService.findPessoaByCpf(cpf);
        return ResponseEntity.ok(pessoa);
    }
}