package br.com.clinicaformare.model.atendimento;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

import br.com.clinicaformare.model.usuario.Paciente;
import br.com.clinicaformare.model.usuario.ResponsavelFinanceiro;
import br.com.clinicaformare.model.usuario.Socia;

@Entity
public class Pacote implements Serializable {
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	private String nome;
	@ManyToOne
	private Socia sociaResponsavel;
	@ManyToOne
	private ResponsavelFinanceiro responsavelFinanceiro; // !!! relacao
	@OneToOne
	private Paciente paciente; // deve ser um dependente do contratante !!!relacao
	// private List<Cliente> pacientesPermitidos;

	@OneToMany(mappedBy = "pacote")
	private List<AtendimentoPadrao> atendimentosPadrao = new ArrayList<>();

	private boolean ehPacotePadrao = false;

	@Override
	public String toString() {
		return "Pacote [id=" + id + ", sociaResponsavel=" + sociaResponsavel + ", contratante=" + responsavelFinanceiro + ", paciente=" + paciente + ", atendimentosPadrao=" + atendimentosPadrao
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

	public Pacote(Socia sociaResponsavel, ResponsavelFinanceiro responsavelFinanceiro) {
		super();
		this.sociaResponsavel = sociaResponsavel;
		this.responsavelFinanceiro = responsavelFinanceiro;
		sociaResponsavel.getPacotes().add(this);
		responsavelFinanceiro.getPacotes().add(this);
		ehPacotePadrao = false;
	}
	public Pacote(ResponsavelFinanceiro responsavelFinanceiro, Paciente paciente) {
		super();
		this.responsavelFinanceiro = responsavelFinanceiro;
		responsavelFinanceiro.getPacotes().add(this);
		paciente.getPacotes().add(this);
		this.paciente = paciente;
	}
	
	
	// Getters and setters

	

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

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

	public ResponsavelFinanceiro getContratante() {
		return responsavelFinanceiro;
	}

	public void setContratante(ResponsavelFinanceiro contratante) {
		this.responsavelFinanceiro = contratante;
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

	public ResponsavelFinanceiro getResponsavelFinanceiro() {
		return responsavelFinanceiro;
	}

	public void setResponsavelFinanceiro(ResponsavelFinanceiro responsavelFinanceiro) {
		this.responsavelFinanceiro = responsavelFinanceiro;
	}

	

}
