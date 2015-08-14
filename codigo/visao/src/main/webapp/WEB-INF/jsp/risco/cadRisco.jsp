<%@ page contentType="text/html; charset=UTF-8" %>
<!-- Modal -->
<div class="modal fade" id="mCadRisco">
	<div class="modal-dialog" role="document">
		<div class="modal-content">
			<div class="modal-header">
				<button type="button" class="close" data-dismiss="modal"
					aria-label="Close">
					<span aria-hidden="true">&times;</span>
				</button>
				<h4 class="modal-title">Novo Risco</h4>
			</div>
			<div class="modal-body">
				<div id="caixaAlerta"
					class="alert alert-warning alert-dismissable">
					<h4>
						<i class="icon fa fa-warning"></i> Validação do formulário!
					</h4>
				</div>
				<!-- form start -->				
				<form id="frmRisco" method="post" class="form-horizontal"
					action="../service/risco/salvarRisco.rest" role="form">
						<input type="text" name="id" style="display: none;"
							id="hId" />
						<div class="form-group">
							<label for="tDescricao" class="col-sm-2 control-label">Descrição*</label> 
							<div class="col-sm-10">
								<input type="text"
									name="descricao" class="form-control input-sm" id="tDescricao" autofocus>
							</div>	
						</div>
						<div class="form-group">
							<label for="sStatus" class="col-sm-2 control-label">Status*</label>
							<div class="col-sm-4"> 
							<select
									id="sStatus" name="status" class="form-control input-sm">
									<option value="N">Novo</option>
									<option value="E">Em Andamento</option>
									<option value="F">Finalizado</option>
							</select>
							</div>
							<label for="sProbabilidade" class="col-sm-2 control-label">Probabilidade</label>
							<div class="col-sm-4"> 
							<select
									id="sProbabilidade" name="probabilidade" class="form-control input-sm">
									<option value="B">Baixa</option>
									<option value="M">Média</option>
									<option value="A">Alta</option>
							</select>
							</div>
						</div>
						<div class="form-group">
							<label for="sImpacto" class="col-sm-2 control-label">Impacto</label> 
							<div class="col-sm-4"> 
							<select
									id="sImpacto" name="impacto" class="form-control input-sm">
									<option value="B">Baixo</option>
									<option value="M">Médio</option>
									<option value="A">Alto</option>
							</select>
							</div>
							<label for="sResponsavel" class="col-sm-2 control-label">Responsável</label> 
							<div class="col-sm-4"> 
							<select
									id="sResponsavel" name="responsavel" class="form-control input-sm">
									<option value=""></option>
									<c:if test="${not empty usuarios}">
										<c:forEach var="usuario" items="${usuarios}">
											<option value="${usuario.id}-${usuario.nome}">${usuario.nome}</option>
										</c:forEach>
									</c:if>
							</select>
							</div>
						</div>
						<div class="form-group">
                      		<label for="tMitigacao" class="col-sm-2 control-label">Estratégia de Mitigação</label>
                      		<div class="col-sm-10">
                      		<textarea name="mitigacao" id="tMitigacao" class="form-control input-sm" rows="3" placeholder="Insira a estratégia de mitigação..."></textarea>
                      		</div>
                    	</div>
                    	<div class="form-group">
                      		<label for="tContingencia" class="col-sm-2 control-label">Plano de Contingência</label>
                      		<div class="col-sm-10">
                      		<textarea name="contingencia" id="tContingencia" class="form-control input-sm" rows="3" placeholder="Insira o plano de contingência..."></textarea>
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