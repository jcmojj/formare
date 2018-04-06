package br.com.clinicaformare.usuario.endereco;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.ArrayList;
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
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import br.com.clinicaformare.model.usuario.Usuario;

@Entity
@Table (uniqueConstraints= {@UniqueConstraint(columnNames = {"cidade","estado","pais"})})
public class Paesci implements Serializable{
	private static final long serialVersionUID = 1L;
	@Id
	
	// Parâmetros Próprios
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	private String cidade;
	private String estado;
	private String pais;
	
	// Parâmetros Derivados
	@OneToMany(mappedBy="paesci")
	private List<Endereco> endereco  = new ArrayList<>();
	@OneToMany(mappedBy="localNascimento")
	private List<Usuario> usuariosLocalNascimento  = new ArrayList<>();
	
	// Constructor
	public Paesci() {
		super();
	}

	public Paesci(String pais, String estado, String cidade) {
		super();
		this.cidade = cidade;
		this.estado = estado;
		this.pais = pais;
	}

	// getters and setters
	public String getCidade() {
		return cidade;
	}

	public void setCidade(String cidade) {
		this.cidade = cidade;
	}

	public String getEstado() {
		return estado;
	}

	public void setEstado(String estado) {
		this.estado = estado;
	}

	public String getPais() {
		return pais;
	}

	public void setPais(String pais) {
		this.pais = pais;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public List<Endereco> getEndereco() {
		return endereco;
	}

	public void setEndereco(List<Endereco> endereco) {
		this.endereco = endereco;
	}

	public List<Usuario> getUsuariosLocalNascimento() {
		return usuariosLocalNascimento;
	}

	public void setUsuariosLocalNascimento(List<Usuario> usuariosLocalNascimento) {
		this.usuariosLocalNascimento = usuariosLocalNascimento;
	}
	
	// String, hashCode and Equals
	@Override
	public String toString() {
		return "Paesci [id=" + id + ", cidade=" + cidade + ", estado=" + estado + ", pais=" + pais + ", endereco=" + endereco + "]";
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
		Paesci other = (Paesci) obj;
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

}
