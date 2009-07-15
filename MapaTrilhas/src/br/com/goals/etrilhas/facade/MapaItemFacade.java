package br.com.goals.etrilhas.facade;

import java.util.Date;

import br.com.goals.etrilhas.dao.BaseDao;
import br.com.goals.etrilhas.dao.VideoDao;
import br.com.goals.etrilhas.modelo.Camada;
import br.com.goals.etrilhas.modelo.Mapa;
import br.com.goals.etrilhas.modelo.MapaItem;
import br.com.goals.etrilhas.modelo.Video;

public class MapaItemFacade{
	private BaseDao<Mapa> mapaDao = new BaseDao<Mapa>();
	private static MapaItemFacade instance = new MapaItemFacade();
	
	private MapaItemFacade(){	
	}
	
	public static MapaItemFacade getInstance() {
		return instance;
	}
	
	public void atualizar(MapaItem mapaItem, Mapa mapa) throws FacadeException {
		try {
			if(mapaItem.getCamada()!=null){
				for(Camada camada:mapa.getCamadas()){
					if(camada.getId().equals(mapaItem.getCamada().getId())){
						//atualizar o antigo
						for(MapaItem mItem:camada.getItems()){
							if(mItem.getId().equals(mapaItem.getId())){
								mItem.setX(mapaItem.getX());
								mItem.setY(mapaItem.getY());
							}
						}
					}
				}
			}
			try{
				if(mapaItem.getTipo()!=null){
					if(mapaItem.getTipo().equals("Video")){
						Video video = (Video)mapaItem.getValor();
						//TODO meio incoerente...
						video.setId(mapaItem.getId());
						VideoDao videoDao = new VideoDao();
						videoDao.criar(video);
					}
				}
			}catch(Exception e){
				e.printStackTrace();
			}
			mapaDao.atualizar(mapa);
		} catch (Exception e) {
			e.printStackTrace();
			throw new FacadeException(e.getMessage());
		}
	}

	public void criar(MapaItem mapaItem, Mapa mapa) throws FacadeException{
		try {
			mapaItem.setId(new Date().getTime());
			for(Camada camada:mapa.getCamadas()){
				if(camada.getId().equals(mapaItem.getCamada().getId())){
					camada.getItems().add(mapaItem);
				}
			}
			mapaDao.atualizar(mapa);
		} catch (Exception e) {
			e.printStackTrace();
			throw new FacadeException(e.getMessage());
		}
	}
}
