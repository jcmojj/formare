package br.com.clinicaformare.bean.entity;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import br.com.clinicaformare.daos.Dao;
import br.com.clinicaformare.usuario.endereco.Endereco;
import br.com.clinicaformare.usuario.endereco.Logradouro;
import br.com.clinicaformare.usuario.endereco.Paesci;
import br.com.clinicaformare.usuario.endereco.TipoEndereco;

@Named
@ViewScoped
public class EnderecoBean extends EntityBean<Endereco> implements Serializable {
	private static final long serialVersionUID = 1L;

	@Inject
	private Dao<Endereco> enderecoDao;
	@Inject
	private Dao<Logradouro> logradouroDao;
	@Inject
	private Dao<Paesci> paesciDao;
	@Inject
	private Dao<TipoEndereco> tipoEnderecoDao;

	// Variáveis
	Endereco enderecoDelete;
	Endereco enderecoNovo = new Endereco();
	List<Endereco> enderecos;

	@PostConstruct
	public void init() {
		postContructEntityBean();
		atualizaLista();
	}

	// Constructor
	public EnderecoBean() {
		super(Endereco.class, "/entity/usuario/endereco/", "endereco");
	}

	// Getters and Setters
	public List<Endereco> getEnderecos() {
		return enderecos;
	}

	public Endereco getEnderecoNovo() {
		return enderecoNovo;
	}

	public void setEnderecoNovo(Endereco enderecoNovo) {
		this.enderecoNovo = enderecoNovo;
	}

	// Métodos Criados
	public void atualizaLista() {
		enderecos = enderecoDao.listaTodos();
	}

	public void atualizaModelo() {
		super.modeloNovo = enderecoNovo;
	}

	public Endereco gerar(String linha) {
		Integer i0 = 0;
		Integer i1 = linha.indexOf(";", i0);
		Integer i2 = linha.indexOf(";", i1+1);
		Integer i3 = linha.indexOf(";", i2+1);
		Integer i4 = linha.indexOf(";", i3+1);
		Integer i5 = linha.indexOf(";", i4+1);
		Integer i6 = linha.indexOf(";", i5+1);
		Integer lenght = linha.length();
		
		Endereco endereco = new Endereco();
		endereco.setTipoEndereco(tipoEnderecoDao.buscaPorId(Long.parseLong(linha.substring(i0, i1))));
		endereco.setLogradouro(logradouroDao.buscaPorId(Long.parseLong(linha.substring(i1+1, i2))));
		endereco.setEndereco(linha.substring(i2+1, i3));
		endereco.setNumero(linha.substring(i3+1, i4));
		endereco.setComplemento(linha.substring(i4+1, i5));
		endereco.setCep(linha.substring(i4+1, i5));
		endereco.setBairro(linha.substring(i5+1, i6));
		endereco.setPaesci(paesciDao.buscaPorId(Long.parseLong(linha.substring(i6+1, lenght))));
		return endereco;
	}

	public void geraNovaEntidade() {
		enderecoNovo = new Endereco();
		modeloNovo = enderecoNovo;
	}

}