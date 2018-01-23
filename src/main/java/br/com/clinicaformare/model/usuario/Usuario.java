package br.com.clinicaformare.model.usuario;

import java.io.Serializable;
import java.util.Calendar;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToOne;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.validator.constraints.Email;

import br.com.clinicaformare.model.usuario.telefone.Telefone;
import br.com.clinicaformare.usuario.endereco.Endereco;

@Entity
public class Usuario implements Serializable {
	private static final long serialVersionUID = 2L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	// Necessário para criação
	@Email
	@Column(nullable = true, unique = true)
	private String email;
	@Column(nullable = true, unique = true, length = 15)
	private String senha;

	private String nome;
	private String sobrenome;
	private Calendar dataNascimento;
	@Column(unique = true)
	private String cpf;
	private String rg;

	// Relações com tipos de Usuário
	protected boolean cliente;
	protected boolean equipe;
	@OneToOne
	Paciente paciente;
	@OneToOne
	Autorizado autorizado;
	@OneToOne
	Pagante pagante;
	@OneToOne
	Profissional profissional;
	@OneToOne
	Socia socia;
	@OneToOne
	Administrador administrador;
	@OneToOne
	Secretaria secretaria;

	@ManyToMany // Join para criar uma tabela única em relacionamento many to many
	@JoinTable(name = "Usuario_Telefone", joinColumns = @JoinColumn(name = "Usuario_id", referencedColumnName = "id"), inverseJoinColumns = @JoinColumn(name = "Telefone_id", referencedColumnName = "id"))
	private List<Telefone> telefones;
	@ManyToMany // Join para criar uma tabela única em relacionamento many to many
	@JoinTable(name = "Usuario_Endereco", joinColumns = @JoinColumn(name = "Usuario_id", referencedColumnName = "id"), inverseJoinColumns = @JoinColumn(name = "Endereco_id", referencedColumnName = "id"))
	private List<Endereco> enderecos;

	@Temporal(TemporalType.TIMESTAMP)
	private Calendar dataCriacao;
	@Temporal(TemporalType.TIMESTAMP)
	private Calendar dataAlteracao;

	@Override
	public String toString() {
		return "Usuario [id=" + id + ", email=" + email + ", senha=" + senha + ", nome=" + nome + ", sobrenome=" + sobrenome + ", dataNascimento=" + dataNascimento + ", cpf=" + cpf + ", rg=" + rg
				+ ", telefones=" + telefones + ", enderecos=" + enderecos + ", Cliente=" + isCliente() + ", Paciente=" + isPaciente() + ", Autorizado=" + isAutorizado() + ", Pagante=" + isPagante()
				+ ", Equipe=" + isEquipe() + ", Profissional=" + isProfissional() + ", Socia=" + isSocia() + ", Administrador=" + isAdministrador() + ", Secretaria=" + isSecretaria() + "]";
	}

	// Constructor
	public Usuario() {
	}

	public Usuario(Long id) {
		this.id = id;
	}

	public Usuario(String email, String senha) {
		this.email = email;
		this.senha = senha;
	}

	// Getters and Setters

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getSobrenome() {
		return sobrenome;
	}

	public void setSobrenome(String sobrenome) {
		this.sobrenome = sobrenome;
	}

	public Calendar getDataNascimento() {
		return dataNascimento;
	}

	public void setDataNascimento(Calendar dataNascimento) {
		this.dataNascimento = dataNascimento;
	}

	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}

	public String getRg() {
		return rg;
	}

	public void setRg(String rg) {
		this.rg = rg;
	}

	public List<Telefone> getTelefones() {
		return telefones;
	}

	public void setTelefones(List<Telefone> telefones) {
		this.telefones = telefones;
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

	public List<Endereco> getEnderecos() {
		return enderecos;
	}

	public void setEnderecos(List<Endereco> enderecos) {
		this.enderecos = enderecos;
	}
	// Getters and Setters das Relacoes

	public Paciente getPaciente() {
		return paciente;
	}

	public void setPaciente(Paciente paciente) {
		this.cliente = !this.paciente.equals(null) & !this.autorizado.equals(null) & !this.pagante.equals(null);
		this.paciente = paciente;
	}

	public Autorizado getAutorizado() {
		return autorizado;
	}

	public void setAutorizado(Autorizado autorizado) {
		this.cliente = !this.paciente.equals(null) & !this.autorizado.equals(null) & !this.pagante.equals(null);
		this.autorizado = autorizado;
	}

	public Pagante getPagante() {
		return pagante;
	}

	public void setPagante(Pagante pagante) {
		this.cliente = !this.paciente.equals(null) & !this.autorizado.equals(null) & !this.pagante.equals(null);
		this.pagante = pagante;
	}

	public Profissional getProfissional() {
		return profissional;
	}

	public void setProfissional(Profissional profissional) {
		this.equipe = !this.profissional.equals(null) & !this.socia.equals(null) & !this.administrador.equals(null) & !this.secretaria.equals(null);
		this.profissional = profissional;
	}

	public Socia getSocia() {
		return socia;
	}

	public void setSocia(Socia socia) {
		this.equipe = !this.profissional.equals(null) & !this.socia.equals(null) & !this.administrador.equals(null) & !this.secretaria.equals(null);
		this.socia = socia;
	}

	public Administrador getAdministrador() {
		return administrador;
	}

	public void setAdministrador(Administrador administrador) {
		this.equipe = !this.profissional.equals(null) & !this.socia.equals(null) & !this.administrador.equals(null) & !this.secretaria.equals(null);
		this.administrador = administrador;
	}

	public Secretaria getSecretaria() {
		return secretaria;
	}

	public void setSecretaria(Secretaria secretaria) {
		this.equipe = !this.profissional.equals(null) & !this.socia.equals(null) & !this.administrador.equals(null) & !this.secretaria.equals(null);
		this.secretaria = secretaria;
	}

	// Getters and Setters dos Booleans
	public boolean isCliente() {
		return cliente; 
	}

	public boolean isPaciente() {
		return !this.paciente.equals(null);
	}

	public boolean isAutorizado() {
		return !this.autorizado.equals(null);
	}

	public boolean isPagante() {
		return !this.pagante.equals(null);
	}

	public boolean isEquipe() {
		return equipe;
	}

	public boolean isProfissional() {
		return !this.profissional.equals(null);
	}

	public boolean isSocia() {
		return !this.socia.equals(null);
	}

	public boolean isAdministrador() {
		return !this.administrador.equals(null);
	}

	public boolean isSecretaria() {
		return !this.secretaria.equals(null);
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
