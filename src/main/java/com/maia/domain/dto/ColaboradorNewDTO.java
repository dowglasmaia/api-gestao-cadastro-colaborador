package com.maia.domain.dto;

import java.io.Serializable;
import java.time.LocalDate;

import javax.persistence.Column;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.br.CPF;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ColaboradorNewDTO implements Serializable {
	private static final long serialVersionUID = 1L;
	
	@Column(nullable = true, length = 100)
	@Length(min = 4, message = "O nome deve conter no minimo 04 caractrers")
	@NotBlank(message = " O campo nome do Colaborador é obrigatório")
	private String nome;

	@Column(unique = true, nullable = true, length = 12)
	@NotBlank(message = "O campo CPF do Colaborador é obrigatório")
	@CPF(message = "CPF Inválido.")
	private String cpf;

	@Column(unique = true, nullable = true, length = 12)
	@NotBlank(message = "O campo Telefone do Colaborador é obrigatório")
	private String telefone;

	@Column(unique = true, nullable = true, length = 50)
	@NotBlank(message = "O campo CPF do Colaborador é obrigatório")
	@Email(message = "E-mail Inválido.")
	private String email;
	
	@JsonFormat(pattern = "yyyy-MM-dd")
	private LocalDate dataNascimento;
	private Long idSetor;
}
