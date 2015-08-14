/**
 * 
 */
package br.edu.ifpb.screamtool.visao.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;

import br.edu.ifpb.screamtool.domain.entity.Projeto;
import br.edu.ifpb.screamtool.domain.entity.Risco;
import br.edu.ifpb.screamtool.domain.entity.Usuario;
import br.edu.ifpb.screamtool.service.negocio.RiscoService;
import br.edu.ifpb.screamtool.service.negocio.UsuarioService;

/**
 * @author edsf
 *
 */
@Controller
@RequestMapping("/private/risco")
@SessionAttributes("projetoAberto")
public class RiscoController {

	/**
	 * 
	 */
	@Autowired
	@Qualifier("riscoService")
	private RiscoService riscoService;

	@Autowired
	@Qualifier("usuarioService")
	private UsuarioService usuarioService;

	/**
	 * @return
	 */
	@ModelAttribute("projetoAberto")
	public Projeto createProjeto() {
		return new Projeto();
	}

	/**
	 * @param model
	 * @param projetoAberto
	 * @return
	 */
	@RequestMapping(method = RequestMethod.GET)
	public String loadPage(ModelMap model,
			@ModelAttribute("projetoAberto") Projeto projetoAberto) {

		String resultado;

		if (projetoAberto.getId() == null) {
			model.remove("projetoAberto");
			resultado = "main";
		} else {

			List<Risco> riscos = riscoService
					.buscarTodosPorProjeto(projetoAberto.getId());

			List<Usuario> usuarios = usuarioService
					.buscarTodosPorProjeto(projetoAberto.getId());

			model.addAttribute("riscos", riscos);
			model.addAttribute("usuarios", usuarios);

			resultado = "risco";
		}

		return resultado;
	}
}
