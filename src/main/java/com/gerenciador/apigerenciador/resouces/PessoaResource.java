/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gerenciador.apigerenciador.resouces;

import com.gerenciador.apigerenciador.models.Pessoa;
import com.gerenciador.apigerenciador.models.Conta;
import com.gerenciador.apigerenciador.repository.ContaRepository;
import com.gerenciador.apigerenciador.repository.PessoaRepository;
import java.security.Principal;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author tasso
 */
@RestController
@RequestMapping(value = "/api")
public class PessoaResource {

    @Autowired
    PessoaRepository pessoaRepository;
    @Autowired
    ContaRepository contaRepository;

    //@PreAuthorize verifica se o usuário está acessando seus dados
    @PreAuthorize("#login == authentication.principal.username")
    @GetMapping("/pessoa/{login}")
    public Pessoa Pessoa(@PathVariable(value = "login") String login, 
           @AuthenticationPrincipal UserDetails userDetails ) {
        System.out.println(userDetails);
        return pessoaRepository.findByLogin(login);
    }

    @GetMapping("/pessoa")
    public List<Pessoa> listaPessoas() {
        return pessoaRepository.findAll();
    }

    @PostMapping("/pessoa")
    public Pessoa createPessoa(@RequestBody Pessoa pessoa) {
        return pessoaRepository.save(pessoa);
    }

    @PutMapping("/pessoa/{login}")
    public Pessoa editPessoa(@PathVariable(value = "login") String login, @RequestBody Pessoa pessoa) {
        pessoa = pessoaRepository.findByLogin(login);
        return pessoaRepository.save(pessoa);
    }
}
