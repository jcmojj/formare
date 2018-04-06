package br.com.clinicaformare.usuario.endereco;

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
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import br.com.clinicaformare.model.usuario.Usuario;

@Entity
@Table(uniqueConstraints=  @UniqueConstraint(columnNames = {"tipo", "whatsapp"}))
public class TipoTelefone implements Serializable {
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	// Parâmetros Próprios
	private String tipo;
	private boolean whatsapp;
	
	// Parâmetros Derivados
	@OneToMany(mappedBy = "tipoTelefone")
	private List<Telefone> telefones;
	
	// Constructor
	public TipoTelefone(String tipo, boolean hasWhatsapp) {
		super();
		this.tipo = tipo;
		this.whatsapp = hasWhatsapp;
	}

	public TipoTelefone() {
		super();
	}
	
	//Getters and Setters
	public Integer getId() {
		return id;
	}
	
	public String getTipo() {
		return tipo;
	}

	public boolean isWhatsapp() {
		return whatsapp;
	}

	public List<Telefone> getTelefones() {
		return telefones;
	}

	// String, hashCode and Equals
	@Override
	public String toString() {
		return "TipoTelefone [id=" + id + ", tipo=" + tipo + ", whatsapp=" + whatsapp + "]";
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
		TipoTelefone other = (TipoTelefone) obj;
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
