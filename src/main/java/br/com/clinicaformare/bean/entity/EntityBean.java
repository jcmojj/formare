package br.com.clinicaformare.bean.entity;

import java.io.IOException;
import java.io.Serializable;
import java.net.URLDecoder;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import javax.persistence.PersistenceException;
import javax.transaction.Transactional;

import org.primefaces.event.RowEditEvent;

import br.com.clinicaformare.dao.acesso.AcessoDao;
import br.com.clinicaformare.daos.Dao;
import br.com.clinicaformare.model.Modelo;
import br.com.clinicaformare.model.acesso.TipoEntidade;
import br.com.clinicaformare.model.usuario.Usuario;
import br.com.clinicaformare.util.FixOnText;
import br.com.clinicaformare.util.listeners.login.UsuarioLogado;

@Named
@ViewScoped
public abstract class EntityBean<T extends Modelo> implements Serializable {
	private static final long serialVersionUID = 1L;
	
	@Inject
	protected AcessoDao acessoDao;
	@Inject
	protected Dao<T> dao;
	@Inject
	@UsuarioLogado
	protected Usuario usuarioLogado;

	// Variáveis
	private Boolean inicializar;
	private Boolean listar;
	private Boolean alterar;
	private Boolean incluir;
	private Boolean deletar;
	protected Modelo modeloDelete;
	protected Modelo modeloNovo;
	protected List<Modelo> modelos = new ArrayList<>();
	private final Class<?> classe;
	protected TipoEntidade tipoEntidade;

	@PostConstruct
	protected void postContructEntityBean() {
//		postContructEntityBean();
		atualizaLista();
		geraNovaEntidade();
		String fileName = tipoEntidade.getFileName();
//		System.out.println("PostConstruct ENTITYBEAN abriu metodo");
//		System.out.println("usuarioLogado" + usuarioLogado);
//		System.out.println("fileName" + fileName);
		inicializar = acessoDao.buscaAcessoInicializarPara(usuarioLogado, fileName);
		listar = acessoDao.buscaAcessoListarPara(usuarioLogado, fileName);
		alterar = acessoDao.buscaAcessoAlterarPara(usuarioLogado, fileName);
		incluir = acessoDao.buscaAcessoIncluirPara(usuarioLogado, fileName);
		deletar = acessoDao.buscaAcessoDeletarPara(usuarioLogado, fileName);
//		System.out.println("PostConstruct ENTITYBEAN fechou metodo");
	}
	public EntityBean(Class<?> classe, TipoEntidade tipoEntidade) {
		this.classe = classe;
		this.tipoEntidade = tipoEntidade;	
	}


