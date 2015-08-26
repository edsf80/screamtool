<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!-- Modal -->
<div class="modal fade" id="mCadProjeto">
	<div class="modal-dialog" role="document">
		<div class="modal-content">
			<div class="modal-header">
				<button type="button" class="close" data-dismiss="modal"
					aria-label="Close">
					<span aria-hidden="true">&times;</span>
				</button>
				<h4 class="modal-title">Novo Projeto</h4>
			</div>
			<div class="modal-body">
				<div id="caixaAlerta" style="display: none;"
					class="alert alert-warning alert-dismissable">
					<h4>
						<i class="icon fa fa-warning"></i> Validação do formulário!
					</h4>
				</div>
				<form id="frmProjeto" method="POST"
					action="../service/projeto" role="form">
					<div class="box-body">
						<input type="text" name="id" style="display: none;"
							id="hIdProjeto" />
						<div class="form-group">
							<label for="tNomeProjeto">Nome*</label> <input type="text"
								name="nome" class="form-control" id="tNomeProjeto" autofocus>
						</div>
						<div class="form-group">
							<label class="control-label" for="tDataInicio">Data de
								início*</label>
							<div class="input-group">
								<div class="input-group-addon">
									<i class="fa fa-calendar"></i>
								</div>
								<input type="text" class="form-control pull-right"
									name="dataInicio" id="tDataInicio" />
							</div>
							<!-- /.input group -->
						</div>
						<!-- /.form group -->
						<div class="form-group">
							<label class="control-label" for="sProdutos">Produto*</label> <select
								id="sProdutos" name="idProduto" class="form-control">
								<option value="">Selecione...</option>
								<c:if test="${not empty produtos}">
									<c:forEach var="produto" items="${produtos}">
										<option value="${produto.id}-${produto.descricao}">${produto.descricao}</option>
									</c:forEach>
								</c:if>
							</select>
						</div>
					</div>
			</div>
			<div class="modal-footer">
				<button type="button" id="bCancelar"
					class="btn btn-default pull-left" data-dismiss="modal">Cancelar</button>
				<button type="submit" id="bSalvar" class="btn btn-primary">Salvar</button>
			</div>
			</form>
			<div class="overlay" style="display: none;">
				<i class="fa fa-refresh fa-spin"></i>
			</div>
		</div>
	</div>
</div>
<!-- Modal -->