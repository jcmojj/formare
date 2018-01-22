package br.com.coisasde.loja.model.usuario;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.OneToMany;

import br.com.coisasde.loja.model.atendimento.Pacote;

@Entity
public class Socia extends Usuario implements Serializable {
	private static final long serialVersionUID = 1L;

	@OneToMany(mappedBy = "sociaResponsavel")
	private List<Pacote> pacotes;

	// Constructor
	public Socia() {
		super();
		super.ehCliente = false;
		super.ehSocia = true;
	}

	public Socia(Long id) {
		super(id);
		super.ehCliente = false;
		super.ehSocia = true;
	}

	public Socia(String email, String senha) {
		super(email, senha);
		super.ehCliente = false;
		super.ehSocia = true;
	}
	// Getters and setters

	public List<Pacote> getPacotes() {
		return pacotes;
	}

	public void setPacotes(List<Pacote> pacotes) {
		this.pacotes = pacotes;
	}

}
