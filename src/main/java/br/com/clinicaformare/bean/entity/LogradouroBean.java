package br.com.clinicaformare.bean.entity;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import br.com.clinicaformare.daos.Dao;
import br.com.clinicaformare.usuario.endereco.Logradouro;

@Named
@ViewScoped
public class LogradouroBean extends EntityBean<Logradouro> implements Serializable{
	private static final long serialVersionUID = 1L;

	@Inject
	private Dao<Logradouro> logradouroDao;

	// Variáveis
	Logradouro logradouroDelete;
	Logradouro logradouroNovo = new Logradouro();
	List<Logradouro> logradouros;

	@PostConstruct
	public void init() {
		postContructEntityBean();
		atualizaLista();
	}
	// Constructor
	public LogradouroBean() {
		super(Logradouro.class, "/entity/usuario/endereco/", "logradouro");
	}
	
	// Getters and Setters
	public List<Logradouro> getLogradouros() {
		return logradouros;
	}
	public Logradouro getLogradouroNovo() {
		return logradouroNovo;
	}
	public void setLogradouroNovo(Logradouro logradouroNovo) {
		this.logradouroNovo = logradouroNovo;
	}
	// Métodos Criados
	public void atualizaLista() {
		logradouros = logradouroDao.listaTodos();
	}
	public void atualizaModelo() {
		super.modeloNovo = logradouroNovo;
	}
	
	public Logradouro gerar(String linha) {
		System.out.println("Logradouro Bean - metodo gerar");
		return new Logradouro(linha);
	}

	public void geraNovaEntidade(){
		System.out.println("Logradouro Bean - metodo geraNovaEntidade");
		logradouroNovo = new Logradouro();
		modeloNovo = logradouroNovo;
	}

}