package br.com.goals.etrilhas.servlet;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.com.goals.etrilhas.dao.MapaItemDao;
import br.com.goals.etrilhas.facade.GaleriaFacade;
import br.com.goals.etrilhas.facade.MapaFacade;
import br.com.goals.etrilhas.facade.MapaItemFacade;
import br.com.goals.etrilhas.modelo.Camada;
import br.com.goals.etrilhas.modelo.Galeria;
import br.com.goals.etrilhas.modelo.Mapa;
import br.com.goals.etrilhas.modelo.MapaItem;
import br.com.goals.template.AreaNaoEncontradaException;
import br.com.goals.template.RequestUtil;
import br.com.goals.template.Template;

/**
 * Servlet implementation class MapaItemDefinirServlet
 */
public class MapaItemDefinir extends BaseServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public MapaItemDefinir() {
        super();
    }

    protected void carregarTemplate(Template template,MapaItem mapaItem,Mapa mapa) throws AreaNaoEncontradaException{
    	template.setInput("nome",mapaItem.getNome());
    	template.setInput("x",mapaItem.getX());
    	template.setInput("y",mapaItem.getY());
    	File dirIcones = new File(template.getTemplatePath().getParent(),"media/icones");
		template.setSelect("icone",mapaItem.getIcone(),dirIcones.list());
		template.setSelect("tipo", mapaItem.getTipo());
		template.setForm("campos do tipo",mapaItem.getValor());
		if(mapaItem.getCamada()==null){
			template.setRadio("camadas",mapa.getCamadas(),null);
		}else{
			template.setRadio("camadas",mapa.getCamadas(),mapaItem.getCamada().getId().toString());
		}
		String linkEdicao = null;
		if(mapaItem.getValor() !=null && mapaItem.getValor() instanceof Galeria){
			Galeria galeria =(Galeria) mapaItem.getValor();
			linkEdicao = "GaleriaServlet?galeriaId=" + galeria.getId();
		}
		template.setLink("linkEdicao", linkEdicao);
    }
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Long id = null;
		Template template = null;
		try{
			id = Long.parseLong(request.getParameter("id"));
			super.setCurrentMapaItemId(request, id);
		}catch(Exception e){
			e.printStackTrace();
		}
		template = getTemplate(request);
		/*
		 * Verificar se possui um tipo
		 */
		try{
			Mapa mapa = getMapa(request);
			MapaItemDao mapaItemDao = new MapaItemDao();
			MapaItem mapaItem = mapaItemDao.selecionar(mapa, id);
			template.set("id",id);
			carregarTemplate(template, mapaItem, mapa);
			template.setMensagem("");
		}catch(Exception e){
			e.printStackTrace();
		}
		response.getWriter().write(template.toString());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Template template = null;
		Mapa mapa = null;
		MapaItem mapaItem = null;
		try{
			String tipo = request.getParameter("tipo");
			Long id=null;
			try{
				id = Long.parseLong(request.getParameter("id"));
			}catch(NumberFormatException e){
				//ok
			}
			mapa = getMapa(request);
			MapaItemDao mapaItemDao = new MapaItemDao();
			if(id==null){
				mapaItem = new MapaItem();
			}else{
				mapaItem = mapaItemDao.selecionar(mapa, id);
			}
			mapaItem.setNome(request.getParameter("nome"));
			mapaItem.setIcone(request.getParameter("icone"));
			try{
				mapaItem.setX(Double.parseDouble(request.getParameter("x")));
			}catch(Exception e){
				throw new Exception("Nao foi possivel atribuir a latitude.");
			}
			try{
				mapaItem.setY(Double.parseDouble(request.getParameter("y")));
			}catch(Exception e){
				throw new Exception("Nao foi possivel atribuir a longitude.");
			}
			//Criar um item do tipo
			Object obj = null;
			if(!tipo.equals(mapaItem.getTipo())){
				//O tipo era diferente do antigo
				obj = Class.forName("br.com.goals.etrilhas.modelo."+tipo).newInstance();
				mapaItem.setTipo(tipo);
				mapaItem.setValor(obj);
			}else{
				//O tipo permanesce o mesmo
				if(mapaItem.getValor()!=null && mapaItem.getValor() instanceof Galeria){
					Galeria galeria = (Galeria)mapaItem.getValor();
					if(galeria.getId()!=null){
						galeria = GaleriaFacade.getInstance().selecionar(galeria.getId());
						mapaItem.setValor(galeria);
					}
				}
				RequestUtil.request(request, mapaItem.getValor());
			}
			MapaItemFacade.getInstance().criarHtml(mapaItem);
			
			//Verificar se colocou em outra camada
			Long camadaId = Long.parseLong(request.getParameter("Camada.id"));
			if(mapaItem.getCamada()==null){
				//o item nao tem camada
				Camada camada = camadaFacade.selecionar(mapa,camadaId);
				mapaItem.setCamada(camada);
				if(camada.getItems()==null){
					camada.setItems(new ArrayList<MapaItem>());
				}
				//camada.getItems().add(mapaItem);
				if(id==null){
					//o item nao esta salvo
					mapaItemFacade.criar(mapaItem,getMapa(request));
				}
			}else if(!camadaId.equals(mapaItem.getCamada().getId())){
				//trocar de camada
				Camada camada = camadaFacade.selecionar(mapa,camadaId);
				//retira da camada antiga
				mapaItem.getCamada().getItems().remove(mapaItem);
				mapaItem.setCamada(camada);
				if(camada.getItems()==null){
					camada.setItems(new ArrayList<MapaItem>());
				}
				camada.getItems().add(mapaItem);
			}
			MapaFacade.getInstance().atualizar(mapa);
			
			//Montar resposta para o usuario
			template = getTemplate(request);
			template.set("id",id);
			carregarTemplate(template, mapaItem, mapa);
			
			template.setMensagem("Alterado com sucesso");
			response.getWriter().write(template.toString());
		}catch(Exception e){
			if(template==null){
				template = getTemplate(request);
			}
			template.setMensagem(e.getMessage());
			e.printStackTrace();
		}
			
	}
}