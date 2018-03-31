package br.com.clinicaformare.dao.calendario;

import java.lang.reflect.Type;
import java.time.LocalDate;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import br.com.clinicaformare.daos.Dao;
import br.com.clinicaformare.model.calendario.Calendario;

public class CalendarioDao<T> extends Dao<T>{
	
	@Inject
	EntityManager manager;

	public CalendarioDao(Class<T> classe) {
		super(classe);
	}
	// super
	public void adiciona(T t) {
		super.adiciona(t);
	}
	
	public T adicionaVolta(T t) {
		return super.adicionaVolta(t);
	}

	public void remove(T t) {
		super.remove(t);
	}

	public void atualiza(T t) {
		super.atualiza(t);
	}

	public List<T> listaTodos() {
		return super.listaTodos();
	}

	public T buscaPorId(Long id) {
		return super.buscaPorId(id);
	}

	public T buscaSomentId(Long id) {
		return super.buscaPorId(id);
	}

	public int contaTodos() {
		return super.contaTodos();
	}

	public List<T> listaTodosPaginada(int firstResult, int maxResults) {
		return super.listaTodosPaginada(firstResult, maxResults);
	}
	
	public EntityManager getEntityManager() {
		return super.getEntityManager();
	}

	public Class<T> getClasse() {
		return super.getClasse();
	}	
	
	
	// inferior
	
	public Calendario  buscaData(LocalDate data) {
		Type tipo = super.getClasse();
		String classe = tipo.getTypeName();
		String jpql = "select d from " + classe + " d where d.data = :data";
		TypedQuery<Calendario> query = this.manager.createQuery(jpql, Calendario.class);
		query.setParameter("data", data);
		return query.getSingleResult();
	}

	
	public void adiciona(Calendario calendario) {
		this.manager.persist(calendario);
	}
	
	
	public Integer getQdeSemanasMes(Integer ano, Integer mes) {
		Type tipo = super.getClasse();
		String classe = tipo.getTypeName();
		String jpql = "select MAX(d.semanaDoMes) from " + classe + " d where YEAR(d.data) = :year AND MONTH(d.data) = :month";// where d.data = :data";
		TypedQuery<Integer> query = this.manager.createQuery(jpql, Integer.class);
		query.setParameter("year", ano);
		query.setParameter("month", mes);
		return query.getSingleResult();
	}
	
	
	public Integer getTotalDiasUteisAno(Integer ano) {
		Type tipo = super.getClasse();
		String classe = tipo.getTypeName();
		String jpql = "select COUNT(d) from " + classe + " d where YEAR(data) = :year AND d.diaUtil is true" ;// where d.data = :data";
		TypedQuery<Integer> query = this.manager.createQuery(jpql, Integer.class);
		query.setParameter("year", ano);
		return query.getSingleResult();
	}
	
	
	public List<Calendario> diasUteisAno(Integer ano) {
		Type tipo = super.getClasse();
		String classe = tipo.getTypeName();
		String jpql = "select d from " + classe + " d where YEAR(data) = :year AND d.diaUtil is true" ;// where d.data = :data";
		TypedQuery<Calendario> query = this.manager.createQuery(jpql, Calendario.class);
		query.setParameter("year", ano);
		return query.getResultList();
	}
	
