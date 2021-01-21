package com.gerenciador.apigerenciador.repository;

import com.gerenciador.apigerenciador.models.Banco;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface BancoRepository extends JpaRepository<Banco, Long>{
    @Query(value = "select*from banco where id IN  (select bancos_id from pessoa_bancos where pessoa_id =:pessoaId)", nativeQuery = true)
    List<Banco> findByCdPessoa(@Param("pessoaId") Long pessoaId);
    
    Banco findById(long id);
    
}
