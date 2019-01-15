package br.com.gameker.webapp.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import br.com.gameker.webapp.entity.UsuarioEntity;
import br.com.gameker.webapp.model.GameModel;
import br.com.gameker.webapp.model.GrupoModel;
import br.com.gameker.webapp.model.PlataformaModel;
import br.com.gameker.webapp.model.UsuarioModel;
import br.com.gameker.webapp.repository.UsuarioRepository;
import br.com.gameker.webapp.service.GameService;
import br.com.gameker.webapp.service.GrupoService;
import br.com.gameker.webapp.service.PlataformaService;
import br.com.gameker.webapp.service.UsuarioService;
 
/**
 * 
 * 
 *OBJETO RESPONSÁVEL POR REALIZAR AS ROTINAS DE USUÁRIO.
 * 
 */
@Controller
@RequestMapping("/game") 
public class GameController {
	
	/**INJETANDO O OBJETO GrupoService*/
	@Autowired
	private PlataformaService plataformaService;
	
	@Autowired 
	private GameService gameService;
 
 
 
	/***
	 * CHAMA A FUNCIONALIDADE PARA CADASTRAR UM NOVO USUÁRIO NO SISTEMA
	 * @param model
	 * @return
	 */
	@RequestMapping(value="/novoCadastroGame", method= RequestMethod.GET)	
	public ModelAndView novoCadastroGame(Model model) {
		
		model.addAttribute("plataformas", plataformaService.consultarPlataformas());
 
		/*OBJETO QUE VAMOS ATRIBUIR OS VALORES DOS CAMPOS*/
		model.addAttribute("gameModel", new GameModel());
 
	    return new ModelAndView("novoCadastroGame");
	}
 
 
 
	/***
	 * SALVA UM NOVO USUÁRIO NO SISTEMA
	 * @param usuarioModel
	 * @param result
	 * @param model
	 * @param redirectAttributes
	 * @return
	 * @throws Exception 
	 */
	@RequestMapping(value="/salvarGame", method= RequestMethod.POST)
	public ModelAndView salvarGame(@ModelAttribute 
								@Valid GameModel gameModel, 
								final BindingResult result,
								Model model,
								RedirectAttributes redirectAttributes) throws BadCredentialsException{
		
		/*VERIFICA SE TEM ALGUM ERRO (@NotEmpty),
		 *SE TIVER ALGUM ERRO DEVEMOS RETORNAR PARA A MESMA PÁGINA PARA O USUÁRIO CORRIGIR*/
		if(result.hasErrors()){
			
			List<PlataformaModel> plataformaModel = plataformaService.consultarPlataformas();
			
			plataformaModel.forEach(plataforma ->{
 
				if(gameModel.getPlataformas() != null && gameModel.getPlataformas().size() > 0){
 
					gameModel.getPlataformas().forEach(plataformaSelecionado ->{
						
						if(plataformaSelecionado != null){
							if(plataforma.getCodigo().equals(plataformaSelecionado))
								plataforma.setChecked(true);
						}					
					});				
				}
 
			});
 
			/*ADICIONA O GRUPOS QUE VÃO SER MOSTRADOS NA PÁGINA*/
			model.addAttribute("plataformas", plataformaModel);
			
			/*ADICIONA OS DADOS DO USUÁRIO PARA COLOCAR NO FORMULÁRIO*/
			model.addAttribute("gameModel", gameModel);
 
			/*RETORNA A VIEW*/
			return new ModelAndView("novoCadastroGame");	
		} else{
			/*SALVANDO UM NOVO REGISTRO*/
			gameService.salvarGame(gameModel);
		}
		
		ModelAndView modelAndView = new ModelAndView("redirect:/game/novoCadastroGame");
 
		/*PASSANDO O ATRIBUTO PARA O ModelAndView QUE VAI REALIZAR 
		 * O REDIRECIONAMENTO COM A MENSAGEM DE SUCESSO*/
		redirectAttributes.addFlashAttribute("msg_resultado", "Registro salvo com sucesso!");
		
		/*REDIRECIONANDO PARA UM NOVO CADASTRO*/
		return modelAndView;
	}
	
	/***
	 * CONSULTA TODOS USUÁRIOS CADASTRADOS NO SISTEMA
	 * @param model
	 * @return
	 */
	@RequestMapping(value="/consultarGame", method= RequestMethod.GET)	
	public ModelAndView consultarGame(Model model) {
 
		/*CONSULTA USUÁRIOS CADASTRADOS*/
		model.addAttribute("gamesModel", this.gameService.consultarGames());
 
		/*RETORNA A VIEW*/
	    return new ModelAndView("consultarGames");
	}
	
