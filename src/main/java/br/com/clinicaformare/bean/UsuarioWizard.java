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

import br.com.clinicaformare.daos.usuario.EnderecoDao;
import br.com.clinicaformare.daos.usuario.PaesciDao;
import br.com.clinicaformare.daos.usuario.UsuarioDao;
import br.com.clinicaformare.model.usuario.Usuario;
import br.com.clinicaformare.model.usuario.telefone.Telefone;
import br.com.clinicaformare.usuario.endereco.Endereco;

@Named
@ViewScoped
public class UsuarioWizard implements Serializable {
	private static final long serialVersionUID = 1L;

	@Inject
	UsuarioDao usuarioDao;

	private Usuario usuario = new Usuario();
	private Endereco endereco = new Endereco();
	private Telefone telefone = new Telefone();

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

	public Telefone getTelefone() {
		return telefone;
	}

	public void setTelefone(Telefone telefone) {
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

	// @Transactional
	// @PostConstruct
	// public void init() {
	// paisesHash = new LinkedHashMap<Long, String>();
	// List<String> paisesString = paesciDao.paises();
	// Long counter = 1L;
	// for (String pais : paisesString) {
	// paisesHash.put(counter, pais);
	// counter++;
	// }
	//
	// }

	// @Transactional
	// public List<String> paises() {
	// System.out.println(paesciDao.paises());
	// return paesciDao.paises();
	// }
	// @Transactional
	// public List<String> estados() {
	// System.out.println("Transa" + paesciDao.estados(this.pais));
	// return paesciDao.estados(this.pais);
	// }
	//
	// @Transactional
	// public List<String> cidades() {
	// System.out.println(paesciDao.cidades(this.pais, this.estado));
	// return paesciDao.cidades(this.pais, this.estado);
	// }
	
	// NASCIMENTO
	// País
	@Inject
	PaesciDao paesciDao;
	
	private String pais;
	private Long paisLong = 0L;
	
	public String getPais() {
		return pais;
	}

	public void setPais(String pais) {
		this.pais = pais;
	}

	public Long getPaisLong() {
		return paisLong;
	}

	public void setPaisLong(Long paisLong) {
		this.estado = null;
		this.cidade = null;
		this.paisLong = paisLong;
		pais = paisesHash.get(paisLong);
		System.out.println("pais" + pais);
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
	private String estado;
	private Long estadoLong = 0L;
		
	public String getEstado() {
		return estado;
	}

	public void setEstado(String estado) {
		this.estado = estado;
	}

	public Long getEstadoLong() {
		return estadoLong;
	}

	public void setEstadoLong(Long estadoLong) {
		System.out.println("TESTE12-");
		this.cidade = null;
		this.estadoLong = estadoLong;
		System.out.println("TESTE13-" + estado);
		estado = estadosHash.get(estadoLong);
		System.out.println("TESTE14-" + estado);
		System.out.println("estado" + estado);
	}
	
	// Generated by Map
	Map<Long, String> estadosHash = new LinkedHashMap<Long, String>();
	
	public Map<Long, String> getEstadosHash() {
		estadosHash = new LinkedHashMap<Long, String>();
		List<String> estadosString = paesciDao.estados(pais);
		Long counter = 1L;
		for (String estado : estadosString) {
			estadosHash.put(counter, estado);
			counter++;
		}
		return estadosHash;
	}
	
	
	// Cidade
	private String cidade;
	private Long cidadeLong = 0L;
	
	public String getCidade() {
		return cidade;
	}

	public void setCidade(String cidade) {
		this.cidade = cidade;
	}

	public Long getCidadeLong() {
		return cidadeLong;
	}

	public void setCidadeLong(Long cidadeLong) {
		this.cidadeLong = cidadeLong;
		cidade = cidadesHash.get(cidadeLong);
		System.out.println("cidade" + cidade);
	}
	
	// Generated by Map
	Map<Long, String> cidadesHash = new LinkedHashMap<Long, String>();
	
	public Map<Long, String> getCidadesHash() {
		System.out.println("TESTE8");
		cidadesHash = new LinkedHashMap<Long, String>();
		System.out.println("TESTE9");
		System.out.println("pais:"+pais+"-");
		System.out.println("estado:"+estado+"-");
		List<String> cidadesString = paesciDao.cidades(pais,estado);
		System.out.println("TESTE10-");
		Long counter = 1L;
		for (String cidade : cidadesString) {
			cidadesHash.put(counter, cidade);
			counter++;
		}
		System.out.println("TESTE11-");
		return cidadesHash;
	}
	
	// Endereco Residencial
	@Inject
	EnderecoDao enderecoDao;
	
	
	
	

}