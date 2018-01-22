package br.com.coisasde.loja.model.usuario;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.OneToMany;

import br.com.coisasde.loja.model.atendimento.Pacote;

@Entity
public class Paciente extends Cliente implements Serializable {
	private static final long serialVersionUID = 1L;

	@OneToMany(mappedBy = "paciente")
	private List<Autorizacao> autorizacoesDoDependente;
	@OneToMany(mappedBy = "paciente")
	private List<Pacote> pacotes;

	// Constructor
	public Paciente() {
		super();
		this.ehCliente = true;
		this.ehPaciente = true;
	}

	public Paciente(Long id) {
		super(id);
		this.ehCliente = true;
		this.ehPaciente = true;
	}

	public Paciente(String email, String senha) {
		super(email, senha);
		this.ehCliente = true;
		this.ehPaciente = true;
	}
	
	//Getters and Setters
	public List<Autorizacao> getAutorizacoesDoDependente() {
		return autorizacoesDoDependente;
	}

	public void setAutorizacoesDoDependente(List<Autorizacao> autorizacoesDoDependente) {
		this.autorizacoesDoDependente = autorizacoesDoDependente;
	}

	public List<Pacote> getPacotes() {
		return pacotes;
	}

	public void setPacotes(List<Pacote> pacotes) {
		this.pacotes = pacotes;
	}
	

	
	
	
}
