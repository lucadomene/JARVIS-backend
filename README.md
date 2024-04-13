# JARVIS-backend
Repository del progetto di backend del corso di "Ingegneria del Software" (UniFe) A.A. 2023/2024
## Il team J.A.R.V.I.S.
* [@svevaturola](https://github.com/svevaturola) - Architetto ![GitHub last commit](https://img.shields.io/github/last-commit/svevaturola/JARVIS-backend?style=flat)
* [@giuliamasina](https://github.com/giuliamasina) - Analista ![GitHub last commit](https://img.shields.io/github/last-commit/giuliamasina/JARVIS-backend?style=flat)
* [@saravaresco](https://github.com/saravaresco) - Quality Assurance ![GitHub last commit](https://img.shields.io/github/last-commit/saravaresco/JARVIS-backend?style=flat)
* [@Marco-178](https://github.com/Marco-178) - Frontend/UX-UI ![GitHub last commit](https://img.shields.io/github/last-commit/Marco-178/JARVIS-backend?style=flat)
* [@lucadomene](https://github.com/lucadomene) - Backend/UX-UI ![GitHub last commit](https://img.shields.io/github/last-commit/lucadomene/JARVIS-backend?style=flat)

## Funzionamento di prova
### Configurazione MySQL
Prima di far partire l'applicativo, bisogna assicurarsi che sia configurata localmente un'istanza di MySQL e che sia attualmente attiva (`systemctl status mysql` per Ubuntu/Debian, `systemctl status mysqld` per Fedora).

Innanzitutto bisognerà disporre di un **username** e **password** configurati per accedere al database.
Successivamente creeremo un database sul quale andrà a lavorare l'applicativo SpringBoot, nel nostro caso avrà un nome di default ovvero `springdatabase`.
Per creare il database, entrare sul terminale MySQL (`mysql -u USERNAME -p`) e digitare la seguente query:
```
CREATE DATABASE springdatabase;
```

Una volta creato il database, andremo a dire a SpringBoot come effettuare l'accesso a MySQL e su quale database lavorare.
Per fare ciò modifichiamo il file [application.properties](https://github.com/lucadomene/JARVIS-backend/blob/main/src/main/resources/application.properties) e scriviamolo come segue:
```
spring.application.name=backend
spring.jpa.hibernate.ddl-auto=update
spring.datasource.url=jdbc:mysql://localhost:3306/springdatabase
spring.datasource.username=IL_VOSTRO_USERNAME
spring.datasource.password=LA_VOSTRA_PASSWORD
spring.datasource.driver-class-name=com.mysql.cj.jdbc.Driver
#spring.jpa.show-sql: true
```
Complimenti! Abbiamo agganciato SpringBoot a MySQL.

### Avvio dell'applicativo
Per far partire l'applicativo SpringBoot, utilizzare il comando
```
./gradlew bootRun
```
dalla root del progetto.
Si avvierà SpringBoot come processo in ascolto sulla porta 8080 (di default utilizza TomCat come server engine).

Per uccidere il processo `ctrl + C`.

### Interazione con le API Database
L'accesso al database è realizzato mediante 3 API: `/add`, `/ls` e `/del/{venueID}`.
#### Aggiunta di records
Il metodo `/add` è di tipo POST, ovvero passa i parametri nel corpo della richiesta HTTP.

Il modo più semplice (e indolore) per testarlo è utilizzando il tool `curl` con la seguente sintassi:
```
curl http://localhost:8080/api/database/add -d name="Casa di Dome" -d address="Occhiobello"
```
Il passaggio di parametri in POST viene indicato con delle coppie "chiave-valore" anteposte dal flag `-d`

Se l'API risponde correttamente restituirà una stringa del tipo "Saved successfully".

#### Elenco dei records
Il metodo `/ls` è di tipo GET e non passa alcun parametro.

Per testarlo:
```
curl http://localhost:8080/api/database/ls
```
Restituirà un oggetto JSON contenente tutti i record salvati sul database.

#### Eliminazione di un record
Il metodo `/del/{venueID}` è di tipo GET (forse avrebbe più senso che fosse DELETE...).

Si passa al posto di `{venueID}` l'identificativo di un record (la chiave primaria nel database) per volerlo eliminare.

Per testarlo:
```
curl http://localhost:8080/api/database/1
```
In risposta, l'API restituirà la stringa "Deleted successfully".
