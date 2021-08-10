package com.maia.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.maia.domain.Colaborador;

@Repository
public interface ColaboradorRepository extends JpaRepository<Colaborador, Long> {

	Optional<Colaborador>findByCpf(String cpf);

	@Query("SELECT c FROM Colaborador c WHERE c.nome like concat(?1,'%') ORDER BY c.nome")
	List<Colaborador> findColaboradoresPorNome(String nome);

	boolean existsByCpf(String isbn);
}
