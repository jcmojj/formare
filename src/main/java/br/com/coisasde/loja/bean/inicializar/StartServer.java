package br.com.coisasde.loja.bean.inicializar;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;
import javax.transaction.Transactional;

import br.com.coisasde.loja.daos.LogradouroDao;
import br.com.coisasde.loja.daos.PaesciDao;
import br.com.coisasde.loja.daos.TipoTelefoneDao;
import br.com.coisasde.loja.model.usuario.endereco.Logradouro;
import br.com.coisasde.loja.model.usuario.endereco.Paesci;
import br.com.coisasde.loja.model.usuario.telefone.TipoTelefone;

@Named
@RequestScoped
public class StartServer {

	@Transactional
	public void allMainValues() {
			logradouro();
			tipoTelefone();
			paesci();		
	}
	
	@Inject
	private LogradouroDao logradouroDao;

	@Transactional
	public void logradouro() {
		try {
			InputStream is = new FileInputStream(
					"/Users/josecarlosoliveira/javaee/eclipse-workspace/formare/src/main/resources/startServer/logradouro");
			InputStreamReader isr = new InputStreamReader(is);
			BufferedReader br = new BufferedReader(isr);
			String logradouro = br.readLine();

			while (logradouro != null) {

				logradouroDao.adiciona(new Logradouro(logradouro));

				logradouro = br.readLine();
			}
			br.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@Inject
	private TipoTelefoneDao tipoTelefoneDao;

	@Transactional
	public void tipoTelefone() {
		try {
			InputStream is = new FileInputStream(
					"/Users/josecarlosoliveira/javaee/eclipse-workspace/formare/src/main/resources/startServer/tipoTelefone");
			InputStreamReader isr = new InputStreamReader(is);
			BufferedReader br = new BufferedReader(isr);
			String tipoTelefone = br.readLine();

			while (tipoTelefone != null) {

				tipoTelefoneDao.adiciona(new TipoTelefone(tipoTelefone));

				tipoTelefone = br.readLine();
			}
			br.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@Inject
	private PaesciDao paesciDao;

	@Transactional
	public void paesci() {
		System.out.println("Entrou tb");
		try {
			InputStream is = new FileInputStream(
					"/Users/josecarlosoliveira/javaee/eclipse-workspace/formare/src/main/resources/startServer/paesci");
			InputStreamReader isr = new InputStreamReader(is);
			BufferedReader br = new BufferedReader(isr);
			String s = br.readLine();
			Integer first;
			Integer second;
			Integer third;
			Integer lenght;
			String pais;
			String estado;
			String cidade;

			while (s != null) {
				first = 0;
				second = s.indexOf("-", first);
				third = s.indexOf("-", second + 1);
				lenght = s.length();
				pais = s.substring(first, second);
				estado = s.substring(second + 1, third);
				cidade = s.substring(third + 1, lenght);
				paesciDao.adiciona(new Paesci(pais, estado, cidade));
				s = br.readLine();
			}
			br.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
