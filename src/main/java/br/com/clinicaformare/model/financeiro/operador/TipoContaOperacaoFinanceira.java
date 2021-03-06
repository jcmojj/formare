package br.com.clinicaformare.model.financeiro.operador;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Cacheable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.QueryHint;

@Entity
@Cacheable
@NamedQuery(	name=TipoContaOperacaoFinanceira.LISTAR, 
			query="select tc from TipoContaOperacaoFinanceira tc",
			hints= {
					@QueryHint(name="org.hibernate.cacheable", value="true"),
					@QueryHint(name="org.hibernate.cacheRegion", value=OperadorFinanceiro.LISTAR)
			})
public class TipoContaOperacaoFinanceira implements Serializable {
	private static final long serialVersionUID = 1L;
	public static final String LISTAR = "TipoContaOperacaoFinanceira.listar";
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@Column(unique = true)
	private String tipo;
	
	@OneToMany(mappedBy="tipoContaOrigem")
	List<TarifaOperacaoFinanceira> tarifasOperacaoFinanceiraOrigem  = new ArrayList<>();
	
	@OneToMany(mappedBy="tipoContaDestino")
	List<TarifaOperacaoFinanceira> tarifasOperacaoFinanceiraDestino  = new ArrayList<>();
	

	// Constructor
	public TipoContaOperacaoFinanceira() {
		super();
	}
	public TipoContaOperacaoFinanceira(String tipo) {
		super();
		this.tipo = tipo;
	}
	@Override
	public String toString() {
		return "TipoConta [id=" + id + ", tipo=" + tipo + "]";
	}
	// Getters and Setters
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getTipo() {
		return tipo;
	}
	public void setTipo(String tipo) {
		this.tipo = tipo;
	}
	
	public List<TarifaOperacaoFinanceira> getTarifasOperacaoFinanceiraOrigem() {
		return tarifasOperacaoFinanceiraOrigem;
	}
	public void setTarifasOperacaoFinanceiraOrigem(List<TarifaOperacaoFinanceira> tarifasOperacaoFinanceiraOrigem) {
		this.tarifasOperacaoFinanceiraOrigem = tarifasOperacaoFinanceiraOrigem;
	}
	public List<TarifaOperacaoFinanceira> getTarifasOperacaoFinanceiraDestino() {
		return tarifasOperacaoFinanceiraDestino;
	}
	public void setTarifasOperacaoFinanceiraDestino(List<TarifaOperacaoFinanceira> tarifasOperacaoFinanceiraDestino) {
		this.tarifasOperacaoFinanceiraDestino = tarifasOperacaoFinanceiraDestino;
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
		TipoContaOperacaoFinanceira other = (TipoContaOperacaoFinanceira) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}
}
