package br.com.goals.hotcoffe.ioc.casosdeuso;

import java.io.PrintWriter;
import java.lang.reflect.Method;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import br.com.goals.hotcoffe.ioc.Controlador;
import br.com.goals.hotcoffe.ioc.view.Template;

public class Ator {
	private static Logger logger = Logger.getLogger(Ator.class);
	private UmCasoDeUso umCasoDeUso;
	private HttpServletRequest request;
	private HttpServletResponse response;
	
	public Ator(UmCasoDeUso umCasoDeUso) {
		this.umCasoDeUso = umCasoDeUso;
		request = umCasoDeUso.request;
		response = umCasoDeUso.response;
	}
	/**
	 * Super abstracao, invoca o Template para cuidar disso
	 * @param obj
	 */
	public synchronized void preencher(Object obj){
		UmCasoDeUso.getCasosDeUso().put(umCasoDeUso.getKey(),umCasoDeUso);
		try {
			PrintWriter printWriter = umCasoDeUso.getResponse().getWriter();
			String form = Template.criarFormulario(obj, umCasoDeUso);
			printWriter.write(form);
			logger.debug("Caso de uso " + umCasoDeUso.getKey() + " aguardando...");
			Controlador controlador = umCasoDeUso.getControlador();
			synchronized (controlador) {
				controlador.notify();
			}
			wait();
			logger.debug(umCasoDeUso.getKey() + " acordou...");
			//popular
			Method[] metodos = obj.getClass().getMethods();
			for (int i = 0; i < metodos.length; i++) {
				String nome = metodos[i].getName();
				if(nome.startsWith("set")){
					String res = request.getParameter(metodos[i].getName());
					metodos[i].invoke(obj, res);
				}
			}
		} catch (Exception e) {
			logger.error("Erro ao mandar preencher " +obj,e);
		}
	}
	public void setRequestResponse(HttpServletRequest request,
			HttpServletResponse response) {
		this.request = request;
		this.response = response;
	}
}
