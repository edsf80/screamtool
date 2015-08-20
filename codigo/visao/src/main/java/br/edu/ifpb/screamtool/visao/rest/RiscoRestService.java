/**
 * 
 */
package br.edu.ifpb.screamtool.visao.rest;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import br.edu.ifpb.screamtool.domain.entity.Projeto;
import br.edu.ifpb.screamtool.domain.entity.Risco;
import br.edu.ifpb.screamtool.domain.entity.Usuario;
import br.edu.ifpb.screamtool.service.negocio.RiscoService;
import br.edu.ifpb.screamtool.visao.form.RiscoForm;

/**
 * @author edsf
 *
 */
@RestController
@RequestMapping(value = "/service/risco")
public class RiscoRestService {

	@Autowired
	@Qualifier("riscoService")
	private RiscoService riscoService;

	@PreAuthorize("hasRole('perm_salvar_risco')")
	@RequestMapping(value = "/salvarRisco.rest", method = RequestMethod.POST)
	public @ResponseBody Risco salvarRisco(@ModelAttribute RiscoForm riscoForm,
			HttpSession session) {

		// Aqui foi utilizado o HttpSession por que tive problemas com o
		// SessionAttributes no RestController
		Projeto projetoAberto = (Projeto) session.getAttribute("projetoAberto");

		Usuario responsavel = null;

		if (!riscoForm.getResponsavel().isEmpty()) {
			String[] dadosResponsavel = riscoForm.getResponsavel().split("-");
			responsavel = new Usuario();
			responsavel.setId(Long.parseLong(dadosResponsavel[0]));
			responsavel.setNome(dadosResponsavel[1]);
		}

		Risco risco = new Risco();
		risco.setId(riscoForm.getId());
		risco.setContingencia(riscoForm.getContingencia().isEmpty() ? null
				: riscoForm.getContingencia());
		risco.setDescricao(riscoForm.getDescricao().isEmpty() ? null
				: riscoForm.getDescricao());
		risco.setImpacto(Risco.NivelProbImpacto.B);
		risco.setMitigacao(riscoForm.getMitigacao().isEmpty() ? null
				: riscoForm.getMitigacao());
		risco.setProbabilidade(Risco.NivelProbImpacto.B);
		risco.setProjeto(projetoAberto);
		risco.setResponsavel(responsavel);
		risco.setStatus(Risco.RiscoStatus.N);

		if (riscoForm.getId() == null) {
			risco = riscoService.criar(risco);
		} else {
			risco = riscoService.atualizar(risco);
			risco.setProjeto(null);
			risco.setResponsavel(responsavel);
		}

		return risco;
	}

	/**
	 * @param id
	 * @return
	 */
	@PreAuthorize("hasRole('perm_consultar_risco')")
	@RequestMapping(value = "/buscarPorId.rest", method = RequestMethod.GET)
	public @ResponseBody Risco buscarPorId(@RequestParam("id") Long id) {

		Risco risco = riscoService.buscarPorIdSemAssociacoes(id);

		return risco;

	}
}
