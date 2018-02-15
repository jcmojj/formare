package br.com.clinicaformare.bean;

import java.io.Serializable;

import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;
import javax.inject.Named;

import br.com.clinicaformare.daos.usuario.UsuarioDao;
import br.com.clinicaformare.model.usuario.Usuario;

@SessionScoped
@Named
public class LoginBean implements Serializable{
	private static final long serialVersionUID = 1L;
	@Inject
	private Usuario usuario;
	@Inject
	private UsuarioDao usuarioDao;
	private boolean isLogado = false;

	public void logar() {
		boolean loginValido = usuarioDao.existe(usuario);
		System.out.println("Login Valido:"+ usuario.getNome()+" "+usuario.getSobrenome());
		this.isLogado = loginValido;
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

}
