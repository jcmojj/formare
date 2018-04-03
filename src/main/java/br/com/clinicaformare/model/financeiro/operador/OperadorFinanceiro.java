package br.com.clinicaformare.model.financeiro.operador;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Cacheable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.QueryHint;

import br.com.clinicaformare.util.listeners.OperadorFinanceiroListener;

@Entity
@Cacheable
@NamedQueries({
	@NamedQuery(	name=OperadorFinanceiro.LISTAR, 
			query="select o from OperadorFinanceiro o",
			hints= {
					@QueryHint(name="org.hibernate.cacheable", value="true"),
					@QueryHint(name="org.hibernate.cacheRegion", value=OperadorFinanceiro.LISTAR)
			}),
	@NamedQuery(	name=OperadorFinanceiro.PADRAO, 
	query="select op from OperadorFinanceiro op where op.operadorFinanceiroPadrao = true",
	hints= {
			@QueryHint(name="org.hibernate.cacheable", value="true"),
			@QueryHint(name="org.hibernate.cacheRegion", value=OperadorFinanceiro.PADRAO)
	})
})

@EntityListeners(OperadorFinanceiroListener.class)
public class OperadorFinanceiro implements Serializable {
	private static final long serialVersionUID = 2L;
	public static final String LISTAR = "OperadorFinanceiro.listar";
	public static final String PADRAO = "OperadorFinanceiro.padrao";
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@Column(unique = true)
	private String nome;
	@Column(unique = false, nullable = false)
	private boolean operadorFinanceiroPadrao;
	
	
	@OneToMany(mappedBy="operadorFinanceiro")
	List<TarifaOperacaoFinanceira> tarifasOperacaoFinanceira  = new ArrayList<>();
	
	// Constructor
	public OperadorFinanceiro() {
		super();
		this.operadorFinanceiroPadrao = false;
	}

	public OperadorFinanceiro(String nome) {
		super();
		this.nome = nome;
		this.operadorFinanceiroPadrao = false;
	}

	@Override
	public String toString() {
		return "OperadorFinanceiro [id=" + id + ", nome=" + nome + "]";
	}
	
	// Getters and Setters
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
		this.nome = nome;
	}

	public List<TarifaOperacaoFinanceira> getTarifasOperacaoFinanceira() {
		return tarifasOperacaoFinanceira;
	}

	public void setTarifasOperacaoFinanceira(List<TarifaOperacaoFinanceira> tarifasOperacaoFinanceira) {
		this.tarifasOperacaoFinanceira = tarifasOperacaoFinanceira;
	}

	public boolean isOperadorFinanceiroPadrao() {
		return operadorFinanceiroPadrao;
	}

	public void setOperadorFinanceiroPadrao(boolean operadorFinanceiroFormare) {
		this.operadorFinanceiroPadrao = operadorFinanceiroFormare;
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
		OperadorFinanceiro other = (OperadorFinanceiro) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}
}
