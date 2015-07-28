<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="sec"	uri="http://www.springframework.org/security/tags"%>
<!-- Content Header (Page header) -->
<section class="content-header">
	<h1>
		Projetos Cadastrados <small>Escolha o projeto que deseja
			alterar</small>
	</h1>
	<ol class="breadcrumb">
		<li><a href="./main.htm"><i class="fa fa-dashboard"></i>
				Home</a></li>
		<li class="active">Projetos</li>
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
							<button id="bAdProjeto"
								class="btn btn-sm btn-primary disabled">Adicionar
								Projeto</button>
						</div>
					</div>
				</div>
				<!-- /.box-header -->
				<div class="box-body">
					<table id="tProjetos" class="table table-bordered table-striped"
						cellspacing="0">
						<thead>
							<tr>
								<th>Id</th>
								<th>Nome</th>
								<th>Id Produto</th>
								<th>Produto</th>
								<th></th>
							</tr>
						</thead>
						<c:if test="${not empty projetos}">
							<tbody>
								<c:forEach var="projeto" items="${projetos}">
									<tr>
										<td>${projeto.id}</td>
										<td>${projeto.nome}</td>
										<td>${projeto.produto.id}</td>
										<td>${projeto.produto.descricao}</td>
										<td>
											<button id="bAltProjeto" type="button"
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
								<th>Id</th>
								<th>Nome</th>
								<th>Id Produto</th>
								<th>Produto</th>
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