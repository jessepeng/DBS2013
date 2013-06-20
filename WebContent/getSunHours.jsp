<%@ include file="WEB-INF/jsp/header.jsp" %>
<%@ page import="org.fu.berlin.dbs2013.Database,org.fu.berlin.dbs2013.data.Wettermessung" %>
<%
	final Database db = Database.getInstance();
	String id = request.getParameter("id");
	double sunHours = db.getAverageSunHours(Integer.valueOf(id));
%>
<div class="container">
<h1>DBS 2013 - Wetterdaten</h1>
<p class="lead">Durchschnittliche Sonnenstunden an der ausgew&auml;hlten Wetterstation:</p>
<p><%= sunHours %></p>
</div>
<%@ include file="WEB-INF/jsp/footer.jsp" %>