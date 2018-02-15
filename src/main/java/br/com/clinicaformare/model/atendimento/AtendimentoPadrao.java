package br.com.clinicaformare.model.atendimento;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import br.com.clinicaformare.model.usuario.Profissional;
import br.com.clinicaformare.model.usuario.Socia;
import br.com.clinicaformare.model.usuario.TipoHorista;
import br.com.clinicaformare.model.usuario.TipoProfissional;
import br.com.clinicaformare.model.usuario.TipoSocia;

@Entity
public class AtendimentoPadrao implements Serializable {
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

//	@Digits(integer=5, fraction=2)
//	@Column(nullable= false, precision=7, scale=2) 
	private Integer quantidadeAtendimentosMensal;


	private Double desconto = 0.0;
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
	TipoSocia tipoSocia;
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
	public AtendimentoPadrao(AtendimentoPadrao atendimentoPadrao) {
		this.quantidadeAtendimentosMensal = atendimentoPadrao.quantidadeAtendimentosMensal;
		this.desconto = atendimentoPadrao.desconto;
		this.valorBrutoHora = atendimentoPadrao.valorBrutoHora;
		this.valorLiquidoHora = atendimentoPadrao.valorLiquidoHora;
		this.porcentagemLiquidoSobreBruto = atendimentoPadrao.porcentagemLiquidoSobreBruto;
		this.atendimentoPadraoDePacote = atendimentoPadrao.atendimentoPadraoDePacote;
	}
	
	public AtendimentoPadrao(Integer quantidadeAtendimentosMensal, TipoHorista tipo, Double desconto, boolean atendimentoPadraoDePacotes) {
		super();
		if(tipo instanceof TipoProfissional) {
			this.tipoProfissional = (TipoProfissional) tipo;
		}
		if(tipo instanceof TipoSocia) {
			this.tipoSocia = (TipoSocia) tipo;
		}
		this.quantidadeAtendimentosMensal = quantidadeAtendimentosMensal;
		this.valorBrutoHora = tipo.getValorBrutoHora() * (1 - desconto);
		this.valorLiquidoHora = tipo.getPorcentagemLiquidoSobreBruto() * this.valorBrutoHora;
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
	public Double getValorBrutoHoraSemDesconto() {
		return this.getTipoHorista().getValorBrutoHora();	
	}
	public Double getValorLiquidoHoraSemDesconto() {
		return this.getTipoHorista().getValorBrutoHora();	
	}
	public TipoHorista getTipoHorista() {
		if(tipoProfissional != null) {
			System.out.println("TipoHorista = Profissional");
			return this.tipoProfissional;
		}
		if(tipoSocia != null ) {
			System.out.println("TipoHorista = Socia");
			return this.tipoSocia;
		}
		System.out.println("TipoHorista = nenhum");
		return null;
	}
	public Double getValorBrutoHora() {
		return valorBrutoHora;
	}

	public void setValorBrutoHora(Double valorBrutoHora) { // mexeu no bruto --> mexe no desconto e no valor liquido do tipoProfissional
		this.valorBrutoHora = valorBrutoHora;
		this.desconto = 1 - this.valorBrutoHora / this.getTipoHorista().getValorBrutoHora();
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

	public TipoSocia getTipoSocia() {
		return tipoSocia;
	}

	public void setTipoSocia(TipoSocia tipoSocia) {
		this.tipoSocia = tipoSocia;
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
		AtendimentoPadrao other = (AtendimentoPadrao) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}
}
