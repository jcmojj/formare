package br.com.clinicaformare.bean;

import java.io.Serializable;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.inject.Named;

import org.primefaces.event.FlowEvent;

import br.com.clinicaformare.model.usuario.Usuario;
import br.com.clinicaformare.model.usuario.telefone.Telefone;
import br.com.clinicaformare.usuario.endereco.Endereco;

@Named
@ViewScoped
public class UsuarioWizard implements Serializable{
	private static final long serialVersionUID = 1L;

	 private Usuario usuario = new Usuario();
	 private Endereco endereco = new Endereco();
	 private Telefone telefone = new Telefone();
     
	    private boolean skip;
	    
	    // Getters and Setters
	    public Usuario getUsuario() {
	        return usuario;
	    }
	 
	    public void setUser(Usuario usuario) {
	        this.usuario = usuario;
	    }
	     
	    public Endereco getEndereco() {
			return endereco;
		}

		public void setEndereco(Endereco endereco) {
			this.endereco = endereco;
		}

		public Telefone getTelefone() {
			return telefone;
		}

		public void setTelefone(Telefone telefone) {
			this.telefone = telefone;
		}
		// Salvar
		public void salvar() {        
	        FacesMessage msg = new FacesMessage("Salvo com sucesso!", "Bem-Vindo :" + usuario.getNome());
	        FacesContext.getCurrentInstance().addMessage(null, msg);
	    }
	    // Wizard
	    public boolean isSkip() {
	        return skip;
	    }
	 
	    public void setSkip(boolean skip) {
	        this.skip = skip;
	    }
	     
	    public String onFlowProcess(FlowEvent event) {
	        if(skip) {
	            skip = false;   //reset in case user goes back
	            return "confirm";
	        }
	        else {
	            return event.getNewStep();
	        }
	    }
	
}