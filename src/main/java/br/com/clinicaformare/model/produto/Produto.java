package br.com.clinicaformare.model.produto;

import java.io.Serializable;
import java.math.BigDecimal;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;

@Entity
public class Produto implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	private String nome;
	@Lob
	private String descricao;
	private BigDecimal preco;
	private Integer quantidade;
//	@ManyToMany
//	private List<SecaoPet> secoesPet = new ArrayList<>();
//	@OneToOne
//	private TipoProdutoPet tipoProdutoPet;
//	@ManyToMany
//	private List<TipoPet> tiposPet = new ArrayList<>();
//	@ManyToMany
//	private List<TipoRacaoPet> tiposRacaoPet = new ArrayList<>();
//	@OneToOne
//	private TipoNovoUsado tipoNovoUsado;
//	@OneToOne
//	private TipoAbertoFechado tipoAbertoFechado;
//	@OneToOne
//	private Marca marca;
//	@ManyToMany
//	private List<IdadePet> idadesPet = new ArrayList<>();
//	@ManyToMany
//	private List<PortePet> portesPet = new ArrayList<>();
//	@ManyToMany
//	private List<RacaCachorro> racasCachorro = new ArrayList<>();
//	@ManyToMany
//	private List<RacaGato> racasGato = new ArrayList<>();
//
//
//
//
//
//
//	@Override
//	public String toString() {
//		return "Produto [id=" + id + ", nome=" + nome + ", descricao=" + descricao + ", preco=" + preco
//				+ ", quantidade=" + quantidade + ", secoesPet=" + secoesPet + ", tipoProdutoPet=" + tipoProdutoPet
//				+ ", tiposPet=" + tiposPet + ", tiposRacaoPet=" + tiposRacaoPet + ", tipoNovoUsado=" + tipoNovoUsado
//				+ ", tipoAbertoFechado=" + tipoAbertoFechado + ", marca=" + marca + ", idadesPet=" + idadesPet
//				+ ", portesPet=" + portesPet + ", racasCachorro=" + racasCachorro + ", racasGato=" + racasGato + "]";
//	}
//
//	public Integer getId() {
//		return id;
//	}
//
//	public void setId(Integer id) {
//		this.id = id;
//	}
//
//	public String getNome() {
//		return nome;
//	}
//
//	public void setNome(String nome) {
//		this.nome = nome;
//	}
//
//	public String getDescricao() {
//		return descricao;
//	}
//
//	public void setDescricao(String descricao) {
//		this.descricao = descricao;
//	}
//
//	public BigDecimal getPreco() {
//		return preco;
//	}
//
//	public void setPreco(BigDecimal preco) {
//		this.preco = preco;
//	}
//
//	public Integer getQuantidade() {
//		return quantidade;
//	}
//
//	public void setQuantidade(Integer quantidade) {
//		this.quantidade = quantidade;
//	}
//
//	public List<SecaoPet> getSecoesPet() {
//		return secoesPet;
//	}
//
//	public void setSecoesPet(List<SecaoPet> secoesPet) {
//		this.secoesPet = secoesPet;
//	}
//
//	public TipoProdutoPet getTipoProdutoPet() {
//		return tipoProdutoPet;
//	}
//
//	public void setTipoProdutoPet(TipoProdutoPet tipoProdutoPet) {
//		this.tipoProdutoPet = tipoProdutoPet;
//	}
//
//	public List<TipoPet> getTiposPet() {
//		return tiposPet;
//	}
//
//	public void setTiposPet(List<TipoPet> tiposPet) {
//		this.tiposPet = tiposPet;
//	}
//
//	public List<TipoRacaoPet> getTiposRacaoPet() {
//		return tiposRacaoPet;
//	}
//
//	public void setTiposRacaoPet(List<TipoRacaoPet> tiposRacaoPet) {
//		this.tiposRacaoPet = tiposRacaoPet;
//	}
//
//	public TipoNovoUsado getTipoNovoUsado() {
//		return tipoNovoUsado;
//	}
//
//	public void setTipoNovoUsado(TipoNovoUsado tipoNovoUsado) {
//		this.tipoNovoUsado = tipoNovoUsado;
//	}
//
//	public TipoAbertoFechado getTipoAbertoFechado() {
//		return tipoAbertoFechado;
//	}
//
//	public void setTipoAbertoFechado(TipoAbertoFechado tipoAbertoFechado) {
//		this.tipoAbertoFechado = tipoAbertoFechado;
//	}
//
//	public List<IdadePet> getIdadesPet() {
//		return idadesPet;
//	}
//
//	public void setIdadesPet(List<IdadePet> idadesPet) {
//		this.idadesPet = idadesPet;
//	}
//
//	public List<PortePet> getPortesPet() {
//		return portesPet;
//	}
//
//	public void setPortesPet(List<PortePet> portesPet) {
//		this.portesPet = portesPet;
//	}
//	public Marca getMarca() {
//		return marca;
//	}
//
//	public void setMarca(Marca marca) {
//		this.marca = marca;
//	}
//
//	public List<RacaCachorro> getRacasCachorro() {
//		return racasCachorro;
//	}
//
//	public void setRacasCachorro(List<RacaCachorro> racasCachorro) {
//		this.racasCachorro = racasCachorro;
//	}
//
//	public List<RacaGato> getRacasGato() {
//		return racasGato;
//	}
//
//	public void setRacasGato(List<RacaGato> racasGato) {
//		this.racasGato = racasGato;
//	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public String getDescricao() {
		return descricao;
	}
	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
	public BigDecimal getPreco() {
		return preco;
	}
	public void setPreco(BigDecimal preco) {
		this.preco = preco;
	}
	public Integer getQuantidade() {
		return quantidade;
	}
	public void setQuantidade(Integer quantidade) {
		this.quantidade = quantidade;
	}

}