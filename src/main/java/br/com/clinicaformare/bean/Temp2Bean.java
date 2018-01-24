package br.com.clinicaformare.bean;

import javax.enterprise.context.RequestScoped;
import javax.inject.Named;

import org.primefaces.context.RequestContext;

@RequestScoped
@Named
public class Temp2Bean {
	
	public void fechaMenu() {
		System.out.println("ANTES");
//		FacesContext.getCurrentInstance().getClass().
//		RequestContext.getCurrentInstance().execute("closeNav();");
//		System.out.println("DEPOIS");
		
	}
	
	public void abreMenu() {
		RequestContext.getCurrentInstance().execute("openNav();");
	}

}
