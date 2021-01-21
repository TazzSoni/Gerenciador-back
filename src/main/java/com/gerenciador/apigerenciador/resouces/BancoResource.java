package com.gerenciador.apigerenciador.resouces;

import com.gerenciador.apigerenciador.models.Pessoa;
import com.gerenciador.apigerenciador.models.Banco;
import com.gerenciador.apigerenciador.repository.BancoRepository;
import com.gerenciador.apigerenciador.repository.PessoaRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/api")
public class BancoResource {

    @Autowired
    PessoaRepository pessoaRepository;
    @Autowired
    BancoRepository bancoRepository;

    @GetMapping("/banco/{login}")
    public List<Banco> listaBancoPessoa(@PathVariable(value = "login") String login) {
        Pessoa p = pessoaRepository.findByLogin(login);
        return (List<Banco>) bancoRepository.findByCdPessoa(p.getId());
    }

    @PostMapping("/banco/{login}")
    public Banco criaBanco(@PathVariable(value = "login") String login, @RequestBody Banco banco) {
        Pessoa p = pessoaRepository.findByLogin(login);
        p.addBancos(banco);
        p.setCarteira();
        pessoaRepository.save(p);
        return banco;
    }

    @PutMapping("/banco/{login}")
    public Banco editBanco(@PathVariable(value = "login") String login, @RequestBody Banco banco) {
        Pessoa p = pessoaRepository.findByLogin(login);
        p.atualizaBanco(banco);
        pessoaRepository.save(p);
        return bancoRepository.save(banco);
    }

    @DeleteMapping("/banco/{login}/{id}")
    public Banco deleteBanco(@PathVariable(value = "login") String login, @PathVariable(value = "id") long id) {
        Pessoa p = pessoaRepository.findByLogin(login);
        Banco banco = bancoRepository.findById(id);
        p.deleteBanco(banco);
        pessoaRepository.save(p);
        bancoRepository.delete(banco);
        return banco;
    }
}

