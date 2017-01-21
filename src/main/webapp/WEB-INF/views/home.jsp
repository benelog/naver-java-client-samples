<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page session="false" %>
<html>
<head>
	<title>API call raw format</title>
</head>
<body>
<h1>login form</h1>
<div>
	<form method="post" action="/home">
		id:<input type="text" name="id"/>
		pw: <input type="password" name="pw"/>
		<input type="button" value="submit" onclick="form.submit()"/>
	</form>
</div>
<h1>API raw data</h1>
<table>
<c:forEach var='api' items='${results}'>
<tr>
	<td width="10%">
	${api.key}
	</td>
	<td width="90%">
	<textarea cols="120" rows="5">${api.value}</textarea>
	</td>
<tr>
</c:forEach>

</table>
</body>
</html>
