package br.com.clinicaformare.bean.entity;

import java.io.File;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URISyntaxException;
import java.net.URLDecoder;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.stream.Stream;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;
import javax.transaction.Transactional;

import br.com.clinicaformare.bean.LoginBean;
import br.com.clinicaformare.daos.usuario.endereco.LogradouroDao;
import br.com.clinicaformare.model.usuario.Usuario;
import br.com.clinicaformare.usuario.endereco.Logradouro;
import br.com.clinicaformare.util.listeners.login.UsuarioLogado;

@Named
@RequestScoped
public class LogradouroEntity {

	@Inject
	@UsuarioLogado
	private Usuario usuarioLogado;

	@Inject
	private LoginBean loginBean;

	public String getUsuarioLogado() {
		if (usuarioLogado == null) {
			return "Sem usuário logado";
		}
		return "UsuarioLogado: " + usuarioLogado.getNome() + usuarioLogado.getSobrenome() + " LoginBean: " + loginBean.getUsuarioLogado().getNome() + loginBean.getUsuarioLogado().getSobrenome();
	}

	@Inject
	private LogradouroDao logradouroDao;

	@Transactional
	public void start() {
		// String fileName = "/Users/josecarlosoliveira/javaee/eclipse-workspace/formare/src/main/resources/startServer/usuario/endereco/logradouro";
		String shortPath = "/entity/usuario/endereco";
		String fileName = "/logradouro";
//		String fullPath = "";
		
		try {
			this.iterar(this.getLinha (shortPath, fileName));
		} catch (IOException e) {
			e.printStackTrace();
		}
//		try {
//
//		// try (Stream<String> stream = Files.lines(Paths.get(fileName))) {
////			Path path = Paths.get(getClass().getClassLoader().getResource(fullPath).toURI());
//			fullPath = this.getPath(shortPath,fileName);
//			Path path = Paths.get(fullPath);
//			Stream<String> lines = Files.lines(path);
//			this.iterar(lines);
//			lines.close();
//		} catch (UnsupportedEncodingException e1) {
//			e1.printStackTrace();
////		} catch (URISyntaxException e) {
////			e.printStackTrace();
//		} catch (IOException e) {
//			e.printStackTrace();
//		}
	}

	public Stream<String> getLinha (String shortPath, String fileName) throws IOException {
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


//	public String getPath(String shortPath, String fileName) throws UnsupportedEncodingException {
////		System.out.println("---->getPath:" + shortPath);
////		String path = this.getClass().getClassLoader().getResource(shortPath).getPath();
////		String fullPath = URLDecoder.decode(path, "UTF-8");
//////		String pathArr[] = fullPath.split("/WEB-INF/classes/");
////		System.out.println("fullpath:" + fullPath);
//////		System.out.println("pathArr:" + pathArr[0]);
//////		fullPath = pathArr[0];
////		String responsePath = "";
////		// to read a file from webcontent
////		responsePath = new File(fullPath).getPath() + File.separatorChar + fileName;
////		System.out.println("responsePath: " + responsePath);
////		return fullPath + fileName;
//		String path = this.getClass().getClassLoader().getResource(shortPath).getPath();
//		String fullPath = URLDecoder.decode(path, "UTF-8");
//		return fullPath + fileName;
//	}
	
}