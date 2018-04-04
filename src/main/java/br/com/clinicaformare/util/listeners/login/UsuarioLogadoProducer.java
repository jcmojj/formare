package br.com.clinicaformare.util.listeners.login;

import java.io.Serializable;
import java.util.Map;

import javax.enterprise.inject.Produces;
import javax.faces.context.FacesContext;

import br.com.clinicaformare.model.usuario.Usuario;
import br.com.clinicaformare.util.faces.SessionMap;

public class UsuarioLogadoProducer implements Serializable{
	private static final long serialVersionUID = 1L;

	@Produces @UsuarioLogado
	public Usuario produzirUsuarioLogado(FacesContext context, @SessionMap Map<String,Object> sessionMap) {
//	System.out.println("Criando Usuario Logado na Classe Produtora");
	Usuario usuario = new Usuario();
//	if(context.getExternalContext().getSessionMap().get("usuarioLogado") == null) {
//		usuario = null;
//	}else {
//		usuario = (Usuario) context.getExternalContext().getSessionMap().get("usuarioLogado"); // define ele no loginBean
//	}
	if(sessionMap.get("usuarioLogado") == null) {
		usuario = null;
	}else {
		usuario = (Usuario) sessionMap.get("usuarioLogado"); // define ele no loginBean
	}
	System.out.println("Usuario Logado produzido na classe produtora: " + usuario);
	return usuario;
}
}
