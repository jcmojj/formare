package br.com.clinicaformare.daos.usuario.endereco;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import br.com.clinicaformare.daos.Dao;
import br.com.clinicaformare.usuario.endereco.Paesci;

@Stateless
public class PaesciDao extends Dao<Paesci> {

	public PaesciDao() {
		super(Paesci.class);
	}
	
	@Inject
	private EntityManager manager;
	
	public List<String> paises(){
		List<Paesci> paescis = manager.createQuery("from Paesci ", Paesci.class).getResultList(); 
		Set<String> paisesSet = new TreeSet<String>();
		for(Paesci paesci: paescis) {
			paisesSet.add(paesci.getPais());
		}
		List<String> paises = new ArrayList<String>(paisesSet);
		return paises;
	}
	
	public List<String> estados(String pais){
		String jpql = "select p from Paesci p where p.pais = :pais order by p.estado desc";
		TypedQuery<Paesci> query = this.manager.createQuery(jpql,Paesci.class);
		query.setParameter("pais", pais);
		List<Paesci> paescis = query.getResultList(); 
		
		Set<String> estadosSet = new TreeSet<>();
		for(Paesci paesci: paescis) {
			estadosSet.add(paesci.getEstado());
		}
		List<String> estados = new ArrayList<String>(estadosSet);
		return estados;
	}
	
	public List<String> cidades(String pais, String estado){
		String jpql = "select p from Paesci p " + 
						" where p.pais = :pais and p.estado = :estado";
		TypedQuery<Paesci> query = this.manager.createQuery(jpql,Paesci.class);
		query.setParameter("pais", pais);
		query.setParameter("estado", estado);
		List<Paesci> paescis = query.getResultList(); 
		Set<String> cidadesSet = new TreeSet<>();
		for(Paesci paesci: paescis) {
			cidadesSet.add(paesci.getCidade());
		}
		List<String> cidades = new ArrayList<String>(cidadesSet);
		return cidades;
	}
	
	public Long getId(String pais, String estado, String cidade){
		String jpql = "select p from Paesci p " + 
						" where p.pais = :pais and " +
						" p.estado = :estado and " +
						" p.cidade = :cidade ";
		TypedQuery<Paesci> query = this.manager.createQuery(jpql,Paesci.class);
		query.setParameter("pais", pais);
		query.setParameter("estado", estado);
		query.setParameter("cidade", cidade);
		
		Paesci paesci = (Paesci) query.getSingleResult(); 
		return paesci.getId();
	}
	
	
	
}

//createCriteria(Usuario.class).add(Restrictions.eq("email", login)).uniqueResult();
