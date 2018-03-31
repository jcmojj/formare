package br.com.clinicaformare.dao.calendario;

import java.time.LocalDate;
import java.util.List;

import br.com.clinicaformare.model.calendario.Calendario;

public interface CalendarioDao {
	public Calendario buscaData(LocalDate data);
	public void adiciona(Calendario calendario);
	public Integer getQdeSemanasMes(Integer ano, Integer mes);
	public Integer getTotalDiasUteisAno(Integer ano);
	public List<Calendario> diasUteisAno(Integer ano);
	public Calendario getPrimeiroDiaUtilDoAno(Integer ano);
	public Calendario getUltimoDiaUtilDoAno(Integer ano);
	public Integer getTotalDiasUteisMes(Integer ano, Integer mes);
	public List<Calendario> getDiasUteisMes(Integer ano, Integer mes);
	public Calendario getPrimeiroDiaUtilDoMes(Integer ano, Integer mes, Integer semana);
	public Calendario getUltimoDiaUtilDoMes(Integer ano, Integer mes, Integer semana);
	public Long getTotalDiasUteisSemanaDoMes(Integer ano, Integer mes, Integer semana);
	public List<Calendario> getDiasUteisSemanaDoMes(Integer ano, Integer mes, Integer semana);
	public Calendario getPrimeiroDiaUtilSemanaDoMes(Integer ano, Integer mes, Integer semana);
	public Calendario getUltimoDiaUtilSemanaDoMes(Integer ano, Integer mes, Integer semana);
	public Calendario getSegundaFeiraDaSemanaDoMes(Integer ano, Integer mes, Integer semana);
	public Calendario getTercaFeiraDaSemanaDoMes(Integer ano, Integer mes, Integer semana);
	public Calendario getQuartaFeiraDaSemanaDoMes(Integer ano, Integer mes, Integer semana);
	public Calendario getQuintaFeiraDaSemanaDoMes(Integer ano, Integer mes, Integer semana);
	public Calendario getSextaFeiraDaSemanaDoMes(Integer ano, Integer mes, Integer semana);
	public Calendario getSabadoDaSemanaDoMes(Integer ano, Integer mes, Integer semana);
}