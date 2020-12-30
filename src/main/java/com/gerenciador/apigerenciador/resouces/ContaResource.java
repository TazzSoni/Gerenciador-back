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
public class ContaResource {

    @Autowired
    PessoaRepository pessoaRepository;
    @Autowired
    ContaRepository contaRepository;

    @GetMapping("/conta/{login}")
    public List<Conta> listaContasPessoa(@PathVariable(value = "login") String login) {
        Pessoa p = pessoaRepository.findByLogin(login);
        return (List<Conta>) contaRepository.findByCdPessoa(p.getId());
    }

    @PostMapping("/conta/{login}")
    public Conta criaConta(@PathVariable(value = "login") String login, @RequestBody Conta conta) {
        Pessoa p = pessoaRepository.findByLogin(login);
        p.addContas(conta);
        pessoaRepository.save(p);
        return conta;
    }

    @PutMapping("/conta/{login}")
    public Conta editConta(@PathVariable(value = "login") String login, @RequestBody Conta conta) {
        return contaRepository.save(conta);
    }
}
