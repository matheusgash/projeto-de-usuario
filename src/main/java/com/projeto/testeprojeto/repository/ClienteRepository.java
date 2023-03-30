package com.projeto.testeprojeto.repository;

import com.projeto.testeprojeto.entity.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface ClienteRepository extends JpaRepository<Cliente, Long> {

    @Query("SELECT c.nome FROM Cliente c")
    List<String> findAllNome();
}