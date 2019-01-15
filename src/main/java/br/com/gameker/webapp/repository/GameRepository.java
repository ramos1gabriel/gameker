package br.com.gameker.webapp.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.gameker.webapp.entity.GameEntity;

public interface GameRepository extends JpaRepository<GameEntity, Long> {
	List<GameEntity> findByNome(String nome);
}
