package br.com.clinicaformare.dao.calendario;

import javax.ejb.Stateless;

import br.com.clinicaformare.model.calendario.CalendarioComDiasUteisNaoContendoSabadoDomingoFeriado2;

@Stateless
@CalendarioQualifier(calendario = CalendarioDiasUteis.SEMSABADO_SEMDOMINGO_SEMFERIADO2)
public class CalendarioComDiasUteisNaoContendoSabadoDomingoFeriadoDao2 extends CalendarioDao2<CalendarioComDiasUteisNaoContendoSabadoDomingoFeriado2> implements CalendarioDao {

	public CalendarioComDiasUteisNaoContendoSabadoDomingoFeriadoDao2() {
		super(CalendarioComDiasUteisNaoContendoSabadoDomingoFeriado2.class);
	}

}