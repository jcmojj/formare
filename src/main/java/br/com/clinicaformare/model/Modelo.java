package br.com.clinicaformare.model;

import java.time.LocalDateTime;

import br.com.clinicaformare.model.usuario.Usuario;

public interface Modelo {
	public Long getId();
	public String toString();
	public int hashCode();
	public boolean equals(Object obj);
//	public void quandoCriar();
//	public void quandoAtualizar();
//	public LocalDateTime getDataCriacao();
//	public LocalDateTime getDataAlteracao();
//	public Usuario getAlterador();
//	public Usuario getCriador();
	public Class<?> getClasse();
//	public String getNome();
//	public void setNome(String nome);
}
