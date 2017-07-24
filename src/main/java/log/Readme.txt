Oppgave:

* Hvilken tjeneste som spør
* på vegene av hvilken bruker
* Hvilken accesstocen som blir brukt med
* beskrivelse av hva som hentes

*legge inn en ny configurasjonsfil til logback via denne propertien:
logging.config=

Tjenester:
Ingen tjenester er logget enda, men det er laget en feed. For å logge tjenester kan du fetche data fra
get-metodene. Les notatene under hver klasse.

For å logge inn:
1. Åpne postmann
2. Kjør eventrest
3. Kjør norgeprofil
4. Åpne norgeprofil-nettsiden
5. Skrive inn localhost:8080/user
6. Fyll ut feltene i postmann
    1. user: Authorization
    2. value: Bearer token
            MERK! token gis fra punkt 5, kan f.eks være localhost:4500/getMostUsedAuthTypes
    3. get: localhost:4500/eventservicemetode
            MERK! metode fra event service, f.eks getMostUsedAuthTypes



Hente all data fra:
http://camp.difi.local/phpmyadmin/navigation.php?token=777329cbbff479f349696e2bbac275a1&db=dc17_IDPevent
brukernavn: DC17
passord: Helgheim

Logger som må opprettes:
*en audit logg enten i sql eller en database tabell, hvor du logger midlertidig informasjon.
*midlertidig logg

Hente hvilken tjeneste som spør:

Hente hvilken bruker:
Anvend EventService sin getUserDetails()
Anvend  StatDay, StatHour, StatMinute, StatMonth, StatStatus, StatYear sin id, issuer, onBehalfOf,

hente hvilken accesstocen som ble brukt:
Anvend Authtype sin toString()
Anvend  StatDay, StatHour, StatMinute, StatMonth, StatStatus, StatYear sin authType, authLevel, cnt og d, logType,

hent beskrivelse av hva som hentes:


Anbefalt rammeverk:
*slf4j
*loggback

Begge er installert. Det har også blitt forsøkt å binde mappen til miljøvariabler, men om mappen faktisk er bundet
riktig er uvisst.












Log:
1. Include logback and jcl-over-slf4j on the classpath.
2. set level by various loggers by using