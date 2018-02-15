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
@NamedQuery(	name=FormaTransferenciaOperacaoFinanceira.LISTAR, 
			query="select F from FormaTransferenciaOperacaoFinanceira F",
			hints= {
					@QueryHint(name="org.hibernate.cacheable", value="true"),
					@QueryHint(name="org.hibernate.cacheRegion", value=FormaTransferenciaOperacaoFinanceira.LISTAR)
			})
public class FormaTransferenciaOperacaoFinanceira implements Serializable {
	private static final long serialVersionUID = 1L;
	public static final String LISTAR = "FormaTransferenciaOperacaoFinanceira.listar";
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@Column(unique = true)
	private String forma;
	
	@OneToMany(mappedBy="formaTransferencia")
	List<TarifaOperacaoFinanceira> tarifasOperacaoFinanceira  = new ArrayList<>();
	
	// Constructor
	public FormaTransferenciaOperacaoFinanceira() {
		super();
	}
	
	public FormaTransferenciaOperacaoFinanceira(String forma) {
		super();
		this.forma = forma;
	}

	@Override
	public String toString() {
		return "FormaTransferenciaOperacaoFinanceira [id=" + id + ", forma=" + forma + "]";
	}
	// Getters and Setters
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getForma() {
		return forma;
	}
	public void setForma(String forma) {
		this.forma = forma;
	}
	
	public List<TarifaOperacaoFinanceira> getTarifasOperacaoFinanceira() {
		return tarifasOperacaoFinanceira;
	}

	public void setTarifasOperacaoFinanceira(List<TarifaOperacaoFinanceira> tarifasOperacaoFinanceira) {
		this.tarifasOperacaoFinanceira = tarifasOperacaoFinanceira;
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
		FormaTransferenciaOperacaoFinanceira other = (FormaTransferenciaOperacaoFinanceira) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

	
}
