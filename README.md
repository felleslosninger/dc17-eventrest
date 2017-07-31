# dc17-eventrest

Rest api for Eventloggen til IDporten.

# Installasjon

* Pull master
* Kjør mvn spring-boot:run

    * NB: Krever OIDC med Idporten
   
# Endepunkter

* /event/{id} - Retunerer event med {id}
* /eventBySsn/ - Retunerer siste 10 Events for en person
* /getLatestEvents/ - Retunerer siste 10 Events sortert på time
* /getAuthTypeById/{id} - Retunerer autentiseringstype med {id}
* /getAllAuthTypes/ - Retunerer alle autentiseringstyper
* /getMostUsedAuthTypes/ - Retunerer alle autentiseringstyper en bruker har brukt, og hvor mange ganger hver av dem er brukt
* /getUnusedAuthTypes/ - Returnerer alle autentiseringstyper man ikke har brukt
* /getLogTypeById/{id} - Returnerer Logtype med {id}
* /getUsedServices/ - Retunerer brukte og ikke brukte servicer
* /getRecentUserActivity/ - Retunerer nylig aktivitet for en bruker
* /getRecentPublicActivity/ - Retunerer nylig aktivitet som "det offentlige" har hentet om en person
* /getPostBoks/ - Retunerer hvilken postboks brukeren har ("e-Boks" eller "Digipost") eller en tom streng


