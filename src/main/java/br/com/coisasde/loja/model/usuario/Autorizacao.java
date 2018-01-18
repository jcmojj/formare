package br.com.coisasde.loja.model.usuario;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;

@Entity
public class Autorizacao implements Serializable{
	private static final long serialVersionUID = 2L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@ManyToOne
	private Cliente autorizador;
	@OneToOne
	private Cliente autorizado;
	@OneToOne
	private Cliente dependente;
	
	private boolean verCadastro;
	private boolean alterarCadastro;
	
	private boolean verEvolucao;
	private boolean modificarEvolucao;
	@Override
	public String toString() {
		return "Autorizacao [id=" + id + ", autorizador=" + autorizador + ", autorizado=" + autorizado + ", dependente=" + dependente + ", verCadastro=" + verCadastro + ", alterarCadastro="
				+ alterarCadastro + ", verEvolucao=" + verEvolucao + ", modificarEvolucao=" + modificarEvolucao + "]";
	}
	// Constructor
	public Autorizacao(Cliente autorizador, Cliente autorizado, Cliente dependente) {
		super();
		this.autorizador = autorizador;
		this.autorizado = autorizado;
		this.dependente = dependente;
	}
	public Autorizacao() {
		super();
		// TODO Auto-generated constructor stub
	}

	//Getters and setters
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Cliente getAutorizador() {
		return autorizador;
	}
	public void setAutorizador(Cliente autorizador) {
		this.autorizador = autorizador;
	}
	public Cliente getAutorizado() {
		return autorizado;
	}
	public void setAutorizado(Cliente autorizado) {
		this.autorizado = autorizado;
	}
	public Cliente getDependente() {
		return dependente;
	}
	public void setDependente(Cliente dependente) {
		this.dependente = dependente;
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
