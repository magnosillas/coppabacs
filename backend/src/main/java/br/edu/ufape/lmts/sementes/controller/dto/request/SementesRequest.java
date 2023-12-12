package br.edu.ufape.lmts.sementes.controller.dto.request;

import br.edu.ufape.lmts.sementes.config.SpringApplicationContext;
import br.edu.ufape.lmts.sementes.model.*;

import java.time.LocalDate;
import java.util.*;
import java.math.*;

import org.modelmapper.ModelMapper;
import jakarta.validation.constraints.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Getter @Setter @NoArgsConstructor 
public  class SementesRequest  {
	private String nome;
	private String descricao;
	private String imagem;
	private String localOrigem;
	private Boolean dominioPublico;
	private Boolean polinizaacaoAbertaMelhorada;
	private LocalDate tempoComunidade;
	private String regiaoColetaDados;
	private float altitudeMaxima;
	private float altitudeMinima;
	private String caracteristicasPositiva;
	private String caracteristicasNegativas;
	private ToleranciaAdversidadesRequest toleranciaAdversidades;
	private List<ProducaoSementesRequest> producaoSementes; 
	private List<TabelaBancoSementesRequest> tabelaBancoSementes; 


	public Sementes convertToEntity() {
		ModelMapper modelMapper = (ModelMapper) SpringApplicationContext.getBean("modelMapper");
		Sementes obj = modelMapper.map(this, Sementes.class);
		return obj;
	}



}