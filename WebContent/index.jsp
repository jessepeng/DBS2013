<%@ include file="WEB-INF/jsp/header.jsp" %>
<div class="container">
	<h1>DBS 2013 - Wetterdaten</h1>
	<p class="lead">Willkommen zu unserer Wetterdatenbank für das Datenbanksysteme-Projekt 2013.</p>
	<p>Bitte eine PLZ oder den Namen einer Stadt eingeben, für die Wetterdaten abgerufen werden sollen:</p>
	<form action="getWeather.jsp" method="POST" class="form-inline">
	<input type="text" name="ort" data-provide="typeahead" class="typeahead" data-items="8" autocomplete="off"/>
	<button type="submit" class="btn">Suche</button>
	</form>
</div>
<%@ include file="WEB-INF/jsp/footer.jsp" %>