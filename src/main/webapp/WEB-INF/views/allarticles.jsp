<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html; ISO-8859-1;charset=UTF-8" %>

<html>
<head>
    <meta charset="UTF-8">
</head>
<body>
<p>lista artykulów z Google Scholar: </p>
<p>artykuły nie starsze niz 5 lat</p>
<form method="get" action="/articles/getall">
    <label>Wpisz tekst</label>
    <input type="text" name="keyWord">
    <input type="submit" value="Wyślij">
</form>
<c:forEach items="${elements}" var="element">

    <p>${element}</p>


</c:forEach>


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