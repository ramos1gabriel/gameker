package br.com.gameker.webapp.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.gameker.webapp.entity.GameEntity;
import br.com.gameker.webapp.entity.PlataformaEntity;
import br.com.gameker.webapp.model.GameModel;
import br.com.gameker.webapp.repository.GameRepository;
import br.com.gameker.webapp.repository.PlataformaRepository;
 
@Component
public class GameService {
	
	@Autowired
	private GameRepository gameRepository;
	
	@Autowired
	private PlataformaRepository plataformaRepository;
	
	public void salvarGame(GameModel gameModel){
 
		GameEntity gameEntity =  new GameEntity();
		
		//SETAR
		gameEntity.setNome(gameModel.getNome());
		gameEntity.setNota(gameModel.getNota());
		gameEntity.setStatus(gameModel.getStatus());
		
		/*PEGANDO A LISTA DE PLATAFORMAS SELECIONADOS*/
		PlataformaEntity plataformaEntity = null;
		List<PlataformaEntity> plataformas =  new ArrayList<PlataformaEntity>();
		for (Long codigoPlataforma : gameModel.getPlataformas()){
			
			if(codigoPlataforma != null){
				plataformaEntity = plataformaRepository.findOne(codigoPlataforma);
				plataformas.add(plataformaEntity);
			}
		}
		
		gameEntity.setPlataformas(plataformas);
 
		/*SALVANDO O REGISTRO*/
		this.gameRepository.save(gameEntity);
	}
	
	public List<GameModel> consultarGames(){
 
		List<GameModel> gameModel = new ArrayList<GameModel>();
 
		List<GameEntity> gameEntity = this.gameRepository.findAll();
 
		for (GameEntity games : gameEntity) {
			gameModel.add(new GameModel(
						games.getCodigo(),
						games.getNome(),
						games.getNota(),
						games.getStatus(),
						null
					));
		}
		
		return gameModel;
	}
 
	/**
	 * DELETA UM USUÁRIO  PELO CÓDIGO
	 * */
	public void excluir(Long codigoGame){
 
		this.gameRepository.delete(codigoGame);
	}
 
	/***
	 * CONSULTA UM USUÁRIO PELO SEU CÓDIGO
	 * @param codigoUsuario
	 * @return
	 */
	public GameModel consultarGame(Long codigoGame){
 
		GameEntity gameEntity = this.gameRepository.findOne(codigoGame);
		
		List<Long> plataformas =  new ArrayList<Long>();
		 
		gameEntity.getPlataformas().forEach(plataforma ->{
			plataformas.add(plataforma.getCodigo());
		}); 
		
		return new GameModel(
				gameEntity.getCodigo(),
				gameEntity.getNome(),
				gameEntity.getNota(),
				gameEntity.getStatus(),
				plataformas);
	}
 
	/**
	 * ALTERA AS INFORMAÇÕES DO USUÁRIO
	 * */
	public void alterarGame(GameModel gameModel){
 
		GameEntity gameEntity =  this.gameRepository.findOne(gameModel.getCodigo());
 
		//SETAR
		gameEntity.setNome(gameModel.getNome());
		gameEntity.setNota(gameModel.getNota());
		gameEntity.setStatus(gameModel.getStatus());
		
		/*PEGANDO A LISTA DE PLATAFORMAS SELECIONADOS*/
		PlataformaEntity plataformaEntity = null;
		List<PlataformaEntity> plataformas =  new ArrayList<PlataformaEntity>();
		for (Long codigoPlataforma : gameModel.getPlataformas()){
			
			if(codigoPlataforma != null){
				plataformaEntity = plataformaRepository.findOne(codigoPlataforma);
				plataformas.add(plataformaEntity);
			}
		}
		
		gameEntity.setPlataformas(plataformas);
 
		/*SALVANDO ALTERAÇÃO DO REGISTRO*/
		this.gameRepository.saveAndFlush(gameEntity);
	}
	
}
