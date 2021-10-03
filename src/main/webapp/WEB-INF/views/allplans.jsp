<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html; ISO-8859-1;charset=UTF-8" %>

<html>
<head>
    <meta charset="UTF-8">
</head>
<body>

<c:forEach items="${treatmentPlanList}" var="plan">

    <p> ${plan.id} ${plan.date} ${plan.time} ${plan.patient} ${plan.treatmentStation}</p>




</c:forEach>

<p>Zabiegi na dziś:</p>
<c:forEach items="${treatmentPlanList2}" var="plan">

    <p> ${plan.id} ${plan.date} ${plan.time} ${plan.patient} ${plan.treatmentStation}</p>

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