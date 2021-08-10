package com.maia.services.interfaces;

import java.util.List;

import javax.validation.Valid;

import org.springframework.transaction.annotation.Transactional;

import com.maia.domain.Colaborador;
import com.maia.domain.dto.ColaboradorBlackListDTO;
import com.maia.domain.dto.ColaboradorDTO;
import com.maia.domain.dto.ColaboradorNewDTO;

public interface ColaboradorService {

	@Transactional
	Colaborador retornaColaboradorPorId(Long id);

	@Transactional
	Colaborador retornaColaboradorPorCpf(String cpf);

	@Transactional
	Colaborador inserirNovoColaborador(Colaborador colaborador);

	@Transactional
	void excluirColaborador(Long id);

	@Transactional(readOnly = true)
	List<ColaboradorDTO> retornaColaboradoresPorSetor(Long idSetor);

	@Transactional(readOnly = true)
	List<ColaboradorBlackListDTO> obterColaboradorBlackListByCpf(String cpf);

	@Transactional(readOnly = true)
	void validaCadastroColaboradorMenorDeIdade(Long idSetor);
	
	@Transactional(readOnly = true)
	void validaCadastroColaboradorMaiorDe65Anos(Long idSetor);

	Colaborador fromDTO(@Valid ColaboradorNewDTO dto);

}
