# MedicalRegistration

Projekt medyczna rejestracja to aplikacja służąca do zapisywania pacjentów na zabiegi fizjoterapeutyczne.
Po zapisaniu pacjenta do aplikacji można zaplanować mu zabiegi. Aplikacja w graficzny sposób przedstawia wolne terminy oraz przypisane zabiegi(kolorowanie selectow).
Jest także możliwość automatycznego planowania pacjenta na maksymalnie 15 dni.

Wykorzystane technologie: Java, SpringBoot, Hibernate, SpringData.
Baza danych: mySQL lub PostgreSQL

Podstawowa gałąź operuje na plikach jsp - dane przekazywane są do widoków.
Pozostałe gałęzie zwracają pliki typu JSON - REST API.