	public Calendario getPrimeiroDiaUtilDoAno(Integer ano) {
		List<Calendario> diasUteisAno = diasUteisAno(ano);
		Calendario primeiro = diasUteisAno.stream().min(Comparator.comparing(d -> d.getId())).get();
		return primeiro;
	}
	
	
	public Calendario getUltimoDiaUtilDoAno(Integer ano) {
		List<Calendario> diasUteisAno = diasUteisAno(ano);
		Calendario ultimo = diasUteisAno.stream().max(Comparator.comparing(d -> d.getId())).get();
		return ultimo;
	}
	
	
	public Integer getTotalDiasUteisMes(Integer ano, Integer mes) {
		Type tipo = super.getClasse();
		String classe = tipo.getTypeName();
		String jpql = "select COUNT(d) from " + classe + " d where YEAR(data) = :year AND MONTH(data) = :month d.diaUtil is true";// where d.data = :data";
		TypedQuery<Integer> query = this.manager.createQuery(jpql, Integer.class);
		query.setParameter("year", ano);
		query.setParameter("month", mes);
		return query.getSingleResult();
	}
	
	
	public List<Calendario> getDiasUteisMes(Integer ano, Integer mes) {
		Type tipo = super.getClasse();
		String classe = tipo.getTypeName();
		String jpql = "select d from " + classe + " d where YEAR(data) = :year AND MONTH(data) = :month d.diaUtil is true";// where d.data = :data";
		TypedQuery<Calendario> query = this.manager.createQuery(jpql, Calendario.class);
		query.setParameter("year", ano);
		query.setParameter("month", mes);
		return query.getResultList();
	}
	
	
	public Calendario getPrimeiroDiaUtilDoMes(Integer ano, Integer mes, Integer semana) {
		List<Calendario> diasUteisMes = getDiasUteisMes(ano, mes);
		Calendario primeiro = diasUteisMes.stream().min(Comparator.comparing(d -> d.getId())).get();
		return primeiro;
	}
	
	
	public Calendario getUltimoDiaUtilDoMes(Integer ano, Integer mes, Integer semana) {
		List<Calendario> diasUteisMes = getDiasUteisMes(ano, mes);
		Calendario ultimo = diasUteisMes.stream().max(Comparator.comparing(d -> d.getId())).get();
		return ultimo;
	}
	
	
	public Long getTotalDiasUteisSemanaDoMes(Integer ano, Integer mes, Integer semana) {
		if(semana>getQdeSemanasMes(ano,mes)) {return null;}
		Type tipo = super.getClasse();
		String classe = tipo.getTypeName();
		String jpql = "select COUNT(d) from " + classe + " d where YEAR(d.data) = :year AND MONTH(d.data) = :month AND d.semanaDoMes = :semanaDoMes AND d.diaUtil is true";// where d.data = :data";
		TypedQuery<Long> query = this.manager.createQuery(jpql, Long.class);
		query.setParameter("year", ano);
		query.setParameter("month", mes);
		query.setParameter("semanaDoMes", semana);
		return query.getSingleResult();
	}
	
	
	public List<Calendario> getDiasUteisSemanaDoMes(Integer ano, Integer mes, Integer semana) {
		if(semana>getQdeSemanasMes(ano,mes)) {return null;}
		Type tipo = super.getClasse();
		String classe = tipo.getTypeName();
		String jpql = "select d from " + classe + " d where YEAR(d.data) = :year AND MONTH(d.data) = :month AND d.semanaDoMes = :semanaDoMes AND d.diaUtil is true";// where d.data = :data";
		TypedQuery<Calendario> query = this.manager.createQuery(jpql, Calendario.class);
		query.setParameter("year", ano);
		query.setParameter("month", mes);
		query.setParameter("semanaDoMes", semana);
		return query.getResultList();
	}
	
	
	public Calendario getPrimeiroDiaUtilSemanaDoMes(Integer ano, Integer mes, Integer semana) {
		List<Calendario> diasUteisSemanaDoMes = getDiasUteisSemanaDoMes(ano, mes, semana);
		Calendario primeiro = diasUteisSemanaDoMes.stream().min(Comparator.comparing(d -> d.getId())).get();
		return primeiro;
	}
	
	
	public Calendario getUltimoDiaUtilSemanaDoMes(Integer ano, Integer mes, Integer semana) {
		List<Calendario> diasUteisSemanaDoMes = getDiasUteisSemanaDoMes(ano, mes, semana);
		Calendario ultimo = diasUteisSemanaDoMes.stream().max(Comparator.comparing(d -> d.getId())).get();
		return ultimo;
	}
	
	
	public Calendario getSegundaFeiraDaSemanaDoMes(Integer ano, Integer mes, Integer semana) {
		List<Calendario> diasUteisSemanaDoMes = getDiasUteisSemanaDoMes(ano, mes, semana);
		Optional<Calendario> optionalCalendario = diasUteisSemanaDoMes.stream().filter(d -> d.getDiaDaSemana().equals(2)).findFirst();
		if (optionalCalendario.isPresent()) {
			return optionalCalendario.get();
		}
		return null;
	}
	
	public Calendario getTercaFeiraDaSemanaDoMes(Integer ano, Integer mes, Integer semana) {
		List<Calendario> diasUteisSemanaDoMes = getDiasUteisSemanaDoMes(ano, mes, semana);
		Optional<Calendario> optionalCalendario = diasUteisSemanaDoMes.stream().filter(d -> d.getDiaDaSemana().equals(3)).findFirst();
		if (optionalCalendario.isPresent()) {
			return optionalCalendario.get();
		}
		return null;
	}
	
	public Calendario getQuartaFeiraDaSemanaDoMes(Integer ano, Integer mes, Integer semana) {
		List<Calendario> diasUteisSemanaDoMes = getDiasUteisSemanaDoMes(ano, mes, semana);
		Optional<Calendario> optionalCalendario = diasUteisSemanaDoMes.stream().filter(d -> d.getDiaDaSemana().equals(4)).findFirst();
		if (optionalCalendario.isPresent()) {
			return optionalCalendario.get();
		}
		return null;
	}
	
	public Calendario getQuintaFeiraDaSemanaDoMes(Integer ano, Integer mes, Integer semana) {
		List<Calendario> diasUteisSemanaDoMes = getDiasUteisSemanaDoMes(ano, mes, semana);
		Optional<Calendario> optionalCalendario = diasUteisSemanaDoMes.stream().filter(d -> d.getDiaDaSemana().equals(5)).findFirst();
		if (optionalCalendario.isPresent()) {
			return optionalCalendario.get();
		}
		return null;
	}
	public Calendario getSextaFeiraDaSemanaDoMes(Integer ano, Integer mes, Integer semana) {
		List<Calendario> diasUteisSemanaDoMes = getDiasUteisSemanaDoMes(ano, mes, semana);
		Optional<Calendario> optionalCalendario = diasUteisSemanaDoMes.stream().filter(d -> d.getDiaDaSemana().equals(6)).findFirst();
		if (optionalCalendario.isPresent()) {
			return optionalCalendario.get();
		}
		return null;
	}
	
	public Calendario getSabadoDaSemanaDoMes(Integer ano, Integer mes, Integer semana) {
		List<Calendario> diasUteisSemanaDoMes = getDiasUteisSemanaDoMes(ano, mes, semana);
		Optional<Calendario> optionalCalendario = diasUteisSemanaDoMes.stream().filter(d -> d.getDiaDaSemana().equals(7)).findFirst();
		if (optionalCalendario.isPresent()) {
			return optionalCalendario.get();
		}
		return null;
	}
	
	
}
