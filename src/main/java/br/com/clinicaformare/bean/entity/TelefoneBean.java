package br.com.clinicaformare.bean.entity;

import java.io.Serializable;
import java.util.List;

import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import br.com.clinicaformare.daos.Dao;
import br.com.clinicaformare.model.acesso.TipoEntidade;
import br.com.clinicaformare.usuario.endereco.CodigoInternacionalTelefonico;
import br.com.clinicaformare.usuario.endereco.Telefone;
import br.com.clinicaformare.usuario.endereco.TipoTelefone;

@Named
@ViewScoped
public class TelefoneBean extends EntityBean<Telefone> implements Serializable{
	private static final long serialVersionUID = 1L;

	@Inject
	private Dao<Telefone> telefoneDao;
	@Inject
	private Dao<TipoTelefone> tipoTelefoneDao;
	@Inject
	private Dao<CodigoInternacionalTelefonico> codigoInternacionalTelefonicoDao;
//	private CodigoInternacionalTelefonicoDao codigoInternacionalTelefonicoDao;

	// Variáveis
	Telefone telefoneDelete;
	Telefone telefoneNovo = new Telefone();
	List<Telefone> telefones;

	// Constructor
	public TelefoneBean() {
		super(Telefone.class, TipoEntidade.TELEFONE);
	}
	
	// Getters and Setters
	public List<Telefone> getTelefones() {
		return telefones;
	}
	public Telefone getTelefoneNovo() {
		return telefoneNovo;
	}
	public void setTelefoneNovo(Telefone telefoneNovo) {
		this.telefoneNovo = telefoneNovo;
	}
	// Métodos Criados
	public void atualizaLista() {
		telefones = telefoneDao.listaTodos();
	}
	public void atualizaModelo() {
		super.modeloNovo = telefoneNovo;
	}
	
	public Telefone gerar(String linha) {
		Integer i0 = 0;
		Integer i1 = linha.indexOf(";", i0);
		Integer i2 = linha.indexOf(";", i1+1);
		Integer i3 = linha.indexOf(";", i2+1);
		Integer i4 = linha.indexOf(";", i3+1);
		Integer lenght = linha.length();
		TipoTelefone TipoTelefone = tipoTelefoneDao.buscaPorId(Long.parseLong(linha.substring(i0, i1)));
		CodigoInternacionalTelefonico codigoInternacionalTelefonico = codigoInternacionalTelefonicoDao.buscaPorId(Long.parseLong(linha.substring(i1+1, i2)));
		String ddd = linha.substring(i2 + 1, i3);
		String numero = linha.substring(i3 + 1, i4);
		boolean hasWhatsapp;
		if(linha.substring(i4+1, lenght).equals("0")) {
			hasWhatsapp = false; System.out.println("F");
		}else {
			hasWhatsapp = true; System.out.println("T");
		}
		Telefone telefone = new Telefone(ddd, codigoInternacionalTelefonico, numero, TipoTelefone, hasWhatsapp);
		return telefone;
	}
	public void geraNovaEntidade(){
		telefoneNovo = new Telefone();
		modeloNovo = telefoneNovo;
	}
}