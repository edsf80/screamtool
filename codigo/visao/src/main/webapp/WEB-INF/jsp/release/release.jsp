<%@ page contentType="text/html; charset=UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<!-- Content Header (Page header) -->
<section class="content-header">
	<h1>
		Releases <small>Control panel</small>
	</h1>
	<ol class="breadcrumb">
		<li><a href="#"><i class="fa fa-dashboard"></i> Home</a></li>
		<li class="active">Calendar</li>
	</ol>
</section>

<!-- Main content -->
<section class="content">
	<div class="row">
		<div class="col-md-3">
			<div class="box box-solid">
				<div class="box-header with-border">
					<h4 class="box-title">Itens de Backlog</h4>
				</div>
				<div class="box-body">
					<ul class="connectedSortable sortable1"
						style="list-style-type: none; margin: 0; padding: 0;">
						<c:if test="${not empty releaseForm}">
							<c:if test="${not empty releaseForm.itensBacklogNaoAlocados}">
								<c:forEach var="itemBacklog" items="${releaseForm.itensBacklogNaoAlocados}">
									<li id='item_${itemBacklog.id}' title='${itemBacklog.descricao}' class='external-event bg-yellow' style='margin: 3px 3px 3px 0; padding: 1px; float: left; width: 100px; height: 90px; text-align: center;'>
										${fn:substring(itemBacklog.descricao, 0, 20)}...<br><br>
										<select>
											<option>0</option>
											<option>1</option>
											<option>2</option>
											<option>3</option>
											<option>5</option>
										</select>
									</li>
								</c:forEach>
							</c:if>	
						</c:if>
					</ul>
				</div>
				<!-- /.box-body -->
			</div>
			<!-- /. box -->
		</div>
		<!-- /.col -->
		<div class="col-md-9">
			<div class="box box-primary">
				<div class="box-header">

					<div class="input-group">
						<button id="bAdRelease" class="btn btn-sm btn-primary">Novo
							Release</button>
						<div class="panel panel-default"></div>

					</div>
					<!-- /.box-header -->
					<div id="boxReleases" class="box-body no-padding">
						<c:if test="${not empty releaseForm}">
							<c:if test="${not empty releaseForm.releases}">
								<c:forEach var="release" items="${releaseForm.releases}">
									<div class="panel panel-default collapsed-panel">
										<div class="panel-heading"> 
				             				<div class="row">
				             					<div class="col-lg-5">${release.nome}</div>
				             					<div class="col-sm-3 pull-left"> 
						     						<div class="input-group">
						     							<button class="btn btn-sm btn-primary sprintButton" data-toggle="modal" data-target="#mCadSprint" data-release-id="${release.id}">
						     								Nova Sprint
						     							</button>
						     						</div>
						     					</div>
						     				</div>
						     			</div>
						     			<div class="panel-body itensSprint" style="width: 100%; heigth: 300px;">						
					             			<table class="table table-bordered">
					             				<tbody>
					             					<c:if test="${not empty release.sprints}">
					             						<c:forEach var="sprint" items="${release.sprints}">
					             							<tr id="sprint_${sprint.id}">
					             								<td>${sprint.nome}</td>
					             								<td style="height:40px;">
						    	  									<ul class="connectedSortable sortable2" style="list-style-type: none; width: 100%">
						    	  										<c:if test="${not empty sprint.itensBacklog}">
						    	  											<c:forEach var="itemBacklog" items="${sprint.itensBacklog}">
						    	  												<li id="item_${itemBacklog.id}" title="${itemBacklog.descricao}" class="external-event bg-yellow" style="margin: 3px 3px 3px 0; padding: 1px; float: left; width: 100px; height: 90px;  
					                                                                text-align: center;">
					                                                                ${fn:substring(itemBacklog.descricao, 0, 20)}...<br><br>
					                                                                <select>
					                                                                	<option>0</option>
					                  													<option>1</option>
					                  													<option>2</option>
					                  													<option>3</option>
					                  													<option>5</option>
					                  												</select>
					                  											</li>
						    	  											</c:forEach>
						    	  										</c:if>
						    	  									</ul>
						    	  								</td>
						    	  							</tr>
					             						</c:forEach>
					             					</c:if>
					             				</tbody>
					             			</table>
				             			</div>
				             		</div>
								</c:forEach>
							</c:if>
						</c:if>	
					</div>
					<!-- /.box-body -->
				</div>
				<!-- /. box -->
			</div>
			<!-- /.col -->
		</div>
		<!-- /.row -->
</section>
<!-- /.content -->
</div>
<!-- /.content-wrapper -->