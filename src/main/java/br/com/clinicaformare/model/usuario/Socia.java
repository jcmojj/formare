package br.com.clinicaformare.model.usuario;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;

import javax.faces.context.FacesContext;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;

import br.com.clinicaformare.model.atendimento.Atendimento;
import br.com.clinicaformare.model.atendimento.Pacote;

@Entity
public class Socia implements Serializable {
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
//	@Inject @UsuarioLogado
//	private Usuario usuarioLogado;
	
	// Parâmetros Próprios
	@OneToOne(mappedBy = "socia")
	Usuario usuario;


	@OneToMany(mappedBy = "sociaSupervisora")
	private List<Paciente> sociasSupervisoras;
	@OneToMany(mappedBy = "sociaResponsavel")
	private List<Paciente> sociasResponsaveis;
	
	@OneToMany(mappedBy = "sociaSupervisora")
	private List<Atendimento> atendimentos;
	@OneToMany(mappedBy = "sociaResponsavel")
	private List<Pacote> pacotes;
	

	// Constructor
	public Socia() {
	}
	public Socia(Long id) {
		this.id = id;
	}
	public Socia(Usuario usuario) {
		super();
		this.usuario = usuario;
	}
	

	// Getters
	public Long getId() {
		return id;
	}
	public Usuario getUsuario() {
		return usuario;
	}
	public List<Paciente> getSociasSupervisoras() {
		return sociasSupervisoras;
	}
	public List<Paciente> getSociasResponsaveis() {
		return sociasResponsaveis;
	}
	public List<Pacote> getPacotes() {
		return pacotes;
	}
	public List<Atendimento> getAtendimentos() {
		return atendimentos;
	}
	// Setters
	
	// String, hashCode and Equals
		@Override
		public String toString() {
			return "Socia [id=" + id + ", usuario[id]=" + usuario.getId() + "]";
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
		Socia other = (Socia) obj;
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
		this.criador = (Usuario)FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("usuarioLogado");
		this.alterador = (Usuario)FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("usuarioLogado");
	}

	// Método Callback para update
	@PreUpdate
	public void quandoAtualizar() {
		this.dataAlteracao = (LocalDateTime.now());
		this.alterador  = (Usuario)FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("usuarioLogado");
	}
	// ------------------------------------------------------------------------------------------------
	public Class<?> getClasse(){
		return this.getClass();
	}
}