package br.com.clinicaformare.model.usuario;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.time.Period;
import java.time.ZoneId;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.br.CPF;

import br.com.clinicaformare.model.usuario.telefone.Telefone;
import br.com.clinicaformare.usuario.endereco.Endereco;
import br.com.clinicaformare.usuario.endereco.Paesci;

@Entity
public class Usuario implements Serializable {
	private static final long serialVersionUID = 2L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id", updatable = false, nullable = false)
	private Long id;

	// Necessário para criação
	@Email(message = "Não é um endereço de e-mail válido")
	@Column(nullable = true, unique = true)
	private String email;
	@Column(nullable = true, unique = true, length = 15)
	private String password;

	private String nome;
	private String sobrenome;
	private String profissao;
	@Temporal(TemporalType.DATE)
	private Date dataNascimento  = new Date();
	@ManyToOne
	private Paesci localNascimento;
	@Column(unique = true)
	@CPF
	private String cpf;
	private String rg;

	// Relações com tipos de Usuário
	protected Boolean cliente = false;
	protected Boolean equipe = false;
	@OneToOne//(cascade = {CascadeType.ALL, CascadeType.MERGE, CascadeType.PERSIST})
	Paciente paciente;
	@OneToOne
	Autorizado autorizado;
	@OneToOne//(cascade = {CascadeType.ALL, CascadeType.MERGE, CascadeType.PERSIST})
	ResponsavelFinanceiro responsavelFinanceiro;
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
	private Calendar dataCriacao = Calendar.getInstance();
	@Temporal(TemporalType.TIMESTAMP)
	private Calendar dataAlteracao = Calendar.getInstance();

	@Override
	public String toString() {
		return "Usuario [id=" + id + ", email=" + email + ", password=" + password + ", nome=" + nome + ", sobrenome=" + sobrenome + ", dataNascimento=" + dataNascimento + ", cpf=" + cpf + ", rg=" + rg
				+ ", telefones=" + telefones + ", enderecos=" + enderecos + ", Cliente=" + isCliente() + ", Paciente=" + isPaciente() + ", Autorizado=" + isAutorizado() + ", ResponsavelFinanceiro=" + isResponsavelFinanceiro()
				+ ", Equipe=" + isEquipe() + ", Profissional=" + isProfissional() + ", Socia=" + isSocia() + ", Administrador=" + isAdministrador() + ", Secretaria=" + isSecretaria() + "]";
	}

	// Constructor
	public Usuario() {
	}

	public Usuario(Long id) {
		this.id = id;
	}

	public Usuario(String email, String password) {
		this.email = email;
		this.password = password;
	}

	public Usuario(String nome) {
		this.nome = nome;
	}
	public Usuario(String nome, String sobrenome, String email) {
		this.nome = nome;
		this.sobrenome = sobrenome;
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

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
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

	public Date getDataNascimento() {
		return dataNascimento;
	}

	public void setDataNascimento(Date dataNascimento) {
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
		this.cliente = (paciente != null) || (autorizado != null) || (responsavelFinanceiro != null);
		this.paciente = paciente;
	}

	public Autorizado getAutorizado() {
		return autorizado;
	}

	public void setAutorizado(Autorizado autorizado) {
		this.cliente = (paciente != null) || (autorizado != null) || (responsavelFinanceiro != null);
		this.autorizado = autorizado;
	}

	public ResponsavelFinanceiro getResponsavelFinanceiro() {
		return responsavelFinanceiro;
	}

	public void setResponsavelFinanceiro(ResponsavelFinanceiro responsavelFinanceiro) {
		this.cliente = (paciente != null) || (autorizado != null) || (responsavelFinanceiro != null);
		this.responsavelFinanceiro = responsavelFinanceiro;
	}

	public Profissional getProfissional() {
		return profissional;
	}

	public void setProfissional(Profissional profissional) {
		this.equipe = (profissional != null) || (socia != null) || (administrador != null) || (secretaria != null);
		// System.out.println("AQUI3");
		this.profissional = profissional;
		// profissional.setUsuario(this);
		// System.out.println("AQUI1");
	}

	public Socia getSocia() {
		return socia;
	}

	public void setSocia(Socia socia) {
		this.equipe = (profissional != null) || (socia != null) || (administrador != null) || (secretaria != null);
		this.socia = socia;
	}

	public Administrador getAdministrador() {
		return administrador;
	}

	public void setAdministrador(Administrador administrador) {
		this.equipe = (profissional != null) || (socia != null) || (administrador != null) || (secretaria != null);
		this.administrador = administrador;
	}

	public Secretaria getSecretaria() {
		return secretaria;
	}

	public void setSecretaria(Secretaria secretaria) {
		this.equipe = (profissional != null) || (socia != null) || (administrador != null) || (secretaria != null);
		this.secretaria = secretaria;
	}

	public Integer getIdade() {
		Calendar now = Calendar.getInstance();
		return Period.between(LocalDateTime.ofInstant(dataNascimento.toInstant(), ZoneId.systemDefault()).toLocalDate(), LocalDateTime.ofInstant(now.toInstant(), ZoneId.systemDefault()).toLocalDate()).getYears();
	}

	// Getters and Setters dos Booleans
	public Boolean isCliente() {
		return cliente;
	}

	public Boolean isPaciente() {
		return (paciente != null) ? true : false;
	}

	public Boolean isAutorizado() {
		return (autorizado != null) ? true : false;
	}

	public Boolean isResponsavelFinanceiro() {
		return (responsavelFinanceiro != null) ? true : false;
	}

	public Boolean isEquipe() {
		return equipe;
	}

	public Boolean isProfissional() {
		return (profissional != null) ? true : false;
	}

	public Boolean isSocia() {
		return (socia != null) ? true : false;
	}

	public Boolean isAdministrador() {
		return (administrador != null) ? true : false;
	}

	public Boolean isSecretaria() {
		return (secretaria != null) ? true : false;
	}
	

	public String getProfissao() {
		return profissao;
	}

	public void setProfissao(String profissao) {
		this.profissao = profissao;
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