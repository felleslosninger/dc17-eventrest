# dc17-eventrest

Rest api for Eventloggen til IDporten.

# Installasjon

* Pull master
* Kjør mvn spring-boot:run


# Endepunkter

* /event/{id} - Finner et event på eventID
* /eventBySsn/{ssn} - Finner siste 10 Events for en person
* /getAuthTypeById/{id} - Finner autentiseringstype etter id 
* /getAllAuthTypes/ - Finner alle autentiseringstyper
* /getMostUsedAuthTypes/{ssn} - Finner mest brukte autentiseringstyper for en person
* /getLogTypeById/{id} - Finner Logtype etter id
* /isReserved/{ssn} - Finner om en person har reservert seg eller ikke
* /getUsedServices/{ssn} - Finner brukte og ikke brukte servicer
* /getRecentUserActivity/{ssn} - Finner nylig aktivitet for en bruker
* /getRecentPublicActivity/{ssn} - Finner nylig aktivitet som "det offentlige" har hentet om en person
