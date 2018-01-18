package br.com.coisasde.loja.model.usuario;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

@Entity
public class Cliente extends Usuario implements Serializable {
	private static final long serialVersionUID = 1L;
	
	// Dependente e o Que está autorizado a fazer
	@OneToMany(mappedBy="autorizador")
	private List<Autorizacao> autorizacoes;	
	@OneToOne(mappedBy="autorizado")
	private Autorizacao autorizacaoDoAutorizado;
	@OneToOne(mappedBy="dependente")
	private Autorizacao autorizacaoDoDependente;

	
	
	
//	// Dependentes do responsável
//	@OneToMany(mappedBy="responsavelPeloDependente")
//	private List<Cliente> dependentes;
//	@ManyToOne
//	private Cliente responsavelPeloDependente;
//	
//	// Compartilhar o dependente
//	@OneToMany(mappedBy="responsavelAutorizador")
//	private List<Cliente> responsaveisAutorizados;
//	@ManyToOne
//	private Cliente responsavelAutorizador;
//	
//	
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
//	// Getters and Setters
	public List<Autorizacao> getAutorizacoes() {
		return autorizacoes;
	}
	public void setAutorizacoes(List<Autorizacao> autorizacoes) {
		this.autorizacoes = autorizacoes;
	}
	public Autorizacao getAutorizacaoDoAutorizado() {
		return autorizacaoDoAutorizado;
	}
	public void setAutorizacaoDoAutorizado(Autorizacao autorizacaoDoAutorizado) {
		this.autorizacaoDoAutorizado = autorizacaoDoAutorizado;
	}
	public Autorizacao getAutorizacaoDoDependente() {
		return autorizacaoDoDependente;
	}
	public void setAutorizacaoDoDependente(Autorizacao autorizacaoDoDependente) {
		this.autorizacaoDoDependente = autorizacaoDoDependente;
	}
//	public List<Cliente> getDependentes() {
//		return dependentes;
//	}
//	public void setDependentes(List<Cliente> dependentes) {
//		this.dependentes = dependentes;
//	}
//	public Cliente getResponsavelPeloDependente() {
//		return responsavelPeloDependente;
//	}
//	public void setResponsavelPeloDependente(Cliente responsavelPeloDependente) {
//		this.responsavelPeloDependente = responsavelPeloDependente;
//	}
//	public List<Cliente> getResponsaveisAutorizados() {
//		return responsaveisAutorizados;
//	}
//	public void setResponsaveisAutorizados(List<Cliente> responsaveisAutorizados) {
//		this.responsaveisAutorizados = responsaveisAutorizados;
//	}
//	public Cliente getResponsavelAutorizador() {
//		return responsavelAutorizador;
//	}
//	public void setResponsavelAutorizador(Cliente responsavelAutorizador) {
//		this.responsavelAutorizador = responsavelAutorizador;
//	}
//	
//	// Métodos Criados
//	public void adicionarDependente(String email, String senha) {
//		Cliente dependente = new Cliente(email,senha);
//		dependente.setResponsavelPeloDependente(this);
//		this.getDependentes().add(dependente);
//		// persistir
//	}
//	public void removerDependente(Cliente dependente) {
//		this.getDependentes().remove(dependente);
//		// persistir
//	}
////	public void adicionarAutorizados(Cliente autorizado, Cliente dependente) {
////		autorizado
////		
////	}
	
	
}