/**
 * 
 */
package br.edu.ifpb.screamtool.visao.rest;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import br.edu.ifpb.screamtool.domain.entity.ItemBacklog;
import br.edu.ifpb.screamtool.domain.entity.Projeto;
import br.edu.ifpb.screamtool.domain.entity.Release;
import br.edu.ifpb.screamtool.service.negocio.ItemBacklogService;
import br.edu.ifpb.screamtool.service.negocio.ReleaseService;
import br.edu.ifpb.screamtool.visao.form.ReleaseForm;

/**
 * @author edsf
 *
 */
@RestController
@RequestMapping(value = "/release")
public class ReleaseRestService {

	@Autowired
	@Qualifier("releaseService")
	private ReleaseService releaseService;

	@Autowired
	@Qualifier("itemBacklogService")
	private ItemBacklogService itemBacklogService;

	@RequestMapping(method = RequestMethod.GET)
	public @ResponseBody ReleaseForm buscarReleases(HttpSession session) {

		ReleaseForm resultado = new ReleaseForm();

		// Aqui foi utilizado o HttpSession por que tive problemas com o
		// SessionAttributes no RestController
		Projeto projetoAberto = (Projeto) session.getAttribute("projetoAberto");

		List<Release> releases = releaseService.buscarPorProjeto(projetoAberto
				.getId());
		resultado.setReleases(releases);

		List<ItemBacklog> itensBacklogNaoAlocados = itemBacklogService
				.buscarTodosPorProdutoNaoAlocados(projetoAberto.getId());
		resultado.setItensBacklogNaoAlocados(itensBacklogNaoAlocados);

		return resultado;
	}

	@RequestMapping(method = RequestMethod.POST)
	public @ResponseBody Release salvarRelease(@RequestBody Release release,
			HttpSession session) {

		// Aqui foi utilizado o HttpSession por que tive problemas com o
		// SessionAttributes no RestController
		Projeto projetoAberto = (Projeto) session.getAttribute("projetoAberto");

		release.setProjeto(projetoAberto);

		Release resultado = null;

		if (release.getId() == null) {
			resultado = releaseService.criar(release);
		} else {
			resultado = releaseService.atualizar(release);
		}

		return resultado;
	}
}
