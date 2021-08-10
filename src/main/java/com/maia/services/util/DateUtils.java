package com.maia.services.util;

import java.time.LocalDate;

import org.springframework.stereotype.Component;

@Component
public class DateUtils {
	
	public int retornaIdadeColaborador(LocalDate dataNascimento) {		
		int anoAtual = LocalDate.now().getYear();		
		int anoNascimento = dataNascimento.getYear();
		
	   return ( anoAtual - anoNascimento);
	}

}
