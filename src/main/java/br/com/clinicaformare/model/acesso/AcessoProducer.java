package br.com.clinicaformare.model.acesso;

import java.util.ArrayList;
import java.util.EnumSet;
import java.util.List;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.transaction.Transactional;

import br.com.clinicaformare.dao.acesso.AcessoDao;
import br.com.clinicaformare.daos.usuario.BasicUserDao;
import br.com.clinicaformare.model.usuario.BasicUser;
import br.com.clinicaformare.model.usuario.Usuario;

//@Named
//@RequestScoped
@Stateless
public class AcessoProducer {
	
	@Inject
	AcessoDao acessoDao;
	
	@Inject
	BasicUserDao basicUsarDao;
	
	@Transactional
	public void criarNovoAcessoPara(BasicUser basicUser) {
		Usuario usuario = basicUsarDao.usarioDeBasicUser(basicUser);
		List<Acesso> acessos = new ArrayList<>();
		// Procurar o tipo de TipoUsuario do Usuario e criar um Acesso para cada TipoEntidade
		for(TipoEntidade tipoEntidade:EnumSet.allOf(TipoEntidade.class)) {
			if(usuario.isAdministrador()) acessos.add(acessoDao.buscaAcessoPadrao(tipoEntidade,TipoUsuario.ADMINISTRADOR));
			if(usuario.isFinanceiro()) acessos.add(acessoDao.buscaAcessoPadrao(tipoEntidade,TipoUsuario.FINANCEIRO));
			if(usuario.isSecretaria()) acessos.add(acessoDao.buscaAcessoPadrao(tipoEntidade,TipoUsuario.SECRETARIA));
			if(usuario.isSocia()) acessos.add(acessoDao.buscaAcessoPadrao(tipoEntidade,TipoUsuario.SOCIA));
			if(usuario.isPaciente()) acessos.add(acessoDao.buscaAcessoPadrao(tipoEntidade,TipoUsuario.PACIENTE));
			if(usuario.isResponsavelFinanceiro()) acessos.add(acessoDao.buscaAcessoPadrao(tipoEntidade,TipoUsuario.RESPONSAVELFINANCEIRO));
			if(usuario.isFornecedor()) acessos.add(acessoDao.buscaAcessoPadrao(tipoEntidade,TipoUsuario.FORNECEDOR));
			if(usuario.isProfissional()) acessos.add(acessoDao.buscaAcessoPadrao(tipoEntidade,TipoUsuario.PROFISSIONAL));
			if(usuario.isAutorizado()) acessos.add(acessoDao.buscaAcessoPadrao(tipoEntidade,TipoUsuario.ADMINISTRADOR));
		}
		// Dentro de um TipoEntidade ver todos os acessos de cada tipo e se algum for true, definir tru
		for(TipoEntidade tipoEntidade:EnumSet.allOf(TipoEntidade.class)) {
			Acesso acesso = new Acesso(basicUser);
			acesso.setTipoEntidade(tipoEntidade);
			acesso.setAlterar(acessos.stream().filter(a -> a.getTipoEntidade().equals(tipoEntidade)).anyMatch(aa -> aa.isAlterar()));
			acesso.setDeletar(acessos.stream().filter(a -> a.getTipoEntidade().equals(tipoEntidade)).anyMatch(aa -> aa.isDeletar()));
			acesso.setIncluir(acessos.stream().filter(a -> a.getTipoEntidade().equals(tipoEntidade)).anyMatch(aa -> aa.isIncluir()));
			acesso.setInicializar(acessos.stream().filter(a -> a.getTipoEntidade().equals(tipoEntidade)).anyMatch(aa -> aa.isInicializar()));
			acesso.setListar(acessos.stream().filter(a -> a.getTipoEntidade().equals(tipoEntidade)).anyMatch(aa -> aa.isListar()));
//			acessoDao.adiciona(acesso);
			acessoDao.adicionaVolta(acesso);
		}
		
		
		
	
	}
		
//		
//			for(TipoUsuario tipoUsuario:EnumSet.allOf(TipoUsuario.class)) {
//				acessos.add(acessoDao.buscaAcessoPadrao(tipoEntidade,tipoUsuario);
//			}
//			
//			
//			
//			acesso.setTipoEntidade(tipoEntidade);
//			this.configurarAcessoPadrao(acesso);
//			acessoDao.adiciona(acesso);
//		}		
//		
//		Acesso acesso = new Acesso(tipoUsuario);
//		produzirAcessoTipoUsuarioSemTipoEntidade(tipoUsuario);
//	}
	@Transactional
	public void adicionarAcessoPadrao() {
		for(TipoUsuario tipoUsuario:EnumSet.allOf(TipoUsuario.class)) {
			for(TipoEntidade tipoEntidade:EnumSet.allOf(TipoEntidade.class)) {
				System.out.println("TipoUsuario: "+tipoUsuario + " TipoEntidade: " + tipoEntidade);
				Acesso acesso = new Acesso(tipoUsuario);
				acesso.setTipoEntidade(tipoEntidade);
				this.configurarAcessoPadrao(acesso);
				acessoDao.adicionaVolta(acesso);
//				acessoDao.adiciona(acesso);
			}
		}
	}

	private Acesso configurarAcessoPadrao(Acesso acesso) {
		switch(acesso.getTipoUsuario()) {
		case ADMINISTRADOR:
			acesso.setAlterar(true);
			acesso.setDeletar(true);
			acesso.setIncluir(true);
			acesso.setInicializar(true);
			acesso.setListar(true);
			break;
		case FINANCEIRO:
			acesso.setAlterar(true);
			acesso.setDeletar(false);
			acesso.setIncluir(false);
			acesso.setInicializar(false);
			acesso.setListar(true);
			break;
		case FORNECEDOR:
			acesso.setAlterar(false);
			acesso.setDeletar(false);
			acesso.setIncluir(false);
			acesso.setInicializar(false);
			acesso.setListar(false);
			break;
		case PACIENTE:
			acesso.setAlterar(false);
			acesso.setDeletar(false);
			acesso.setIncluir(false);
			acesso.setInicializar(false);
			acesso.setListar(false);
			break;
		case PROFISSIONAL:
			acesso.setAlterar(false);
			acesso.setDeletar(false);
			acesso.setIncluir(false);
			acesso.setInicializar(false);
			acesso.setListar(true);
			break;
		case RESPONSAVELFINANCEIRO:
			acesso.setAlterar(false);
			acesso.setDeletar(false);
			acesso.setIncluir(false);
			acesso.setInicializar(false);
			acesso.setListar(false);
			break;
		case SECRETARIA:
			acesso.setAlterar(false);
			acesso.setDeletar(false);
			acesso.setIncluir(true);
			acesso.setInicializar(false);
			acesso.setListar(true);
			break;
		case SOCIA:
			acesso.setAlterar(true);
			acesso.setDeletar(false);
			acesso.setIncluir(true);
			acesso.setInicializar(false);
			acesso.setListar(true);
			break;
//		case USUARIO:
//			acesso.setAlterar(true);
//			acesso.setDeletar(true);
//			acesso.setIncluir(true);
//			acesso.setInicializar(true);
//			acesso.setListar(true);
//			break;
		}
		return acesso;
	}
	
	public Acesso produzirAcessoUsuario(BasicUser basicUser) {
		TipoUsuario tipoUsuario = null;
		Acesso acesso = new Acesso(basicUser); // tipoUsuario = null
//		acesso.
		
		
		
		return null;
	}	
}
