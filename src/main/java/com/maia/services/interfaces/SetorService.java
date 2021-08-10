package com.maia.services.interfaces;

import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import com.maia.domain.Setor;

public interface SetorService {

	@Transactional(readOnly = true)
	List<Setor> retornaSetores();	

}
