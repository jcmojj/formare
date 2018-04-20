package br.com.clinicaformare.model.usuario;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Period;
import java.util.ArrayList;
import java.util.List;

import javax.faces.context.FacesContext;
import javax.persistence.Cacheable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.PostPersist;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.persistence.QueryHint;

import org.apache.commons.text.WordUtils;
import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.br.CPF;

import br.com.clinicaformare.model.acesso.Acesso;
import br.com.clinicaformare.usuario.endereco.Endereco;
import br.com.clinicaformare.usuario.endereco.Paesci;
import br.com.clinicaformare.usuario.endereco.Telefone;

@Entity
//@Table(uniqueConstraints=  @UniqueConstraint(columnNames = {"email", "password"}))
@Cacheable
@NamedQuery(	name=Usuario.LISTAR, 
			query="select u from Usuario u",
			hints= {
					@QueryHint(name="org.hibernate.cacheable", value="true"),
					@QueryHint(name="org.hibernate.cacheRegion", value=Usuario.LISTAR)
			})
public class Usuario implements Serializable {
	private static final long serialVersionUID = 2L;
	public static final String LISTAR = "Usuario.listar";
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id", updatable = false, nullable = false)
	private Long id;
	// Variáveis de alteração
	@OneToOne(mappedBy = "usuario")
	private Alterador alteradorDesseUsuario;
	@OneToOne(mappedBy = "usuario")
	private Criador criadorDesseUsuario;

	// Necessário para criação
	@Email(message = "Não é um endereço de e-mail válido")
	@Column(nullable = true)//, unique = true)
	private String email;
	@Column(nullable = true, length = 15)//, unique = true)
	private String password;

	private String nome;
	private String sobrenome;
	private String profissao;
	private LocalDate dataNascimento;//  = LocalDate.now();
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
	private Paciente paciente;
	@OneToOne
	private Autorizado autorizado;
	@OneToOne//(cascade = {CascadeType.ALL, CascadeType.MERGE, CascadeType.PERSIST})
	private ResponsavelFinanceiro responsavelFinanceiro;
	@OneToOne
	private Profissional profissional;
	@OneToOne
	private Socia socia;
	@OneToOne
	private Administrador administrador;
	@OneToOne
	private Secretaria secretaria;
	@OneToOne
	private Fornecedor fornecedor;
	@OneToOne
	private Financeiro financeiro;
	@OneToMany(mappedBy = "usuario")
	List<Acesso> acessos = new ArrayList<>();


	@ManyToMany // Join para criar uma tabela única em relacionamento many to many
	@JoinTable(name = "Usuario_Telefone", joinColumns = @JoinColumn(name = "Usuario_id", referencedColumnName = "id"), inverseJoinColumns = @JoinColumn(name = "Telefone_id", referencedColumnName = "id"))
	private List<Telefone> telefones = new ArrayList<>();
	@ManyToMany // Join para criar uma tabela única em relacionamento many to many
	@JoinTable(name = "Usuario_Endereco", joinColumns = @JoinColumn(name = "Usuario_id", referencedColumnName = "id"), inverseJoinColumns = @JoinColumn(name = "Endereco_id", referencedColumnName = "id"))
	private List<Endereco> enderecos  = new ArrayList<>();


	@Override
	public String toString() {
		return "Usuario [id=" + id + ", email=" + email + ", password=" + password + ", nome=" + nome + ", sobrenome=" + sobrenome + ", dataNascimento=" + dataNascimento + ", cpf=" + cpf + ", rg=" + rg
				+ ", enderecos=" + enderecos + ", Cliente=" + isCliente() + ", Paciente=" + isPaciente() + ", Autorizado=" + isAutorizado() + ", ResponsavelFinanceiro=" + isResponsavelFinanceiro()
				+ ", Equipe=" + isEquipe() + ", Profissional=" + isProfissional() + ", Socia=" + isSocia() + ", Administrador=" + isAdministrador() + ", Secretaria=" + isSecretaria() + "]";
	}

	// Constructor
	public Usuario() {
		System.out.println("New Usuario");
	}

//	public Usuario(Long id) {
//		this.id = id;
//	}

