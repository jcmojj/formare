package br.com.clinicaformare.model.usuario;

public interface TipoHorista {
	public String getTipo();
	public void setTipo(String nome);
	public Double getValorBrutoHora();
	public void setValorBrutoHora(Double valorBrutoHora);
	public Double getValorLiquidoHora();
	public void setValorLiquidoHora(Double valorLiquidoHora);
	public Double getPorcentagemLiquidoSobreBruto();
	public void setPorcentagemLiquidoSobreBruto(Double porcentagem);
}
