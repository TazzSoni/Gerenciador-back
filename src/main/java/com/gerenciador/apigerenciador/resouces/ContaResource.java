package com.gerenciador.apigerenciador.resouces;

import com.gerenciador.apigerenciador.models.Pessoa;
import com.gerenciador.apigerenciador.models.Conta;
import com.gerenciador.apigerenciador.repository.ContaRepository;
import com.gerenciador.apigerenciador.repository.PessoaRepository;
import java.util.List;
import java.util.Optional;
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
        p.setDespesas();
        pessoaRepository.save(p);
        return conta;
    }

    @PutMapping("/conta/{login}")
    public Conta editConta(@PathVariable(value = "login") String login, @RequestBody Conta conta) {
        return contaRepository.save(conta);
    }

    @DeleteMapping("/conta/{login}/{id}")
    public Conta deleteConta(@PathVariable(value = "login") String login, @PathVariable(value = "id") long id) {
        Pessoa p = pessoaRepository.findByLogin(login);
        Conta conta = contaRepository.findById(id);
        p.deleteConta(conta);
        pessoaRepository.save(p);
        contaRepository.delete(conta);
        return conta;
    }
}
