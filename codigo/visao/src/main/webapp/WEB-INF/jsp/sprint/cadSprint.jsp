<!-- Colocar o calendário na frente do modal. Sem isso ele fica atrás no chrome. -->
<style>
.daterangepicker {z-index: 1151 !important;}
</style>
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
				<div style="display: none;"
					class="alert alert-warning alert-dismissable caixa-alerta">
					<h4>
						<i class="icon fa fa-warning"></i> Validação do formulário!
					</h4>
				</div>
				<form id="frmSprint" method="POST"
					action="../service/sprint" role="form">
					<div class="box-body">
						<input type="text" name="id" style="display: none;"
							id="hIdSprint" field="id"/>
						<div class="form-group">
							<label for="tNomeSprint">Nome*</label> <input type="text"
								name="nome" field="nome" class="form-control" id="tNomeSprint" autofocus>
						</div>						
						<!-- Date range -->
			            <div class="form-group">
			            	<label>Período do Sprint*</label>			
			                <div class="input-group">
			                	<div class="input-group-addon">
			                    	<i class="fa fa-calendar"></i>
			                	</div>
			                	<input name="periodoSprint" id="drPeriodoSprint" type="text" class="form-control pull-right" id="reservation">
			                </div>
			                <!-- /.input group -->
			            </div>
						<div class="form-group">
                      		<label for="tObjetivoSprint">Objetivo do Sprint</label>
                      		<textarea name="objetivoSprint" id="tObjetivoSprint" class="form-control" rows="3" placeholder="Insira o objetivo do Sprint..."></textarea>
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