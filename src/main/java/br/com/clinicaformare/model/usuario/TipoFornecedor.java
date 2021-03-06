package br.com.clinicaformare.model.usuario;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;


@Entity
public class TipoFornecedor implements Serializable {
	private static final long serialVersionUID = 2L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	private String tipo;
	private Double valorBrutoHora;
	private Double valorLiquidoHora;
	private Double porcentagemLiquidoSobreBruto;
//	@OneToMany(mappedBy = "tipoFornecedor")
//	private List<AtendimentoPadrao> atendimentosPadrao  = new ArrayList<>();
	@ManyToMany(mappedBy = "tiposFornecedor")
	private List<Fornecedor> fornecedores  = new ArrayList<>();

	// Constructor
	@Override
	public String toString() {
		return "TipoFornecedor(" + id + ")=" + tipo ;
	}

	public TipoFornecedor(String tipo, Double valorBrutoHora, Double valorLiquidoHora) {
		super();
		this.tipo = tipo;
		this.valorBrutoHora = valorBrutoHora;
		this.valorLiquidoHora = valorLiquidoHora;
		this.porcentagemLiquidoSobreBruto =  valorLiquidoHora/valorBrutoHora;

	}

	public TipoFornecedor() {
		super();
	}

	// Getters and Setters
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getTipo() {
		return tipo;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}

	public Double getValorBrutoHora() {
		return valorBrutoHora;
	}

	public void setValorBrutoHora(Double valorBrutoHora) { // mexeu no bruto --> mexe no liquido
		this.valorBrutoHora = valorBrutoHora;
		this.valorLiquidoHora = this.valorBrutoHora * this.porcentagemLiquidoSobreBruto;
	}

	public Double getValorLiquidoHora() {
		return valorLiquidoHora;
	}

	public void setValorLiquidoHora(Double valorLiquidoHora) { // mexeu no liquido --> mexe na porcentagem L/B
		this.valorLiquidoHora = valorLiquidoHora;
		this.porcentagemLiquidoSobreBruto = this.valorLiquidoHora / this.valorBrutoHora;
	}

	public Double getPorcentagemLiquidoSobreBruto() {
		return porcentagemLiquidoSobreBruto;
	}

	public void setPorcentagemLiquidoSobreBruto(Double porcentagemLiquidoSobreBruto) { // mexeu na porcentagem L/B --> mexe no liquido
		this.porcentagemLiquidoSobreBruto = porcentagemLiquidoSobreBruto;
		this.valorLiquidoHora = this.valorBrutoHora * this.porcentagemLiquidoSobreBruto;
	}



//	public List<AtendimentoPadrao> getAtendimentosPadrao() {
//		return atendimentosPadrao;
//	}
//
//	public void setAtendimentosPadrao(List<AtendimentoPadrao> atendimentosPadrao) {
//		this.atendimentosPadrao = atendimentosPadrao;
//	}

	public List<Fornecedor> getFornecedores() {
		return fornecedores;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		TipoFornecedor other = (TipoFornecedor) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

}