	// Getters and Setters
	public boolean isInicializar() {
		return inicializar;
	}
	public boolean isListar() {
		return listar;
	}
	public boolean isAlterar() {
		return alterar;
	}
	public boolean isIncluir() {
		return incluir;
	}
	public boolean isDeletar() {
		return deletar;
	}
	public Modelo getModeloDelete() {
		return modeloDelete;
	}
	public void setModeloDelete(Modelo modelo) {
		this.modeloDelete = modelo;
	}
//	public Modelo getModeloNovo() {
//		return modeloNovo;
//	}
//	public void setModeloNovo(Modelo modeloNovo) {
//		this.modeloNovo = modeloNovo;
//	}
	public TipoEntidade getTipoEntidade() {
		return tipoEntidade;
	}
	public String inicializando() {
		System.out.println("EntityBean metodo inicializando: " + tipoEntidade.getTipo());
		dao.listaTodos().stream().forEach(System.out::println);
		if (dao.isEmpty() && isInicializar()) {
			try {
				this.iterar(this.getLinha(tipoEntidade.getShortPath(), tipoEntidade.getFileName()));
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return "/entity/" + tipoEntidade.getFileName() + "?faces-redirect=true";
	}
	public Stream<String> getLinha(String shortPath, String fileName) throws IOException {
		System.out.println("EntityBean metodo getLinha");
		String pathString = this.getClass().getClassLoader().getResource(shortPath).getPath();
		String fullPath = URLDecoder.decode(pathString, "UTF-8");
		Path path = Paths.get(fullPath + fileName);
		Stream<String> lines = Files.lines(path);
		return lines;
	}
	@SuppressWarnings("unchecked")
	public void iterar(Stream<String> lines) {
		System.out.println("EntityBean metodo iterar");
		lines.limit(30).forEach(linha -> {
			if (linha.equals(""))
				return; // se a linha for vazia, para de persistir
			dao.adicionaVolta((T) this.gerar(linha));
		});
	}
	public abstract Modelo gerar(String linha);
	public String abrir() {
		if (isListar()) {
			return "/entity/" + tipoEntidade.getFileName() + "?faces-redirect=true";
		} else {
			return "";
		}
	}
	public abstract void atualizaLista();
	@SuppressWarnings("unchecked")
	@Transactional
	public void onRowEdit(RowEditEvent event) {
		System.out.println("EntityBean metodo onRowEdit");
		dao.atualiza((T) event.getObject());
		this.atualizaLista();
		FacesMessage msg = new FacesMessage(this.tipoEntidade.getTipo() + " editado:", (((T) event.getObject())).toString());
		FacesContext.getCurrentInstance().addMessage(null, msg);
	}
	public void onRowCancel(RowEditEvent event) {
		System.out.println("EntityBean metodo onRowCancel");
		@SuppressWarnings("unchecked")
		FacesMessage msg = new FacesMessage("Edição de "+ this.tipoEntidade.getTipo() +  " cancelada", (((T) event.getObject())).toString());
		FacesContext.getCurrentInstance().addMessage(null, msg);
	}
	@SuppressWarnings("unchecked")
	@Transactional
	public void apagar() {
		System.out.println("EntityBean metodo apagar");
		FacesMessage msg = new FacesMessage(this.tipoEntidade.getTipo() + " deletado:", modeloDelete.toString());
		FacesContext.getCurrentInstance().addMessage(null, msg);
		dao.remove((T) modeloDelete);
		this.atualizaLista();
	}
	@SuppressWarnings("unchecked")
	@Transactional
	public void adicionar() {
		System.out.println("EntityBean metodo adicionar");
		FacesMessage msg = new FacesMessage(this.tipoEntidade.getTipo() + " não entrou");
		atualizaModelo();
		try {
			dao.adiciona((T) modeloNovo);
			msg = new FacesMessage(this.tipoEntidade.getTipo() + " Adicionado", modeloNovo.toString());
		} catch (PersistenceException p) {
			p.printStackTrace();
			if (p.getMessage().contains("ConstraintViolationException")) {
				if (p.getMessage().contains("Duplicate"))
					msg = new FacesMessage(classe.getSimpleName(), " Já existe um cadastro");
				else if (p.getMessage().contains("duplicate"))
					msg = new FacesMessage(classe.getSimpleName(), " Já existe um cadastro");
				else if (p.getMessage().contains("key"))
					msg = new FacesMessage(classe.getSimpleName(), " Já existe um cadastro");
				else
					msg = new FacesMessage(classe.getSimpleName(), " Registro com informações já existentes na base de dados");
			} else {
				msg = new FacesMessage(classe.getSimpleName(), "Problemas ao gravar no banco");
			}
			FacesContext.getCurrentInstance().getExternalContext().getFlash().setKeepMessages(true);
			FacesContext.getCurrentInstance().addMessage(null, msg);
		} catch (Exception e) {
			System.out.println("EXCEPTION: " + e.getMessage());
			if (e.getMessage().contains("ConstraintViolationException")) {
				msg = new FacesMessage(classe.getSimpleName(), "Esse " + classe.getSimpleName() + " já foi cadastrado");
				FacesContext.getCurrentInstance().getExternalContext().getFlash().setKeepMessages(true);
				FacesContext.getCurrentInstance().addMessage("formMessages:msgs", msg);
				try {
					FacesContext.getCurrentInstance().getExternalContext().redirect(tipoEntidade.getFileName() + ".xhtml");
				} catch (IOException e1) {
					e1.printStackTrace();
				}
			} else if (e.getMessage().contains("GenericJDBCException")) {
				msg = new FacesMessage(classe.getSimpleName(), "Esse " + classe.getSimpleName() + " já foi cadastrado");
				FacesContext.getCurrentInstance().getExternalContext().getFlash().setKeepMessages(true);
				FacesContext.getCurrentInstance().addMessage("formMessages:msgs", msg);
				try {
					FacesContext.getCurrentInstance().getExternalContext().redirect(tipoEntidade.getFileName() + ".xhtml");
				} catch (IOException e1) {
					e1.printStackTrace();
				}
			} else {
				msg = new FacesMessage(classe.getSimpleName(), "Inconsistências nos dados: " + e.getMessage());
				FacesContext.getCurrentInstance().getExternalContext().getFlash().setKeepMessages(true);
				FacesContext.getCurrentInstance().addMessage("formMessages:msgs", msg);
				try {
					FacesContext.getCurrentInstance().getExternalContext().redirect(tipoEntidade.getFileName() + ".xhtml");
				} catch (IOException e1) {
					e1.printStackTrace();
				}

			}
		}
		FacesContext.getCurrentInstance().getExternalContext().getFlash().setKeepMessages(true);
		FacesContext.getCurrentInstance().addMessage(null, msg);
		this.atualizaLista();
		geraNovaEntidade();
	}
	public abstract void geraNovaEntidade();
	public abstract void atualizaModelo();
	public Class<?> getClasse() {
		return classe;
	}
	public String getIdName() {
		return FixOnText.desCapitalizeFirstStringChar(tipoEntidade.getNomeEntidade());
	}
	public String getCompleteName() {
		return tipoEntidade.getTipo();
	}
	public String getTableId() {
		return FixOnText.desCapitalizeFirstStringChar(tipoEntidade.getNomeEntidade()) + "Table";
	}
//	public String getNovoFormId() {
//		return "formNovo"+ FixOnText.desCapitalizeFirstStringChar(tipoEntidade.getNomeEntidade()) + "Entity";
//	}
//	public String getListFormId() {
//		return "formLista"+ FixOnText.desCapitalizeFirstStringChar(tipoEntidade.getNomeEntidade()) + "Entity";
//	}

}