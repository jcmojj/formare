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
import br.com.clinicaformare.util.listeners.login.UsuarioLogado;

@Named
@ViewScoped
public abstract class EntityBean<T extends Modelo> implements Serializable {
	private static final long serialVersionUID = 1L;
//	private final T value;
	private final Class<?> classe;
	// private String className;
	@Inject
	private AcessoDao acessoDao;
	@Inject
	private Dao<T> dao;
//	private String shortPath;
//	private String fileName;
	
	protected TipoEntidade tipoEntidade;

	@Inject
	@UsuarioLogado
	Usuario usuarioLogado;

	// Construtor
	// @SuppressWarnings("unchecked")
//	public EntityBean(Class<?> classe, String shortPath, String fileName) {// , Modelo modeloDelete, Modelo modeloNovo, List<Modelo> modelos) {
//		this.classe = classe;
//		this.shortPath = shortPath;
//		this.fileName = fileName;
//		// className = this.classe.getName();
//	}
	
	public EntityBean(Class<?> classe, TipoEntidade tipoEntidade) {// , Modelo modeloDelete, Modelo modeloNovo, List<Modelo> modelos) {
		this.classe = classe;
		this.tipoEntidade = tipoEntidade;	
//		this.shortPath = tipoEntidade.getShortPath();
//		this.fileName = tipoEntidade.getFileName();
	}

	// }
	// Clazz clazz = classe.newInstance();
	// final ParameterizedType type = (ParameterizedType) getClass().getGenericSuperclass();
	// Class<T> theType = (Class<T>) (type).getActualTypeArguments()[0];
	// this.dao = (Dao<T>)dao;
	// }
	// @Inject
	// private Dao<classe> dao;

	// public void setDao(Dao<T> dao) {
	// this.dao = dao;
	// }

	// Variáveis
	Boolean inicializar;
	Boolean listar;
	Boolean alterar;
	Boolean incluir;
	Boolean deletar;

	protected void postContructEntityBean() {
		String fileName = tipoEntidade.getFileName();
		System.out.println("PostConstruct ENTITYBEAN abriu metodo");
		System.out.println("usuarioLogado" + usuarioLogado);
		System.out.println("fileName" + fileName);
		inicializar = acessoDao.buscaAcessoInicializarPara(usuarioLogado, fileName);
		listar = acessoDao.buscaAcessoListarPara(usuarioLogado, fileName);
		alterar = acessoDao.buscaAcessoAlterarPara(usuarioLogado, fileName);
		incluir = acessoDao.buscaAcessoIncluirPara(usuarioLogado, fileName);
		deletar = acessoDao.buscaAcessoDeletarPara(usuarioLogado, fileName);
		System.out.println("PostConstruct ENTITYBEAN fechou metodo");
	}

	protected Modelo modeloDelete;
	protected Modelo modeloNovo;
	protected List<Modelo> modelos = new ArrayList<>();

	// Logradouro logradouroDelete;
	// Object object = classe.newInstance();
	// Modelo modeloNovo = new Class<T>();
	// Class<?> teste =
	// new Class<T>(classe);
	//// classe.newInstance();
	// Modelo modeloNovo =
	// Logradouro logradouroNovo = new Logradouro();
	// List<Logradouro> logradouros;

	// @PostConstruct
	// public void init() {
	// // class.ge
	// // logradouros = logradouroDao.listaTodos();
	// }

	// Getters and Setters
	public boolean isInicializar() {
//		System.out.println("ENTITY BEAN: INICIALIZAR");
		return inicializar;
	}

	public boolean isListar() {
//		System.out.println("ENTITY BEAN: isListar");
		return listar;
	}

	public boolean isAlterar() {
//		System.out.println("ENTITY BEAN: isAlterar");
		return alterar;
	}

	public boolean isIncluir() {
//		System.out.println("ENTITY BEAN: isIncluir");
		return incluir;
	}

	public boolean isDeletar() {
//		System.out.println("ENTITY BEAN: isDeletar");
		return deletar;
	}

	public Modelo getModeloDelete() {
		return modeloDelete;
	}

	public void setModeloDelete(Modelo modelo) {
		this.modeloDelete = modelo;
	}

	public Modelo getModeloNovo() {
		return modeloNovo;
	}

	public void setModeloNovo(Modelo modeloNovo) {
		this.modeloNovo = modeloNovo;
	}

	public TipoEntidade getTipoEntidade() {
		return tipoEntidade;
	}

