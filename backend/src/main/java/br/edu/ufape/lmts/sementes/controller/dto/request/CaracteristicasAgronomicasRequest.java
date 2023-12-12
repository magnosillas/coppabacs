package br.edu.ufape.lmts.sementes.controller.dto.request;

import br.edu.ufape.lmts.sementes.config.SpringApplicationContext;
import br.edu.ufape.lmts.sementes.model.*;

import java.util.*;
import java.math.*;

import org.modelmapper.ModelMapper;
import jakarta.validation.constraints.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Getter @Setter @NoArgsConstructor 
public  class CaracteristicasAgronomicasRequest  {
	private int cicloFenologico;
	private int standRecomendado;
	private int produtividade;
	private double altitudePlanta;
	private String tipoGrão;
	private Cor corGrao;
	private Cor corCaule;
	private Cor corFolha;
	private Cor corFLor;
	private String habitoCrescimento;
	private SementesRequest sementes;

	public CaracteristicasAgronomicas convertToEntity() {
		ModelMapper modelMapper = (ModelMapper) SpringApplicationContext.getBean("modelMapper");
		CaracteristicasAgronomicas obj = modelMapper.map(this, CaracteristicasAgronomicas.class);
		return obj;
	}



}