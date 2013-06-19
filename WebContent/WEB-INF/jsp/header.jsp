<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>DBS 2013 - Wetterdaten</title>
<link href="<%= config.getServletContext().getContextPath() %>/css/bootstrap.css" type="text/css" rel="stylesheet" />
<script src="<%= config.getServletContext().getContextPath() %>/js/jquery-2.0.2.min.js" type="text/javascript"></script>
<script src="<%= config.getServletContext().getContextPath() %>/js/bootstrap.js" type="text/javascript"></script>
<style>
body {
	padding-top: 60px;
}
</style>
<script type="text/javascript">
$(document).ready(function() {
	$('.typeahead').typeahead({
		source: function(query, process) {
			return $.get('<%= config.getServletContext().getContextPath() %>/AutoCompleteServlet', { suggest: query }, function (data) {
				process(data);
			});
		}
	});
});
</script>
</head>
<body>
<div class="navbar navbar-inverse navbar-fixed-top">
	<div class="navbar-inner">
	<div class="container">
	<a class="brand" href="<%= config.getServletContext().getContextPath() %>/index.jsp">DBS 2013</a>
	</div>
	</div>
</div>