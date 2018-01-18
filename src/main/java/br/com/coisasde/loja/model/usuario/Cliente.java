package br.com.coisasde.loja.model.usuario;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

@Entity
public class Cliente extends Usuario implements Serializable {
	private static final long serialVersionUID = 1L;

	// Dependentes do responsável
	@OneToMany(mappedBy="responsavelPeloDependente")
	private List<Cliente> dependentes;
	@ManyToOne
	private Cliente responsavelPeloDependente;
	
	// Compartilhar o dependente
	@OneToMany(mappedBy="responsavelAutorizador")
	private List<Cliente> responsaveisAutorizados;
	@ManyToOne
	private Cliente responsavelAutorizador;
	
	
	// Constructor
	public Cliente() {
		super();
	}
	public Cliente(Long id) {
		super(id);
	}
	public Cliente(String email, String senha) {
		super(email, senha);
	}
	// Getters and Setters
	public List<Cliente> getDependentes() {
		return dependentes;
	}
	public void setDependentes(List<Cliente> dependentes) {
		this.dependentes = dependentes;
	}
	public Cliente getResponsavelPeloDependente() {
		return responsavelPeloDependente;
	}
	public void setResponsavelPeloDependente(Cliente responsavelPeloDependente) {
		this.responsavelPeloDependente = responsavelPeloDependente;
	}
	public List<Cliente> getResponsaveisAutorizados() {
		return responsaveisAutorizados;
	}
	public void setResponsaveisAutorizados(List<Cliente> responsaveisAutorizados) {
		this.responsaveisAutorizados = responsaveisAutorizados;
	}
	public Cliente getResponsavelAutorizador() {
		return responsavelAutorizador;
	}
	public void setResponsavelAutorizador(Cliente responsavelAutorizador) {
		this.responsavelAutorizador = responsavelAutorizador;
	}
	
	// Métodos Criados
	public void adicionarDependente(String email, String senha) {
		Cliente dependente = new Cliente(email,senha);
		dependente.setResponsavelPeloDependente(this);
		this.getDependentes().add(dependente);
		// persistir
	}
	public void removerDependente(Cliente dependente) {
		this.getDependentes().remove(dependente);
		// persistir
	}
//	public void adicionarAutorizados(Cliente autorizado, Cliente dependente) {
//		autorizado
//		
//	}
	
	
}