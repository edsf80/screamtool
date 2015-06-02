/**
 * 
 */
package br.edu.ifpb.screamtool.visao.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import br.edu.ifpb.screamtool.service.negocio.UsuarioService;
import br.edu.ifpb.screamtool.service.vo.UsuarioVO;

/**
 * @author edsf
 *
 */
@RestController
@RequestMapping(value = "/service")
public class MainRestService {

	@Autowired
	@Qualifier("usuarioService")
	private UsuarioService usuarioService;

	@RequestMapping(value = "/buscarUsuarioSessao.rest", method = RequestMethod.GET)
	public @ResponseBody UsuarioVO buscarUsuarioAutenticado() {

		UsuarioVO resposta = usuarioService.buscarUsuarioLogado();

		return resposta;

	}
}
