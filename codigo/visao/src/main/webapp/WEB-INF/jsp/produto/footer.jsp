<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="sec"	uri="http://www.springframework.org/security/tags"%>
<sec:authorize access="hasRole('perm_salvar_produto')">
<div class="perm_salvar_produto" style="display: none;">S</div>
</sec:authorize>
<script src="../js/produto.min.js" type="text/javascript"></script>
