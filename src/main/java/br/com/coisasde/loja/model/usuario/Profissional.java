package br.com.coisasde.loja.model.usuario;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;

@Entity
public class Profissional extends Usuario implements Serializable {
	private static final long serialVersionUID = 1L;

	@ManyToOne
	private TipoProfissional tipoProfissional;

	private Double valorBrutoHora;
	private Double valorLiquidoHora;
	private Double porcentagemLiquidoSobreBruto;

	private boolean remuneracaoEspecial;

	@Override
	public String toString() {
		return "Profissional [tipoProfissional=" + tipoProfissional + ", valorBrutoHora=" + valorBrutoHora + ", valorLiquidoHora=" + valorLiquidoHora + ", porcentagemLiquidoSobreBruto="
				+ porcentagemLiquidoSobreBruto + ", remuneracaoEspecial=" + remuneracaoEspecial + "]";
	}

	// Constructor
	public Profissional() {
		super();
		this.ehCliente = false;
		this.ehProfissional = true;
	}

	public Profissional(Long id) {
		super(id);
		this.ehCliente = false;
		this.ehProfissional = true;
	}

	public Profissional(String email, String senha) {
		super(email, senha);
		this.ehCliente = false;
		this.ehProfissional = true;
	}

	public Profissional(String email, String senha, TipoProfissional tipoProfissional) {
		super(email, senha);
		this.tipoProfissional = tipoProfissional;
		this.valorBrutoHora = tipoProfissional.getValorBrutoHora();
		this.valorLiquidoHora = tipoProfissional.getValorLiquidoHora();
		this.ehCliente = false;
		this.ehProfissional = true;
		super.quandoCriar();
	}

	// Getters and Setters
	public TipoProfissional getTipoProfissional() {
		return tipoProfissional;
	}

	public void setTipoProfissional(TipoProfissional tipoProfissional) {
		this.tipoProfissional = tipoProfissional;
	}

	public Double getValorBrutoHora() {
		return valorBrutoHora;
	}

	public void setValorBrutoHora(Double valorBrutoHora) {
		this.valorBrutoHora = valorBrutoHora;
	}

	public Double getValorLiquidoHora() {
		return valorLiquidoHora;
	}

	public void setValorLiquidoHora(Double valorLiquidoHora) {
		this.valorLiquidoHora = valorLiquidoHora;
	}

	public Double getPorcentagemLiquidoSobreBruto() {
		return porcentagemLiquidoSobreBruto;
	}

	public void setPorcentagemLiquidoSobreBruto(Double porcentagemLiquidoSobreBruto) {
		this.porcentagemLiquidoSobreBruto = porcentagemLiquidoSobreBruto;
	}

	public boolean isRemuneracaoEspecial() {
		return remuneracaoEspecial;
	}

	public void setRemuneracaoEspecial(boolean remuneracaoEspecial) {
		this.remuneracaoEspecial = remuneracaoEspecial;
	}
}
