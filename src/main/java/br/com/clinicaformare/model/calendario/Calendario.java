package br.com.clinicaformare.model.calendario;

import java.time.LocalDate;
import java.time.LocalTime;

public interface Calendario {
	public Long getId();
	public LocalDate getData();
	public Integer getSemanaDoMes();
	public Integer getDiaDaSemana();
	public boolean isDiaUtil();
	public void setDiaUtil(boolean diaUtil);
	public String getNomeFeriado();
	public void setNomeFeriado(String nomeFeriado);
	public LocalTime getAbertura();
	public void setAbertura(LocalTime abertura);
	public LocalTime getFechamento();
	public void setFechamento(LocalTime fechamento);
}
