package com.vsm.gerenciador_pessoa_cidade;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class GerenciadorPessoaCidadeApplication {
	public static void main(String[] args) {
		SpringApplication.run(GerenciadorPessoaCidadeApplication.class, args);
	}
}