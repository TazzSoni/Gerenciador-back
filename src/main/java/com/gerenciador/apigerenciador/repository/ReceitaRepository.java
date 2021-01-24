package com.gerenciador.apigerenciador.repository;

import com.gerenciador.apigerenciador.models.Receita;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface ReceitaRepository extends JpaRepository<Receita, Long>{
    @Query(value = "select*from receita where id IN  (select receitas_id from pessoa_receitass where pessoa_id =:pessoaId)", nativeQuery = true)
    List<Receita> findByCdPessoa(@Param("pessoaId") Long pessoaId);
    
    Receita findById(long id);
    
}
