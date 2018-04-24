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
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;

import br.com.clinicaformare.model.Modelo;
import br.com.clinicaformare.model.usuario.Usuario;
import br.com.clinicaformare.util.FixOnText;

@Entity
public class Endereco implements Serializable, Modelo {
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	// Parâmetros Próprios
	@ManyToOne(optional = false)
	private TipoEndereco tipoEndereco;
	@ManyToOne(optional = false)
	private Logradouro logradouro;
	@Column(nullable = false)
	private String endereco;
	@Column(nullable = false)
	private String numero;
	private String complemento;
	@Column(nullable = false)
	private String cep;
	private String bairro;
	@ManyToOne(optional = false)
	private Paesci paesci;
	
	// Parâmetros Derivados
	@ManyToMany(mappedBy = "enderecos")
	private List<Usuario> usuarios  = new ArrayList<>();

	// Constructor
	public Endereco() {
		super();
	}

	// Getters and Setters
	public Long getId() {
		return id;
	}
	
	public Logradouro getLogradouro() {
		return logradouro;
	}


	public void setLogradouro(Logradouro logradouro) {
		this.logradouro = logradouro;
	}

	public String getEndereco() {
		return endereco;
	}

	public void setEndereco(String endereco) {
		this.endereco = FixOnText.withAllWordsFirstCharCapitalized(endereco);
	}

	public String getNumero() {
		return numero;
	}

	public void setNumero(String numero) {
		this.numero = numero.trim();
	}

	public String getComplemento() {
		return complemento;
	}

	public void setComplemento(String complemento) {
		this.complemento = FixOnText.withAllWordsFirstCharCapitalized(complemento);
	}

	public String getCep() {
		return cep;
	}

	public void setCep(String cep) {
		this.cep = FixOnText.withOnlyNumbersOnString(cep);
	}

	public String getBairro() {
		return bairro;
	}

	public void setBairro(String bairro) {
		this.bairro = FixOnText.withAllWordsFirstCharCapitalized(bairro);
	}
	
	public Paesci getPaesci() {
		return paesci;
	}
	
	public void setPaesci(Paesci paesci) {
		this.paesci = paesci;
	}
	public TipoEndereco getTipoEndereco() {
		return tipoEndereco;
	}
	
	public void setTipoEndereco(TipoEndereco tipoEndereco) {
		this.tipoEndereco = tipoEndereco;
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
		return "Endereco [id=" + id + ", tipoEndereco=" + tipoEndereco + ", logradouro=" + logradouro + ", endereco=" + endereco + ", numero=" + numero + ", complemento=" + complemento + ", cep="
				+ cep + ", bairro=" + bairro + "]";
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
		Endereco other = (Endereco) obj;
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