<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page contentType="text/html; ISO-8859-1;charset=UTF-8" %>

<html>
<head>
    <meta charset="UTF-8">
</head>
<body>

<form:form method="post" modelAttribute="procedure">
    <label>Nazwa Procedury:</label><br>
    <form:input path="procedureName" id="addProcedureTest"/><form:errors path="procedureName"/><br>
    <label>Czas procedury</label><br>
    <form:input path="procedureTime" id="addProcedureTest2"/><form:errors path="procedureTime"/><br>
    
    <input type="submit" value="wyślij" id="addProcedureButton"/>
</form:form>

<a href="/procedure/add">Dodaj procedure</a>
<a href="/procedure/getall">Lista procedur</a>
<a href="/patients/getall">Lista pacjentów</a>
<a href="/patients/add">Dodaj pacjenta</a>
<a href="/stations/getall">Lista kabinek</a>
<a href="/stations/add">Dodaj kabinkę</a>
<a href="/plans/getall">Lista zabiegów</a>
<a href="/plans/add">Zaplanuj Pacjenta</a>
<a href="/articles/getall">Znajdź artykuł naukowy</a>

</body>
</html>