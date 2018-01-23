package br.com.clinicaformare.model.atendimento;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import br.com.clinicaformare.model.usuario.TipoProfissional;

@Entity
public class AtendimentoPadrao implements Serializable {
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	private Double quantidadeAtendimentosSemanais;
	@ManyToOne
	TipoProfissional tipoProfissional;

	private Double desconto;
	private Double valorBrutoHora;
	private Double valorLiquidoHora;
	private Double porcentagemLiquidoSobreBruto;

	@ManyToOne
	private Pacote pacote;
	private boolean atendimentoPadraoDePacote;

	@Override
	public String toString() {
		return "AtendimentoPadrao [id=" + id + ", quantidadeAtendimentosSemanais=" + quantidadeAtendimentosSemanais + ", tipoProfissional=" + tipoProfissional + "]";
	}

	// Constructor
	public AtendimentoPadrao(Double quantidadeAtendimentosSemanais, TipoProfissional tipoProfissional, Double desconto, boolean atendimentoPadraoDePacotes) {
		super();
		this.quantidadeAtendimentosSemanais = quantidadeAtendimentosSemanais;
		this.tipoProfissional = tipoProfissional;
		this.valorBrutoHora = tipoProfissional.getValorBrutoHora() * (1 - desconto);
		this.valorLiquidoHora = tipoProfissional.getPorcentagemLiquidoSobreBruto() * this.valorBrutoHora;
		atendimentoPadraoDePacote = atendimentoPadraoDePacotes;
	}

	public AtendimentoPadrao() {
		super();
		this.atendimentoPadraoDePacote = false;

	}

	// Getters and Setters
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Double getQuantidadeAtendimentosSemanais() {
		return quantidadeAtendimentosSemanais;
	}

	public void setQuantidadeAtendimentosSemanais(Double quantidadeAtendimentosSemanais) {
		this.quantidadeAtendimentosSemanais = quantidadeAtendimentosSemanais;
	}

	public TipoProfissional getTipoProfissional() {
		return tipoProfissional;
	}

	public void setTipoProfissional(TipoProfissional tipoProfissional) {
		this.tipoProfissional = tipoProfissional;
	}

	// Getters and Setter modificados - valores Bruto, Liquido, Razao L/B, Desconto

	public Double getValorBrutoHora() {
		return valorBrutoHora;
	}

	public void setValorBrutoHora(Double valorBrutoHora) { // mexeu no bruto --> mexe no desconto e no valor liquido do tipoProfissional
		this.valorBrutoHora = valorBrutoHora;
		this.desconto = 1 - this.valorBrutoHora / this.tipoProfissional.getValorBrutoHora();
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

	public void setPorcentagemLiquidoSobreBruto(Double porcentagemLiquidoSobreBruto) { // mexeu na porcentagem --> mexe no liquido
		this.porcentagemLiquidoSobreBruto = porcentagemLiquidoSobreBruto;
		this.valorLiquidoHora = this.valorBrutoHora * this.porcentagemLiquidoSobreBruto;
	}

	public Double getDesconto() {
		return desconto;
	}

	public void setDesconto(Double desconto) { // mexeu no desconto --> mexe no valor bruto e no valor liquido comparados com o tipoProfissional
		this.desconto = desconto;
		this.setValorBrutoHora(this.tipoProfissional.getValorBrutoHora() * (1 - desconto));
		this.setValorLiquidoHora(this.tipoProfissional.getValorLiquidoHora() * (1 - desconto));
	}

	public Pacote getPacote() {
		return pacote;
	}

	public void setPacote(Pacote pacote) {
		this.pacote = pacote;
	}

	public boolean isAtendimentoPadraoDePacote() {
		return atendimentoPadraoDePacote;
	}

	public void setAtendimentoPadraoDePacote(boolean atendimentoPadraoDePacote) {
		this.atendimentoPadraoDePacote = atendimentoPadraoDePacote;
	}

}
