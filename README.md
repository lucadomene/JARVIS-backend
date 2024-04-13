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
Innanzitutto bisognerà disporre di un username e password configurati per accedere al database. 
Successivamente creeremo un database sul quale andrà a lavorare l'applicativo SpringBoot, nel nostro caso avrà un nome di default ovvero `springdatabase`.
Per creare il database, entrare sul terminale MySQL (`mysql -u {user} -p`, premere invio e inserire la propria password) e digitare la seguente query:
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
