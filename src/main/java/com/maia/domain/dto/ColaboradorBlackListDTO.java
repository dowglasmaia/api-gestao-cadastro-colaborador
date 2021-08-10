package com.maia.domain.dto;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@AllArgsConstructor
public class ColaboradorBlackListDTO implements Serializable {
	private static final long serialVersionUID = 1L;
	
	private Long id;	
    private String name;    
    private String cpf;
   	  
  
    @Override
    public String toString() {
        return "id=" + id + ", name=" + name + ", cpf=" + cpf ;
    }
}
