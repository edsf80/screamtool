<!-- Modal -->
<div class="modal fade" id="mCadRelease">
	<div class="modal-dialog" role="document">
		<div class="modal-content">
			<div class="modal-header">
				<button type="button" class="close" data-dismiss="modal"
					aria-label="Close">
					<span aria-hidden="true">&times;</span>
				</button>
				<h4 class="modal-title">Novo Release</h4>
			</div>
			<div class="modal-body">
				<div style="display: none;"
					class="alert alert-warning alert-dismissable caixa-alerta">
					<h4>
						<i class="icon fa fa-warning"></i> Validação do formulário!
					</h4>
				</div>
				<form id="frmRelease" method="POST"
					action="../service/releases" role="form">
					<div class="box-body">
						<input type="text" name="id" style="display: none;"
							id="hIdRelease" />
						<div class="form-group">
							<label for="tNomeRelease">Nome*</label> <input type="text"
								name="nome" field="nome" class="form-control" id="tNomeRelease" autofocus>
						</div>
						<div class="form-group">
							<label for="tDescricaoRelease">Descrição*</label> <input type="text"
								name="descricao" field="descricao" class="form-control" id="tDescricaoRelease" autofocus>
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