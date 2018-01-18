package br.com.coisasde.loja.bean;

import java.io.Serializable;
import java.util.Calendar;
import java.util.List;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;

import br.com.coisasde.loja.daos.UsuarioDao;
import br.com.coisasde.loja.model.usuario.Usuario;



@Named
@RequestScoped
public class UsuarioBean implements Serializable{
	private static final long serialVersionUID = 1L;

	@Inject
	UsuarioDao usuarioDao;
	
	private Usuario usuario = new Usuario();
	private List<Usuario> usuarios;
	

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public void grava() {
//		System.out.println("Id - GRAVA: " + usuario.getId());
		if(this.usuario.getId() == null){
		usuario.setDataCriacao(Calendar.getInstance());
		usuario.setDataAlteracao(Calendar.getInstance());
		usuarioDao.adiciona(usuario);
		}else{
			System.out.println("Gravando o usuario");
			System.out.println("Id: " + usuario.getId());
//			System.out.println("Apelido: " + usuario.getPortes());
			System.out.println("Email: " + usuario.getEmail());
			System.out.println("Senha: " + usuario.getSenha());
			System.out.println("DataAlteracao: " + usuario.getDataAlteracao().getTime());
			System.out.println("DataCriacao: " + usuario.getDataCriacao().getTime());
			usuario.setDataAlteracao(Calendar.getInstance());
			usuarioDao.atualiza(usuario);
		}
		this.usuarios = usuarioDao.listaTodos();

		limpaFormularioDoJSF();
	}

	public List<Usuario> getUsuarios() {
		if(this.usuarios == null){
			this.usuarios = usuarioDao.listaTodos();
		}
		System.out.println("Listando os usuarios");
		return usuarios;
	}
	
	public void remove(Usuario usuario) {
		System.out.println("Removendo a usuario");
		System.out.println("Id: " + usuario.getId());
		System.out.println("Email: " + usuario.getEmail());
		System.out.println("Senha: " + usuario.getSenha());
		System.out.println("DataAlteracao: " + usuario.getDataAlteracao().getTime());
		System.out.println("DataCriacao: " + usuario.getDataCriacao().getTime());

		usuarioDao.remove(usuario);
		this.usuarios = usuarioDao.listaTodos();
	}

	/**
	 * Esse metodo apenas limpa o formulario da forma com que o JSF espera.
	 * Invoque-o no momento em que precisar do formulario vazio.
	 */
	private void limpaFormularioDoJSF() {
		this.usuario = new Usuario();
	}
}