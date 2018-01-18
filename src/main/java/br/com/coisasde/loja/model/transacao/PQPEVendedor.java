package br.com.coisasde.loja.model.transacao;

//@Entity
public class PQPEVendedor {
//	@Id
//	@GeneratedValue(strategy = GenerationType.IDENTITY)
//	private Integer id;
//
//	@ManyToOne(cascade = CascadeType.ALL)
//	private Transacao transacao;
//
//	@OneToOne(cascade = CascadeType.ALL)
//	private Produto produto;
//	private Integer quantidade;
//	private BigDecimal precoUnitario;
//
//	@ManyToOne(cascade = CascadeType.ALL)
//	private Usuario vendedor;
//
//	@Temporal(TemporalType.TIMESTAMP)
//	private Calendar dataCriacao;
//
//	@Transient
//	private Usuario comprador;
//
//	@Transient
//	private BigDecimal precoTotal;
//
//	@Override
//	public String toString() {
//		return "PQPEVendedor [id=" + id + ", transacao=" + transacao + ", produto=" + produto + ", quantidade="
//				+ quantidade + ", precoUnitario=" + precoUnitario + ", vendedor=" + vendedor + ", dataCriacao="
//				+ dataCriacao + ", comprador=" + comprador + ", precoTotal=" + precoTotal + "]";
//	}
//
//	public Usuario getComprador() {
//		return this.getTransacao().getComprador();
//	}
//
//	public PQPEVendedor(Transacao transacao, Produto produto, Integer quantidade, BigDecimal precoUnitario,
//			Usuario vendedor) {
//		super();
//		this.transacao = transacao;
//		this.produto = produto;
//		this.quantidade = quantidade;
//		this.precoUnitario = precoUnitario;
//		this.vendedor = vendedor;
//	}
//
//	// Método Callback para persistir
//	public Integer getId() {
//		return id;
//	}
//
//	public void setId(Integer id) {
//		this.id = id;
//	}
//
//	public Transacao getTransacao() {
//		return transacao;
//	}
//
//	public void setTransacao(Transacao transacao) {
//		this.transacao = transacao;
//	}
//
//	public Produto getProduto() {
//		return produto;
//	}
//
//	public void setProduto(Produto produto) {
//		this.produto = produto;
//	}
//
//	public Integer getQuantidade() {
//		return quantidade;
//	}
//
//	public void setQuantidade(Integer quantidade) {
//		this.quantidade = quantidade;
//	}
//
//	public BigDecimal getPrecoUnitario() {
//		return precoUnitario;
//	}
//
//	public void setPrecoUnitario(BigDecimal precoUnitario) {
//		this.precoUnitario = precoUnitario;
//	}
//
//	public Usuario getVendedor() {
//		return vendedor;
//	}
//
//	public void setVendedor(Usuario vendedor) {
//		this.vendedor = vendedor;
//	}
//
//	public BigDecimal getPrecoTotal() {
//		return precoTotal;
//	}
//
//	public void setPrecoTotal(BigDecimal precoTotal) {
//		this.precoTotal = precoTotal;
//	}
//
//	public void setComprador(Usuario comprador) {
//		this.comprador = comprador;
//	}
//
//	private Calendar getDataCriacao() {
//		return dataCriacao;
//	}
//
//	private void setDataCriacao(Calendar dataCriacao) {
//		this.dataCriacao = dataCriacao;
//	}
//
//	@PrePersist
//	public void quandoPersistir() {
//		this.setDataCriacao(Calendar.getInstance());
//	}
}
