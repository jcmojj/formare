package br.com.clinicaformare.util;

import br.com.clinicaformare.model.usuario.Usuario;

public class UsuarioLogado {

	private Usuario user;

	// Constructor
	public UsuarioLogado(Usuario usuarioLogado) {
		this.user = usuarioLogado;
	}
	
	// Getters and Setters
	public Usuario getUserLogado() {
		return user;
	}
	
	// String, hashCode and Equals
	@Override
	public String toString() {
		return "UsuarioLogado [usuarioLogado=" + user + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((user == null) ? 0 : user.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		UsuarioLogado other = (UsuarioLogado) obj;
		if (user == null) {
			if (other.user != null)
				return false;
		} else if (!user.equals(other.user))
			return false;
		return true;
	}
	
}
