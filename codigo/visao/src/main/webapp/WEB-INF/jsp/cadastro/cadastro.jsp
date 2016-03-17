<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles" %>
<%@ taglib uri="http://tiles.apache.org/tags-tiles-extras" prefix="tilesx" %>
<tilesx:useAttribute id="list" name="modais" classname="java.util.List" />
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
	<title>ScreamTool | <tiles:getAsString name="titulo"/></title>
	<meta
	content='width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no'
	name='viewport'>

	<link href="../css/main.min.css" rel="stylesheet" type="text/css" />
	<!-- Bootstrap 3.3.4 -->
    <!-- <link href="../bootstrap/css/bootstrap.min.css" rel="stylesheet" type="text/css" /> -->
    <!-- Font Awesome Icons -->
    <!-- <link href="../bootstrap/css/font-awesome-4.3.css" rel="stylesheet" type="text/css" /> -->
    <!-- Ionicons -->
    <!-- <link href="https://code.ionicframework.com/ionicons/2.0.1/css/ionicons.min.css" rel="stylesheet" type="text/css" /> -->
    <!-- Datatables -->
    <!-- <link href="../plugins/datatables/jquery.dataTables.css" rel="stylesheet" type="text/css" /> -->
    <!-- Theme style -->
    <!-- <link href="../dist/css/AdminLTE.min.css" rel="stylesheet" type="text/css" /> -->
    <!-- AdminLTE Skins. Choose a skin from the css/skins 
         folder instead of downloading all of them to reduce the load. -->
    <!-- <link href="../dist/css/skins/_all-skins.min.css" rel="stylesheet" type="text/css" /> -->
	<!-- HTML5 Shim and Respond.js IE8 support of HTML5 elements and media queries -->
	<!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
	<!--[if lt IE 9]>
        <script src="https://oss.maxcdn.com/html5shiv/3.7.2/html5shiv.min.js"></script>
        <script src="https://oss.maxcdn.com/respond/1.4.2/respond.min.js"></script>
    <![endif]-->
<style type="text/css">
.modal-content .overlay, .overlay-wrapper .overlay {
	z-index: 50;
	background: rgba(255, 255, 255, 0.7);
	border-radius: 3px;
}

.modal-content>.overlay, .overlay-wrapper>.overlay, .modal>.loading-img,
	.overlay-wrapper>.loading-img {
	position: absolute;
	top: 0;
	left: 0;
	width: 100%;
	height: 100%;
}

.modal-content .overlay {
	z-index: 1010;
	background: rgba(255, 255, 255, 0.7);
	border-radius: 3px;
}

.modal-content .overlay>.fa {
	position: absolute;
	top: 50%;
	left: 50%;
	margin-left: -15px;
	margin-top: -15px;
	color: #000;
	font-size: 30px;
}

.modal-content .overlay.dark {
	background: rgba(0, 0, 0, 0.5);
}  
</style>
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
									    <li><a href="teste.htm"><i class="fa fa-circle-o"></i>
												Testes</a></li>
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
		              <a href="release.htm">
		                <i class="fa fa-gift"></i> <span>Releases</span>
		              </a>
		            </li>
		            <li>
		              <a href="planoentrega.htm">
		                <i class="fa fa-gift"></i> <span>Plano de Entregas</span>
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

	<c:forEach var="modal" items="${list}">
  		<tiles:insertAttribute value="${modal}" flush="true" />
  		  
	</c:forEach>	

	<tiles:insertAttribute name="footer" />	
</body>
</html>