package br.com.clinicaformare.bean;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import javax.transaction.Transactional;

import org.primefaces.context.RequestContext;
import org.primefaces.event.CellEditEvent;

import br.com.clinicaformare.daos.AtendimentoPadraoDao;
import br.com.clinicaformare.daos.PacoteDao;
import br.com.clinicaformare.daos.usuario.TipoProfissionalDao;
import br.com.clinicaformare.daos.usuario.UsuarioDao;
import br.com.clinicaformare.model.atendimento.AtendimentoPadrao;
import br.com.clinicaformare.model.atendimento.Pacote;
import br.com.clinicaformare.model.usuario.Paciente;
import br.com.clinicaformare.model.usuario.ResponsavelFinanceiro;
import br.com.clinicaformare.model.usuario.TipoProfissional;
import br.com.clinicaformare.model.usuario.Usuario;

@Named
@ViewScoped
public class NovoPacoteBean implements Serializable {
	private static final long serialVersionUID = 1L;

	@Inject
	UsuarioDao usuarioDao;
	@Inject
	PacoteDao pacoteDao;
	@Inject
	AtendimentoPadraoDao atendimentoPadraoDao;
	@Inject
	TipoProfissionalDao tipoProfissionalDao;

	private ResponsavelFinanceiro responsavelFinanceiro = new ResponsavelFinanceiro();
	private Paciente paciente = new Paciente();
	private Long sociaResponsavelId = 0L;
	private Long responsavelFinanceiroId = 0L;
	private Long pacienteId = 0L;
	private Long pacotePadraoId = 0L;
	private List<AtendimentoPadrao> atendimentosPadrao = new ArrayList<>();
	// Material e Calcular Desconto
	private boolean mostraPainelControle = true;
	private boolean mostraDesconto = true;
	private boolean mostraSetup = false;
	private boolean mostraMaterial = false;
	private Double valorMaterial = 0.0;
	private Double valorTotalAlterandoDesconto = 0.0;
	private Double valorDeTodosDescontos = 0.0;
	//Especialidade
	private Long tipoProfissionalId = 0L;
	private Integer qtdeAtendimento = 0;
	private List<AtendimentoPadrao> atendimentosEspecialidade = new ArrayList<>();
	
	
	// Getters and Setters

	public Long getSociaResponsavelId() {
		return sociaResponsavelId;
	}


	public void setSociaResponsavelId(Long sociaResponsavelId) {
		this.sociaResponsavelId = sociaResponsavelId;
	}


	public Long getPacotePadraoId() {
		return pacotePadraoId;
	}


	public void setPacotePadraoId(Long pacotePadraoId) {
		System.out.println("pacotePadraoId"+":"+pacotePadraoId);
		this.pacotePadraoId = pacotePadraoId;
		if(pacotePadraoId == null) {
			this.atendimentosPadrao.removeIf(aP -> aP.isAtendimentoPadraoDePacote());
			System.out.println("TESTE1");
		}else {
			List<AtendimentoPadrao> atendimentosPadraoDePacote = atendimentoPadraoDao.listaAtendimentosPadraoDePacote(pacotePadraoId);
			this.atendimentosPadrao.removeIf(aP -> aP.isAtendimentoPadraoDePacote());System.out.println("TESTE2");
			for(AtendimentoPadrao atendimentoPadraoDePacote : atendimentosPadraoDePacote) {
				Integer quantidadeAtendimentosMensal = atendimentoPadraoDePacote.getQuantidadeAtendimentosMensal();
				TipoProfissional tipoProfissional = atendimentoPadraoDePacote.getTipoProfissional();
				Double desconto = atendimentoPadraoDePacote.getDesconto();
				boolean atendimentoPadraoDePacotes = true;
				
				AtendimentoPadrao atendimentoPadrao = new AtendimentoPadrao(quantidadeAtendimentosMensal, tipoProfissional, desconto, atendimentoPadraoDePacotes);
				atendimentoPadrao.setValorBrutoHora(atendimentoPadraoDePacote.getValorBrutoHora());System.out.println("TESTE2");
				atendimentoPadrao.setValorLiquidoHora(atendimentoPadraoDePacote.getValorLiquidoHora());System.out.println("TESTE3");
				atendimentoPadrao.setPorcentagemLiquidoSobreBruto(atendimentoPadraoDePacote.getPorcentagemLiquidoSobreBruto());System.out.println("TESTE4");
				
				this.atendimentosPadrao.add(atendimentoPadrao);System.out.println("TESTE5");
			}
			
			
			
			atendimentosPadrao.forEach(atendimentoPadrao -> System.out.println(atendimentoPadrao));
		}
		System.out.println("pacotePadraoId"+":"+pacotePadraoId);
	}


	public Long getPacienteId() {
		return pacienteId;
	}


	public void setPacienteId(Long pacienteId) {
		this.pacienteId = pacienteId;
	}


	public Long getResponsavelFinanceiroId() {
		return responsavelFinanceiroId;
	}


