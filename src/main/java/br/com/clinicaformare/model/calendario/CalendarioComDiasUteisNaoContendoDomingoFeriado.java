package br.com.clinicaformare.model.calendario;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class CalendarioComDiasUteisNaoContendoDomingoFeriado  implements Calendario, Serializable {
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@Column(unique = true, nullable = false)
	private LocalDate data;
	private Integer semanaDoMes;
	private Integer diaDaSemana;
	private String nomeFeriado;
	private boolean diaUtil;
	private LocalTime abertura;
	private LocalTime fechamento;

	// Constructor
	public CalendarioComDiasUteisNaoContendoDomingoFeriado() {
	}

	public CalendarioComDiasUteisNaoContendoDomingoFeriado(LocalDate data) {
		this.data = data;
		this.nomeFeriado = "";
		this.diaUtil = true;
		if ((data.getDayOfWeek().getValue() == 7)) {
			this.diaUtil = false;
		}
		if (data.getDayOfWeek().getValue() == 7) { // domingo
			this.diaDaSemana = 1;
		} else {
			this.diaDaSemana = data.getDayOfWeek().getValue() + 1; // segunda sera 2
		}
		this.semanaDoMes = 1;
		for (LocalDate date = data.withDayOfMonth(1); date.isBefore(data.plusDays(1)); date = date.plusDays(1)) {
			if (date.getDayOfWeek().getValue() == 7) {
				this.semanaDoMes++;
			}
		}
		this.abertura = LocalTime.of(8, 0);
		this.fechamento = LocalTime.of(19,0);
	}

	// Getter and Setter
	public Long getId() {
		return id;
	}

	public LocalDate getData() {
		return data;
	}

	public Integer getDiaDaSemana() {
		return diaDaSemana;
	}

	public Integer getSemanaDoMes() {
		return semanaDoMes;
	}

	public void setSemanaDoMes(Integer semanaDoMes) {
		this.semanaDoMes = semanaDoMes;
	}

	public boolean isDiaUtil() {
		return diaUtil;
	}

	public void setDiaUtil(boolean diaUtil) {
		this.diaUtil = diaUtil;
	}

	public String getNomeFeriado() {
		return nomeFeriado;
	}

	public void setNomeFeriado(String nomeFeriado) {
		this.nomeFeriado = nomeFeriado;
	}

	public LocalTime getAbertura() {
		return abertura;
	}

	public void setAbertura(LocalTime abertura) {
		this.abertura = abertura;
	}

	public LocalTime getFechamento() {
		return fechamento;
	}

	public void setFechamento(LocalTime fechamento) {
		this.fechamento = fechamento;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
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
		CalendarioComDiasUteisNaoContendoDomingoFeriado other = (CalendarioComDiasUteisNaoContendoDomingoFeriado) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}


}
