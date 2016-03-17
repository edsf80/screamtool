/**
 * 
 */
package br.edu.ifpb.screamtool.visao.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.SessionAttributes;

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
@RequestMapping(value = "/risco")
@SessionAttributes("projetoAberto")
public class RiscoRestService {

	@Autowired
	@Qualifier("riscoService")
	private RiscoService riscoService;

	@PreAuthorize("hasRole('perm_salvar_risco')")
	@RequestMapping(method = RequestMethod.POST)
	public @ResponseBody Risco salvarRisco(@ModelAttribute RiscoForm riscoForm,
			@ModelAttribute("projetoAberto") Projeto projetoAberto) {

		// Aqui foi utilizado o HttpSession por que tive problemas com o
		// SessionAttributes no RestController
		//Projeto projetoAberto = (Projeto) session.getAttribute("projetoAberto");

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
		risco.setImpacto(riscoForm.getImpacto());
		risco.setMitigacao(riscoForm.getMitigacao().isEmpty() ? null
				: riscoForm.getMitigacao());
		risco.setProbabilidade(riscoForm.getProbabilidade());
		risco.setProjeto(projetoAberto);
		risco.setResponsavel(responsavel);
		risco.setStatus(riscoForm.getStatus());

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
	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public @ResponseBody Risco buscarPorId(@PathVariable("id") Long id) {

		Risco risco = riscoService.buscarPorIdSemAssociacoes(id);

		return risco;

	}
}
