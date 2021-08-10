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
import com.maia.domain.Setor;
import com.maia.services.impl.ColaboradorServiceImpl;
import com.maia.services.impl.SetorServiceImpl;

@RestController
@RequestMapping(value = "/api/v1/setores")
public class SetorController {

	@Autowired
	private SetorServiceImpl setorService;

	@GetMapping()
	public ResponseEntity<List<Setor>> obterSetores() {
		List<Setor> result = setorService.retornaSetores();
		return ResponseEntity.ok().body(result);
	}

}
