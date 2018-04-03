package br.com.clinicaformare.bean;

import java.io.Serializable;

import javax.annotation.PostConstruct;
import javax.enterprise.context.SessionScoped;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;

import br.com.clinicaformare.daos.usuario.UsuarioDao;
import br.com.clinicaformare.model.usuario.Usuario;

@SessionScoped
@Named
public class LoginBean implements Serializable{
	private static final long serialVersionUID = 1L;
	
	@Inject
	private Usuario usuario;// = new Usuario();
	@Inject
	private UsuarioDao usuarioDao;
	private boolean logado = false;

	public String logar() {
		if(usuarioDao.existe(usuario)) {
			usuario = usuarioDao.buscaLoginPassword(usuario);
			System.out.println("Login Valido:"+ usuario.getNome()+" "+usuario.getSobrenome());
			this.logado = true;
			FacesContext context = FacesContext.getCurrentInstance();
			context.getExternalContext().getSessionMap().put("usuarioLogado", this.usuario);
		}else{
			this.logado = false;
		}
		return "startserver?faces-redirect-true";
	}
	
	public void deslogar() {
		FacesContext context = FacesContext.getCurrentInstance();
		context.getExternalContext().getSessionMap().clear();
		this.logado = false;
	}
	
	public Usuario getUsuario() {
		return usuario;
	}

	public boolean isLogado() {
		return logado;
	}
	
	@PostConstruct
	public void criarUsuarioRaiz() {
		if(usuarioDao.buscaPorId(1L) == null) {
			 Usuario usuario = new Usuario();
			 usuario.setNome("José Carlos");
			 usuario.setSobrenome("Melo de Oliveira Júnior");
			 usuario.setCpf("331.881.858-55");
			 usuario.setRg("30.028.659-4");
			 usuario.setProfissao("Developer");
			 usuario.setEmail("jcmojj@gmail.com");
			 usuario.setPassword("123");
			 usuario = usuarioDao.adicionaVolta(usuario);
			System.out.println("Usuario Criado:" + usuario);
//			return new UsuarioLogado(usuario);
		}
	}

}
