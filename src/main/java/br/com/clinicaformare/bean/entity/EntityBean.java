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
import javax.transaction.Transactional;

import org.primefaces.event.RowEditEvent;

import br.com.clinicaformare.daos.Dao;
import br.com.clinicaformare.model.Modelo;

@Named
@ViewScoped
public class EntityBean<T extends Modelo> implements Serializable {
	private static final long serialVersionUID = 1L;

	private final Class<?> classe;
	private String className;
	@Inject
	private Dao<T> dao;
	private String shortPath;
	private String fileName;

	// Construtor
//	@SuppressWarnings("unchecked")
	public EntityBean(Class<?> classe, String shortPath, String fileName) {//, Modelo modeloDelete, Modelo modeloNovo, List<Modelo> modelos) {
		this.classe = classe;
		this.shortPath = shortPath;
		this.fileName = fileName;
		className = this.classe.getName();
	}	
		
//	}
//		Clazz clazz = classe.newInstance();
//		final ParameterizedType type = (ParameterizedType) getClass().getGenericSuperclass();
//		Class<T> theType = (Class<T>) (type).getActualTypeArguments()[0];
//		this.dao = (Dao<T>)dao;
//	}
//	@Inject
//	private Dao<classe> dao;

//	public void setDao(Dao<T> dao) {
//		this.dao = dao;
//	}


	// Variáveis
	boolean inicializar = false;
	boolean listar = false;
	boolean alterar = false;
	boolean incluir = false;
	boolean deletar = false;
	protected Modelo modeloDelete;
	protected Modelo modeloNovo;
	protected List<Modelo> modelos = new ArrayList<>();
	
//	Logradouro logradouroDelete;
//	Object object = classe.newInstance();
//	Modelo modeloNovo = new Class<T>();
//	Class<?> teste = 
//			new Class<T>(classe);
////			classe.newInstance();
//	Modelo modeloNovo = 
//	Logradouro logradouroNovo = new Logradouro();
//	List<Logradouro> logradouros;

	@PostConstruct
	public void init() {
		// class.ge
		// logradouros = logradouroDao.listaTodos();
	}

	// Getters and Setters
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

	public Modelo getModeloNovo() {
		return modeloNovo;
	}

	public void setModeloNovo(Modelo modeloNovo) {
		this.modeloNovo = modeloNovo;
	}

	public String inicializar() {
		listar = true;
		alterar = false;
		incluir = false;
		deletar = false;
		 if (!inicializar) {
		 inicializar = true;
//		 String shortPath = "/entity/usuario/endereco/";
//		 String fileName = "logradouro";
		
		 try {
		 this.iterar(this.getLinha(shortPath, fileName));
		 } catch (IOException e) {
		 e.printStackTrace();
		 }
		 }
		return "logradouroentity?faces-redirect=true";
	}
	
	
	public Stream<String> getLinha(String shortPath, String fileName) throws IOException {
		String pathString = this.getClass().getClassLoader().getResource(shortPath).getPath();
		String fullPath = URLDecoder.decode(pathString, "UTF-8");
		Path path = Paths.get(fullPath + fileName);
		Stream<String> lines = Files.lines(path);
		return lines;
	}

	@SuppressWarnings("unchecked")
	public void iterar(Stream<String> lines) {
		lines.forEach(linha -> dao.adiciona((T)this.gerar(linha)));
	}
	public Modelo gerar(String linha) {return null;}
//	public T gerar(String linha, Class <T> classe) {
//		Container<String> container = new Container<>(() -> linha);
////		return new EntityBean<>()
////		return null;
//	}

	public String listar() {
		listar = true;
		alterar = false;
		incluir = false;
		deletar = false;
		return "logradouroentity?faces-redirect=true";
	}

	public String alterar() {
		listar = true;
		alterar = true;
		incluir = false;
		deletar = false;
		return "logradouroentity?faces-redirect=true";
	}

	public String incluir() {
		listar = true;
		alterar = false;
		incluir = true;
		deletar = false;
		return "logradouroentity?faces-redirect=true";
	}

	public String deletar() {
		listar = true;
		alterar = false;
		incluir = false;
		deletar = true;
		return "logradouroentity?faces-redirect=true";
	}

	 @SuppressWarnings("unchecked")
	@Transactional
	 public void onRowEdit(RowEditEvent event) {
	 dao.atualiza((T) event.getObject());
	 this.init();
	 FacesMessage msg = new FacesMessage(classe.getSimpleName() +" editado:", "(" + (((T) event.getObject()).getId()).toString().toString() + ") " + ((T) event.getObject()).getNome());
	 FacesContext.getCurrentInstance().addMessage(null, msg);
	 }
//	
	 public void onRowCancel(RowEditEvent event) {
	 @SuppressWarnings("unchecked")
	FacesMessage msg = new FacesMessage("Edição CanceladaZ", (((T) event.getObject()).getId()).toString());
	 FacesContext.getCurrentInstance().addMessage(null, msg);
	 }
//	
	 @SuppressWarnings("unchecked")
	@Transactional
	 public void apagar() {
	 FacesMessage msg = new FacesMessage(classe.getSimpleName() +  " deletado:", modeloDelete.getId().toString());
	 FacesContext.getCurrentInstance().addMessage(null, msg);
	 dao.remove((T)modeloDelete);
	 this.init();
	 }
//	
	 @SuppressWarnings("unchecked")
	@Transactional
	 public void adicionar() {
	 dao.adiciona((T)modeloNovo);
	 this.init();
	 FacesMessage msg = new FacesMessage(classe.getSimpleName() + " Adicionado", modeloNovo.getNome());
	 FacesContext.getCurrentInstance().addMessage(null, msg);
//	 T = classe.newInstance();
//	 modeloNovo = new Logradouro();
	 geraNovaEntidade();
	 }
	 
	 public void geraNovaEntidade() {}

//// @SuppressWarnings("unchecked")
//public void iterar(Stream<String> lines) {
// lines.forEach(linha -> dao.adiciona((Class<T>)this.gerar(linha)));
// }
//
//public <T> gerar(String linha) {
//	
//	return new Logradouro(linha);
//}
//
//public List<Logradouro> getLogradouros() {
//	return logradouros;
//}
	 
	// public void onCellEdit(CellEditEvent event) {
	// Object oldValue = event.getOldValue();
	// Object newValue = event.getNewValue();
	//
	// if(newValue != null && !newValue.equals(oldValue)) {
	// FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_INFO, "Célula Modificada", "Antiga: " + oldValue + ", Nova: " + newValue);
	// FacesContext.getCurrentInstance().addMessage(null, msg);
	// }
	// }

}