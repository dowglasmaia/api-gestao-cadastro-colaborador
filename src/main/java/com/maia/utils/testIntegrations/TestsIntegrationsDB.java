package com.maia.utils.testIntegrations;

import java.text.ParseException;
import java.time.LocalDate;
import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.maia.domain.Colaborador;
import com.maia.domain.Setor;
import com.maia.repository.ColaboradorRepository;
import com.maia.repository.SetorRepository;

@Service
public class TestsIntegrationsDB {
	
	@Autowired	private ColaboradorRepository colaboradorRepo;
	
	@Autowired private SetorRepository setorRepo;

	public void instanciateTestDatabase() throws ParseException {
		
		/* Setor */
		Setor st1 = Setor.builder().descricao("Ti").build();
		Setor st2 = Setor.builder().descricao("Vendas").build();
		Setor st3 = Setor.builder().descricao("Rh").build();		
		setorRepo.saveAll(Arrays.asList(st1,st2,st3));
		/* Fim - Setor */
		
		/* Colaborador */
		Colaborador c1 =  Colaborador.builder()
				.nome("Maia")
				.cpf("65555311001")
				.dataNascimento(LocalDate.parse("2005-10-20"))
				.email("dowglasmaia@gmail.com.ol")
				.telefone("48998350548")
				.setor(st1)
				.build();
		
		Colaborador c2 =  Colaborador.builder()
				.nome("Dowglas")
				.cpf("77334862031")
				.dataNascimento(LocalDate.parse("1985-10-20"))
				.email("dowglasmaia@gmail.m")
				.telefone("48998350543")
				.setor(st1)
				.build();
		
		Colaborador c3 =  Colaborador.builder()
				.nome("Dowglas Maia")
				.cpf("26272162014")
				.dataNascimento(LocalDate.parse("1956-10-20"))
				.email("dowglasmaia@gmail.cm")
				.telefone("48998350241")
				.setor(st1)
				.build();	
		
		Colaborador c4 =  Colaborador.builder()
				.nome("Kayron Maia")
				.cpf("48536339012")
				.dataNascimento(LocalDate.parse("2000-10-20"))
				.email("dowglasmaia@live.com.op")
				.telefone("48998350540")
				.setor(st1)
				.build();		
		
		Colaborador c5 =  Colaborador.builder()
				.nome("Shirle Maia")
				.cpf("63866181060")
				.dataNascimento(LocalDate.parse("1985-10-20"))
				.email("dowglasmaia@live.com.br")
				.telefone("48998350546")
				.setor(st3)
				.build();	
		
		Colaborador c6 =  Colaborador.builder()
				.nome("Gustavo")
				.cpf("15044036064")
				.dataNascimento(LocalDate.parse("1985-10-20"))
				.email("dowglasmaia@gmail.com.pr")
				.telefone("45998350548")
				.setor(st1)
				.build();
		
		Colaborador c7 =  Colaborador.builder()
				.nome("Gustavo")
				.cpf("38938058000")
				.dataNascimento(LocalDate.parse("1985-10-20"))
				.email("dowglasmaia@gmail.com.ol01")
				.telefone("41998350548")
				.setor(st1)
				.build();
		
		Colaborador c8 =  Colaborador.builder()
				.nome("Gustavo")
				.cpf("67570852087")
				.dataNascimento(LocalDate.parse("1985-10-20"))
				.email("dowglasmaia@gmail.9l")
				.telefone("18998350548")
				.setor(st1)
				.build();
		
		Colaborador c9 =  Colaborador.builder()
				.nome("Lima")
				.cpf("61463517092")
				.dataNascimento(LocalDate.parse("1985-10-20"))
				.email("dowglasmaia@gmail.com.o8")
				.telefone("46998350548")
				.setor(st1)
				.build();
		
		Colaborador c10 =  Colaborador.builder()
				.nome("Marcos")
				.cpf("60891252029")
				.dataNascimento(LocalDate.parse("1985-10-20"))
				.email("dowglasmaia@gmail.i")
				.telefone("38998350548")
				.setor(st1)
				.build();
		
		colaboradorRepo.saveAll(Arrays.asList(c1,c2,c3,c4,c5,c6,c7,c8,c9,c10));
		/* Fim - Colaborador */
	
				
	}
	
}