	public void setResponsavelFinanceiroId(Long responsavelFinanceiroId) {
		this.responsavelFinanceiroId = responsavelFinanceiroId;
	}


	public ResponsavelFinanceiro getResponsavelFinanceiro() {
		return responsavelFinanceiro;
	}


	public void setResponsavelFinanceiro(ResponsavelFinanceiro responsavelFinanceiro) {
		this.responsavelFinanceiro = responsavelFinanceiro;
	}


	public Paciente getPaciente() {
		return paciente;
	}


	public void setPaciente(Paciente paciente) {
		this.paciente = paciente;
	}

	public List<AtendimentoPadrao> getAtendimentosPadrao() {
		return atendimentosPadrao;
	}


	public void setAtendimentosPadrao(List<AtendimentoPadrao> atendimentosPadrao) {
		this.atendimentosPadrao = atendimentosPadrao;
	}


	public boolean isMostraDesconto() {
		return mostraDesconto;
	}


	public void setMostraDesconto(boolean mostraDesconto) {
		System.out.println("Mostra desconto"+":"+mostraDesconto);
		this.mostraDesconto = mostraDesconto;
	}


	public Long getTipoProfissionalId() {
		return tipoProfissionalId;
	}


	public void setTipoProfissionalId(Long tipoProfissionalId) {
		this.tipoProfissionalId = tipoProfissionalId;
	}


	public Integer getQtdeAtendimento() {
		return qtdeAtendimento;
	}


	public void setQtdeAtendimento(Integer qtdeAtendimento) {
		this.qtdeAtendimento = qtdeAtendimento;
	}


	public List<AtendimentoPadrao> getAtendimentosEspecialidade() {
		return atendimentosEspecialidade;
	}


	public void setAtendimentosEspecialidade(List<AtendimentoPadrao> atendimentosEspecialidade) {
		this.atendimentosEspecialidade = atendimentosEspecialidade;
	}


	public boolean isMostraSetup() {
		return mostraSetup;
	}


	public void setMostraSetup(boolean mostraSetup) {
		this.mostraSetup = mostraSetup;
	}
	public boolean isMostraMaterial() {
		return mostraMaterial;
	}


	public void setMostraMaterial(boolean mostraMaterial) {
		System.out.println("Mostra Material"+":"+mostraMaterial);
		this.mostraMaterial = mostraMaterial;
	}

	public Double getValorMaterial() {
		return valorMaterial;
	}


	public boolean isMostraPainelControle() {
		return mostraPainelControle;
	}


	public void setMostraPainelControle(boolean mostraPainelControle) {
		this.mostraPainelControle = mostraPainelControle;
	}


	public void setValorMaterial(Double valorMaterial) {
		System.out.println("Atualizar Valor Material"+":"+valorMaterial);
		this.valorMaterial = valorMaterial;
	}


	public Double getValorTotalAlterandoDesconto() {
		return valorTotalAlterandoDesconto;
	}


	public void setValorTotalAlterandoDesconto(Double valorTotalAlterandoDesconto) {
		this.valorTotalAlterandoDesconto = valorTotalAlterandoDesconto;
	}

	public Double getValorDeTodosDescontos() {
		return valorDeTodosDescontos*100;
	}


	public void setValorDeTodosDescontos(Double valorDeTodosDescontos) {
		this.valorDeTodosDescontos = valorDeTodosDescontos/100;
	}


	// Especificos
	public List<Usuario> getListaTodosUsuarioClienteDoMaisNovoAoMaisVelho(){
		return usuarioDao.listaTodosUsuarioClienteDoMaisNovoAoMaisVelho();
	}
	public List<Usuario> getListaTodosUsuariosDoTipoSocia(){
		System.out.println("entrou listaTodosUsuariosDoTipoSocia");
		List<Usuario> usuariosTipoSocia = usuarioDao.listaTodosUsuariosDoTipoSocia();
		usuariosTipoSocia.forEach(u -> System.out.println(u));
		return usuariosTipoSocia;
		
	}
	public List<TipoProfissional> getTodosTiposProfissional(){
		return tipoProfissionalDao.listaTodos();
	}
			



	// Atendimento Padrao
	public List<Pacote> getPacotesPadrao(){
		return pacoteDao.listaPacotesPadrao();
	}
	public Integer getQuantidadeAtendimentosPadrao() {
		return this.atendimentosPadrao.size();
	}
	public int getTotalAtendimentosPadrao() {
		return atendimentosPadrao.stream().mapToInt(AtendimentoPadrao::getQuantidadeAtendimentosMensal).sum();
	}
	
	public Double getTotalValorBrutoTotalAtendimentosPadrao() {
		return atendimentosPadrao.stream().mapToDouble(AtendimentoPadrao::getValorBrutoTotal).sum();
	}
	public Double getTotalValorLiquidoTotalAtendimentosPadrao() {
		return atendimentosPadrao.stream().mapToDouble(AtendimentoPadrao::getValorLiquidoTotal).sum();
	}
	
