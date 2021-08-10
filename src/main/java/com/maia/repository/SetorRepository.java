package com.maia.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.maia.domain.Setor;

@Repository
public interface SetorRepository extends JpaRepository<Setor, Long> {

	Optional<Setor>findByDescricao(String descricao);

		
	

}
