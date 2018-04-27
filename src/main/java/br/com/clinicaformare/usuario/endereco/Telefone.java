package br.com.clinicaformare.usuario.endereco;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;

import javax.faces.context.FacesContext;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;

import br.com.clinicaformare.model.Modelo;
import br.com.clinicaformare.model.usuario.Usuario;
import br.com.clinicaformare.util.FixOnText;

@Entity
public class Telefone extends Modelo implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(nullable = false)
	private Long id;
	@Column(nullable = false)
	private String ddd;
	@Column(nullable = false)
	private String numero;
	@ManyToOne(optional = false)
	private TipoTelefone tipoTelefone;
	@ManyToOne(optional = false)
	private CodigoInternacionalTelefonico codigoInternacionalTelefonico;
	@Column(nullable = false)
	private boolean whatsapp;

	// Parâmetros Derivados
	@ManyToMany(mappedBy = "telefones")
	private List<Usuario> usuarios;

	// Constructor
	public Telefone() {
		super();
	}

	public Telefone(String ddd, CodigoInternacionalTelefonico codigoInternacionalTelefonico, String numero, TipoTelefone tipoTelefone, boolean hasWhatsapp) {
		super();
		this.ddd = FixOnText.withOnlyNumbersOnString(ddd);
		this.numero = FixOnText.withOnlyNumbersOnString(numero);
		this.tipoTelefone = tipoTelefone;
		this.codigoInternacionalTelefonico = codigoInternacionalTelefonico;
		this.whatsapp = hasWhatsapp;
	}

	// Getters and Setters
	public Long getId() {
		return id;
	}

	public String getDdd() {
		return ddd;
	}

	public void setDdd(String ddd) {
		this.ddd = FixOnText.withOnlyNumbersOnString(ddd);
	}

	public String getNumero() {
		return numero;
	}

	public void setNumero(String numero) {
		this.numero = FixOnText.withOnlyNumbersOnString(numero);
	}

	public TipoTelefone getTipoTelefone() {
		return tipoTelefone;
	}

	public void setTipoTelefone(TipoTelefone tipoTelefone) {
		this.tipoTelefone = tipoTelefone;
	}

	public CodigoInternacionalTelefonico getCodigoInternacionalTelefonico() {
		return codigoInternacionalTelefonico;
	}

	public void setCodigoInternacionalTelefonico(CodigoInternacionalTelefonico codigoInternacionalTelefonico) {
		this.codigoInternacionalTelefonico = codigoInternacionalTelefonico;
	}
	
	public boolean isWhatsapp() {
		return whatsapp;
	}
	public void setWhatsapp(boolean whatsapp) {
		this.whatsapp = whatsapp;
	}
	
	public List<Usuario> getUsuarios() {
		return usuarios;
	}

	public void setUsuarios(List<Usuario> usuarios) {
		this.usuarios = usuarios;
	}

	// String, hashCode and Equals
	@Override
	public String toString() {
		return "(" + id + ") Telefone " + tipoTelefone + " " + codigoInternacionalTelefonico.getCodigo() + " " + ddd + " " + numero + ((whatsapp == true) ? " com whatsapp":" sem whatsapp");
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

	public Class<?> getClasse() {
		return this.getClass();
	}
}
