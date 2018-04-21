package br.com.clinicaformare.model.usuario;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import javax.faces.context.FacesContext;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;

import org.apache.commons.text.WordUtils;
import org.hibernate.validator.constraints.br.CPF;

import br.com.clinicaformare.model.acesso.Acesso;

@Entity
//@Table(uniqueConstraints=  @UniqueConstraint(columnNames = {"email", "password"}))
//@Cacheable
//@NamedQuery(	name=BasicUser.LISTAR, 
//			query="select u from BasicUser u",
//			hints= {
//					@QueryHint(name="org.hibernate.cacheable", value="true"),
//					@QueryHint(name="org.hibernate.cacheRegion", value=BasicUser.LISTAR)
//			})
public class BasicUser implements Serializable {
	private static final long serialVersionUID = 2L;
	public static final String LISTAR = "BasicUser.listar";
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id", updatable = false, nullable = false)
	private Long id;
	// Variáveis de alteração


	// Necessário para criação

	private String nome;
	private String sobrenome;
	@Column(unique = true)
	@CPF
	private String cpf;
	@OneToOne(mappedBy = "basicUser", fetch = FetchType.LAZY)
	private Usuario usuario;
	@OneToMany(mappedBy = "basicUser")
	List<Acesso> acessos = new ArrayList<>();
	public Usuario getUsuario() {
		return usuario;
	}
	public List<Acesso> getAcessos() {
		return acessos;
	}
	// Constructor
	public BasicUser() {
		System.out.println("New BasicUser");
		StackTraceElement[] stackTraceElements = Thread.currentThread().getStackTrace();
		System.out.println("Dentro BASIC USER");
		for(int i  = 0;i<stackTraceElements.length;i++) {
			System.out.println("i"+stackTraceElements[i].toString());
		}
	}
//	public BasicUser(Usuario usuario) {
//		System.out.println("New BasicUser");
//		this.usuario = usuario;
//	}

	// Getters and Setters

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
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


	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf.trim();
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}
	@Override
	public String toString() {
		return "BasicUser [id=" + id + ", nome=" + nome + ", sobrenome=" + sobrenome + ", cpf=" + cpf + ", usuario=" + usuario + "]";
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
		BasicUser other = (BasicUser) obj;
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
		private BasicUser alterador;
		@ManyToOne()
		private BasicUser criador;
		
		// Getters de persistência
		public LocalDateTime getDataCriacao() {
			return dataCriacao;
		}
		public LocalDateTime getDataAlteracao() {
			return dataAlteracao;
		}
		public BasicUser getAlterador() {
			return alterador;
		}
		public BasicUser getCriador() {
			return criador;
		}
		
		// Método Callback para persistir
		@PrePersist
		public void quandoCriar() {
			this.dataCriacao = (LocalDateTime.now());
			this.dataAlteracao = (LocalDateTime.now());
			this.criador = 		(BasicUser)FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("usuarioLogado");
			this.alterador = 	(BasicUser)FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("usuarioLogado");
		}

		// Método Callback para update
		@PreUpdate
		public void quandoAtualizar() {
			this.dataAlteracao = (LocalDateTime.now());
			this.alterador  = (BasicUser)FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("usuarioLogado");
		}
		// ------------------------------------------------------------------------------------------------

	

}