package com.gerenciador.apigerenciador.repository;

import com.gerenciador.apigerenciador.models.Pessoa;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PessoaRepository extends JpaRepository<Pessoa, String>{
    
    Pessoa findByLogin(String login);
    
}
