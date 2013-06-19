<%@ include file="../WEB-INF/jsp/header.jsp" %>
<div class="container">
	<h1>DBS 2013 - Admin</h1>
	<p class="lead">Administratortools</p>
	<% 
	String successParam = request.getParameter("success");
	if (successParam != null) {
		boolean success = Boolean.valueOf(successParam);
		String div = success ? "alert alert-success" : "alert alert-error";
		String message = success ? "Befehl wurde erfolgreich ausgef&uuml;hrt." : "Befehl wurde nicht erfolgreich ausgef&uuml;hrt.";
		%><div class="<%= div %>"><strong><%= message %></strong</div><%
	}
		%>
	<p>Bitte w&auml;hle eine der folgenden Aktionen:</p>
	<ul>
	<li><a href="<%= config.getServletContext().getContextPath() %>/admin/AdminServlet?command=connectStations">Orte mit Wetterstationen verknüpfen</a></li>
	<li><a href="<%= config.getServletContext().getContextPath() %>/admin/index.jsp?success=true">Erfolgreich</a></li>
	<li><a href="<%= config.getServletContext().getContextPath() %>/admin/index.jsp?success=false">Nicht erfolgreich</a></li>
	</ul>
</div>
<%@ include file="../WEB-INF/jsp/footer.jsp" %>