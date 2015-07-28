<!-- Modal -->
<div class="modal fade" id="mCadProduto">
	<div class="modal-dialog" role="document">
		<div class="modal-content">
			<div class="modal-header">
				<button type="button" class="close" data-dismiss="modal"
					aria-label="Close">
					<span aria-hidden="true">&times;</span>
				</button>
				<h4 class="modal-title">Novo Produto</h4>
			</div>
			<div class="modal-body">
				<!-- form start -->
				<div id="caixaAlerta" style="display: none;"
					class="alert alert-warning alert-dismissable">
					<h4>
						<i class="icon fa fa-warning"></i> Validação do formulário!
					</h4>
				</div>
				<form id="frmProduto" method="POST"
					action="../service/produto/salvarProduto.rest" role="form">
					<div class="box-body">
						<input type="text" name="id" style="display: none;"
							id="hIdProduto" />
						<div class="form-group">
							<label for="tDescricao">Descrição</label> <input type="text"
								name="descricao" class="form-control" id="tDescricao" autofocus>
						</div>
					</div>
			</div>
			<!-- /.modal-body -->
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