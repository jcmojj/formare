package br.com.clinicaformare.bean.entity;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import br.com.clinicaformare.daos.usuario.endereco.LogradouroDao;
import br.com.clinicaformare.usuario.endereco.Logradouro;

@Named
@ViewScoped
public class LogradouroBean extends EntityBean<Logradouro> implements Serializable{
	private static final long serialVersionUID = 1L;

	@Inject
	private LogradouroDao logradouroDao;

	// Variáveis
	Logradouro logradouroDelete;
	Logradouro logradouroNovo = new Logradouro();
	List<Logradouro> logradouros;

	@PostConstruct
	public void init() {
		System.out.println("Logradouro Bean - inicio metodo init");
		postContructEntityBean();
		atualizaLista();
		System.out.println("Logradouro Bean - fim metodo init");
	}
	// Constructor
	public LogradouroBean() {
		super(Logradouro.class, "/entity/usuario/endereco/", "logradouro");
	}
	
	// Getters and Setters
	public List<Logradouro> getLogradouros() {
		System.out.println("Logradouro Bean - metodo getLogradouros");
		return logradouros;
	}
	public Logradouro getLogradouroNovo() {
		System.out.println("Logradouro Bean - metodo getLogradouroNovo");
		return logradouroNovo;
	}
	public void setLogradouroNovo(Logradouro logradouroNovo) {
		System.out.println("Logradouro Bean - inicio metodo setLogradouroNovo");
		this.logradouroNovo = logradouroNovo;
		System.out.println("Logradouro Bean - fim metodo setLogradouroNovo");
	}
	// Métodos Criados
	public void atualizaLista() {
		System.out.println("Logradouro Bean - inicio metodo atualizaLista");
		logradouros = logradouroDao.listaTodos();
		System.out.println("Logradouro Bean - fim metodo atualizaLista");
	}
	public void atualizaModelo() {
		System.out.println("Logradouro Bean - metodo atualizaModelo");
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