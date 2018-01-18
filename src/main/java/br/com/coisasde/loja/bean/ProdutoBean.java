package br.com.coisasde.loja.bean;

import javax.enterprise.context.RequestScoped;
import javax.inject.Named;

import br.com.coisasde.loja.model.produto.Produto;

@Named
@RequestScoped
public class ProdutoBean {
	private Produto produto = new Produto();
//	private List<Long> secoesPetId = new ArrayList<>();
//	private Long tipoProdutoPetId = 0L;
//	private List<Long> tiposPetId = new ArrayList<>();
//	private List<Long> tiposRacaoPetId = new ArrayList<>();
//	private Long tipoNovoUsadoId = 0L;
//	private Long tipoAbertoFechadoId = 0L;
//	private List<Long> idadesPetId = new ArrayList<>();
//	private List <Long> portesPetId = new ArrayList<>();
//
//
//
//	@Inject
//	private ProdutoDao produtoDao;
//	@Inject
//	private SecaoPetDao secaoPetDao;
//	@Inject
//	private TipoProdutoPetDao tipoProdutoPetDao;
//	@Inject
//	private TipoPetDao tipoPetDao;
//	@Inject
//	private TipoRacaoPetDao tipoRacaoPetDao;
//	@Inject
//	private NecessitaPreRenderizacaoDeDao necessitaPreRenderizacaoDeDao;
//	@Inject
//	private TipoNovoUsadoDao tipoNovoUsadoDao;
//	@Inject
//	private TipoAbertoFechadoDao tipoAbertoFechadoDao;
//	@Inject
//	private IdadePetDao idadePetDao;
//	@Inject
//	private PortePetDao portePetDao;
//
////	// Ajax
////	private Renderizado renderizado = new Renderizado();
//	
//	// Persistencia
//	@Transactional
//	public void salvar() {
//		for (Long secaoId : secoesPetId) {
//			produto.getSecoesPet().add(new SecaoPet(secaoId));
//		}
//		produto.setTipoProdutoPet(new TipoProdutoPet(tipoProdutoPetId));
//		for (Long tipoPetId : tiposPetId) {
//			produto.getTiposPet().add(new TipoPet(tipoPetId));
//		}
//		for (Long tipoRacaoPetId : tiposRacaoPetId) {
//			produto.getTiposRacaoPet().add(new TipoRacaoPet(tipoRacaoPetId));
//		}
//		produto.setTipoNovoUsado(new TipoNovoUsado(tipoNovoUsadoId));
//		produto.setTipoAbertoFechado(new TipoAbertoFechado(tipoAbertoFechadoId));
//		for (Long idadePetId : idadesPetId) {
//			produto.getIdadesPet().add(new IdadePet(idadePetId));
//		}
//		for (Long portePetId : portesPetId) {
//			produto.getPortesPet().add(new PortePet(portePetId));
//		}
//		produtoDao.adiciona(produto);
//		this.produto = new Produto();
//		this.secoesPetId = new ArrayList<>();
//		this.tipoProdutoPetId = 0L;
//		this.tiposPetId = new ArrayList<>();
//		this.tiposRacaoPetId = new ArrayList<>();
//		this.tipoNovoUsadoId = 0L;
//		this.tipoAbertoFechadoId = 0L;
//		this.idadesPetId = new ArrayList<>();
//		this.portesPetId = new ArrayList<>();
//
//	}
//	// Getters and Setters
//
//	public Produto getProduto() {
//		return produto;
//	}
//
//	public void setProduto(Produto produto) {
//		this.produto = produto;
//	}
//
//	public List<Long> getSecoesPetId() {
//		return secoesPetId;
//	}
//
//	public void setSecoesPetId(List<Long> secoesPetId) {
//		this.secoesPetId = secoesPetId;
//	}
//
//	public Long getTipoProdutoPetId() {
//		return tipoProdutoPetId;
//	}
//
//	public void setTipoProdutoPetId(Long tipoProdutoPetId) {
//		this.tipoProdutoPetId = tipoProdutoPetId;
//	}
//
//	public List<Long> getTiposPetId() {
//		return tiposPetId;
//	}
//
//	public void setTiposPetId(List<Long> tiposPetId) {
//		this.tiposPetId = tiposPetId;
//	}
//
//	public List<Long> getTiposRacaoPetId() {
//		return tiposRacaoPetId;
//	}
//
//	public void setTiposRacaoPetId(List<Long> tiposRacaoPetId) {
//		this.tiposRacaoPetId = tiposRacaoPetId;
//		
//	}
//
//	public Long getTipoNovoUsadoId() {
//		return tipoNovoUsadoId;
//	}
//
//	public void setTipoNovoUsadoId(Long tipoNovoUsadoId) {
//		this.tipoNovoUsadoId = tipoNovoUsadoId;
//	}
//	
//
//	public Long getTipoAbertoFechadoId() {
//		return tipoAbertoFechadoId;
//	}
//	public void setTipoAbertoFechadoId(Long tipoAbertoFechadoId) {
//		this.tipoAbertoFechadoId = tipoAbertoFechadoId;
//	}
//	
//	public List<Long> getIdadesPetId() {
//		return idadesPetId;
//	}
//
//	public void setIdadesPetId(List<Long> idadesPetId) {
//		this.idadesPetId = idadesPetId;
//	}
//
//	public List<Long> getPortesPetId() {
//		return portesPetId;
//	}
//
//	public void setPortesPetId(List<Long> portesPetId) {
//		this.portesPetId = portesPetId;
//	}
//
//	// OUTROS
//	public List<SecaoPet> getSecoesPet() {
//		return secaoPetDao.listaTodos();
//	}
//
//	public List<TipoProdutoPet> getTiposProdutoPet() {
//		return tipoProdutoPetDao.listaTodos();
//	}
//
//	public List<TipoPet> getTiposPet() {
//		return tipoPetDao.listaTodos();
//	}
//
//	public List<TipoRacaoPet> getTiposRacaoPet() {
//		return tipoRacaoPetDao.listaTodos();
//	}
//	public List<TipoNovoUsado> getTiposNovoUsado(){
//		return tipoNovoUsadoDao.listaTodos();
//	}
//	public List<TipoAbertoFechado> getTiposAbertoFechado(){
//		return tipoAbertoFechadoDao.listaTodos();
//	}
//	public List<IdadePet> getIdadesPet(){
//		return idadePetDao.listaTodos();
//	}
//	public List<PortePet> getPortesPet(){
//		return portePetDao.listaTodos();
//	}
//	// Ajax
//	public boolean getTipoRacaoRenderizado() {
//		if(tipoProdutoPetId == 0) {
//			return false;
//		}
//		NecessitaPreRenderizacaoDe necessitaPreRenderizacaoDe = necessitaPreRenderizacaoDeDao.buscaPorId(tipoProdutoPetId);
//		return necessitaPreRenderizacaoDe.getTipoRacaoPet();
//	}
}