package br.com.coisasde.loja.model.usuario;

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
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.validator.constraints.Email;

import br.com.coisasde.loja.model.usuario.endereco.Endereco;
import br.com.coisasde.loja.model.usuario.telefone.Telefone;

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

	@ManyToMany
	@JoinTable(name = "Usuario_Telefone", joinColumns = @JoinColumn(name = "Usuario_id", referencedColumnName = "id"), inverseJoinColumns = @JoinColumn(name = "Telefone_id", referencedColumnName = "id"))
	private List<Telefone> telefones;
	@ManyToMany
	@JoinTable(name = "Usuario_Endereco", joinColumns = @JoinColumn(name = "Usuario_id", referencedColumnName = "id"), inverseJoinColumns = @JoinColumn(name = "Endereco_id", referencedColumnName = "id"))
	private List<Endereco> enderecos;

	// Todo usuario nasce como cliente, quem define outras funcoes são as sócias
	protected boolean ehCliente;
	protected boolean ehPaciente;
	protected boolean ehTerapeuta;
	protected boolean ehSocia;
	protected boolean ehAdministrador;
	protected boolean ehSecretaria;

	@Temporal(TemporalType.TIMESTAMP)
	private Calendar dataCriacao;
	@Temporal(TemporalType.TIMESTAMP)
	private Calendar dataAlteracao;

	@Override
	public String toString() {
		return "Usuario [id=" + id + ", email=" + email + ", senha=" + senha + ", nome=" + nome + ", sobrenome=" + sobrenome + ", cpf=" + cpf + "]";
	}

	// Constructor
	public Usuario(String email, String senha) {
		super();
		this.email = email;
		this.senha = senha;
		this.ehCliente = true;
		this.ehPaciente = false;
		this.ehTerapeuta = false;
		this.ehSocia = false;
		this.ehAdministrador = false;
		this.ehSecretaria = false;
		quandoCriar();
	}

	public Usuario() {
		super();
	}

	public Usuario(Long id) {
		super();
		quandoAtualizar();
		this.id = id;
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

	public boolean isEhCliente() {
		return ehCliente;
	}

	public void setEhCliente(boolean ehCliente) {
		this.ehCliente = ehCliente;
	}

	public boolean isEhPaciente() {
		return ehPaciente;
	}

	public void setEhPaciente(boolean ehPaciente) {
		this.ehPaciente = ehPaciente;
	}

	public boolean isEhTerapeuta() {
		return ehTerapeuta;
	}

	public void setEhTerapeuta(boolean ehTerapeuta) {
		this.ehTerapeuta = ehTerapeuta;
	}

	public boolean isEhSocia() {
		return ehSocia;
	}

	public void setEhSocia(boolean ehSocia) {
		this.ehSocia = ehSocia;
	}

	public boolean isEhAdministrador() {
		return ehAdministrador;
	}

	public void setEhAdministrador(boolean ehAdministrador) {
		this.ehAdministrador = ehAdministrador;
	}

	public boolean isEhSecretaria() {
		return ehSecretaria;
	}

	public void setEhSecretaria(boolean ehSecretaria) {
		this.ehSecretaria = ehSecretaria;
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
