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
import br.com.gameker.webapp.model.GrupoModel;
import br.com.gameker.webapp.model.UsuarioModel;
import br.com.gameker.webapp.repository.UsuarioRepository;
import br.com.gameker.webapp.service.GrupoService;
import br.com.gameker.webapp.service.UsuarioService;
 
/**
 * 
 * 
 *OBJETO RESPONSÁVEL POR REALIZAR AS ROTINAS DE USUÁRIO.
 * 
 */
@Controller
@RequestMapping("/usuario") 
public class UsuarioController {
	
	@Autowired
	private UsuarioRepository usuarioRepository;
 
	/**INJETANDO O OBJETO GrupoService*/
	@Autowired
	private GrupoService grupoService;
 
	/**INJETANDO O OBJETO UsuarioService*/
	@Autowired 
	private UsuarioService usuarioService;
 
 
 
	/***
	 * CHAMA A FUNCIONALIDADE PARA CADASTRAR UM NOVO USUÁRIO NO SISTEMA
	 * @param model
	 * @return
	 */
	@RequestMapping(value="/novoCadastro", method= RequestMethod.GET)	
	public ModelAndView novoCadastro(Model model) {
 
		/*LISTA DE GRUPOS QUE VAMOS MOSTRAR NA PÁGINA*/
		model.addAttribute("grupos", grupoService.consultarGrupos());
 
		/*OBJETO QUE VAMOS ATRIBUIR OS VALORES DOS CAMPOS*/
		model.addAttribute("usuarioModel", new UsuarioModel());
 
	    return new ModelAndView("novoCadastro");
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
	@RequestMapping(value="/salvarUsuario", method= RequestMethod.POST)
	public ModelAndView salvarUsuario(@ModelAttribute 
								@Valid UsuarioModel usuarioModel, 
								final BindingResult result,
								Model model,
								RedirectAttributes redirectAttributes) throws BadCredentialsException{
		
		
		/* VALIDACOES */
		
		//TAMANHO DO LOGIN
		if(!"".equals(usuarioModel.getLogin()) && usuarioModel.getLogin().length() < 4){
			result.addError(new ObjectError("tamanhoLogin", "tamanhoLogin"));
			model.addAttribute("tamanhoLogin", "O login deve ter no mínimo 4 caracteres!");
		}
		
		//TAMANHO DA SENHA
		if(!"".equals(usuarioModel.getSenha()) && usuarioModel.getSenha().length() < 6){
			result.addError(new ObjectError("tamanhoSenha", "tamanhoSenha"));
			model.addAttribute("tamanhoSenha", "A senha deve ter no mínimo 6 caracteres!");
		}
		
		//REGISTRO DUPLICADO
		UsuarioEntity usuario = usuarioRepository.findByLogin(usuarioModel.getLogin());
		if(usuario != null){
			result.addError(new ObjectError("erroDuplicidade", "erroDuplicidade"));
			model.addAttribute("erroDuplicidade", "Esse conta já existe!");
		}
		
		/*VERIFICA SE TEM ALGUM ERRO (@NotEmpty),
		 *SE TIVER ALGUM ERRO DEVEMOS RETORNAR PARA A MESMA PÁGINA PARA O USUÁRIO CORRIGIR*/
		
		if(result.hasErrors()){
//			List<GrupoModel> gruposModel =grupoService.consultarGrupos();			
// 
//			/*POSICIONANDO OS CKECKBOX SELECIONADOS
//			 * 
//			 * SE O SISTEMA ENCONTROU ALGUM ERRO DE VALIDAÇÃO DEVEMOS 
//			 * BUSCAR OS GRUPOS E MARCAR COMO SELECIONADO NOVAMENTE 
//			 * PARA MOSTRAR NÁ PÁGINAS DA FORMA QUE O USUÁRIO ENVIO A REQUEST*/
//			gruposModel.forEach(grupo ->{
// 
//				if(usuarioModel.getGrupos() != null && usuarioModel.getGrupos().size() >0){
// 
//					usuarioModel.getGrupos().forEach(grupoSelecionado->{
// 
//						/*DEVEMOS MOSTRAR NA PÁGINA OS GRUPOS COM O CHECKBOX SELECIONADO*/
//						if(grupoSelecionado!= null){
//							if(grupo.getCodigo().equals(grupoSelecionado))
//								grupo.setChecked(true);
//						}					
//					});				
//				}
// 
//			});
 
			/*ADICIONA O GRUPOS QUE VÃO SER MOSTRADOS NA PÁGINA*/
			//model.addAttribute("grupos", gruposModel);
 
			/*ADICIONA OS DADOS DO USUÁRIO PARA COLOCAR NO FORMULÁRIO*/
			model.addAttribute("usuarioModel", usuarioModel);
 
			/*RETORNA A VIEW*/
			return new ModelAndView("novoCadastro");	
		} else{
 
			/*SALVANDO UM NOVO REGISTRO*/
			usuarioService.salvarUsuario(usuarioModel);
		}
 
 
		ModelAndView modelAndView = new ModelAndView("redirect:/");
 
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
	@RequestMapping(value="/consultar", method= RequestMethod.GET)	
	public ModelAndView consultar(Model model) {
 
		/*CONSULTA USUÁRIOS CADASTRADOS*/
		model.addAttribute("usuariosModel", this.usuarioService.consultarUsuarios());
 
		/*RETORNA A VIEW*/
	    return new ModelAndView("consultarCadastros");
	}
	
	/***
	 * EXCLUI UM REGISTRO DO SISTEMA PELO CÓDIGO
	 * @param codigoUsuario
	 * @return
	 */
	@RequestMapping(value="/excluir", method= RequestMethod.POST)
	public ModelAndView excluir(@RequestParam("codigoUsuario") Long codigoUsuario){
 
		ModelAndView modelAndView = new ModelAndView("redirect:/usuario/consultar");
 
		/*EXCLUINDO O REGISTRO*/
		this.usuarioService.excluir(codigoUsuario);
 
		/*RETORNANDO A VIEW*/
		return modelAndView;
	}
	
	/***
	 * CONSULTA UM USUÁRIO PELO CÓDIGO PARA REALIZAR ALTERAÇÕES NAS INFORAMÇÕES CADASTRADAS.
	 * @param codigoUsuario
	 * @param model
	 * @return
	 */
	@RequestMapping(value="/editarCadastro", method= RequestMethod.GET)		
	public ModelAndView editarCadastro(@RequestParam("codigoUsuario") Long codigoUsuario, Model model) {
 
		/*CONSULTA OS GRUPOS CADASTRADOS*/
		List<GrupoModel> gruposModel =grupoService.consultarGrupos();			
 
		/*CONSULTA O USUÁRIO PELO CÓDIGO*/
		UsuarioModel usuarioModel = this.usuarioService.consultarUsuario(codigoUsuario);
 
		/*ADICIONANDO INFORMAÇÕES DO USUÁRIO PARA MOSTRAR NA PÁGINA(VIEW)*/
		model.addAttribute("usuarioModel", usuarioModel);
 
		/*CHAMA A VIEW /src/main/resources/templates/editarCadastro.html*/
	    return new ModelAndView("editarCadastro");
	 }
 
         /***
	 * SALVA AS ALTERAÇÕES REALIZADAS NO CADASTRO DO USUÁRIO
	 * @param usuarioModel
	 * @param result
	 * @param model
	 * @param redirectAttributes
	 * @return
	 */
	@RequestMapping(value="/salvarAlteracao", method= RequestMethod.POST)
	public ModelAndView salvarAlteracao(@ModelAttribute 
					    @Valid UsuarioModel usuarioModel, 
					    final BindingResult result,
					    Model model,
					   RedirectAttributes redirectAttributes){
 
		boolean isErroNullCampos = false;
 
		/*AQUI ESTAMOS VERIFICANDO SE TEM ALGUM CAMPO QUE NÃO ESTÁ PREENCHIDO,
		 * MENOS O CAMPO DA SENHA, POIS SE O USUÁRIO NÃO INFORMAR VAMOS MANTER A
		 * SENHA JÁ CADASTRADA*/
		for (FieldError fieldError : result.getFieldErrors()) {
		    if(!fieldError.getField().equals("senha")){
		 	isErroNullCampos = true;	
		    }	
		}
 
		/*SE ENCONTROU ERRO DEVEMOS RETORNAR PARA A VIEW PARA QUE O 
		 * USUÁRIO TERMINE DE INFORMAR OS DADOS*/
		if(isErroNullCampos){
 
		    List<GrupoModel> gruposModel =grupoService.consultarGrupos();			
 
		    gruposModel.forEach(grupo ->{
 
		         if(usuarioModel.getGrupos() != null && usuarioModel.getGrupos().size() >0){
 
			     /*DEIXA CHECADO OS GRUPOS QUE O USUÁRIO SELECIONOU*/
			     usuarioModel.getGrupos().forEach(grupoSelecionado->{
 
			         if(grupoSelecionado!= null){
				    if(grupo.getCodigo().equals(grupoSelecionado))
				        grupo.setChecked(true);
			         }					
		             });				
			  }
 
		     });
 
		     /*ADICIONANDO GRUPOS PARA MOSTRAR NA PÁGINA(VIEW)*/
		     model.addAttribute("grupos", gruposModel);
 
		     /*ADICIONANDO O OBJETO usuarioModel PARA MOSTRAR NA PÁGINA(VIEW) AS INFORMAÇÕES DO USUÁRIO*/
		     model.addAttribute("usuarioModel", usuarioModel);
 
		     /*RETORNANDO A VIEW*/
		     return new ModelAndView("editarCadastro");	
		}
		else{
 
		     /*SALVANDO AS INFORMAÇÕES ALTERADAS DO USUÁRIO*/
		     usuarioService.alterarUsuario(usuarioModel);
 
		}
 
		/*APÓS SALVAR VAMOS REDIRICIONAR O USUÁRIO PARA A PÁGINA DE CONSULTA*/
		ModelAndView modelAndView = new ModelAndView("redirect:/usuario/consultar");
 
		/*RETORNANDO A VIEW*/
		return modelAndView;
	}
}
