package br.com.clinicaformare.usuario.endereco;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;

import javax.faces.context.FacesContext;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;

import br.com.clinicaformare.model.usuario.Usuario;

@Entity
public class Telefone implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	private Integer ddd;
	private String numero;
	@ManyToMany(mappedBy = "telefones")
	private List<Usuario> usuarios;
	@ManyToOne
	private TipoTelefone tipoTelefone;

	// Constructor
	public Telefone() {
		super();
	}

	public Telefone(Integer ddd, String numero, TipoTelefone tipoTelefone) {
		super();
		this.setDdd(ddd);
		this.numero = numero;
		this.tipoTelefone = tipoTelefone;
	}

	// Getters and Setters
	public String getNumero() {
		return numero;
	}

	public void setNumero(String numero) {
		this.numero = numero;
	}

	public TipoTelefone getTipo() {
		return tipoTelefone;
	}

	public void setTipo(TipoTelefone tipoTelefone) {
		this.tipoTelefone = tipoTelefone;
	}

	public Integer getDdd() {
		return ddd;
	}

	public void setDdd(Integer ddd) {
		this.ddd = ddd;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public List<Usuario> getUsuarios() {
		return usuarios;
	}

	public void setUsuarios(List<Usuario> usuarios) {
		this.usuarios = usuarios;
	}

	public TipoTelefone getTipoTelefone() {
		return tipoTelefone;
	}

	public void setTipoTelefone(TipoTelefone tipoTelefone) {
		this.tipoTelefone = tipoTelefone;
	}

	// String, hashCode and Equals
	@Override
	public String toString() {
		return "Telefone [id=" + id + ", ddd=" + ddd + ", numero=" + numero + ", tipoTelefone=" + tipoTelefone + "]";
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
		Telefone other = (Telefone) obj;
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
		this.criador = (Usuario) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("usuarioLogado");
		this.alterador = (Usuario) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("usuarioLogado");
	}

	// Método Callback para update
	@PreUpdate
	public void quandoAtualizar() {
		this.dataAlteracao = (LocalDateTime.now());
		this.alterador = (Usuario) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("usuarioLogado");
	}
	// ------------------------------------------------------------------------------------------------

}
