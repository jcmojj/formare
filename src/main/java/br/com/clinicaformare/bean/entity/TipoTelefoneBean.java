package br.com.clinicaformare.bean.entity;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import br.com.clinicaformare.daos.Dao;
import br.com.clinicaformare.usuario.endereco.TipoTelefone;

@Named
@ViewScoped
public class TipoTelefoneBean extends EntityBean<TipoTelefone> implements Serializable{
	private static final long serialVersionUID = 1L;

	@Inject
	private Dao<TipoTelefone> tipoTelefoneDao;

	// Variáveis
	TipoTelefone tipoTelefoneDelete;
	TipoTelefone tipoTelefoneNovo = new TipoTelefone();
	List<TipoTelefone> tipoTelefones;

	@PostConstruct
	public void init() {
		postContructEntityBean();
		atualizaLista();
	}
	// Constructor
	public TipoTelefoneBean() {
		super(TipoTelefone.class, "/entity/usuario/endereco/", "tipotelefone");
	}
	// Getters and Setters
	public List<TipoTelefone> getTipoTelefones() {
		return tipoTelefones;
	}
	public TipoTelefone getTipoTelefoneNovo() {
		return tipoTelefoneNovo;
	}
	public void setTipoTelefoneNovo(TipoTelefone tipoTelefoneNovo) {
		this.tipoTelefoneNovo = tipoTelefoneNovo;
	}
	
	// Métodos Criados
	public void atualizaLista() {
		tipoTelefones = tipoTelefoneDao.listaTodos();
	}
	
	public void atualizaModelo() {
		super.modeloNovo = tipoTelefoneNovo;
	}

	public TipoTelefone gerar(String linha) {
		Integer i0 = 0;
		Integer i1 = linha.indexOf(";", i0);
		Integer lenght = linha.length();
		boolean hasWhatsapp;
		if(linha.substring(i0, i1).equals("0")) {
			hasWhatsapp = false; System.out.println("F");
		}else {
			hasWhatsapp = true; System.out.println("T");
		}
		String tipo = linha.substring(i1 + 1, lenght);
		TipoTelefone tipoTelefone = new TipoTelefone(tipo,hasWhatsapp);
		return tipoTelefone;
	}
	public void geraNovaEntidade(){
		tipoTelefoneNovo = new TipoTelefone();
		modeloNovo = tipoTelefoneNovo;
	}
}