package br.com.gameker.webapp.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.gameker.webapp.entity.GameEntity;
import br.com.gameker.webapp.entity.PlataformaEntity;

public interface PlataformaRepository extends JpaRepository<PlataformaEntity, Long>{
	List<PlataformaEntity> findByGamesIn(GameEntity gameEntity);
}
