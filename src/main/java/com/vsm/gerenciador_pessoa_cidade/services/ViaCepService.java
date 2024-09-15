package com.vsm.gerenciador_pessoa_cidade.services;

import com.vsm.gerenciador_pessoa_cidade.entities.Endereco;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(url= "https://viacep.com.br/ws/" , name = "viacep")
public interface ViaCepService {

    @GetMapping("{cep}/json")
    Endereco findEnderecoByCEP(@PathVariable String cep);

}