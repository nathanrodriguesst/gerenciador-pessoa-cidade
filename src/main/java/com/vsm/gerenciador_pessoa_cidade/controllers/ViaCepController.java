package com.vsm.gerenciador_pessoa_cidade.controllers;

import com.vsm.gerenciador_pessoa_cidade.entities.Cidade;
import com.vsm.gerenciador_pessoa_cidade.entities.Endereco;
import com.vsm.gerenciador_pessoa_cidade.services.CidadeService;
import com.vsm.gerenciador_pessoa_cidade.services.ViaCepService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/v1/consultar/cep")
public class ViaCepController {

    @Autowired
    private ViaCepService viaCepService;

    @Autowired
    private CidadeService cidadeService;

    @GetMapping("/{cep}")
    public ResponseEntity<Endereco> getCep(@PathVariable String cep) {
        cep = cep.replaceAll("^0-9", "");
        Endereco endereco = this.viaCepService.findEnderecoByCEP(cep);

        Cidade cidade = this.cidadeService.findCidadeByIbge(endereco.getIbge());
        endereco.setCidade(cidade);

        return ResponseEntity.ok(endereco);
    }
}