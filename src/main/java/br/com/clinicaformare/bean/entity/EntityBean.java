package br.com.clinicaformare.bean.entity;

import java.io.IOException;
import java.io.Serializable;
import java.net.URLDecoder;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
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

import br.com.clinicaformare.daos.usuario.endereco.LogradouroDao;
import br.com.clinicaformare.usuario.endereco.Logradouro;

@Named
@ViewScoped
public class EntityBean implements Serializable {
	private static final long serialVersionUID = 1L;


	@Inject
	private LogradouroDao logradouroDao;

	// Variáveis
	boolean inicializar = false;
	boolean listar = false;
	boolean alterar = false;
	boolean incluir = false;
	boolean deletar = false;
	Logradouro logradouroDelete;
	Logradouro logradouroNovo = new Logradouro();
	List<Logradouro> logradouros;

	@PostConstruct
	public void init() {
		logradouros = logradouroDao.listaTodos();
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

	public Logradouro getLogradouro() {
		return logradouroDelete;
	}

	public void setLogradouro(Logradouro logradouro) {
		this.logradouroDelete = logradouro;
	}

	public Logradouro getLogradouroNovo() {
		return logradouroNovo;
	}

	public void setLogradouroNovo(Logradouro logradouroNovo) {
		this.logradouroNovo = logradouroNovo;
	}

	public String inicializar() {
		listar = true;
		alterar = false;
		incluir = false;
		deletar = false;
		if (!inicializar) {
			inicializar = true;
			String shortPath = "/entity/usuario/endereco/";
			String fileName = "logradouro";

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

	public void iterar(Stream<String> lines) {
		lines.forEach(linha -> logradouroDao.adiciona(this.gerar(linha)));
	}

	public Logradouro gerar(String linha) {
		return new Logradouro(linha);
	}

	public List<Logradouro> getLogradouros() {
		return logradouros;
	}

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

	@Transactional
	public void onRowEdit(RowEditEvent event) {
		logradouroDao.atualiza((Logradouro) event.getObject());
		this.init();
		FacesMessage msg = new FacesMessage("Logradouro Editado", "(" + (((Logradouro) event.getObject()).getId()).toString() + ") " + ((Logradouro) event.getObject()).getNome());
		FacesContext.getCurrentInstance().addMessage(null, msg);
	}

	public void onRowCancel(RowEditEvent event) {
		FacesMessage msg = new FacesMessage("Edição Cancelada", (((Logradouro) event.getObject()).getId()).toString());
		FacesContext.getCurrentInstance().addMessage(null, msg);
	}

	@Transactional
	public void apagar() {
		logradouroDao.remove(logradouroDelete);
		this.init();
		FacesMessage msg = new FacesMessage("Logradouro Deletado", logradouroDelete.getId().toString());
		FacesContext.getCurrentInstance().addMessage(null, msg);
	}

	@Transactional
	public void adicionar() {
		logradouroDao.adiciona(logradouroNovo);
		this.init();
		FacesMessage msg = new FacesMessage("Logradouro Adicionado", logradouroNovo.getNome());
		FacesContext.getCurrentInstance().addMessage(null, msg);
		logradouroNovo = new Logradouro();
	}

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