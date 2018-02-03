package br.com.clinicaformare.model.usuario;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
public class Autorizacao implements Serializable {
	private static final long serialVersionUID = 2L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@ManyToOne
	private ResponsavelFinanceiro autorizador;
	@ManyToOne
	private Autorizado autorizado;
	@ManyToOne
	private Paciente paciente;

	private boolean verCadastro;
	private boolean alterarCadastro;

	private boolean verEvolucao;
	private boolean modificarEvolucao;

	@Override
	public String toString() {
		return "Autorizacao [id=" + id + ", autorizador=" + autorizador + ", autorizado=" + autorizado + ", paciente=" + paciente + "]";
	}

	// Constructor
	public Autorizacao() {
		super();
	}

	public Autorizacao(ResponsavelFinanceiro autorizador, Autorizado autorizado, Paciente paciente) {
		super();
		this.autorizador = autorizador;
		this.autorizado = autorizado;
		this.paciente = paciente;
	}

	// Getters and setters
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public ResponsavelFinanceiro getAutorizador() {
		return autorizador;
	}

	public void setAutorizador(ResponsavelFinanceiro autorizador) {
		this.autorizador = autorizador;
	}

	public Autorizado getAutorizado() {
		return autorizado;
	}

	public void setAutorizado(Autorizado autorizado) {
		this.autorizado = autorizado;
	}

	public Paciente getPaciente() {
		return paciente;
	}

	public void setPaciente(Paciente paciente) {
		this.paciente = paciente;
	}

	public boolean isVerCadastro() {
		return verCadastro;
	}

	public void setVerCadastro(boolean verCadastro) {
		this.verCadastro = verCadastro;
	}

	public boolean isAlterarCadastro() {
		return alterarCadastro;
	}

	public void setAlterarCadastro(boolean alterarCadastro) {
		this.alterarCadastro = alterarCadastro;
	}

	public boolean isVerEvolucao() {
		return verEvolucao;
	}

	public void setVerEvolucao(boolean verEvolucao) {
		this.verEvolucao = verEvolucao;
	}

	public boolean isModificarEvolucao() {
		return modificarEvolucao;
	}

	public void setModificarEvolucao(boolean modificarEvolucao) {
		this.modificarEvolucao = modificarEvolucao;
	}

}
