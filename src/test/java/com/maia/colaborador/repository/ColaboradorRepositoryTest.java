package com.maia.colaborador.repository;

import java.time.LocalDate;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.maia.domain.Colaborador;
import com.maia.repository.ColaboradorRepository;

@ExtendWith(SpringExtension.class)
@ActiveProfiles("test")
@DataJpaTest
public class ColaboradorRepositoryTest {

	@Autowired
	private TestEntityManager entityManager;

	@Autowired
	private ColaboradorRepository repository;

	private Colaborador createColaborador() {
		return Colaborador.builder()
				.cpf("73619083061")
				.dataNascimento(LocalDate.parse("1990-10-10"))
				.email("dowglasmaia@maia.com")
				.nome("Dowglas Maia Test")
				.telefone("11-8986-5656")
				.build();
	}

	@Test
	@DisplayName("Deve retorna verdadeiro quando existir um colaborador com cpf informado")
	public void retornaColaboradorporCpf() {
		// cenario
		String cpf = "73619083061";

		Colaborador colaborador = createColaborador();
		entityManager.persist(colaborador);

		// execução
		boolean result = repository.existsByCpf(cpf);

		// verificação
		Assertions.assertThat(result).isTrue();

	}
	
	@Test
	@DisplayName("Deve Salvar um novo colaborador")
	public void saveColaborador() {
		Colaborador colaborador = createColaborador();
		
		Colaborador savedColaborador = repository.save(colaborador);
		
		Assertions.assertThat(savedColaborador.getId() ).isNotNull();
		
		
	}

}
