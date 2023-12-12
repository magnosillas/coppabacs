package br.edu.ufape.lmts.sementes.controller.dto.request;

import br.edu.ufape.lmts.sementes.config.SpringApplicationContext;
import br.edu.ufape.lmts.sementes.enums.Resistencia;
import br.edu.ufape.lmts.sementes.model.*;

import java.util.*;
import java.math.*;

import org.modelmapper.ModelMapper;
import jakarta.validation.constraints.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Getter @Setter @NoArgsConstructor 
public  class ToleranciaAdversidadesRequest  {
	private Resistencia altaTemperatura;
	private Resistencia baixaTemperatura;
	private Resistencia geada;
	private Resistencia chuvaExcessiva;
	private Resistencia seca;
	private Resistencia ventos;
	private Resistencia salinidade;
	private Resistencia toxidadeAluminio;
	private Resistencia soloArgiloso;
	private Resistencia soloArenoso;
	private Resistencia soloAcido;
	private Resistencia soloBaixaFertilidade;

	public ToleranciaAdversidades convertToEntity() {
		ModelMapper modelMapper = (ModelMapper) SpringApplicationContext.getBean("modelMapper");
		ToleranciaAdversidades obj = modelMapper.map(this, ToleranciaAdversidades.class);
		return obj;
	}



}