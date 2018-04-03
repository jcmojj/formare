package br.com.clinicaformare.util;

import javax.enterprise.context.RequestScoped;
import javax.enterprise.context.SessionScoped;
import javax.enterprise.inject.Produces;
import javax.faces.context.FacesContext;

import br.com.clinicaformare.model.usuario.Usuario;

public class ClasseProdutora {
	
//	@Inject
//	private UsuarioDao usuarioDao;
	
//	@Inject
//	private FacesContext context;
	
	@Produces
	@RequestScoped
	public FacesContext getFacesContext(){
	    return FacesContext.getCurrentInstance();
	}
	
	@Produces @UsuarioLogado
	public Usuario produzirUsuarioLogado() {
		System.out.println("Criando Usuario Logado na Classe Produtora");
		Usuario usuario = new Usuario();
		if(FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("usuarioLogado") == null) {
			usuario = null;
		}else {
			usuario = (Usuario) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("usuarioLogado"); // define ele no loginBean
		}
		System.out.println("Usuario Logado produzido na classe produtora: " + usuario);
		return usuario;
//		if(usuarioDao.buscaPorId(1L) == null) {
//			Usuario usuario = new Usuario("Jose", "Oliveira", "jcmojj@gmail.com");
//			usuario = usuarioDao.adicionaVolta(usuario);
//			System.out.println("Usuario Criado:" + usuario);
//			return new UsuarioLogado(usuario);
//		}
//		return new UsuarioLogado(usuarioDao.buscaPorId(1L));
	}
	
}
