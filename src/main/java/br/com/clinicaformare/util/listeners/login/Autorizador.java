package br.com.clinicaformare.util.listeners.login;

import java.util.Arrays;
import java.util.List;

import javax.enterprise.event.Observes;
import javax.faces.application.NavigationHandler;
import javax.faces.context.FacesContext;
import javax.faces.event.PhaseEvent;

import br.com.clinicaformare.model.usuario.Usuario;

public class Autorizador {

	public void controleDeAcesso(FacesContext context, @UsuarioLogado Usuario usuarioLogado, @Observes @After @RestoreView PhaseEvent event) {
		System.out.println("Recuperou a árvore: " + event.getPhaseId());
		String nomePagina = context.getViewRoot().getViewId();
		System.out.println();
		System.out.println("Autorizador");
		System.out.println("Nome Pagina: " + nomePagina);
		System.out.println("Usuario Logado do Autorizador:" + usuarioLogado);
		System.out.println("Usuario Logado do Original:" + FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("usuarioLogado"));

		// se login, liberado
		if (paginaAutorizada(nomePagina)) {
			return;
		}
		// se existe usuario, liberado
		if (usuarioLogado != null) {
			return;
		}
		// se nada de antes vai pra login
		NavigationHandler handler = context.getApplication().getNavigationHandler();
		handler.handleNavigation(context, null, "home?faces-redirect-true");
		context.renderResponse();
	}

	public boolean paginaAutorizada(String nomePagina) {
		String nome1 = "/home.xhtml";
		String nome2 = "/usuariowizard.xhtml";
		String nome3 = "/recuperarusuario.xhtml";
		String nome4 = "/teste.xhtml";
		List<String> paginasAutorizadas = Arrays.asList(nome1, nome2, nome3, nome4);
		if (paginasAutorizadas.stream().anyMatch(nome -> nome.equals(nomePagina))) {
			System.out.println("Página Liberada!");
		}
		return paginasAutorizadas.stream().anyMatch(nome -> nome.equals(nomePagina));
	}

}
