<%@ page contentType="text/html; charset=UTF-8" %>
<!-- Modal -->
<div class="modal fade" id="mCadItemBacklog">
	<div class="modal-dialog" role="document">
		<div class="modal-content">
			<div class="modal-header">
				<button type="button" class="close" data-dismiss="modal"
					aria-label="Close">
					<span aria-hidden="true">&times;</span>
				</button>
				<h4 class="modal-title">Novo Item de Backlog</h4>
			</div>
			<div class="modal-body">
				<div id="caixaAlerta"
					class="alert alert-warning alert-dismissable">
					<h4>
						<i class="icon fa fa-warning"></i> Validação do formulário!
					</h4>
				</div>
				<!-- form start -->				
				<form id="frmItemBacklog" method="POST"
					action="../service/itembacklog" role="form">
						<input type="text" name="id" style="display: none;"
							id="hIdItemBacklog" />
						<div class="form-group">
							<label for="tDescricao">Descrição*</label> <input type="text"
								name="descricao" class="form-control" id="tDescricao" autofocus>
						</div>
						<div class="form-group">
							<label for="sStatus">Status*</label> <select
									id="sStatus" name="status" class="form-control">
									<option value="N">Novo</option>
									<option value="P">Preparado</option>
							</select>
						</div>
						<div class="form-group">
							<label for="sPontos">Pontos</label> <select
									id="sPontos" name="storyPoints" class="form-control">
									<option value=""></option>
									<option value="2">2</option>
									<option value="8">8</option>
									<option value="13">13</option>
									<option value="20">20</option>
							</select>
						</div>						
						<div class="form-group">
                      		<label for="tEstoriaUsuario">Estória do Usuário*</label>
                      		<textarea name="estoriaUsuario" id="tEstoriaUsuario" class="form-control" rows="3" placeholder="Insira a estória do usuário..."></textarea>
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