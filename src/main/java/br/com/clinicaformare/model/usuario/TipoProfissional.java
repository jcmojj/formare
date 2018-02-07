package br.com.clinicaformare.model.usuario;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;

import br.com.clinicaformare.model.atendimento.AtendimentoPadrao;

@Entity
public class TipoProfissional implements Serializable {
	private static final long serialVersionUID = 2L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	private String tipo;
	private Double valorBrutoHora;
	private Double valorLiquidoHora;
	private Double porcentagemLiquidoSobreBruto;
	private boolean especialista = false;
	private boolean deSocia = false;
	@OneToMany(mappedBy = "tipoProfissional")
	private List<Profissional> profissionais;
	@OneToMany(mappedBy = "tipoProfissional")
	private List<AtendimentoPadrao> atendimentosPadrao;
	@ManyToMany(mappedBy = "tiposProfissionais")
	private List<Socia> socias;

	// Constructor
	@Override
	public String toString() {
		return "TipoProfissional(" + id + ")=" + tipo ;
	}

	public TipoProfissional(String tipo, Double valorBrutoHora, Double valorLiquidoHora, boolean especialista, boolean deSocia) {
		super();
		this.tipo = tipo;
		this.valorBrutoHora = valorBrutoHora;
		this.valorLiquidoHora = valorLiquidoHora;
		this.porcentagemLiquidoSobreBruto =  valorLiquidoHora/valorBrutoHora;
		this.especialista = especialista;
		this.deSocia = deSocia;
	}

	public TipoProfissional() {
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

	public List<Profissional> getProfissionais() {
		return profissionais;
	}

	public void setProfissionais(List<Profissional> profissionais) {
		this.profissionais = profissionais;
	}

	public List<AtendimentoPadrao> getAtendimentosPadrao() {
		return atendimentosPadrao;
	}

	public void setAtendimentosPadrao(List<AtendimentoPadrao> atendimentosPadrao) {
		this.atendimentosPadrao = atendimentosPadrao;
	}

	public boolean isEspecialista() {
		return especialista;
	}

	public void setEspecialista(boolean especialista) {
		this.especialista = especialista;
	}

	public boolean isDeSocia() {
		return deSocia;
	}

	public void setDeSocia(boolean deSocia) {
		this.deSocia = deSocia;
	}

	public List<Socia> getSocias() {
		return socias;
	}

	public void setSocias(List<Socia> socias) {
		this.socias = socias;
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
		TipoProfissional other = (TipoProfissional) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}
	
}
