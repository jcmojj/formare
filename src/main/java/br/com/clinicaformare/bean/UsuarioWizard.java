package br.com.clinicaformare.bean;

import java.io.Serializable;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import javax.transaction.Transactional;

import org.primefaces.event.FlowEvent;

import br.com.clinicaformare.daos.usuario.UsuarioDao;
import br.com.clinicaformare.daos.usuario.endereco.EnderecoDao;
import br.com.clinicaformare.daos.usuario.endereco.LogradouroDao;
import br.com.clinicaformare.daos.usuario.endereco.PaesciDao;
import br.com.clinicaformare.daos.usuario.endereco.TipoEnderecoDao;
import br.com.clinicaformare.model.usuario.Usuario;
import br.com.clinicaformare.usuario.endereco.Endereco;
import br.com.clinicaformare.usuario.endereco.Logradouro;
import br.com.clinicaformare.usuario.endereco.Acesso;
import br.com.clinicaformare.usuario.endereco.TipoEndereco;

@Named
@ViewScoped
public class UsuarioWizard implements Serializable {
	private static final long serialVersionUID = 1L;

	@Inject
	UsuarioDao usuarioDao;

	private Usuario usuario = new Usuario();
	private Endereco endereco = new Endereco();
	private Acesso telefone = new Acesso();

	private boolean skip;

	// Getters and Setters
	public Usuario getUsuario() {
		return usuario;
	}

	public void setUser(Usuario usuario) {
		this.usuario = usuario;
	}

	public Endereco getEndereco() {
		return endereco;
	}

	public void setEndereco(Endereco endereco) {
		this.endereco = endereco;
	}

	public Acesso getTelefone() {
		return telefone;
	}

	public void setTelefone(Acesso telefone) {
		this.telefone = telefone;
	}

	// Salvar
	@Transactional
	public void salvar() {
		FacesMessage msg = new FacesMessage("Salvo com sucesso!", "Bem-Vindo :" + usuario.getNome());
		FacesContext.getCurrentInstance().addMessage(null, msg);
	}

	// Wizard
	public boolean isSkip() {
		return skip;
	}

	public void setSkip(boolean skip) {
		this.skip = skip;
	}

	public String onFlowProcess(FlowEvent event) {
		if (skip) {
			skip = false; // reset in case user goes back
			return "confirm";
		} else {
			return event.getNewStep();
		}
	}

	// País
	@Inject
	PaesciDao paesciDao;

	private String paisNascimento;
	private Long paisNascimentoLong = 0L;

	public String getPaisNascimento() {
		return paisNascimento;
	}

	public void setPaisNascimento(String paisNascimento) {
		this.paisNascimento = paisNascimento;
	}

	public Long getPaisNascimentoLong() {
		return paisNascimentoLong;
	}

	public void setPaisNascimentoLong(Long paisNascimentoLong) {
		this.estadoNascimento = null;
		this.cidadeNascimento = null;
		this.paisNascimentoLong = paisNascimentoLong;
		paisNascimento = paisesHash.get(paisNascimentoLong);
		System.out.println("pais" + paisNascimento);
	}

	// Generated by Map
	Map<Long, String> paisesHash = new LinkedHashMap<Long, String>();

	public Map<Long, String> getPaisesHash() {
		paisesHash = new LinkedHashMap<Long, String>();
		List<String> paisesString = paesciDao.paises();
		Long counter = 1L;
		for (String pais : paisesString) {
			paisesHash.put(counter, pais);
			counter++;
		}
		return paisesHash;
	}

	// Estado
	private String estadoNascimento;
	private Long estadoNascimentoLong = 0L;

	public String getEstadoNascimento() {
		return estadoNascimento;
	}

	public void setEstadoNascimento(String estadoNascimento) {
		this.estadoNascimento = estadoNascimento;
	}

	public Long getEstadoNascimentoLong() {
		return estadoNascimentoLong;
	}

