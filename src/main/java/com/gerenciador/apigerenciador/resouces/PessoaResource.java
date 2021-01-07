package com.gerenciador.apigerenciador.resouces;

import com.gerenciador.apigerenciador.models.Pessoa;
import com.gerenciador.apigerenciador.repository.ContaRepository;
import com.gerenciador.apigerenciador.repository.PessoaRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/api")
public class PessoaResource {

    @Autowired
    PessoaRepository pessoaRepository;
    @Autowired
    ContaRepository contaRepository;

    //@PreAuthorize verifica se o usuário está acessando seus dados
    @PreAuthorize("@securityCheck.check(#login,authentication)")
    @GetMapping("/pessoa/{login}")
    public Pessoa Pessoa(@PathVariable(value = "login") String login){
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
