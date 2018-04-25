package br.com.clinicaformare.bean.entity;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import br.com.clinicaformare.daos.Dao;
import br.com.clinicaformare.model.acesso.TipoEntidade;
import br.com.clinicaformare.usuario.endereco.CodigoInternacionalTelefonico;

@Named
@ViewScoped
public class CodigoInternacionalTelefonicoBean extends EntityBean<CodigoInternacionalTelefonico> implements Serializable{
	private static final long serialVersionUID = 1L;

	@Inject
	private Dao<CodigoInternacionalTelefonico> codigoInternacionalTelefonicoDao;

	// Variáveis
	CodigoInternacionalTelefonico codigoInternacionalTelefonicoDelete;
	CodigoInternacionalTelefonico codigoInternacionalTelefonicoNovo = new CodigoInternacionalTelefonico();
	List<CodigoInternacionalTelefonico> codigoInternacionalTelefonicos;

	@PostConstruct
	public void init() {
		postContructEntityBean();
		atualizaLista();
	}
	// Constructor
	public CodigoInternacionalTelefonicoBean() {
		super(CodigoInternacionalTelefonico.class, TipoEntidade.CODIGOINTERNACIONALTELEFONICO);
//		super(CodigoInternacionalTelefonico.class, "/entity/usuario/endereco/", "codigointernacionaltelefonico");
	}
	// Getters and Setters
	public List<CodigoInternacionalTelefonico> getCodigoInternacionalTelefonicos() {
		return codigoInternacionalTelefonicos;
	}
	public CodigoInternacionalTelefonico getCodigoInternacionalTelefonicoNovo() {
		return codigoInternacionalTelefonicoNovo;
	}
	public void setCodigoInternacionalTelefonicoNovo(CodigoInternacionalTelefonico codigoInternacionalTelefonicoNovo) {
		this.codigoInternacionalTelefonicoNovo = codigoInternacionalTelefonicoNovo;
	}
	
	// Métodos Criados
	public void atualizaLista() {
		codigoInternacionalTelefonicos = codigoInternacionalTelefonicoDao.listaTodos();
	}
	
	public void atualizaModelo() {
		super.modeloNovo = codigoInternacionalTelefonicoNovo;
	}

	public CodigoInternacionalTelefonico gerar(String linha) {
		System.out.println(linha);
		Integer i0 = 0;
		Integer i1 = linha.indexOf(";", i0);
		Integer i2 = linha.indexOf(";", i1+1);
		Integer lenght = linha.length();
		String codigo = linha.substring(i0, i1); 
		System.out.println(codigo);
		String pais = linha.substring(i1 + 1, i2);
		System.out.println(pais);
		String continente = linha.substring(i2 + 1, lenght);
		System.out.println(continente);
		CodigoInternacionalTelefonico codigoInternacionalTelefonico = new CodigoInternacionalTelefonico(codigo, pais, continente);
		return codigoInternacionalTelefonico;
	}
	public void geraNovaEntidade(){
		codigoInternacionalTelefonicoNovo = new CodigoInternacionalTelefonico();
		modeloNovo = codigoInternacionalTelefonicoNovo;
	}
}