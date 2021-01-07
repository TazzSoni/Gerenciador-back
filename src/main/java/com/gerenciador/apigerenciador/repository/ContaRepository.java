package com.gerenciador.apigerenciador.repository;

import com.gerenciador.apigerenciador.models.Conta;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface ContaRepository extends JpaRepository<Conta, Long>{
    @Query(value = "select*from conta where id IN  (select contas_id from pessoa_contas where pessoa_id =:pessoaId)", nativeQuery = true)
    List<Conta> findByCdPessoa(@Param("pessoaId") Long pessoaId);
    
}