	public String inicializando() {
		System.out.println("EntityBean metodo inicializando: " + tipoEntidade.getTipo());
		dao.listaTodos().stream().forEach(System.out::println);
		// inicializar = true;
		// listar = true;
		// alterar = false;
		// incluir = false;
		// deletar = false;
		if (dao.isEmpty() && isInicializar()) {
			// String shortPath = "/entity/usuario/endereco/";
			// String fileName = "logradouro";

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

	public Modelo gerar(String linha) {
		return null;
	}
	// public T gerar(String linha, Class <T> classe) {
	// Container<String> container = new Container<>(() -> linha);
	//// return new EntityBean<>()
	//// return null;
	// }

	public String abrir() {
		if (isListar()) {
			return "/entity/" + tipoEntidade.getFileName() + "?faces-redirect=true";
		} else {
			return "";
		}
	}

	
	public void atualizaLista() {
	}

	@SuppressWarnings("unchecked")
	@Transactional
	public void onRowEdit(RowEditEvent event) {
		System.out.println("EntityBean metodo onRowEdit");
		dao.atualiza((T) event.getObject());
		this.atualizaLista();
		FacesMessage msg = new FacesMessage(classe.getSimpleName() + " Editado:", (((T) event.getObject())).toString());
		FacesContext.getCurrentInstance().addMessage(null, msg);
	}

	//
	public void onRowCancel(RowEditEvent event) {
		System.out.println("EntityBean metodo onRowCancel");
		@SuppressWarnings("unchecked")
		FacesMessage msg = new FacesMessage("Edição Cancelada", (((T) event.getObject())).toString());
		FacesContext.getCurrentInstance().addMessage(null, msg);
	}

	//
	@SuppressWarnings("unchecked")
	@Transactional
	public void apagar() {
		System.out.println("EntityBean metodo apagar");
		FacesMessage msg = new FacesMessage(classe.getSimpleName() + " deletado:", modeloDelete.toString());
		FacesContext.getCurrentInstance().addMessage(null, msg);
		dao.remove((T) modeloDelete);
		this.atualizaLista();
	}

	//
	@SuppressWarnings("unchecked")
	@Transactional
	public void adicionar() {
		System.out.println("EntityBean metodo adicionar");
		FacesMessage msg = new FacesMessage(classe.getSimpleName() + " nao entrou");
		atualizaModelo();
		// if(dao.adiciona((T) modeloNovo)) {
		// FacesMessage msg = new FacesMessage(classe.getSimpleName() + " Adicionado", modeloNovo.toString());
		// FacesContext.getCurrentInstance().addMessage(null, msg);
		// }else {
		// FacesMessage msg = new FacesMessage(classe.getSimpleName() + " Não adicionado", "Já está cadastrado");
		// FacesContext.getCurrentInstance().addMessage(null, msg);
		// }
		// FacesContext.getCurrentInstance().addMessage(null, dao.adiciona((T) modeloNovo));

		// T = classe.newInstance();
		// modeloNovo = new Logradouro();
		try {
			dao.adiciona((T) modeloNovo);
			msg = new FacesMessage(classe.getSimpleName() + " Adicionado", modeloNovo.toString());
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
//			e.printStackTrace();
			System.out.println("EXCEPTION: " + e.getMessage());
			if (e.getMessage().contains("ConstraintViolationException")) {
				msg = new FacesMessage(classe.getSimpleName(), "Esse " + classe.getSimpleName() + " já foi cadastrado");
				FacesContext.getCurrentInstance().getExternalContext().getFlash().setKeepMessages(true);
				FacesContext.getCurrentInstance().addMessage("formMessages:msgs", msg);
				try {
					FacesContext.getCurrentInstance().getExternalContext().redirect(tipoEntidade.getFileName() + ".xhtml");
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			} else if (e.getMessage().contains("GenericJDBCException")) {
				msg = new FacesMessage(classe.getSimpleName(), "Esse " + classe.getSimpleName() + " já foi cadastrado");
				FacesContext.getCurrentInstance().getExternalContext().getFlash().setKeepMessages(true);
				FacesContext.getCurrentInstance().addMessage("formMessages:msgs", msg);
				try {
					FacesContext.getCurrentInstance().getExternalContext().redirect(tipoEntidade.getFileName() + ".xhtml");
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			} else {
				msg = new FacesMessage(classe.getSimpleName(), "Inconsistências nos dados: " + e.getMessage());
				FacesContext.getCurrentInstance().getExternalContext().getFlash().setKeepMessages(true);
				FacesContext.getCurrentInstance().addMessage("formMessages:msgs", msg);
				try {
					FacesContext.getCurrentInstance().getExternalContext().redirect(tipoEntidade.getFileName() + ".xhtml");
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}

			}
		}
		FacesContext.getCurrentInstance().getExternalContext().getFlash().setKeepMessages(true);
		FacesContext.getCurrentInstance().addMessage(null, msg);
		this.atualizaLista();
		geraNovaEntidade();
	}

	public void geraNovaEntidade() {
	}

	public void atualizaModelo() {

	}

	public Class<?> getClasse() {
		return classe;
	}

	//// @SuppressWarnings("unchecked")
	// public void iterar(Stream<String> lines) {
	// lines.forEach(linha -> dao.adiciona((Class<T>)this.gerar(linha)));
	// }
	//
	// public <T> gerar(String linha) {
	//
	// return new Logradouro(linha);
	// }
	//
	// public List<Logradouro> getLogradouros() {
	// return logradouros;
	// }

	// public void onCellEdit(CellEditEvent event) {
	// Object oldValue = event.getOldValue();
	// Object newValue = event.getNewValue();
	//
	// if(newValue != null && !newValue.equals(oldValue)) {
	// FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_INFO, "Célula Modificada", "Antiga: " + oldValue + ", Nova: " + newValue);
	// FacesContext.getCurrentInstance().addMessage(null, msg);
	// }
	// }
	
//	public String getTituloTabelas() {
//		return 
//	}

}