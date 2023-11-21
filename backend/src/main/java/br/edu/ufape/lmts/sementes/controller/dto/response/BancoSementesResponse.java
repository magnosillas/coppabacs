package br.edu.ufape.lmts.sementes.controller.dto.response;

import java.util.*;
import java.math.*;
import br.edu.ufape.lmts.sementes.model.*;
import br.edu.ufape.lmts.sementes.config.SpringApplicationContext;
import org.modelmapper.ModelMapper;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;



@Getter @Setter @NoArgsConstructor
public  class BancoSementesResponse  {
	private Long id;
	private String nome;
	private String municipio;
	private String comunidade;
	private String anoFundacao;
	private String historiaBanco;
	private String variedadesTrabalhadas;
	private String resposavel1;
	private String contatoResponsavel1;
	private String resposavel2;
	private String contatoResponsavel2;
	private EnderecoResponse endereco; 
	private ObjetosBancoSementesResponse objetosBancoSementes; 
	private TecnicoResponse tecnico; 
	private List<DoacaoUsuarioResponse> doacaoUsuario; 
	private List<RetiradaUsuarioResponse> retiradaUsuario; 
	private List<TransacaoGenericaResponse> transacaoGenerica; 



	public BancoSementesResponse(BancoSementes obj) {
		ModelMapper modelMapper = (ModelMapper) SpringApplicationContext.getBean("modelMapper");
		modelMapper.map(obj, this);	
	}

}
