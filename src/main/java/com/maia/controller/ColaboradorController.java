package com.maia.controller;

import java.net.URI;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.maia.domain.Colaborador;
import com.maia.domain.dto.ColaboradorDTO;
import com.maia.domain.dto.ColaboradorNewDTO;
import com.maia.services.interfaces.ColaboradorService;

@RestController
@RequestMapping(value = "/api/v1/colaboradores")
public class ColaboradorController {

	@Autowired
	private ColaboradorService colaboradorService;

		
	@GetMapping("/{id}")
	public ResponseEntity<Colaborador> obterColaboradorPorId(@PathVariable Long id) {
		Colaborador colaborador = colaboradorService.retornaColaboradorPorId(id);
		return ResponseEntity.ok().body(colaborador);
	}

	@GetMapping()
	public ResponseEntity<Colaborador> obterColaboradorPorCpf(@RequestParam(value = "cpf") String cpf) {
		Colaborador result = colaboradorService.retornaColaboradorPorCpf(cpf);
		return ResponseEntity.ok().body(result);
	}

	@PostMapping()
	public ResponseEntity<Void> insertNovoColaborador(@Valid @RequestBody ColaboradorNewDTO dto) {
		Colaborador newColaborador = colaboradorService.fromDTO(dto);
		
		newColaborador = colaboradorService.inserirNovoColaborador(newColaborador);
		
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(newColaborador.getId())
				.toUri();
		return ResponseEntity.created(uri).build();
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<Void> deleteColaborador(@PathVariable Long id) {
		colaboradorService.excluirColaborador(id);
		return ResponseEntity.noContent().build();
	}

	@GetMapping("/setor/{idSetor}")
	public ResponseEntity<List<ColaboradorDTO >> obterColaboradoresPorSetor(@PathVariable Long idSetor) {
		
		List<ColaboradorDTO> result = colaboradorService.retornaColaboradoresPorSetor(idSetor);

		return ResponseEntity.ok().body(result);
	}

}
