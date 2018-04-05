package br.com.clinicaformare.util.faces;

import java.io.Serializable;
import java.util.Map;

import javax.enterprise.inject.Produces;
import javax.faces.application.Application;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;

public class FacesProducer implements Serializable{
	private static final long serialVersionUID = 1L;
	
	@Produces
	private FacesContext context = FacesContext.getCurrentInstance();
	
	@Produces
	public ExternalContext externalContext = FacesContext.getCurrentInstance().getExternalContext();
	
	@Produces
	public  Application application 	= FacesContext.getCurrentInstance().getApplication();
	
	// Aonde guardar os objetos na aplicação, seção, request
	@Produces @ApplicationMap
	public Map<String,Object> disponibilizaApplicationMap(ExternalContext ec){
		return ec.getApplicationMap();
	}
	
	@Produces @SessionMap
	public Map<String,Object> disponibilizaSessionMap(ExternalContext ec) {
		return ec.getSessionMap();
	}
	
	@Produces @RequestMap
	public Map<String,Object> disponibilizaRequestMap(ExternalContext ec){
		return ec.getRequestMap();
	}
	
	@Produces @RequestParameterMap
	public Map<String,String> disponibilizaParameterMap(ExternalContext ec){
		return (Map<String, String>) ec.getRequestParameterMap();//casting não está verificado e no livro não tem
	}
}
