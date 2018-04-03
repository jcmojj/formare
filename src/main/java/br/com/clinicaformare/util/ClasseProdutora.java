package br.com.clinicaformare.util;

import javax.enterprise.inject.Produces;
import javax.faces.context.FacesContext;
import javax.inject.Inject;

import br.com.clinicaformare.daos.usuario.UsuarioDao;
import br.com.clinicaformare.model.usuario.Usuario;

public class ClasseProdutora {
	
	@Inject
	private UsuarioDao usuarioDao;
	
	@Inject
	private FacesContext context;
	
	@Produces
	public FacesContext produzirFacesContext() {
		return FacesContext.getCurrentInstance();
	}
	
	@Produces @UsuarioLogadoQualifier
	public Usuario produzirUsuarioLogado(Usuario usuario) {
		System.out.println("Criando Usuario Logado");
		usuario = 
		
//		if(usuarioDao.buscaPorId(1L) == null) {
//			Usuario usuario = new Usuario("Jose", "Oliveira", "jcmojj@gmail.com");
//			usuario = usuarioDao.adicionaVolta(usuario);
//			System.out.println("Usuario Criado:" + usuario);
//			return new UsuarioLogado(usuario);
//		}
//		return new UsuarioLogado(usuarioDao.buscaPorId(1L));
	}
	
}
