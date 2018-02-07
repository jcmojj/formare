package br.com.clinicaformare.model.atendimento;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import br.com.clinicaformare.model.usuario.Profissional;
import br.com.clinicaformare.model.usuario.Socia;
import br.com.clinicaformare.model.usuario.TipoProfissional;

@Entity
public class AtendimentoPadrao implements Serializable {
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

//	@Digits(integer=5, fraction=2)
//	@Column(nullable= false, precision=7, scale=2) 
	private Integer quantidadeAtendimentosMensal;


	private Double desconto;
	private Double valorBrutoHora;
	private Double valorLiquidoHora;
	private Double porcentagemLiquidoSobreBruto;
	private boolean atendimentoPadraoDePacote = false;
	
	@ManyToOne
	private Pacote pacote;
	@ManyToOne
	TipoProfissional tipoProfissional;
	@ManyToOne
	private Profissional profissional;
	@ManyToOne
	private Socia socia;


	

	@Override
	public String toString() {
		return "AtendimentoPadrao [id=" + id + ", quantidadeAtendimentosMensal=" + quantidadeAtendimentosMensal + ", tipoProfissional=" + tipoProfissional + ", valorBrutoHora=" + valorBrutoHora
				+ ", valorLiquidoHora=" + valorLiquidoHora + ", atendimentoPadraoDePacote=" + atendimentoPadraoDePacote + "]";
	}

	// Constructor
	public AtendimentoPadrao() {
		super();
		this.atendimentoPadraoDePacote = false;
	}
	public AtendimentoPadrao(AtendimentoPadrao atendimento) {
		this.quantidadeAtendimentosMensal = atendimento.quantidadeAtendimentosMensal;
		this.tipoProfissional = atendimento.tipoProfissional;
		this.desconto = atendimento.desconto;
		this.valorBrutoHora = atendimento.valorBrutoHora;
		this.valorLiquidoHora =atendimento.valorLiquidoHora;
		this.porcentagemLiquidoSobreBruto = atendimento.porcentagemLiquidoSobreBruto;
		this.atendimentoPadraoDePacote = false;
	}
	
	public AtendimentoPadrao(Integer quantidadeAtendimentosMensal, TipoProfissional tipoProfissional, Double desconto, boolean atendimentoPadraoDePacotes) {
		super();System.out.println("AQUI1");
		this.quantidadeAtendimentosMensal = quantidadeAtendimentosMensal;System.out.println("AQUI2");
		this.tipoProfissional = tipoProfissional;System.out.println("AQUI3");
		this.valorBrutoHora = tipoProfissional.getValorBrutoHora() * (1 - desconto);System.out.println("AQUI4");
		this.valorLiquidoHora = tipoProfissional.getPorcentagemLiquidoSobreBruto() * this.valorBrutoHora;System.out.println("AQUI5");
		this.porcentagemLiquidoSobreBruto = this.valorLiquidoHora / this.valorBrutoHora;
		atendimentoPadraoDePacote = atendimentoPadraoDePacotes; System.out.println("AQUI6: "+atendimentoPadraoDePacote);
		this.desconto = desconto;
	}

	// Getters and Setters
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Integer getQuantidadeAtendimentosMensal() {
		return quantidadeAtendimentosMensal;
	}

	public void setQuantidadeAtendimentosMensal(Integer quantidadeAtendimentosMensais) {
		this.quantidadeAtendimentosMensal = quantidadeAtendimentosMensais;
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

	public Double getValorBrutoTotal() {
		return valorBrutoHora*quantidadeAtendimentosMensal;
	}

	public Double getValorLiquidoTotal() {
		return valorLiquidoHora*quantidadeAtendimentosMensal;
	}

	public Profissional getProfissional() {
		return profissional;
	}

	public void setProfissional(Profissional profissional) {
		this.profissional = profissional;
	}

	public Socia getSocia() {
		return socia;
	}

	public void setSocia(Socia socia) {
		this.socia = socia;
	}

}
