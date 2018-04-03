package br.com.clinicaformare.util.listeners;

import javax.faces.application.NavigationHandler;
import javax.faces.context.FacesContext;
import javax.faces.event.PhaseEvent;
import javax.faces.event.PhaseId;
import javax.faces.event.PhaseListener;
import javax.inject.Inject;

import br.com.clinicaformare.model.usuario.Usuario;
import br.com.clinicaformare.util.UsuarioLogado;

public class Autorizador implements PhaseListener {
	private static final long serialVersionUID = 1L;
	
	@Inject @UsuarioLogado
	private Usuario usuarioLogado;
	
	@Inject
	private FacesContext context; 
	
	
	@Override
	public void afterPhase(PhaseEvent event) {
		System.out.println("Recuperou a árvore: " + event.getPhaseId());
//		FacesContext context = event.getFacesContext();
		String nomePagina = context.getViewRoot().getViewId();
		System.out.println();
		System.out.println("Autorizador");
		System.out.println("Nome Pagina: " + nomePagina);
		System.out.println("Usuario Logado do Autorizador:" + usuarioLogado);
		System.out.println("Usuario Logado do Original:" + FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("usuarioLogado"));
		
		// se login, liberado
		if("/login.xhtml".equals(nomePagina)) {
			return;
		}
		// se existe usuario, liberado
		if(usuarioLogado != null) {
			return;
		}
		// se nada de antes vai pra login
		NavigationHandler handler = context.getApplication().getNavigationHandler();
		handler.handleNavigation(context, null, "login?faces-redirect-true");
		context.renderResponse();
		
	}

	@Override
	public void beforePhase(PhaseEvent event) {
		
	}

	@Override
	public PhaseId getPhaseId() {
		return PhaseId.RESTORE_VIEW;
	}

}