	public void setEstadoNascimentoLong(Long estadoNascimentoLong) {
		this.cidadeNascimento = null;
		this.estadoNascimentoLong = estadoNascimentoLong;
		estadoNascimento = estadosHash.get(estadoNascimentoLong);
	}

	// Generated by Map
	Map<Long, String> estadosHash = new LinkedHashMap<Long, String>();

	public Map<Long, String> getEstadosHash() {
		estadosHash = new LinkedHashMap<Long, String>();
		List<String> estadosString = paesciDao.estados(paisNascimento);
		Long counter = 1L;
		for (String estado : estadosString) {
			estadosHash.put(counter, estado);
			counter++;
		}
		return estadosHash;
	}

	// Cidade
	private String cidadeNascimento;
	private Long cidadeNascimentoLong = 0L;

	public String getCidadeNascimento() {
		return cidadeNascimento;
	}

	public void setCidadeNascimento(String cidadeNascimento) {
		this.cidadeNascimento = cidadeNascimento;
	}

	public Long getCidadeNascimentoLong() {
		return cidadeNascimentoLong;
	}

	public void setCidadeNascimentoLong(Long cidadeNascimentoLong) {
		this.cidadeNascimentoLong = cidadeNascimentoLong;
		cidadeNascimento = cidadesHash.get(cidadeNascimentoLong);
		System.out.println("cidade" + cidadeNascimento);
	}

	// Generated by Map
	Map<Long, String> cidadesHash = new LinkedHashMap<Long, String>();

	public Map<Long, String> getCidadesHash() {
		cidadesHash = new LinkedHashMap<Long, String>();
		List<String> cidadesString = paesciDao.cidades(paisNascimento, estadoNascimento);
		Long counter = 1L;
		for (String cidade : cidadesString) {
			cidadesHash.put(counter, cidade);
			counter++;
		}
		return cidadesHash;
	}

	// Endereco
	@Inject
	EnderecoDao enderecoDao;
	// @Inject @Entidade(tipo = TipoEntidade.LOGRADOURO)
	@Inject
	LogradouroDao logradouroDao;
	@Inject
	TipoEnderecoDao tipoEnderecoDao;

	public List<Logradouro> getLogradouros() {
		List<Logradouro> logradouros = logradouroDao.listaTodos();
		return logradouros;
	}

	public List<TipoEndereco> getTiposEndereco() {
		List<TipoEndereco> tiposEndereco = tipoEnderecoDao.listaTodos();
		return tiposEndereco;
	}

	// Endereco Residencial
	private Long logradouroResidencialId = 0L;

	private TipoEndereco tipoEnderecoResidencial = tipoEnderecoDao.buscaPorId(1L);
	private Endereco enderecoResidencial = new Endereco();
	// enderecoResidencial
	// .setTipoEndereco(tipoEnderecoResidencial);

	public Long getLogradouroResidencialId() {
		return logradouroResidencialId;
	}

	public void setLogradouroResidencialId(Long logradouroResidencialId) {
		this.logradouroResidencialId = logradouroResidencialId;
	}

	public Endereco getEnderecoResidencial() {
		return enderecoResidencial;
	}

	public void setEnderecoResidencial(Endereco enderecoResidencial) {
		this.enderecoResidencial = enderecoResidencial;
	}

	// Endereco Residencial - Pais
	private String paisResidencial;
	private Long paisResidencialLong = 0L;

	public String getPaisResidencial() {
		return paisResidencial;
	}

	public void setPaisResidencial(String paisResidencial) {
		this.paisResidencial = paisResidencial;
	}

	public Long getPaisResidencialLong() {
		return paisResidencialLong;
	}

	public void setPaisResidencialLong(Long paisResidencialLong) {
		this.estadoResidencial = null;
		this.cidadeResidencial = null;
		this.paisResidencialLong = paisResidencialLong;
		paisResidencial = paisesHash.get(paisResidencialLong);
		System.out.println("pais" + paisResidencial);
	}

