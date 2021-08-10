package com.maia.services.impl;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.maia.domain.Colaborador;
import com.maia.domain.Setor;
import com.maia.domain.dto.ColaboradorBlackListDTO;
import com.maia.domain.dto.ColaboradorDTO;
import com.maia.domain.dto.ColaboradorNewDTO;
import com.maia.repository.ColaboradorRepository;
import com.maia.repository.SetorRepository;
import com.maia.services.exceptions.ObjectNotFoundException;
import com.maia.services.exceptions.OpoerationErrorException;
import com.maia.services.interfaces.ColaboradorService;
import com.maia.services.util.DateUtils;

@Service
public class ColaboradorServiceImpl implements ColaboradorService {

	private static final String GET_ENDPOINT_COLABORADOR_POR_CPF = "https://5e74cb4b9dff120016353b04.mockapi.io/api/v1/blacklist?search=";

	@Autowired
	private ColaboradorRepository repository;
	
	@Autowired
	private SetorRepository setorRepository;

	@Autowired
	private RestTemplate restTemplate;
		
	@Autowired
	private DateUtils dateUtil;

	@Override
	public List<ColaboradorBlackListDTO> obterColaboradorBlackListByCpf(String cpf) {

		Gson gson = new Gson();

		HttpHeaders headers = new HttpHeaders();
		headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
		HttpEntity<String> entity = new HttpEntity<String>("parameters", headers);

		ResponseEntity<String> responseEntity = restTemplate.exchange(GET_ENDPOINT_COLABORADOR_POR_CPF + cpf,
				HttpMethod.GET, entity, String.class);

		TypeToken<List<ColaboradorBlackListDTO>> collectionType = new TypeToken<List<ColaboradorBlackListDTO>>() {};

		List<ColaboradorBlackListDTO> result = gson.fromJson(responseEntity.getBody(), collectionType.getType());

		return result;
	}

	@Override
	public Colaborador retornaColaboradorPorId(Long id) {
		Optional<Colaborador> Colaborador = repository.findById(id);
		return Colaborador
				.orElseThrow(() -> new ObjectNotFoundException("Colaborador não encontrado para o Código: " + id));
	}

	@Override
	public Colaborador retornaColaboradorPorCpf(String cpf) {
		Optional<Colaborador> Colaborador = repository.findByCpf(cpf);
		return Colaborador
				.orElseThrow(() -> new ObjectNotFoundException("Colaborador não encontrado para o CPF: " + cpf));
	}

	@Override
	public Colaborador inserirNovoColaborador(Colaborador colaborador) {
		
		this.validaColaboradorPorCpf(colaborador.getCpf());

		colaborador.setId(null);
		
		int idadeDoNovoColaborador = dateUtil.retornaIdadeColaborador(colaborador.getDataNascimento());
		
		try {
		
			boolean estarNalista = this.obterColaboradorBlackListByCpf(colaborador.getCpf()).size() > 0 ? true : false;

			if (estarNalista) {
				throw new OpoerationErrorException("Colaborador não pode ser Cadastrado, consulte o RH");
			}		
		
			if( idadeDoNovoColaborador < 18 ) {
				this.validaCadastroColaboradorMenorDeIdade(colaborador.getSetor().getId());		  
			}	
			
			if(  idadeDoNovoColaborador > 65 ) {
				this.validaCadastroColaboradorMaiorDe65Anos(colaborador.getSetor().getId());		  
			}
			
			return repository.save(colaborador);
			
		} catch (Exception e) {
			throw new OpoerationErrorException("Falha ao tentar salvar um novo Colaborador; " +e.getMessage());
		}

	}

	private boolean validaColaboradorPorCpf(String cpf) {
		boolean result = repository.findByCpf(cpf).isPresent();		
		if(result) {
			throw new OpoerationErrorException("Colaborador de CPF: " +cpf +" já estar cadastrado na base de Dados.");
		}
		return result;		
	}