	/***
	 * EXCLUI UM REGISTRO DO SISTEMA PELO CÓDIGO
	 * @param codigoUsuario
	 * @return
	 */
	@RequestMapping(value="/excluirGame", method= RequestMethod.POST)
	public ModelAndView excluirGame(@RequestParam("codigoGame") Long codigoGame){
 
		ModelAndView modelAndView = new ModelAndView("redirect:/game/consultarGame");
 
		/*EXCLUINDO O REGISTRO*/
		this.gameService.excluir(codigoGame);
 
		/*RETORNANDO A VIEW*/
		return modelAndView;
	}
	
	/***
	 * CONSULTA UM USUÁRIO PELO CÓDIGO PARA REALIZAR ALTERAÇÕES NAS INFORAMÇÕES CADASTRADAS.
	 * @param codigoUsuario
	 * @param model
	 * @return
	 */
	@RequestMapping(value="/editarCadastroGame", method= RequestMethod.GET)		
	public ModelAndView editarCadastroGame(@RequestParam("codigoGame") Long codigoGame, Model model) {
		
		/*CONSULTA O USUÁRIO PELO CÓDIGO*/
		GameModel gameModel = this.gameService.consultarGame(codigoGame);
		
		List<PlataformaModel> plataformaModel = plataformaService.consultarPlataformas();
		
		plataformaModel.forEach(plataforma ->{
			    gameModel.getPlataformas().forEach(plataformaCadastrado -> {
			    	
			 	if(plataformaCadastrado!= null){
				    if(plataforma.getCodigo().equals(plataformaCadastrado))
				    	plataforma.setChecked(true);
				}					
		    });				
 
		});
		
		model.addAttribute("plataformas", plataformaModel);
 
		/*ADICIONANDO INFORMAÇÕES DO USUÁRIO PARA MOSTRAR NA PÁGINA(VIEW)*/
		model.addAttribute("gameModel", gameModel);
 
		/*CHAMA A VIEW /src/main/resources/templates/editarCadastro.html*/
	    return new ModelAndView("novoCadastroGame");
	 }
 
         /***
	 * SALVA AS ALTERAÇÕES REALIZADAS NO CADASTRO DO USUÁRIO
	 * @param usuarioModel
	 * @param result
	 * @param model
	 * @param redirectAttributes
	 * @return
	 */
	@RequestMapping(value="/salvarAlteracaoGame", method= RequestMethod.POST)
	public ModelAndView salvarAlteracaoGame(@ModelAttribute 
					    @Valid GameModel gameModel, 
					    final BindingResult result,
					    Model model,
					   RedirectAttributes redirectAttributes){
 
		boolean isErroNullCampos = false;
 
		/*AQUI ESTAMOS VERIFICANDO SE TEM ALGUM CAMPO QUE NÃO ESTÁ PREENCHIDO,
		 * MENOS O CAMPO DA SENHA, POIS SE O USUÁRIO NÃO INFORMAR VAMOS MANTER A
		 * SENHA JÁ CADASTRADA*/
		for (FieldError fieldError : result.getFieldErrors()) {
			isErroNullCampos = true;	
		}
 
		/*SE ENCONTROU ERRO DEVEMOS RETORNAR PARA A VIEW PARA QUE O 
		 * USUÁRIO TERMINE DE INFORMAR OS DADOS*/
		if(isErroNullCampos){
			
			List<PlataformaModel> plataformaModel = plataformaService.consultarPlataformas();			
			 
		    plataformaModel.forEach(plataforma ->{
 
		         if(gameModel.getPlataformas() != null && gameModel.getPlataformas().size() >0){
 
			     /*DEIXA CHECADO OS GRUPOS QUE O USUÁRIO SELECIONOU*/
		        	 gameModel.getPlataformas().forEach(plataformaSelecionado->{
 
			         if(plataformaSelecionado!= null){
	        	 		if(plataforma.getCodigo().equals(plataformaSelecionado))
							plataforma.setChecked(true);
			         	}
		             });	
			  }
 
		     });
 
		     /*ADICIONANDO GRUPOS PARA MOSTRAR NA PÁGINA(VIEW)*/
		     model.addAttribute("plataformas", plataformaModel);
			
		     /*ADICIONANDO O OBJETO usuarioModel PARA MOSTRAR NA PÁGINA(VIEW) AS INFORMAÇÕES DO USUÁRIO*/
		     model.addAttribute("gameModel", gameModel);
 
		     /*RETORNANDO A VIEW*/
		     return new ModelAndView("novoCadastroGame");	
		} else{
		     /*SALVANDO AS INFORMAÇÕES ALTERADAS DO USUÁRIO*/
		     gameService.alterarGame(gameModel);
		}
 
		/*APÓS SALVAR VAMOS REDIRICIONAR O USUÁRIO PARA A PÁGINA DE CONSULTA*/
		ModelAndView modelAndView = new ModelAndView("redirect:/game/consultarGame");
 
		/*RETORNANDO A VIEW*/
		return modelAndView;
	}
}
