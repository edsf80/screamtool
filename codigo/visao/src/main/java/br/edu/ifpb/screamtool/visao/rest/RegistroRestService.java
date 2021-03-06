/**
 * 
 */
package br.edu.ifpb.screamtool.visao.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import br.edu.ifpb.screamtool.service.negocio.UsuarioService;
import br.edu.ifpb.screamtool.service.vo.RegistroUsuarioVO;
import br.edu.ifpb.screamtool.visao.exception.ResponseEntity;

/**
 * @author edsf
 *
 */
@RestController
public class RegistroRestService {

	@Autowired
	@Qualifier("usuarioService")
	private UsuarioService usuarioService;

	@RequestMapping(value = "/registro.rest", method = RequestMethod.POST)
	@ResponseBody
	public ResponseEntity<Boolean> registrarUsuario(
			@ModelAttribute RegistroUsuarioVO usuario) {

		Boolean resultado = new Boolean(
				usuarioService.registrarUsuario(usuario));

		ResponseEntity<Boolean> responseEntity = new ResponseEntity<Boolean>(
				resultado, 200);

		return responseEntity;

	}
}
