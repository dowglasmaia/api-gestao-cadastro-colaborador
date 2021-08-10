package com.maia.domain.dto;

import java.io.Serializable;
import java.time.LocalDate;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.maia.domain.Colaborador;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
public class ColaboradorDTO implements Serializable {
	private static final long serialVersionUID = 1L;
	
	private Long id;
	private String nome;
	private String cpf;
	private String telefone;
	private String email;
	
	@JsonFormat(pattern = "yyyy-MM-dd")
	private LocalDate dataNascimento;
	private Long idSetor;
	private String nomeSetor;
	
	public ColaboradorDTO() {
	
	}	
	
	public ColaboradorDTO(Colaborador colaborador) {
		super();
		this.id = colaborador.getId();
		this.nome = colaborador.getNome();
		this.cpf = colaborador.getCpf();
		this.telefone = colaborador.getTelefone();
		this.email = colaborador.getEmail();
		this.dataNascimento = colaborador.getDataNascimento();
		this.idSetor = colaborador.getSetor().getId();
		this.nomeSetor = colaborador.getSetor().getDescricao();
	}
	
	
}
