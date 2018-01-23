package br.com.clinicaformare.model.usuario;

import java.io.Serializable;
import java.util.Calendar;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import br.com.clinicaformare.model.atendimento.Pacote;

@Entity
public class Pagante implements Serializable {
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@OneToOne(mappedBy = "pagante")
	Usuario usuario;
	@Temporal(TemporalType.TIMESTAMP)
	private Calendar dataCriacao;
	@Temporal(TemporalType.TIMESTAMP)
	private Calendar dataAlteracao;

	// Dependente e o Que está autorizado a fazer
	@OneToMany(mappedBy = "autorizador") // ok
	private List<Autorizacao> autorizacoes;

	@OneToMany(mappedBy = "contratante")
	private List<Pacote> pacotes;

	@Override
	public String toString() {
		return "Pagante [id=" + id + ", usuario=" + usuario + ", dataCriacao=" + dataCriacao + ", dataAlteracao=" + dataAlteracao + ", autorizacoes=" + autorizacoes + ", pacotes=" + pacotes + "]";
	}

	// Constructor
	public Pagante() {
		super();
	}

	public Pagante(Long id) {
		this.id = id;
	}

	// Getters and Setters
	public List<Autorizacao> getAutorizacoes() {
		return autorizacoes;
	}

	public void setAutorizacoes(List<Autorizacao> autorizacoes) {
		this.autorizacoes = autorizacoes;
	}

	public List<Pacote> getPacotes() {
		return pacotes;
	}

	public void setPacotes(List<Pacote> pacotes) {
		this.pacotes = pacotes;
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
