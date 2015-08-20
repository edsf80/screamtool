<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
	<title>ScreamTool | <tiles:getAsString name="titulo"/></title>
	<tiles:insertAttribute name="header" />
</head>
<body class="skin-blue">
	<!-- Site wrapper -->
	<div class="wrapper">

		<header class="main-header">
			<a href="./main.htm" class="logo"><b>Scream</b>Tool</a>
			<!-- Header Navbar: style can be found in header.less -->
			<nav class="navbar navbar-static-top" role="navigation">
				<!-- Sidebar toggle button-->
				<a href="#" class="sidebar-toggle" data-toggle="offcanvas"
					role="button"> <span class="sr-only">Toggle navigation</span> <span
					class="icon-bar"></span> <span class="icon-bar"></span> <span
					class="icon-bar"></span>
				</a>
				<div class="navbar-custom-menu">
					<ul class="nav navbar-nav">
						<sec:authorize access="hasAnyRole('perm_consultar_produto', 'perm_consultar_projeto')">
							<li class="dropdown"><a href="#" class="dropdown-toggle"
								data-toggle="dropdown">Administração <span class="caret"></span></a>
								<ul class="dropdown-menu" role="menu">
									<sec:authorize access="hasRole('perm_consultar_produto')">
										<li><a href="produto.htm"><i class="fa fa-circle-o"></i>
												Produtos</a></li>
									</sec:authorize>
									<sec:authorize access="hasRole('perm_consultar_projeto')">
										<li><a href="projeto.htm"><i class="fa fa-circle-o"></i>
												Projetos</a></li>
									</sec:authorize>
								</ul>
							</li>
						</sec:authorize>
						<li class="dropdown"><a href="#" class="dropdown-toggle"
							data-toggle="dropdown">Meus Projetos <span class="caret"></span></a>
							<ul id="mProjetos" class="dropdown-menu" role="menu">
								<c:choose>
  									<c:when test="${not empty usuario.projetos}">
    									<c:forEach var="projeto" items="${usuario.projetos}">
											<li>
												<a href="./dashboard.htm?projeto=${projeto.id}"><i class="fa fa-circle-o"></i> ${projeto.nome}</a>
											</li>
										</c:forEach>			
  									</c:when>
  									<c:otherwise>
    									<li>Não há projetos associados</li>
 	 								</c:otherwise>
								</c:choose>
							</ul></li>
						<!-- User Account: style can be found in dropdown.less -->
						<li class="dropdown user user-menu"><a href="#"
							class="dropdown-toggle" data-toggle="dropdown"> <img
								src="../images/user.png" class="user-image" alt="User Image" />
								<span id="sNomeUsuario" class="hidden-xs">${usuario.nome}</span>
						</a>
							<ul class="dropdown-menu">
								<!-- User image -->
								<li class="user-header"><img src="../images/user.png"
									class="img-circle" alt="User Image" />
									<p id="pNomeUsuarioMenu">
										${usuario.nome}
										<small>Member since Nov. 2012</small>
									</p></li>
								<!-- Menu Body -->
								<li class="user-body">
									<div class="col-xs-4 text-center">
										<a href="#">Followers</a>
									</div>
									<div class="col-xs-4 text-center">
										<a href="#">Sales</a>
									</div>
									<div class="col-xs-4 text-center">
										<a href="#">Friends</a>
									</div>
								</li>
								<!-- Menu Footer-->
								<li class="user-footer">
									<div class="pull-left">
										<a href="#" class="btn btn-default btn-flat">Perfil</a>
									</div>
									<div class="pull-right">
										<a href="../logout.html" class="btn btn-default btn-flat">Sair</a>
									</div>
								</li>
							</ul></li>
					</ul>
				</div>
			</nav>	
		</header>

		<!-- =============================================== -->

		<!-- Left side column. contains the sidebar -->
		<aside class="main-sidebar">
			<!-- sidebar: style can be found in sidebar.less -->
			<section class="sidebar">
				<!-- Sidebar user panel -->
				<div class="user-panel">
					<div class="pull-left image">
						<img src="../images/user.png" class="img-circle"
							alt="User Image" />
					</div>
					<div class="pull-left info">
						<p id="pNomeUsuarioPainel">${usuario.nome}</p>

						<a href="#"><i class="fa fa-circle text-success"></i> Online</a>
					</div>
				</div>
				<!-- search form -->
				<form action="#" method="get" class="sidebar-form">
					<div class="input-group">
						<input type="text" name="q" class="form-control"
							placeholder="Pesquisar..." /> <span class="input-group-btn">
							<button type='submit' name='seach' id='search-btn'
								class="btn btn-flat">
								<i class="fa fa-search"></i>
							</button>
						</span>
					</div>
				</form>
				<!-- /.search form -->
				<c:if test="${not empty projetoAberto}">
				<!-- sidebar menu: : style can be found in sidebar.less -->
		          <ul class="sidebar-menu">
		            <li class="header">${projetoAberto.nome}</li>
		            <li>
		              <a href="dashboard.htm">
		                <i class="fa fa-dashboard"></i> <span>Dashboard</span>
		              </a>
		            </li>
		            <sec:authorize access="hasRole('perm_consultar_item_backlog')">
		            <li>
		              <a href="itembacklog.htm">
		                <i class="fa fa-newspaper-o"></i> <span>Backlog</span>
		              </a>
		            </li>
		            </sec:authorize>
		            <li>
		              <a href="widgets.html">
		                <i class="fa fa-gift"></i> <span>Releases</span>
		              </a>
		            </li>
		            <li>
		              <a href="widgets.html">
		                <i class="fa fa-fighter-jet"></i> <span>Sprints</span>
		              </a>
		            </li>
		            <li>
		              <a href="risco.htm">
		                <i class="fa fa-fire-extinguisher"></i> <span>Riscos</span>
		              </a>
		            </li>
		          </ul>
		        </c:if>  
		        </section>
		        <!-- /.sidebar -->
			</section>
			<!-- /.sidebar -->
		</aside>

		<!-- =============================================== -->

		<!-- Content Wrapper. Contains page content -->
		<div class="content-wrapper">
			<tiles:insertAttribute name="content" />			
		</div>
		
		<footer class="main-footer">
			<div class="pull-right hidden-xs">
				<b>Version</b> 2.0
			</div>
			<strong>Copyright &copy; 2014-2015 <a
				href="http://almsaeedstudio.com">Almsaeed Studio</a>.
			</strong> All rights reserved.
		</footer>
	</div>
	<!-- ./wrapper -->

	<tiles:insertAttribute name="modal" />	

	<tiles:insertAttribute name="footer" />
	
</body>
</html>