/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gerenciador.apigerenciador.repository;

import com.gerenciador.apigerenciador.models.Pessoa;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 *
 * @author tasso
 */
public interface PessoaRepository extends JpaRepository<Pessoa, Long>{
    
}
