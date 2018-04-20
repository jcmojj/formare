package br.com.clinicaformare.dao.acesso;

import java.util.List;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import br.com.clinicaformare.daos.Dao;
import br.com.clinicaformare.model.acesso.Acesso;
import br.com.clinicaformare.model.acesso.TipoEntidade;
import br.com.clinicaformare.model.acesso.TipoUsuario;
import br.com.clinicaformare.model.usuario.Usuario;

@Stateless
public class AcessoDao extends Dao<Acesso> {
	@Inject
	EntityManager manager;

	public AcessoDao() {
		super(Acesso.class);
	}

	public List<Acesso> listarAcessos() {
		TypedQuery<Acesso> query = manager.createNamedQuery(Acesso.LISTAR, Acesso.class);
		return query.getResultList();
	}

	public Acesso buscaAcessoPadrao(TipoEntidade tipoEntidade, TipoUsuario tipoUsuario) {
		System.out.println("ACESSODAO: buscaAcessoPadrao");
		System.out.println("Procurando Acesso para: " + tipoEntidade + " e " + tipoUsuario);
		String jpql = "select a from Acesso a where a.tipoEntidade = " + ":tipoEntidade and a.tipoUsuario = :tipoUsuario";
		TypedQuery<Acesso> query = manager.createQuery(jpql, Acesso.class);
		query.setParameter("tipoEntidade", tipoEntidade);
		query.setParameter("tipoUsuario", tipoUsuario);
		query.getResultList().forEach(a -> System.out.println("Existe Acesso: " + a));
		return query.getSingleResult();
	}

	public Boolean buscaAcessoInicializarPara(Usuario usuario, String enumString) {
		System.out.println("ACESSODAO: buscaAcessoInicializarPara");
		TipoEntidade tipoEntidade  = this.tipoEntidadeFromString(enumString);
		String jpql = "select (a.inicializar) from Acesso a where a.tipoEntidade = :tipoEntidade " + "and a.usuario = :usuario";
		TypedQuery<Boolean> query = manager.createQuery(jpql, Boolean.class);
		query.setParameter("tipoEntidade", tipoEntidade);
		query.setParameter("usuario", usuario);
		query.getResultList().forEach(r -> System.out.println("Inicializar: " + r));
		return query.getSingleResult();
	}

	public Boolean buscaAcessoListarPara(Usuario usuario, String enumString) {
		System.out.println("ACESSODAO: buscaAcessoListarPara");
		TipoEntidade tipoEntidade  = this.tipoEntidadeFromString(enumString);
		String jpql = "select (a.listar) from Acesso a where a.tipoEntidade = :tipoEntidade " + "and a.usuario = :usuario";
		TypedQuery<Boolean> query = manager.createQuery(jpql, Boolean.class);
		query.setParameter("tipoEntidade", tipoEntidade);
		query.setParameter("usuario", usuario);
		query.getResultList().forEach(r -> System.out.println("Listar: " + r));
		return query.getSingleResult();
	}

	public Boolean buscaAcessoAlterarPara(Usuario usuario, String enumString) {
		System.out.println("ACESSODAO: buscaAcessoAlterarPara");
		TipoEntidade tipoEntidade  = this.tipoEntidadeFromString(enumString);
		String jpql = "select (a.alterar) from Acesso a where a.tipoEntidade = :tipoEntidade " + "and a.usuario = :usuario";
		TypedQuery<Boolean> query = manager.createQuery(jpql, Boolean.class);
		query.setParameter("tipoEntidade", tipoEntidade);
		query.setParameter("usuario", usuario);
		query.getResultList().forEach(r -> System.out.println("Alterar: " + r));
		return query.getSingleResult();
	}

	public Boolean buscaAcessoIncluirPara(Usuario usuario, String enumString) {
		System.out.println("ACESSODAO: buscaAcessoIncluirPara");
		TipoEntidade tipoEntidade  = this.tipoEntidadeFromString(enumString);
		String jpql = "select (a.incluir) from Acesso a where a.tipoEntidade = :tipoEntidade " + "and a.usuario = :usuario";
		TypedQuery<Boolean> query = manager.createQuery(jpql, Boolean.class);
		query.setParameter("tipoEntidade", tipoEntidade);
		query.setParameter("usuario", usuario);
		query.getResultList().forEach(r -> System.out.println("Incluir: " + r));
		return query.getSingleResult();
	}

	public Boolean buscaAcessoDeletarPara(Usuario usuario, String enumString) {
		System.out.println("ACESSODAO: buscaAcessoDeletarPara");
		TipoEntidade tipoEntidade  = this.tipoEntidadeFromString(enumString);
		System.out.println("Procurando Acesso para: " + tipoEntidade + " e " + usuario.getId());
		String jpql = "select (a.deletar) from Acesso a where a.tipoEntidade = :tipoEntidade " + "and a.usuario = :usuario";
		TypedQuery<Boolean> query = manager.createQuery(jpql, Boolean.class);
		query.setParameter("tipoEntidade", tipoEntidade);
		query.setParameter("usuario", usuario);
		query.getResultList().forEach(r -> System.out.println("Deletar: " + r));
		System.out.println("Fim do buscaAcessoDeletarPara");
		return query.getSingleResult();
	}

	private TipoEntidade tipoEntidadeFromString(String enumString) {
		System.out.println("ENUM STRING: " + enumString);
		for (TipoEntidade te : TipoEntidade.values()) {
			if (te.toString().equalsIgnoreCase(enumString.trim().toUpperCase())) {
				System.out.println("ENUM ACHADO: " + te);
				return te;
			}
		}
		System.out.println("ENUM NÃO ACHADO");
		return null;
	}

}
