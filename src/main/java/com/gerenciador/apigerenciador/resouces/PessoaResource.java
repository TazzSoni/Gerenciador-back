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
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
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

    @GetMapping("/pessoa/{login}")
    public Pessoa Pessoa(@PathVariable(value="login") String login) {
        return pessoaRepository.findByLogin(login);
    }

    @GetMapping("/pessoa/{login}/{conta}")
    public List<Conta> listaContasPessoa(@PathVariable(value="login") String login) {
        System.out.println("aqui");
     Pessoa p =   pessoaRepository.findByLogin(login);
        System.out.println(p.toString());
        return (List<Conta>) contaRepository.findByCdPessoa(p.getId());
    }
    
    @GetMapping("/pessoa")
    public List<Pessoa> listaPessoas() {
        return pessoaRepository.findAll();
    }
}