	public Usuario(String email, String password) {
		this.email = WordUtils.capitalize(email).trim().replaceAll("  ", " ").replaceAll("  ", " ").replaceAll("  ", " ").replaceAll("  ", " ").replaceAll(" ", "");
		this.password = password;
	}

//	public Usuario(String nome) {
//		this.nome = nome;
//	}
	public Usuario(String nome, String sobrenome, String email) {
		this.nome = WordUtils.capitalize(nome).trim().replaceAll("  ", " ").replaceAll("  ", " ").replaceAll("  ", " ").replaceAll("  ", " ");
		this.sobrenome = WordUtils.capitalize(sobrenome).trim().replaceAll("  ", " ").replaceAll("  ", " ").replaceAll("  ", " ").replaceAll("  ", " ");
		this.email = WordUtils.capitalize(email).trim().replaceAll("  ", " ").replaceAll("  ", " ").replaceAll("  ", " ").replaceAll("  ", " ").replaceAll(" ", "");
	}

//	@PostPersist
//	private void postPersistir() {
//		Criador criador = new Criador(this);
//		this.criadorDesseUsuario = criador;
//		Alterador alterador = new Alterador(this);
//		this.alteradorDesseUsuario = alterador;
//		
//	}
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
		this.email = email.trim().toLowerCase().replaceAll("  ", " ").replaceAll("  ", " ").replaceAll("  ", " ").replaceAll("  ", " ").replaceAll(" ", "");
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password.trim();
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = WordUtils.capitalize(nome).trim().replaceAll("  ", " ").replaceAll("  ", " ").replaceAll("  ", " ").replaceAll("  ", " ");
	}

	public String getSobrenome() {
		return sobrenome;
	}

	public void setSobrenome(String sobrenome) {
		this.sobrenome = WordUtils.capitalize(sobrenome).trim().replaceAll("  ", " ").replaceAll("  ", " ").replaceAll("  ", " ").replaceAll("  ", " ");
	}

	public LocalDate getDataNascimento() {
		return dataNascimento;
	}

	public void setDataNascimento(LocalDate dataNascimento) {
		this.dataNascimento = dataNascimento;
	}

	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf.trim();
	}

	public String getRg() {
		return rg;
	}

	public void setRg(String rg) {
		this.rg = rg.trim();
	}

	public List<Telefone> getTelefones() {
		return telefones;
	}

	public List<Endereco> getEnderecos() {
		return enderecos;
	}

	public Paesci getLocalNascimento() {
		return localNascimento;
	}

	public void setLocalNascimento(Paesci localNascimento) {
		this.localNascimento = localNascimento;
	}
	public String getProfissao() {
		return profissao;
	}

	public void setProfissao(String profissao) {
		this.profissao = WordUtils.capitalize(profissao).trim().replaceAll("  ", " ").replaceAll("  ", " ").replaceAll("  ", " ").replaceAll("  ", " ");
	}

	public List<Acesso> getAcessos() {
		return acessos;
	}

	public Alterador getAlteradorDesseUsuario() {
		return alteradorDesseUsuario;
	}

	public Criador getCriadorDesseUsuario() {
		return criadorDesseUsuario;
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
		this.profissional = profissional;
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
	public Fornecedor getFornecedor() {
		return fornecedor;
	}
	public void setFornecedor(Fornecedor fornecedor) {
		this.fornecedor = fornecedor;
	}

	public Financeiro getFinanceiro() {
		return financeiro;
	}

	public void setFinanceiro(Financeiro financeiro) {
		this.financeiro = financeiro;
	}

	public Integer getIdade() {
//		Calendar now = Calendar.getInstance();
//		return Period.between(LocalDateTime.ofInstant(dataNascimento.toInstant(), ZoneId.systemDefault()).toLocalDate(), LocalDateTime.ofInstant(now.toInstant(), ZoneId.systemDefault()).toLocalDate()).getYears();
		return Period.between(dataNascimento, LocalDate.now()).getYears();
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
	
	public Boolean isFornecedor() {
		return (fornecedor != null) ? true : false;
	}
	public Boolean isFinanceiro() {
		return (financeiro != null) ? true : false;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Usuario other = (Usuario) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}
	
	// -----------------------------------Registro de Alteração-----------------------------------------
		// Parâmetros de Persistência
		private LocalDateTime dataCriacao;
		private LocalDateTime dataAlteracao;
		@ManyToOne
		private Usuario alterador;
		@ManyToOne
		private Usuario criador;
		
		// Getters de persistência
		public LocalDateTime getDataCriacao() {
			return dataCriacao;
		}
		public LocalDateTime getDataAlteracao() {
			return dataAlteracao;
		}
		public Usuario getAlterador() {
			return alterador;
		}
		public Usuario getCriador() {
			return criador;
		}
		
		// Método Callback para persistir
		@PrePersist
		public void quandoCriar() {
			this.dataCriacao = (LocalDateTime.now());
			this.dataAlteracao = (LocalDateTime.now());
			this.criador = 		(Usuario)FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("usuarioLogado");
			this.alterador = 	(Usuario)FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("usuarioLogado");
		}

		// Método Callback para update
		@PreUpdate
		public void quandoAtualizar() {
			this.dataAlteracao = (LocalDateTime.now());
			this.alterador  = (Usuario)FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("usuarioLogado");
		}
		// ------------------------------------------------------------------------------------------------

	

}