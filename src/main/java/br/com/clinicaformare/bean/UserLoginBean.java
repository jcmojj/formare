package br.com.clinicaformare.bean;

import java.io.Serializable;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Named;

import org.primefaces.context.RequestContext;

@Named
@ViewScoped
public class UserLoginBean implements Serializable{
	private static final long serialVersionUID = 1L;

	private String username = "";
     
    private String password = "";
 
    public String getUsername() {
    	System.out.println("getUsername: " + username);
        return username;
    }
 
    public void setUsername(String username) {
    	System.out.println("setUsername: " + username);
        this.username = username;
    }
 
    public String getPassword() {
    	 System.out.println("getPassword: " + password);
        return password;
    }
 
    public void setPassword(String password) {
    	 System.out.println("setPassword: " + password);
        this.password = password;
    }
   
    public void login() {
        FacesMessage message = null;
        boolean loggedIn = false;
        System.out.println("Username: " + username);
        System.out.println("Password: " + password);
         
        if(username != null && username.equals("admin") && password != null && password.equals("admin")) {
            loggedIn = true;
            message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Bem-Vindo", username);
        } else {
            loggedIn = false;
            message = new FacesMessage(FacesMessage.SEVERITY_WARN, "Loggin Error", "Invalid credentials");
        }
         
        FacesContext.getCurrentInstance().addMessage(null, message);
        RequestContext rc = RequestContext.getCurrentInstance();
        rc.addCallbackParam("loggedIn", loggedIn);
    }   
}
