<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page contentType="text/html; ISO-8859-1;charset=UTF-8" %>

<html>
<head>
    <meta charset="UTF-8">
</head>
<body>


<form:form modelAttribute="treatmentPlan" method="post">

    <form:select path="date" items="${date}"/>
    <form:select path="time" items="${time}"/>
    <form:select path="treatmentStation" items="${treatmentStation}" itemLabel="nameStation" itemValue="id" />
    <form:select path="patient" items="${patients}" itemLabel="surName" itemValue="id"/>
    <input type="submit" value="wyslij"/>
    <input type="number" step="1" min="1" max="15" value="1" name="dayNumber">

</form:form>

<p>${uniqueError}</p>
<p>${sundayError}</p>
</body>
<a href="/procedure/add">Dodaj procedure</a>
<a href="/procedure/getall">Lista procedur</a>
<a href="/patients/getall">Lista pacjentów</a>
<a href="/patients/add">Dodaj pacjenta</a>
<a href="/stations/getall">Lista kabinek</a>
<a href="/stations/add">Dodaj kabinkę</a>
<a href="/plans/getall">Lista zabiegów</a>
<a href="/plans/add">Zaplanuj Pacjenta</a>
<a href="/articles/getall">Znajdź artykuł naukowy</a>
</html>