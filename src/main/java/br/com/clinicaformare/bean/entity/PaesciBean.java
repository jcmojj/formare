package br.com.clinicaformare.bean.entity;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import br.com.clinicaformare.daos.Dao;
import br.com.clinicaformare.model.acesso.TipoEntidade;
import br.com.clinicaformare.usuario.endereco.Paesci;
import br.com.clinicaformare.usuario.endereco.Telefone;

@Named
@ViewScoped
public class PaesciBean extends EntityBean<Paesci> implements Serializable{
	private static final long serialVersionUID = 1L;

	@Inject
	private Dao<Paesci> paesciDao;

	// Variáveis
	Paesci paesciDelete;
	Paesci paesciNovo = new Paesci();
	List<Paesci> paescis;

	@PostConstruct
	public void init() {
		postContructEntityBean();
		atualizaLista();
	}
	// Constructor
	public PaesciBean() {
//		super(Paesci.class, "/entity/usuario/endereco/", "paesci");
		super(Paesci.class, TipoEntidade.PAESCI);
	}
	// Getters and Setters
	public List<Paesci> getPaescis() {
		return paescis;
	}
	public Paesci getPaesciNovo() {
		return paesciNovo;
	}
	public void setPaesciNovo(Paesci paesciNovo) {
		this.paesciNovo = paesciNovo;
	}
	
	// Métodos Criados
	public void atualizaLista() {
		paescis = paesciDao.listaTodos();
	}
	
	public void atualizaModelo() {
		super.modeloNovo = paesciNovo;
	}

	public Paesci gerar(String linha) {
		System.out.println("Linha: " + linha);
		Integer i0 = 0;
		Integer i1 = linha.indexOf("-", i0);
		Integer i2 = linha.indexOf("-", i1+1);
		Integer lenght = linha.length();
		String pais = linha.substring(i0, i1);
		String estado = linha.substring(i1 + 1, i2);
		String cidade = linha.substring(i2 + 1, lenght);
		Paesci paesci = new Paesci(pais, estado, cidade);
		return paesci;
	}
	public void geraNovaEntidade(){
		paesciNovo = new Paesci();
		modeloNovo = paesciNovo;
	}
}