	// Endereco Residencial - Estado
	private String estadoResidencial;
	private Long estadoResidencialLong = 0L;

	public String getEstadoResidencial() {
		return estadoResidencial;
	}

	public void setEstadoResidencial(String estadoResidencial) {
		this.estadoResidencial = estadoResidencial;
	}

	public Long getEstadoResidencialLong() {
		return estadoResidencialLong;
	}

	public void setEstadoResidencialLong(Long estadoResidencialLong) {
		this.cidadeResidencial = null;
		this.estadoResidencialLong = estadoResidencialLong;
		estadoResidencial = estadosHash.get(estadoResidencialLong);
	}

	// Endereco Residencial - Cidade
	private String cidadeResidencial;
	private Long cidadeResidencialLong = 0L;

	public String getCidadeResidencial() {
		return cidadeResidencial;
	}

	public void setCidadeResidencial(String cidadeResidencial) {
		this.cidadeResidencial = cidadeResidencial;
	}

	public Long getCidadeResidencialLong() {
		return cidadeResidencialLong;
	}

	public void setCidadeResidencialLong(Long cidadeResidencialLong) {
		this.cidadeResidencialLong = cidadeResidencialLong;
		cidadeResidencial = cidadesHash.get(cidadeResidencialLong);
		System.out.println("cidade" + cidadeResidencial);
	}

	// Endereco Comercial
	private Long logradouroComercialId = 0L;

	private TipoEndereco tipoEnderecoComercial = tipoEnderecoDao.buscaPorId(1L);
	private Endereco enderecoComercial = new Endereco();

	public Long getLogradouroComercialId() {
		return logradouroComercialId;
	}

	public void setLogradouroComercialId(Long logradouroComercialId) {
		this.logradouroComercialId = logradouroComercialId;
	}

	public Endereco getEnderecoComercial() {
		return enderecoComercial;
	}

	public void setEnderecoComercial(Endereco enderecoComercial) {
		this.enderecoComercial = enderecoComercial;
	}

	// Endereco Comercial - Pais
	private String paisComercial;
	private Long paisComercialLong = 0L;

	public String getPaisComercial() {
		return paisComercial;
	}

	public void setPaisComercial(String paisComercial) {
		this.paisComercial = paisComercial;
	}

	public Long getPaisComercialLong() {
		return paisComercialLong;
	}

	public void setPaisComercialLong(Long paisComercialLong) {
		this.estadoComercial = null;
		this.cidadeComercial = null;
		this.paisComercialLong = paisComercialLong;
		paisComercial = paisesHash.get(paisComercialLong);
		System.out.println("pais" + paisComercial);
	}

	// Endereco Comercial - Estado
	private String estadoComercial;
	private Long estadoComercialLong = 0L;

	public String getEstadoComercial() {
		return estadoComercial;
	}

	public void setEstadoComercial(String estadoComercial) {
		this.estadoComercial = estadoComercial;
	}

	public Long getEstadoComercialLong() {
		return estadoComercialLong;
	}

	public void setEstadoComercialLong(Long estadoComercialLong) {
		this.cidadeComercial = null;
		this.estadoComercialLong = estadoComercialLong;
		estadoComercial = estadosHash.get(estadoComercialLong);
	}

	// Endereco Comercial - Cidade
	private String cidadeComercial;
	private Long cidadeComercialLong = 0L;

	public String getCidadeComercial() {
		return cidadeComercial;
	}

	public void setCidadeComercial(String cidadeComercial) {
		this.cidadeComercial = cidadeComercial;
	}

	public Long getCidadeComercialLong() {
		return cidadeComercialLong;
	}

	public void setCidadeComercialLong(Long cidadeComercialLong) {
		this.cidadeComercialLong = cidadeComercialLong;
		cidadeComercial = cidadesHash.get(cidadeComercialLong);
		System.out.println("cidade" + cidadeComercial);
	}

	//

}