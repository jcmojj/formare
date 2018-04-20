package br.com.clinicaformare.usuario.endereco;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import javax.faces.context.FacesContext;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;

import br.com.clinicaformare.model.Modelo;
import br.com.clinicaformare.model.usuario.Alterador;
import br.com.clinicaformare.model.usuario.Criador;
import br.com.clinicaformare.model.usuario.Usuario;
import br.com.clinicaformare.util.ArrumarTexto;

@Entity
public class Logradouro implements Serializable, Modelo{
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	// Parâmetros Próprios
	@Column(unique = true)
	private String nome;
	
	// Parâmetros Derivados
	@OneToMany(mappedBy = "logradouro")
	private List<Endereco> endereco  = new ArrayList<>();

	// Construtor
	public Logradouro() {
		super();
	}

	public Logradouro(String nome) {
		super();
		this.nome = nome;
	}

	// Getters and setters
//	public String getLogradouro() {
//		return nome;
//	}
//
//	public void setLogradouro(String nome) {
//		this.nome = nome.trim();
//	}

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
		
		this.nome = ArrumarTexto.capitalizeString(nome.trim());
	}
	// String, hashCode and Equals
	
	@Override
	public String toString() {
		return "(" + id + ") " + nome + "";
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
		Logradouro other = (Logradouro) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}
	
//	public boolean existe(Object obj) {
//		if (this == obj)
//			return true;
//		if (obj == null)
//			return false;
//		if (getClass() != obj.getClass())
//			return false;
//		Logradouro other = (Logradouro) obj;
//		if (nome == null) {
//			if (other.nome != null)
//				return false;
//		} else if (!nome.equals(other.nome))
//			return false;
//		return true;
//	}

	// -----------------------------------Registro de Alteração-----------------------------------------
	// Parâmetros de Persistência
	private LocalDateTime dataCriacao;
	private LocalDateTime dataAlteracao;
	@ManyToOne
	private Alterador alterador;
	@ManyToOne
	private Criador criador;
	
	// Getters de persistência
	public LocalDateTime getDataCriacao() {
		return dataCriacao;
	}
	public LocalDateTime getDataAlteracao() {
		return dataAlteracao;
	}
	public Alterador getAlterador() {
		System.out.println("Logradouro + getalterador");
		return alterador;
	}
	public Criador getCriador() {
		System.out.println("Logradouro + getCriador");
		return criador;
	}
	
	// Método Callback para persistir
	@PrePersist
	public void quandoCriar() {
		this.dataCriacao = (LocalDateTime.now());
		this.dataAlteracao = (LocalDateTime.now());
		System.out.println("Criador de logradouro");
		this.criador = ((Usuario)FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("usuarioLogado")).getCriadorDesseUsuario();
		System.out.println("Alterador de logradouro");
		this.alterador = ((Usuario)FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("usuarioLogado")).getAlteradorDesseUsuario();
	}

	// Método Callback para update
	@PreUpdate
	public void quandoAtualizar() {
		System.out.println("Logradouro + quandoAtualizar");
		this.dataAlteracao = (LocalDateTime.now());
		this.alterador = ((Usuario)FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("usuarioLogado")).getAlteradorDesseUsuario();
	}
	// ------------------------------------------------------------------------------------------------
	public Class<?> getClasse(){
		return this.getClass();
	}
	public static String capitalizeString(String string) {
		  char[] chars = string.toLowerCase().toCharArray();
		  boolean found = false;
		  for (int i = 0; i < chars.length; i++) {
		    if (!found && Character.isLetter(chars[i])) {
		      chars[i] = Character.toUpperCase(chars[i]);
		      found = true;
		    } else if (Character.isWhitespace(chars[i]) || chars[i]=='.' || chars[i]=='\'') { // You can add other chars here
		      found = false;
		    }
		  }
		  return String.valueOf(chars);
		}
}