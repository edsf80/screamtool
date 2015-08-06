<%@ page contentType="text/html; charset=UTF-8" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="sec"	uri="http://www.springframework.org/security/tags"%>
<!-- Content Header (Page header) -->
<section class="content-header">
	<h1>
		Itens de Backlog Cadastrados <small>Escolha o item de backlog que deseja
			alterar</small>
	</h1>
	<ol class="breadcrumb">
		<li><a href="./main.htm"><i class="fa fa-dashboard"></i>
				Home</a></li>
		<li class="active">Itens de Backlog</li>
	</ol>
</section>

<!-- Main content -->
<section class="content">
	<div class="row">
		<div class="col-xs-12">
			<div class="box box-primary">
				<div class="box-header">
					<h3 class="box-title"></h3>
					<div class="box-tools">
						<div class="input-group">
							<button id="bAdItemBacklog" class="btn btn-sm btn-primary disabled">Novo
								Item de Backlog</button>
						</div>
					</div>
				</div>
				<!-- /.box-header -->
				<div class="box-body">
					<table id="tItensBacklog" class="table table-bordered table-striped"
						cellspacing="0">
						<thead>
							<tr>
								<th>id</th>
								<th>Descrição</th>
								<th>Status</th>
								<th>Pontos</th>
								<th>Estória</th>
								<th></th>
							</tr>
						</thead>

						<c:if test="${not empty itensBacklog}">
							<tbody>
								<c:forEach var="itemBacklog" items="${itensBacklog}">
									<tr>
										<td>${itemBacklog.id}</td>
										<td>${itemBacklog.descricao}</td>
										<td>
											<c:choose>
  												<c:when test="${itemBacklog.status == 'N'}">
													<span class="label label-danger">NOVO</span>
												</c:when>
												<c:when test="${itemBacklog.status == 'P'}">
													<span class="label label-warning">PREPARADO</span>
												</c:when>
												<c:when test="${itemBacklog.status == 'A'}">
													<span class="label label-primary">EM ANDAMENTO</span>
												</c:when>
												<c:when test="${itemBacklog.status == 'A'}">
													<span class="label label-success">FINALIZADO</span>
												</c:when>
											</c:choose>	
										</td>
										<td>${itemBacklog.storyPoints}</td>
										<td>${itemBacklog.estoriaUsuario}</td>
										<td>
											<button id="bAltItemBacklog" type="button"
												class="btn btn-xs btn-default disabled">
												<i class='fa fa-edit'></i>
											</button>
											<button id="bExcItemBacklog" type="button"
												class="btn btn-xs btn-default disabled">
												<i class='fa fa-trash'></i>
											</button>
										</td>
									</tr>
								</c:forEach>
							</tbody>
						</c:if>

						<tfoot>
							<tr>
								<th>Id</th>
								<th>Descrição</th>
								<th>Status</th>
								<th>Pontos</th>
								<th>Estória</th>
								<th></th>
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