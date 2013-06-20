<%@ include file="WEB-INF/jsp/header.jsp" %>
<%@ page import="org.fu.berlin.dbs2013.Database,org.fu.berlin.dbs2013.data.Wettermessung" %>
<%
	final Database db = Database.getInstance();
	String ort = request.getParameter("ort");
	Wettermessung messung = null;
	if (ort != null) {
		ort = ort.substring(0, ort.lastIndexOf("(") - 1);
		messung = db.getLastWettermessung(ort);
	} else {
		response.sendRedirect(request.getContextPath() + "/index.jsp");
	}
%>
<div class="container">
<h1>DBS 2013 - Wetterdaten</h1>
<p class="lead">Ausgew&auml;hlte Wetterstation: <%= ort %></p>
<p>Wetterdaten vom Datum <%= messung.getDatum() %></p>
<table class="table">
<tr><th>Attribut</th><th>Wert</th></tr>
<tr><td>Mindestemperatur</td><td><%= messung.getMin_temp() %></td>
<tr><td>Durchschnittstemperatur</td><td><%= messung.getDurchschnitt_temp() %></td>
<tr><td>Maximaltemperatur</td><td><%= messung.getMax_temp() %></td>
<tr><td>Relative Feuchte</td><td><%= messung.getRelative_feuchte() %></td>
<tr><td>Durchschnittlicher Wind</td><td><%= messung.getMittel_wind() %></td>
<tr><td>Maximaler Wind</td><td><%= messung.getMax_wind() %></td>
<tr><td>Sonnenstunden</td><td><%= messung.getSonne() %></td>
<tr><td>Bedeckungsgrad</td><td><%= messung.getBedeckung() %></td>
<tr><td>Niederschlag</td><td><%= messung.getBedeckung() %></td>
<tr><td>Luftdruck</td><td><%= messung.getLuftdruck() %></td>
</table>
<p class="lead">Weitere Queries mit dieser Wetterstation:</p>
<ul>
<li><a href="<%= request.getContextPath() %>/getSunHours.jsp?id=<%= messung.getStations_ID() %>">Durchschnittliche Sonnenstunden</a></li>
</ul>
</div>
<%@ include file="WEB-INF/jsp/footer.jsp" %>