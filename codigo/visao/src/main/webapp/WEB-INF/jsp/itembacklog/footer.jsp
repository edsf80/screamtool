<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="sec"	uri="http://www.springframework.org/security/tags"%>
<sec:authorize access="hasRole('perm_salvar_item_backlog')">
<script type="text/javascript">
$(function() {
	$("#bAdItemBacklog").removeClass("disabled");
});
</script>
</sec:authorize>
<script src="../dist/js/pages/itembacklog.js"></script>