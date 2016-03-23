<%@ page contentType="text/html; charset=UTF-8" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="sec"	uri="http://www.springframework.org/security/tags"%>
<!-- Content Header (Page header) -->
<section class="content-header">
	<h1>
		Riscos Cadastrados <small>Escolha o risco que deseja
			alterar</small>
	</h1>
	<ol class="breadcrumb">
		<li><a href="./main.htm"><i class="fa fa-dashboard"></i>
				Home</a></li>
		<li class="active">Riscos</li>
	</ol>
</section>

<!-- Main content -->
<section class="content">
	<div class="row">
		<div class="col-xs-12">
			<div class="box box-primary">
				<div class="box-header">
					<div class="input-group">
							<button id="bAdRisco" class="btn btn-sm btn-primary disabled">Novo
								Risco</button>
					</div>
				</div>
				<!-- /.box-header -->
				<div class="box-body">
					<table id="tRiscos" class="table table-bordered table-striped"
						cellspacing="0">
						<thead>
							<tr>
								<th>id</th>
								<th>Descrição</th>
								<th>Status</th>
								<th>Responsável</th>
								<th>Opções</th>
							</tr>
						</thead>

						<c:if test="${not empty riscos}">
							<tbody>
								<c:forEach var="risco" items="${riscos}">
									<tr>
										<td>${risco.id}</td>
										<td>${risco.descricao}</td>
										<td>
											<c:choose>
  												<c:when test="${risco.status == 'N'}">
													<span class="label label-danger">Novo</span>
												</c:when>
												<c:when test="${risco.status == 'E'}">
													<span class="label label-primary">Em Andamento</span>
												</c:when>
												<c:when test="${risco.status == 'F'}">
													<span class="label label-success">Finalizado</span>
												</c:when>
											</c:choose>	
										</td>
										<td>
											<c:if test="${not empty risco.responsavel}">	
												${risco.responsavel.nome}
											</c:if>	
										</td>
										<td>
											<button id="bAltRisco" type="button"
												class="btn btn-xs btn-default disabled">
												<i class='fa fa-edit'></i>
											</button>
										</td>
									</tr>
								</c:forEach>
							</tbody>
						</c:if>

						<tfoot>
							<tr>
								<th>id</th>
								<th>Descrição</th>
								<th>Status</th>
								<th>Responsável</th>
								<th>Opções</th>
							</tr>
						</tfoot>
					</table>
				</div>
				<!-- /.box-body -->
			</div>
			<!-- /.box -->
		</div>
		<!-- /.col -->
	</div>
	<!-- /.row -->
</section>
<!-- /.content -->