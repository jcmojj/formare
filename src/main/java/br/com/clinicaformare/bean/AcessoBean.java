package br.com.clinicaformare.bean;

import java.io.Serializable;

import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;
import javax.inject.Named;

import br.com.clinicaformare.model.usuario.Usuario;
import br.com.clinicaformare.util.listeners.login.UsuarioLogado;

@SessionScoped
@Named
public class AcessoBean implements Serializable{
	private static final long serialVersionUID = 1L;

	@Inject @UsuarioLogado
	Usuario usuarioLogado;
	
	
	
}
