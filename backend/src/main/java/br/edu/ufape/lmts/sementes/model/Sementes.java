package br.edu.ufape.lmts.sementes.model;

import java.time.LocalDate;
import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Inheritance;
import jakarta.persistence.InheritanceType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Inheritance(strategy = InheritanceType.JOINED)
@Getter @Setter @NoArgsConstructor @AllArgsConstructor 
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@ToString
public  class Sementes  {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@EqualsAndHashCode.Include
	private long id;
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
	@OneToOne
	@ToString.Exclude
	private ToleranciaAdversidades toleranciaAdversidades; 
	@OneToMany
	@JoinColumn(name = "sementes_id")
	@ToString.Exclude
	private List<ProducaoSementes> producaoSementes; 
	@OneToMany
	@JoinColumn(name = "sementes_id")
	@ToString.Exclude
	private List<TabelaBancoSementes> tabelaBancoSementes;
}