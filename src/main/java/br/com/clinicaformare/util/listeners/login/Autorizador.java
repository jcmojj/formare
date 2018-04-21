package br.com.clinicaformare.util.listeners.login;

import java.util.ArrayList;
import java.util.List;

import javax.enterprise.event.Observes;
import javax.faces.application.NavigationHandler;
import javax.faces.context.FacesContext;
import javax.faces.event.PhaseEvent;

import br.com.clinicaformare.model.usuario.BasicUser;

public class Autorizador {

//	public void controleDeAcesso(FacesContext context, @UsuarioLogado Usuario usuarioLogado, @Observes @After @RestoreView PhaseEvent event) {
	public void controleDeAcesso(FacesContext context, @BasicUserLogado BasicUser basicUser, @Observes @After @RestoreView PhaseEvent event) {
		
		System.out.println("Recuperou a árvore: " + event.getPhaseId());
		String nomePagina = context.getViewRoot().getViewId();
		System.out.println();
		System.out.println("Autorizador");
		System.out.println("Nome Pagina: " + nomePagina);
//		System.out.println("Usuario Logado do Autorizador:" + usuarioLogado);
//		System.out.println("Usuario Logado do Original:" + FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("usuarioLogado"));
		System.out.println("BasicUser Logado do Autorizador:" + basicUser);
		System.out.println("BasicUser Logado do Original:" + FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("basicUser"));

		// se login, liberado
		if (paginaAutorizada(nomePagina)) {
			return;
		}
		// se existe usuario, liberado
		if (basicUser != null) {
			return;
		}
		// se nada de antes vai pra login
		NavigationHandler handler = context.getApplication().getNavigationHandler();
		handler.handleNavigation(context, null, "home?faces-redirect=true");
		context.renderResponse();
	}

	public boolean paginaAutorizada(String nomePagina) {
		List<String> paginasAutorizadas = new ArrayList<>();
		paginasAutorizadas.add("/home.xhtml");
		paginasAutorizadas.add("/usuariowizard.xhtml");
		paginasAutorizadas.add("/recuperarusuario.xhtml");
		paginasAutorizadas.add("/teste.xhtml");
		paginasAutorizadas.add("/login.xhtml");
		if (paginasAutorizadas.stream().anyMatch(nome -> nome.equals(nomePagina))) {
			System.out.println("Página Liberada!");
		}
		return paginasAutorizadas.stream().anyMatch(nome -> nome.equals(nomePagina));
	}

}
