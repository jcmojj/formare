package br.com.clinicaformare.model.usuario;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.List;

import javax.faces.context.FacesContext;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;


@Entity
public class NivelProfissional implements Serializable {
	private static final long serialVersionUID = 2L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
//	@Inject @UsuarioLogado @Transient
//	private Usuario usuarioLogado;
	
//	@Inject
//	private UsuarioRecuperado usuarioRecuperado;
	
//	@Inject
//	private Event<UsuarioRecuperado> recuperarUsuarioEvent;
	
//	@Inject @UsuarioLogado
//	private Event<Usuario> usuarioLogadoEvent;
	
//	@Inject
//	private transient UsuarioRecuperado usuarioRecuperado;// = new UsuarioRecuperado();
	
//	Usuario usuarioLogado;// = new Usuario(); 
	
	// Parâmetros Próprios
	private String nivel;
	
	// Parâmetros Derivados
	@OneToMany (mappedBy = "nivelProfissional")
	private List<EspecializacaoDoProfissional> especializacoesDoProfissional;
	
	// Parâmetros de Persistência
	private LocalDate dataCriacao;
	private LocalDate dataAlteracao;
	@ManyToOne
	private Usuario alteradoPor;
	@ManyToOne
	private Usuario criadoPor;
	
//	//Usuario Logado
//	private transient Usuario usuarioLogado;
//	//Usuario Logado
//		private transient LoginBean loginBean;
//	@Transient
//	private Long usuarioLogadoId;
	
//	@PostContruct
//	private void teste() {
//		
//	}
	
	
	// Constructor
	public NivelProfissional() {
		super();
//		usuarioLogado = (Usuario)FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("usuarioLogado");
	}
	public NivelProfissional(Long id) {
		super();
		this.id = id;
//		usuarioLogado = (Usuario)FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("usuarioLogado");
	}	
	public NivelProfissional(String nivel) {
		super();
		this.nivel = nivel;
//		usuarioLogado = (Usuario)FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("usuarioLogado");
	}
	
	// Getters and Setters
	public Long getId() {
		return id;
	}
	public String getNivel() {
		return nivel;
	}
	public List<EspecializacaoDoProfissional> getEspecializacoesDoProfissional() {
		return especializacoesDoProfissional;
	}
	public LocalDate getDataCriacao() {
		return dataCriacao;
	}
	public LocalDate getDataAlteracao() {
		return dataAlteracao;
	}
	public Usuario getAlteradoPor() {
		return alteradoPor;
	}
	public Usuario getCriadoPor() {
		return criadoPor;
	}
	
	
	// String, hashCode and Equals
	@Override
	public String toString() {
		return "NivelProfissional [id=" + id + ", nivel=" + nivel + "]";
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
		NivelProfissional other = (NivelProfissional) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

	// Método Callback para persistir
	@PrePersist
	public void quandoCriar() {
//		recuperarUsuarioEvent.fire(usuarioRecuperado);
		
//		Usuario usuarioLogado = new Usuario();
//		usuarioLogadoEvent.fire(usuarioLogado);
//		System.out.println("Quando Criar - Usuario: " + usuarioLogado);
		this.dataCriacao = (LocalDate.now());
		this.dataAlteracao = (LocalDate.now());
		this.criadoPor = (Usuario)FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("usuarioLogado");
//		this.alteradoPor = usuarioLogado;
	}

	// Método Callback para update
	@PreUpdate
	public void quandoAtualizar() {
//		Usuario usuarioLogado = new Usuario();
//		usuarioLogadoEvent.fire(usuarioLogado);
		this.dataAlteracao = (LocalDate.now());
//		this.alteradoPor = usuarioLogado;
	}


}
