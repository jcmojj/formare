package br.com.clinicaformare.bean;

import java.io.Serializable;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;

import br.com.clinicaformare.daos.usuario.UsuarioDao;
import br.com.clinicaformare.model.usuario.Usuario;
import br.com.clinicaformare.util.faces.SessionMap;

@RequestScoped // --> nao pode ser isso
@Named
public class LoginBean implements Serializable{
	private static final long serialVersionUID = 1L;
	
//	@Inject
//	@SessionScoped
	private Usuario usuario = new Usuario();
	@Inject
	private UsuarioDao usuarioDao;
//	@Inject
//	private FacesContext context;

		@Inject @SessionMap
	private  Map<String,Object> sessionMap;// ---> precisa
	
//	@Inject @RequestParameterMap
//	private Map<String,String> parameterMap;
	
	private boolean logado = false;

	public String logar() {
		System.out.println("IMPRIMIR LOGAR ANTES DO IF");
		System.out.println("usuario:" + usuario);
		System.out.println("usuarioDao.existe(usuario):" + usuarioDao.existe(usuario));
		if(usuarioDao.existe(usuario)) {
			System.out.println("IMPRIMIR LOGAR APÓS O IF");
			usuario = usuarioDao.buscaLoginPassword(usuario);
			System.out.println("Login Valido:"+ usuario.getNome()+" "+usuario.getSobrenome());
			this.logado = true;
//			FacesContext context = FacesContext.getCurrentInstance();
//			context.getExternalContext().getSessionMap().put("usuarioLogado", this.usuario);
			sessionMap.put("usuarioLogado", this.usuario); //--->> precisa
//			System.out.println("Login Valido2:"+ sessionMap.get("usuarioLogado"));
//			System.out.println();
//			parameterMap.put("usuarioLogadoId", ((Long)this.usuario.getId()).toString());
		}else{
			this.logado = false;
		}
		return "startserver?faces-redirect-true";
	}
	
	public void deslogar() {
//		usuario = null;
//		sessionMap.remove("usuarioLogado");
//		parameterMap.remove("usuarioLogadoId");
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
