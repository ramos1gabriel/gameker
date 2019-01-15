package br.com.gameker.webapp.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.gameker.webapp.entity.PlataformaEntity;
import br.com.gameker.webapp.model.PlataformaModel;
import br.com.gameker.webapp.repository.PlataformaRepository;

@Service
@Transactional
public class PlataformaService {
	
	@Autowired
	private PlataformaRepository plataformaRepository;
	
	@Transactional(readOnly = true)
	public List<PlataformaModel> consultarPlataformas(){
 
		List<PlataformaModel> plataformaModel =  new ArrayList<PlataformaModel>();
		
		List<PlataformaEntity> plataformaEntity = this.plataformaRepository.findAll();
 
		plataformaEntity.forEach(plataforma ->{ 
			plataformaModel.add(new PlataformaModel	(plataforma.getCodigo(), plataforma.getNome())); 
	    });
	    
		return plataformaModel;
	}
}
