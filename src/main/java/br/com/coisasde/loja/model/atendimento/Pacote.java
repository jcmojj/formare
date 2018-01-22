package br.com.coisasde.loja.model.atendimento;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

import br.com.coisasde.loja.model.usuario.Pagante;
import br.com.coisasde.loja.model.usuario.Paciente;
import br.com.coisasde.loja.model.usuario.Socia;

@Entity
public class Pacote implements Serializable {
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@ManyToOne
	private Socia sociaResponsavel;
	@ManyToOne
	private Pagante contratante; // !!! relacao
	@OneToOne
	private Paciente paciente; // deve ser um dependente do contratante !!!relacao
	// private List<Cliente> pacientesPermitidos;

	@OneToMany(mappedBy = "pacote")
	private List<AtendimentoPadrao> atendimentosPadrao;

	private boolean ehPacotePadrao;

	@Override
	public String toString() {
		return "Pacote [id=" + id + ", sociaResponsavel=" + sociaResponsavel + ", contratante=" + contratante + ", paciente=" + paciente + ", atendimentosPadrao=" + atendimentosPadrao
				+ ", ehPacotePadrao=" + ehPacotePadrao + "]";
	}

	// Constructor
	public Pacote() {
		super();
		ehPacotePadrao = true;
	}

	public Pacote(Long id) {
		super();
		this.id = id;
	}

	public Pacote(Socia sociaResponsavel, Pagante contratante) {
		super();
		this.sociaResponsavel = sociaResponsavel;
		this.contratante = contratante;
		ehPacotePadrao = false;
	}
	// Getters and setters

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Socia getSociaResponsavel() {
		return sociaResponsavel;
	}

	public void setSociaResponsavel(Socia sociaResponsavel) {
		this.sociaResponsavel = sociaResponsavel;
	}

	public Pagante getContratante() {
		return contratante;
	}

	public void setContratante(Pagante contratante) {
		this.contratante = contratante;
	}

	public Paciente getPaciente() {
		return paciente;
	}

	public void setPaciente(Paciente paciente) {
		this.paciente = paciente;
	}

	public List<AtendimentoPadrao> getAtendimentosPadrao() {
		return atendimentosPadrao;
	}

	public void setAtendimentosPadrao(List<AtendimentoPadrao> atendimentosPadrao) {
		this.atendimentosPadrao = atendimentosPadrao;
	}

	public boolean isEhPacotePadrao() {
		return ehPacotePadrao;
	}

	public void setEhPacotePadrao(boolean ehPacotePadrao) {
		this.ehPacotePadrao = ehPacotePadrao;
	}

}
