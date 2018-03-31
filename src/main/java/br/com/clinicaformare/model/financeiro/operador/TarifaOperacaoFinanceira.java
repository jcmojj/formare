package br.com.clinicaformare.model.financeiro.operador;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;

import javax.persistence.Cacheable;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.QueryHint;

import br.com.clinicaformare.model.financeiro.Pagamento;

@Entity
@Cacheable
@NamedQueries({
@NamedQuery(	name=TarifaOperacaoFinanceira.LISTAR_TARIFAS, 
			query="select tarifa from TarifaOperacaoFinanceira tarifa",
			hints= {
					@QueryHint(name="org.hibernate.cacheable", value="true"),
					@QueryHint(name="org.hibernate.cacheRegion", value=TarifaOperacaoFinanceira.LISTAR_TARIFAS)
			}),
@NamedQuery(	name=TarifaOperacaoFinanceira.SELECIONAR_TARIFA, 
			query="select sum(tarifa.valor) from TarifaOperacaoFinanceira tarifa where " + "tarifa.operadorFinanceiro = :operadorFinanceiro and "
					+ "tarifa.formaTransferencia = :formaTransferencia and " + "tarifa.tipoContaOrigem = :tipoContaOrigem and " + "tarifa.tipoContaDestino = :tipoContaDestino and "
					+ "tarifa.tipoTarifa = :tipoTarifa and " + "tarifa.coletorTarifa = :coletorTarifa",
			hints= {
					@QueryHint(name="org.hibernate.cacheable", value="true"),
					@QueryHint(name="org.hibernate.cacheRegion", value=TarifaOperacaoFinanceira.SELECIONAR_TARIFA)
			}),
@NamedQuery(	name=TarifaOperacaoFinanceira.SELECIONAR_TARIFA_BYCOLETOR, 
query="select sum(tarifa.valor) from TarifaOperacaoFinanceira tarifa where " + "tarifa.operadorFinanceiro = :operadorFinanceiro and "
		+ "tarifa.formaTransferencia = :formaTransferencia and " + "tarifa.tipoContaOrigem = :tipoContaOrigem and " + "tarifa.tipoContaDestino = :tipoContaDestino and "
		+ "tarifa.tipoTarifa = :tipoTarifa",
hints= {
		@QueryHint(name="org.hibernate.cacheable", value="true"),
		@QueryHint(name="org.hibernate.cacheRegion", value=TarifaOperacaoFinanceira.SELECIONAR_TARIFA_BYCOLETOR)
})
})

public class TarifaOperacaoFinanceira implements Serializable {
	private static final long serialVersionUID = 2L;
	public static final String LISTAR_TARIFAS = "TarifaOperacaoFinanceira.listarTarifas";
	public static final String SELECIONAR_TARIFA = "TarifaOperacaoFinanceira.selecionarTarifa";
	public static final String SELECIONAR_TARIFA_BYCOLETOR = "TarifaOperacaoFinanceira.selecionarTarifaByColetor";
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@ManyToOne
	private OperadorFinanceiro operadorFinanceiro;
	@ManyToOne
	private FormaTransferenciaOperacaoFinanceira formaTransferencia;
	@ManyToOne
	private TipoContaOperacaoFinanceira tipoContaOrigem;
	@ManyToOne
	private TipoContaOperacaoFinanceira tipoContaDestino;
	@ManyToOne
	private TipoTarifaOperacaoFinanceira tipoTarifa;
	@ManyToOne
	private ColetorTarifaOperacaoFinanceira coletorTarifa;
	private BigDecimal valor;
	private BigDecimal fixa;
	private BigDecimal percentual;
	

	@OneToMany(mappedBy = "tarifa")
	private List<Pagamento> pagamentos;

	// Constructors
	public TarifaOperacaoFinanceira() {
		super();
	}
	

	@Override
	public String toString() {
		return "TarifaOperacaoFinanceira [id=" + id + ", operadorFinanceiro=" + operadorFinanceiro + ", formaTransferencia=" + formaTransferencia + ", tipoContaOrigem=" + tipoContaOrigem + ", tipoContaDestino=" + tipoContaDestino
				+ ", tipoTarifa=" + tipoTarifa + ", coletorTarifa=" + coletorTarifa + ", tarifa=" + valor + "]";
	}


	// Getters and Setters
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public OperadorFinanceiro getOperadorFinanceiro() {
		return operadorFinanceiro;
	}

	public void setOperadorFinanceiro(OperadorFinanceiro operador) {
		this.operadorFinanceiro = operador;
	}

	public FormaTransferenciaOperacaoFinanceira getFormaTransferencia() {
		return formaTransferencia;
	}

	public void setFormaTransferencia(FormaTransferenciaOperacaoFinanceira forma) {
		this.formaTransferencia = forma;
	}

	public TipoContaOperacaoFinanceira getTipoContaOrigem() {
		return tipoContaOrigem;
	}

	public void setTipoContaOrigem(TipoContaOperacaoFinanceira tipoContaOrigem) {
		this.tipoContaOrigem = tipoContaOrigem;
	}

	public TipoContaOperacaoFinanceira getTipoContaDestino() {
		return tipoContaDestino;
	}

	public void setTipoContaDestino(TipoContaOperacaoFinanceira tipoContaDestino) {
		this.tipoContaDestino = tipoContaDestino;
	}

	public TipoTarifaOperacaoFinanceira getTipoTarifa() {
		return tipoTarifa;
	}

	public void setTipoTarifa(TipoTarifaOperacaoFinanceira tipoTarifa) {
		this.tipoTarifa = tipoTarifa;
	}

	public ColetorTarifaOperacaoFinanceira getColetorTarifa() {
		return coletorTarifa;
	}

	public void setColetorTarifa(ColetorTarifaOperacaoFinanceira coletorTarifa) {
		this.coletorTarifa = coletorTarifa;
	}

	public BigDecimal getValor() {
		return valor;
	}

	public void setValor(BigDecimal tarifa) {
		this.valor = tarifa;
	}

	public List<Pagamento> getPagamentos() {
		return pagamentos;
	}

	public void setPagamentos(List<Pagamento> pagamentos) {
		this.pagamentos = pagamentos;
	}
	
	public BigDecimal getFixa() {
		return fixa;
	}


	public void setFixa(BigDecimal fixa) {
		this.fixa = fixa;
	}


	public BigDecimal getPercentual() {
		return percentual;
	}


	public void setPercentual(BigDecimal percentual) {
		this.percentual = percentual;
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
		TarifaOperacaoFinanceira other = (TarifaOperacaoFinanceira) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

}
