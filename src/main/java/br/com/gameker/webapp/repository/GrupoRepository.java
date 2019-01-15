package br.com.gameker.webapp.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.gameker.webapp.entity.GrupoEntity;
import br.com.gameker.webapp.entity.UsuarioEntity;

public interface GrupoRepository extends JpaRepository<GrupoEntity, Long>{
	List<GrupoEntity> findByUsuariosIn(UsuarioEntity usuarioEntity);
}
