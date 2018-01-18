package br.com.coisasde.loja.model.transacao;

//@Entity
public class Transacao {
//	@Id
//	@GeneratedValue(strategy = GenerationType.IDENTITY)
//	private Integer id;
//
//
//	@OneToOne(cascade = CascadeType.ALL)
//	private Usuario comprador;
//	@OneToMany(mappedBy = "transacao", cascade = CascadeType.ALL) 
//	private List<PQPEVendedor> produtoEVendedor;
////	@OneToOne(cascade = CascadeType.ALL)
////	private Pagamento pagamento;
//	@Temporal(TemporalType.TIMESTAMP)
//	private Calendar dataCriacao;
////	@OneToMany
////	private List<Entrega> entregas;
//
////	@Override
////	public String toString() {
////		return "Transacao [id=" + id + ", preco=" + preco + ", quantidade=" + quantidade + ", comprador=" + comprador
////				+ ", dataCriacao=" + dataCriacao + ", dataAlteracao=" + dataAlteracao + "]";
////	}
//
////	public Transacao(BigDecimal preco, Integer quantidade, Usuario comprador,
////			br.com.coisasde.loja.model.transacao.PQPEVendedor produtoEVendedor, Pagamento pagamento,
////			Calendar dataCriacao, Calendar dataAlteracao) {
////		super();
////		this.preco = preco;
////		this.quantidade = quantidade;
////		this.comprador = comprador;
////		this.produtoEVendedor = produtoEVendedor;
////		this.pagamento = pagamento;
////		this.dataCriacao = dataCriacao;
////		this.dataAlteracao = dataAlteracao;
////	}
//
//	public Integer getId() {
//		return id;
//	}
//
//	public void setId(Integer id) {
//		this.id = id;
//	}
//
////	public BigDecimal getPreco() {
////		return preco;
////	}
////
////	public void setPreco(BigDecimal preco) {
////		this.preco = preco;
////	}
////
////	public Integer getQuantidade() {
////		return quantidade;
////	}
////
////	public void setQuantidade(Integer quantidade) {
////		this.quantidade = quantidade;
////	}
//
//	public Usuario getComprador() {
//		return comprador;
//	}
//
//	public void setComprador(Usuario comprador) {
//		this.comprador = comprador;
//	}
//
////	public PQPEVendedor getProdutoEVendedor() {
////		return produtoEVendedor;
////	}
////
////	public void setProdutoEVendedor(PQPEVendedor produtoEVendedor) {
////		this.produtoEVendedor = produtoEVendedor;
////	}
////
////	public Pagamento getPagamento() {
////		return pagamento;
////	}
////
////	public void setPagamento(Pagamento pagamento) {
////		this.pagamento = pagamento;
////	}
//
//	public Calendar getDataCriacao() {
//		return dataCriacao;
//	}
//
//	public void setDataCriacao(Calendar dataCriacao) {
//		this.dataCriacao = dataCriacao;
//	}
//
////	public Calendar getDataAlteracao() {
////		return dataAlteracao;
////	}
////
////	public void setDataAlteracao(Calendar dataAlteracao) {
////		this.dataAlteracao = dataAlteracao;
////	}
//
////	// Método Callback para persistir
////	@PrePersist
////	public void quandoPersistir() {
////		this.setDataCriacao(Calendar.getInstance());
////		this.setDataAlteracao(Calendar.getInstance());
////	}
//


}