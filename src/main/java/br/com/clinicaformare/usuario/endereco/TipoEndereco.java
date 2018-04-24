package br.com.clinicaformare.usuario.endereco;

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
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;

import br.com.clinicaformare.model.Modelo;
import br.com.clinicaformare.model.usuario.Usuario;
import br.com.clinicaformare.util.FixOnText;

@Entity
public class TipoEndereco implements Serializable, Modelo {
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	// Parâmetros Próprios
	@Column(unique=true, nullable = false)
	private String tipo;
	
	// Parâmetros Derivados
	@OneToMany(mappedBy = "tipoEndereco")
	private List<Endereco> enderecos  = new ArrayList<>();
	
	// Constructor
	public TipoEndereco() {
		super();
	}
//	public TipoEndereco(Long id) {
//		super();
//		this.id = id;
//	}
	public TipoEndereco(String tipo) {
		super();
		this.tipo = FixOnText.withFirstCharOnStringCapitalized(tipo);
	}

	//Getters and Setters
	public String getTipo() {
		return tipo;
	}

	public void setTipo(String tipo) {
		this.tipo = FixOnText.withFirstCharOnStringCapitalized(tipo);
	}

	public List<Endereco> getEnderecos() {
		return enderecos;
	}

	public void setEnderecos(List<Endereco> enderecos) {
		this.enderecos = enderecos;
	}

	public Long getId() {
		return id;
	}

	// String, hashCode and Equals
	@Override
	public String toString() {
		return "TipoEndereco [id=" + id + ", tipo=" + tipo + "]";
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
		TipoEndereco other = (TipoEndereco) obj;
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
		@ManyToOne(fetch = FetchType.LAZY)
		private Usuario alterador;
		@ManyToOne(fetch = FetchType.LAZY)
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

		public Class<?> getClasse(){
			return this.getClass();
		}

}
