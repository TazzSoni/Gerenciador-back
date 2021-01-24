package com.gerenciador.apigerenciador.resouces;

import com.gerenciador.apigerenciador.models.Pessoa;
import com.gerenciador.apigerenciador.models.Receita;
import com.gerenciador.apigerenciador.repository.ReceitaRepository;
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
public class ReceitaResource {

    @Autowired
    PessoaRepository pessoaRepository;
    @Autowired
    ReceitaRepository receitaRepository;

    @GetMapping("/receita/{login}")
    public List<Receita> listaReceitaPessoa(@PathVariable(value = "login") String login) {
        Pessoa p = pessoaRepository.findByLogin(login);
        return (List<Receita>) receitaRepository.findByCdPessoa(p.getId());
    }

    @PostMapping("/receita/{login}")
    public Receita criaReceita(@PathVariable(value = "login") String login, @RequestBody Receita receita) {
        Pessoa p = pessoaRepository.findByLogin(login);
        p.addReceitas(receita);
        p.setCarteira();
        pessoaRepository.save(p);
        return receita;
    }

    @PutMapping("/receita/{login}")
    public Receita editReceita(@PathVariable(value = "login") String login, @RequestBody Receita receita) {
        Pessoa p = pessoaRepository.findByLogin(login);
        p.atualizaReceita(receita);
        pessoaRepository.save(p);
        return receitaRepository.save(receita);
    }

    @DeleteMapping("/receita/{login}/{id}")
    public Receita deleteReceita(@PathVariable(value = "login") String login, @PathVariable(value = "id") long id) {
        Pessoa p = pessoaRepository.findByLogin(login);
        Receita receita = receitaRepository.findById(id);
        p.deleteReceita(receita);
        pessoaRepository.save(p);
        receitaRepository.delete(receita);
        return receita;
    }
}

