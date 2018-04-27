package br.com.clinicaformare.bean.entity;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import br.com.clinicaformare.daos.Dao;
import br.com.clinicaformare.model.acesso.TipoEntidade;
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
	List<TipoTelefone> tiposTelefone;

	@PostConstruct
	public void init() {
		postContructEntityBean();
//		System.out.println("@PostConstruct TipoTelefone");
		atualizaLista();
	}
	// Constructor
	public TipoTelefoneBean() {
		super(TipoTelefone.class, TipoEntidade.TIPOTELEFONE);
	}
	// Getters and Setters
	public List<TipoTelefone> getTiposTelefone() {
//		System.out.println("getTiposTelefone");
//		tiposTelefone.stream().forEach(System.out::println);
		return tiposTelefone;
	}
	public TipoTelefone getTipoTelefoneNovo() {
		return tipoTelefoneNovo;
	}
	public void setTipoTelefoneNovo(TipoTelefone tipoTelefoneNovo) {
		this.tipoTelefoneNovo = tipoTelefoneNovo;
	}
	
	// Métodos Criados
	public void atualizaLista() {
		System.out.println("atualizaLista");
		tiposTelefone = tipoTelefoneDao.listaTodos();
		tiposTelefone.stream().forEach(System.out::println);
	}
	
	public void atualizaModelo() {
		super.modeloNovo = tipoTelefoneNovo;
	}

	public TipoTelefone gerar(String linha) {
		Integer i0 = 0;
//		Integer i1 = linha.indexOf(";", i0);
		Integer lenght = linha.length();
//		boolean hasWhatsapp;
//		if(linha.substring(i0, i1).equals("0")) {
//			hasWhatsapp = false; System.out.println("F");
//		}else {
//			hasWhatsapp = true; System.out.println("T");
//		}
		String tipo = linha.substring(i0, lenght);
		TipoTelefone tipoTelefone = new TipoTelefone(tipo);//,hasWhatsapp);
		return tipoTelefone;
	}
	public void geraNovaEntidade(){
		tipoTelefoneNovo = new TipoTelefone();
		modeloNovo = tipoTelefoneNovo;
	}
}