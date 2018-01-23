package br.com.coisasde.loja.model.usuario;

import java.io.Serializable;
import java.util.Calendar;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
public class Profissional implements Serializable {
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@OneToOne(mappedBy = "profissional")
	Usuario usuario;
	@Temporal(TemporalType.TIMESTAMP)
	private Calendar dataCriacao;
	@Temporal(TemporalType.TIMESTAMP)
	private Calendar dataAlteracao;

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
	}

	public Profissional(Long id) {
		this.id = id;
	}

	public Profissional(TipoProfissional tipoProfissional) {
		this.tipoProfissional = tipoProfissional;
		this.valorBrutoHora = tipoProfissional.getValorBrutoHora();
		this.valorLiquidoHora = tipoProfissional.getValorLiquidoHora();
		this.porcentagemLiquidoSobreBruto = tipoProfissional.getPorcentagemLiquidoSobreBruto();
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
	

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
//		System.out.println("AQUI19");
//		usuario.setProfissional(this);
//		System.out.println("AQUI2");
	}

	public Calendar getDataCriacao() {
		return dataCriacao;
	}

	public void setDataCriacao(Calendar dataCriacao) {
		this.dataCriacao = dataCriacao;
	}

	public Calendar getDataAlteracao() {
		return dataAlteracao;
	}

	public void setDataAlteracao(Calendar dataAlteracao) {
		this.dataAlteracao = dataAlteracao;
	}

	// Método Callback para persistir
	@PrePersist
	public void quandoCriar() {
		this.setDataCriacao(Calendar.getInstance());
		this.setDataAlteracao(Calendar.getInstance());
	}

	// Método Callback para update
	@PreUpdate
	public void quandoAtualizar() {
		this.setDataAlteracao(Calendar.getInstance());
	}
}
