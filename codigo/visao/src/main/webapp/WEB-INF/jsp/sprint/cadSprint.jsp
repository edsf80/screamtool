<!-- Modal -->
<div class="modal fade" id="mCadSprint">
	<div class="modal-dialog" role="document">
		<div class="modal-content">
			<div class="modal-header">
				<button type="button" class="close" data-dismiss="modal"
					aria-label="Close">
					<span aria-hidden="true">&times;</span>
				</button>
				<h4 class="modal-title">Novo Sprint</h4>
			</div>
			<div class="modal-body">
				<div id="caixaAlerta" style="display: none;"
					class="alert alert-warning alert-dismissable">
					<h4>
						<i class="icon fa fa-warning"></i> Validação do formulário!
					</h4>
				</div>
				<form id="frmSprint" method="POST"
					action="../service/sprint" role="form">
					<div class="box-body">
						<input type="text" name="id" style="display: none;"
							id="hIdSprint" />
						<div class="form-group">
							<label for="tDescricaoSprint">Descrição*</label> <input type="text"
								name="descricao" field="descricao" class="form-control" id="tDescricaoSprint" autofocus>
						</div>						
						<div class="form-group">
							<label class="control-label" for="tDataInicio">Início*</label>
							<div class="input-group">
								<div class="input-group-addon">
									<i class="fa fa-calendar"></i>
								</div>
								<input type="text" class="form-control pull-right"
									name="dataInicio" id="tDataInicio" />
							</div>
							<!-- /.input group -->
						</div>
						<div class="form-group">
							<label class="control-label" for="tDataTermino">Término*</label>
							<div class="input-group">
								<div class="input-group-addon">
									<i class="fa fa-calendar"></i>
								</div>
								<input type="text" class="form-control pull-right"
									name="dataTermino" id="tDataTermino" />
							</div>
							<!-- /.input group -->
						</div>
					</div>
			</div>
			<div class="modal-footer">
				<button type="button" id="bCancelar"
					class="btn btn-primary pull-left" data-dismiss="modal">Cancelar</button>
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