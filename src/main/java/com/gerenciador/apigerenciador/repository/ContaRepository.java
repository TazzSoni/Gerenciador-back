/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gerenciador.apigerenciador.repository;

import com.gerenciador.apigerenciador.models.Conta;
import com.gerenciador.apigerenciador.models.Pessoa;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

/**
 *
 * @author tasso
 */
public interface ContaRepository extends JpaRepository<Conta, Long>{
    @Query(value = "select * from conta where cd_pessoa =:pessoaId", nativeQuery = true)
    
    List<Conta> findByCdPessoa(@Param("pessoaId") Long pessoaId);
    
}
