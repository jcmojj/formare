package br.com.clinicaformare.bean.entity;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import br.com.clinicaformare.daos.Dao;
import br.com.clinicaformare.usuario.endereco.TipoEndereco;

@Named
@ViewScoped
public class TipoEnderecoBean extends EntityBean<TipoEndereco> implements Serializable{
	private static final long serialVersionUID = 1L;

	@Inject
	private Dao<TipoEndereco> tipoEnderecoDao;

	// Variáveis
	TipoEndereco tipoEnderecoDelete;
	TipoEndereco tipoEnderecoNovo = new TipoEndereco();
	List<TipoEndereco> tipoEnderecos;

	@PostConstruct
	public void init() {
		postContructEntityBean();
		atualizaLista();
	}
	// Constructor
	public TipoEnderecoBean() {
		super(TipoEndereco.class, "/entity/usuario/endereco/", "tipoendereco");
	}
	// Getters and Setters
	public List<TipoEndereco> getTipoEnderecos() {
		return tipoEnderecos;
	}
	public TipoEndereco getTipoEnderecoNovo() {
		return tipoEnderecoNovo;
	}
	public void setTipoEnderecoNovo(TipoEndereco tipoEnderecoNovo) {
		this.tipoEnderecoNovo = tipoEnderecoNovo;
	}
	
	// Métodos Criados
	public void atualizaLista() {
		tipoEnderecos = tipoEnderecoDao.listaTodos();
	}
	
	public void atualizaModelo() {
		super.modeloNovo = tipoEnderecoNovo;
	}

	public TipoEndereco gerar(String linha) {
		return new TipoEndereco(linha.substring(0, linha.length()));
	}
	public void geraNovaEntidade(){
		tipoEnderecoNovo = new TipoEndereco();
		modeloNovo = tipoEnderecoNovo;
	}
}