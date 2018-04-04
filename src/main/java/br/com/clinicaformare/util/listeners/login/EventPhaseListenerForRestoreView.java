package br.com.clinicaformare.util.listeners.login;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.event.Event;
import javax.faces.event.PhaseEvent;
import javax.faces.event.PhaseId;
import javax.faces.event.PhaseListener;
import javax.inject.Inject;

@ApplicationScoped
public class EventPhaseListenerForRestoreView implements PhaseListener {
	private static final long serialVersionUID = 1L;
	
	@Inject @After @RestoreView
	private Event<PhaseEvent> afterRestoreView;
	
	@Override
	public void afterPhase(PhaseEvent event) {
		afterRestoreView.fire(event);
	}

	@Override
	public void beforePhase(PhaseEvent event) {
		
	}

	@Override
	public PhaseId getPhaseId() {
		return PhaseId.RESTORE_VIEW;
	}

}
