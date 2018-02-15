package br.com.clinicaformare.model.usuario;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import br.com.clinicaformare.model.atendimento.Pacote;
import br.com.clinicaformare.model.financeiro.Pagamento;

@Entity
public class Paciente implements Serializable {
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@OneToOne(mappedBy = "paciente")//, cascade = {CascadeType.ALL, CascadeType.MERGE, CascadeType.PERSIST})
	Usuario usuario;
	@Temporal(TemporalType.TIMESTAMP)
	private Calendar dataCriacao;
	@Temporal(TemporalType.TIMESTAMP)
	private Calendar dataAlteracao;

	@OneToMany(mappedBy = "paciente")
	private List<Autorizacao> autorizacoesDoDependente  = new ArrayList<>();
	@OneToMany(mappedBy = "paciente")//, cascade = {CascadeType.ALL, CascadeType.MERGE, CascadeType.PERSIST})
	private List<Pacote> pacotes  = new ArrayList<>();
	
	@OneToMany(mappedBy = "paciente")
	private List<Pagamento> pagamentos  = new ArrayList<>();
	

	@Override
	public String toString() {
		return "Paciente [id=" + id + ", usuario=" + usuario + ", dataCriacao=" + dataCriacao + ", dataAlteracao=" + dataAlteracao + ", autorizacoesDoDependente=" + autorizacoesDoDependente;
	}

	// Constructor
	public Paciente() {
	}

	public Paciente(Long id) {
		this.id = id;
	}

	public Paciente(Usuario usuario) {
		super();
		this.usuario = usuario;
		usuario.setPaciente(this);
	}

	// Getters and Setters
	public List<Autorizacao> getAutorizacoesDoDependente() {
		return autorizacoesDoDependente;
	}

	public void setAutorizacoesDoDependente(List<Autorizacao> autorizacoesDoDependente) {
		this.autorizacoesDoDependente = autorizacoesDoDependente;
	}

	public List<Pacote> getPacotes() {
		return pacotes;
	}

	public void setPacotes(List<Pacote> pacotes) {
		this.pacotes = pacotes;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public Calendar getDataCriacao() {
		return dataCriacao;
	}

	public void setDataCriacao(Calendar dataCriacao) {
		this.dataCriacao = dataCriacao;
	}

	public Calendar getDataAlteracao() {
		return dataAlteracao;
	}

	public void setDataAlteracao(Calendar dataAlteracao) {
		this.dataAlteracao = dataAlteracao;
	}

	public List<Pagamento> getPagamentos() {
		return pagamentos;
	}

	public void setPagamentos(List<Pagamento> pagamentos) {
		this.pagamentos = pagamentos;
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
		Paciente other = (Paciente) obj;
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
		this.setDataCriacao(Calendar.getInstance());
		this.setDataAlteracao(Calendar.getInstance());
	}

	// Método Callback para update
	@PreUpdate
	public void quandoAtualizar() {
		this.setDataAlteracao(Calendar.getInstance());
	}
}
