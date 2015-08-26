/**
 * 
 */
package br.edu.ifpb.screamtool.visao.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;

import br.edu.ifpb.screamtool.domain.entity.Projeto;
import br.edu.ifpb.screamtool.service.vo.UsuarioVO;

/**
 * @author edsf
 *
 */
@Controller
@RequestMapping("/dashboard")
@SessionAttributes({ "usuario", "projetoAberto" })
public class DashboardController {

	/**
	 * Esse método deve existir por que caso nenhum projeto esteja aberto o
	 * método loadPage apresentará uma exceção. Assim, caso o projeto não esteja
	 * aberto o atributo do método será um projeto sem atributos.
	 * 
	 * @return Um projeto sem atributos.
	 */
	@ModelAttribute("projetoAberto")
	public Projeto createProjeto() {
		return new Projeto();
	}

	/**
	 * @param model
	 * @param idProjeto
	 * @param projetoAberto
	 * @param usuario
	 * @return
	 */
	@RequestMapping(method = RequestMethod.GET)
	public String loadPage(
			ModelMap model,
			@RequestParam(required = false, value = "projeto") String idProjeto,
			@ModelAttribute("projetoAberto") Projeto projetoAberto,
			// Esse atributo é capturado da sessão vide @SessionAttributes
			@ModelAttribute("usuario") UsuarioVO usuario) {

		if (idProjeto != null) {

			Projeto projetoSelecionado = null;

			for (Projeto projeto : usuario.getProjetos()) {
				if (projeto.getId().equals(Long.parseLong(idProjeto))) {
					projetoSelecionado = projeto;
				}
			}

			// Se o usuário colocou o id de um projeto que não existe ou que ele
			// não pode ver o mesmo deverá ser redirecionado para a página
			// principal.
			if (projetoSelecionado != null) {
				model.addAttribute("projetoAberto", projetoSelecionado);
			} else {
				model.remove("projetoAberto");
			}
		} else {
			// Isso siginifica que nenhum projeto está aberto. Deve redirecionar
			// para página principal sem abrir o menu do projeto. Tira o projeto
			// do modelo que fica na sessão para que não fique sem projeto
			// aberto e o menu do projeto aparecendo sem nome de projeto.
			if (projetoAberto.getId() == null) {
				model.remove("projetoAberto");
			}
		}

		return "main";
	}
}
