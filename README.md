# JARVIS-backend
Repository del progetto di backend del corso di "Ingegneria del Software" (UniFe) A.A. 2023/2024
## Il team J.A.R.V.I.S.
* [@svevaturola](https://github.com/svevaturola) - Architetto ![GitHub last commit](https://img.shields.io/github/last-commit/svevaturola/JARVIS-backend?style=flat)
* [@giuliamasina](https://github.com/giuliamasina) - Analista ![GitHub last commit](https://img.shields.io/github/last-commit/giuliamasina/JARVIS-backend?style=flat)
* [@saravaresco](https://github.com/saravaresco) - Quality Assurance ![GitHub last commit](https://img.shields.io/github/last-commit/saravaresco/JARVIS-backend?style=flat)
* [@Marco-178](https://github.com/Marco-178) - Frontend/UX-UI ![GitHub last commit](https://img.shields.io/github/last-commit/Marco-178/JARVIS-backend?style=flat)
* [@lucadomene](https://github.com/lucadomene) - Backend/UX-UI ![GitHub last commit](https://img.shields.io/github/last-commit/lucadomene/JARVIS-backend?style=flat)

## Funzionamento di prova
### Avvio dell'applicativo
L'applicativo utilizza servizi REST per ottenere da altri team i dati necessari al funzionamento del progetto. 
Per facilitare il processo di sviluppo e testing, è possibile utilizzare un mock del servizio con Mockoon. 

Per questo progetto sono stati necessari alcuni dati riguardanti gli eventi e gli utenti.
Sono forniti nel progetto i file JSON corrispondenti a questi dati per configurare il mock tramite Mockoon.

È quindi opportuno abilitare il servizio REST, che sia un mock o un servizio reale, sulla porta 3000 prima di avviare il backend dell'applicativo.

Per far partire l'applicativo SpringBoot, utilizzare il comando
```sh
./gradlew bootRun
```
dalla root del progetto.
Si avvierà SpringBoot come processo in ascolto sulla porta 8080 (di default utilizza TomCat come server engine).

Per uccidere il processo `ctrl + C`.

### Interazione con le API Database
L'accesso al database è realizzato mediante 3 API: `/add`, `/ls` e `/del?id={venueID}`.
#### Aggiunta di records
Il metodo `/add` è di tipo POST, ovvero passa i parametri nel corpo della richiesta HTTP.

Il modo più semplice (e indolore) per testarlo è utilizzando il tool `curl` con la seguente sintassi:
```sh
curl -H "Content-Type: application/json" -X POST --data \
'{"name": "UniFe", "address": "Via Saragat 1, Ferrara, 44121", "max_capacity": 800, "rent_cost": 100, "weekdayHours": {"start": "8:00:00", "end": "20:00:00"}, "weekendHours": {"start": "10:00:00", "end": "15:00:00"}, "closingDays": ["2024-12-25", "2024-12-31"]}' \
'http://localhost:8080/api/database/add'
```
Il passaggio di parametri in POST viene indicato con delle coppie "chiave-valore" anteposte dal flag `-d`

Se l'API risponde correttamente restituirà una stringa del tipo "Saved successfully".

#### Elenco dei records
Il metodo `/ls` è di tipo GET e non passa alcun parametro.

Per testarlo:
```sh
curl http://localhost:8080/api/database/ls
```
Restituirà un oggetto JSON contenente tutti i record salvati sul database.

#### Eliminazione di un record
Il metodo `/del?id={venueID}` è di tipo GET (forse avrebbe più senso che fosse DELETE...).

Si passa al posto di `{venueID}` l'identificativo di un record (la chiave primaria nel database) per volerlo eliminare.

Per testarlo:
```sh
curl http://localhost:8080/api/database/del?id=1
```
In risposta, l'API restituirà la stringa "Deleted successfully".
