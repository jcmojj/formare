package br.com.coisasde.loja.model.usuario;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.OneToMany;

import br.com.coisasde.loja.model.atendimento.Pacote;

@Entity
public class ClientePagante extends Cliente implements Serializable {
	private static final long serialVersionUID = 1L;

	// Dependente e o Que está autorizado a fazer
	@OneToMany(mappedBy = "autorizador") // ok
	private List<Autorizacao> autorizacoes;

	@OneToMany(mappedBy = "contratante")
	private List<Pacote> pacotes;

	// Constructor
	public ClientePagante() {
		super();
		this.ehCliente = true;
		this.ehClientePagante = true;

	}

	public ClientePagante(Long id) {
		super(id);
		this.ehCliente = true;
		this.ehClientePagante = true;
	}

	public ClientePagante(String email, String senha) {
		super(email, senha);
		this.ehCliente = true;
		this.ehClientePagante = true;
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

}
