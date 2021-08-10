package com.maia.services.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.maia.domain.Setor;
import com.maia.repository.SetorRepository;
import com.maia.services.interfaces.SetorService;

@Service
public class SetorServiceImpl implements SetorService{

	@Autowired
	private SetorRepository repository;

	@Override
	public List<Setor> retornaSetores() {
		return repository.findAll();
	}

}
