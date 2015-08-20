<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="sec"	uri="http://www.springframework.org/security/tags"%>
<sec:authorize access="hasRole('perm_salvar_item_backlog')">
<div class="perm_salvar_item_backlog" style="display: none;">S</div>
</sec:authorize>
<script src="../js/itembacklog.js"></script>