	// Salvar
	@Transactional
	public void salvar() {
//		FacesMessage msg = new FacesMessage("Salvo com sucesso!", "Bem-Vindo :" + usuario.getNome());
//		FacesContext.getCurrentInstance().addMessage(null, msg);
	}
	//Especialidade
	public void adicionaEspecialidade(ActionEvent event) {
		System.out.println("Adicionando ESPECIALIDADE");
		TipoProfissional tipoProfissional = tipoProfissionalDao.buscaPorId(tipoProfissionalId);
		AtendimentoPadrao atendimentoEspecialidade = new AtendimentoPadrao(qtdeAtendimento, tipoProfissional, 0.0, false);
		atendimentosEspecialidade.add(atendimentoEspecialidade);
		atendimentosEspecialidade.forEach(atendimento -> System.out.println("AtendimentoEspecialidade:"+atendimento));
	}
	public Integer getQuantidadeAtendimentosEspecialidade() {
		return this.atendimentosEspecialidade.size();
	}
	public int getTotalAtendimentosEspecialidade() {
		return atendimentosEspecialidade.stream().mapToInt(AtendimentoPadrao::getQuantidadeAtendimentosMensal).sum();
	}
	
	public Double getTotalValorBrutoTotalAtendimentosEspecialidade() {
		return atendimentosEspecialidade.stream().mapToDouble(AtendimentoPadrao::getValorBrutoTotal).sum();
	}
	public Double getTotalValorLiquidoTotalAtendimentosEspecialidade() {
		return atendimentosEspecialidade.stream().mapToDouble(AtendimentoPadrao::getValorLiquidoTotal).sum();
	}
	public void removeEspecialidade(AtendimentoPadrao atendimentoEspecialidade) {
		System.out.println("Imprimindo atendimentosEspecialidade");
		atendimentosEspecialidade.forEach(atendimento -> System.out.println("Atendimento:"+atendimento));
		System.out.println("REMOVE:" + atendimentoEspecialidade);
		atendimentosEspecialidade.remove(atendimentoEspecialidade);
		atendimentosEspecialidade.forEach(atendimento -> System.out.println("Atendimento:"+atendimento));
	}

	// Alterar Descontos para dar valor Total
	public void processarValorTotalAlterandoDesconto() {
		//Double valorTotalComDescontoAtual = getTotalValorBrutoTotalAtendimentosPadrao() + getTotalValorBrutoTotalAtendimentosEspecialidade();
		//Zerar descontos
		atendimentosPadrao.forEach(atendimento -> atendimento.setDesconto(0.0));
		atendimentosEspecialidade.forEach(atendimento -> atendimento.setDesconto(0.0));
		Double valorTotalSemDesconto = getTotalValorBrutoTotalAtendimentosPadrao() + getTotalValorBrutoTotalAtendimentosEspecialidade();
		Double desconto = 1 - (valorTotalAlterandoDesconto - valorMaterial)/valorTotalSemDesconto;
		//Atualizar Desconto
		atendimentosPadrao.forEach(atendimento -> atendimento.setDesconto(desconto));
		atendimentosEspecialidade.forEach(atendimento -> atendimento.setDesconto(desconto));
	}
	// Zerar todos descontos
	public void zerarTodosDescontos() {
		atendimentosPadrao.forEach(atendimento -> atendimento.setDesconto(valorDeTodosDescontos));
		atendimentosEspecialidade.forEach(atendimento -> atendimento.setDesconto(valorDeTodosDescontos));
	}
	// Modificando Linhas e Colunas
//	  public void onRowEdit(RowEditEvent event) {
//	        FacesMessage msg = new FacesMessage("Atendimento Editado", ((AtendimentoPadrao) event.getObject()).getId().toString());
//	        FacesContext.getCurrentInstance().addMessage(null, msg);
//	    }
//	     
//	    public void onRowCancel(RowEditEvent event) {
//	        FacesMessage msg = new FacesMessage("Edição Cancelada", ((AtendimentoPadrao) event.getObject()).getId().toString());
//	        FacesContext.getCurrentInstance().addMessage(null, msg);
//	    }
	     
	    public void onCellEdit(CellEditEvent event) {
	        Object oldValue = event.getOldValue();
	        Object newValue = event.getNewValue();
	         
	        if(newValue != null && !newValue.equals(oldValue)) {
	            FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_INFO, "Célula Modificada", "Antes: " + oldValue + ", Agora:" + newValue);
	            FacesContext.getCurrentInstance().addMessage(null, msg);
	        }
	        System.out.println("Entrou no RequestContext.getCurrentInstance()");
	    }
	    public void refreshEspecialidade() {
	    		RequestContext.getCurrentInstance().update(":especialidade");
	    }
	    // Mensagens
	    public void msgDesconto() {
        String summary = mostraDesconto ? "Desconto será exibido" : "Desconto não será exibido";
	        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(summary));
	    }

}