	@Override
	public void excluirColaborador(Long id) {
		retornaColaboradorPorId(id);
		try {
			repository.deleteById(id);
		} catch (Exception e) {
			throw new OpoerationErrorException("Falha ao Excluir Colaborador de Código:" + id);
		}
	}

	@Override
	public List<ColaboradorDTO> retornaColaboradoresPorSetor(Long idSetor) {
		
		List<ColaboradorDTO> colaboradores = repository.findAll()
				.stream()
				.filter(c -> c.getSetor().getId() == idSetor)
				.map(result -> new ColaboradorDTO(result))
				.collect(Collectors.toList());
		
		return colaboradores;
	}
	
	@Override
	public void validaCadastroColaboradorMaiorDe65Anos(Long idSetor) {
		try {			  
			  LocalDate anoAtual = LocalDate.now();
			  
			  List<ColaboradorDTO> totalDeColaboradoresCadastrados = retornaColaboradoresPorSetor(idSetor);				
			  
			  double totalColaboradoresMaioresDe65AnosPermitodoParaCadastro = totalDeColaboradoresCadastrados.size() * 0.20;
			  
			  if(  totalColaboradoresMaioresDe65AnosPermitodoParaCadastro >= 1) {				  
				  double totalColaboradoresMaioresDe65AnosCadastrados = totalDeColaboradoresCadastrados
						  .stream()
						  .filter(cl -> cl.getDataNascimento().isBefore(anoAtual.minusYears(65)))
						  .count();					  
				  
				  if (totalColaboradoresMaioresDe65AnosPermitodoParaCadastro < ( totalColaboradoresMaioresDe65AnosCadastrados + 1) ) {
					  throw new OpoerationErrorException("Não é mais permitido cadastrar colaboradores maiores de 65 Anos");
				  }		 
			  }else {
				  throw new OpoerationErrorException("consulte o RH para validar a cota de colaboradores por faixa etária de idade para este departamento.");
			  }
		} catch (Exception e) {
			throw new OpoerationErrorException(e.getMessage());
		}
		
	}
	
	
	@Override
	public void validaCadastroColaboradorMenorDeIdade(Long idSetor) {			
		try {			  
			  LocalDate anoAtual = LocalDate.now();
			  
			  List<ColaboradorDTO> totalDeColaboradoresCadastrados = retornaColaboradoresPorSetor(idSetor);		  
			  
			  double totalColaboradoresMenoresDe18AnosPermitodoParaCadastro = totalDeColaboradoresCadastrados.size() * 0.20;
			  			  
			  if( totalColaboradoresMenoresDe18AnosPermitodoParaCadastro >= 1 ) {	
				  double totalColaboradoresMenoresDe18AnosCadastrados =  totalDeColaboradoresCadastrados
						  .stream()
						  .filter(cl -> cl.getDataNascimento().isAfter(anoAtual.minusYears(18)))
						  .count();
				  
				  if (totalColaboradoresMenoresDe18AnosPermitodoParaCadastro < ( totalColaboradoresMenoresDe18AnosCadastrados + 1) ) {
					  throw new OpoerationErrorException("Não é mais permitido cadastrar colaboradores menores de 18 Anos");
				  }				  
			  }else {
				  throw new OpoerationErrorException("consulte o RH para validar a cota de colaboradores por faixa etária de idade para este departamento.");
			  }
		} catch (Exception e) {
			throw new OpoerationErrorException(e.getMessage());
		}

	}

	@Override
	public Colaborador fromDTO(ColaboradorNewDTO dto) {
		
		Setor setor =  setorRepository.findById(dto.getIdSetor()).orElseThrow( 
				()-> new OpoerationErrorException("Setor não localizado para o ID: "+dto.getIdSetor()) );
				
		Colaborador colaborador = Colaborador.builder() 
				.cpf(dto.getCpf())
				.dataNascimento(dto.getDataNascimento())
				.email(dto.getEmail())
				.nome(dto.getNome())
				.setor(setor)
				.telefone(dto.getTelefone())
				.build();
				
		return colaborador;
	}

	
}
