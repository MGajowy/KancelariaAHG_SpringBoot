
---------------------------------------------------------------------------------------------------
# INSTRUKCJA URUCHOMIENIA APLIKACJI "Kancelaria AHG"
---------------------------------------------------------------------------------------------------

## 1. Informacje techniczne:
- Spring Boot
- Docker
- DB: PostgreSQL 9.6
- Java 11

## 2. Budowa paczek aplikacji:
        1. Pobierz repozytowium backend:
        https://github.com/MGajowy/KancelariaAHG_SpringBoot.git

        ### WAŻNE
                 Ustawienie ścieżki wygenetownych plików TS dla aplikacji frontend AHG-app:
                 Otwórz plik pom.xml i w properties ustaw zmienną:

                 <generateTsFile>TU PODAJ SCIEZKĘ DO MIEJSCA REPOZYTORIUM AHG-app</generateTsFile>
                 przykład:
                 <generateTsFile>D:/Projekty_Michal/KancelariaAHG</generateTsFile>

                 wskazując ścieżkę/katalog w którym aktualnie, lokalnie znajdzie się wypakowana aplikacja AHG-app.

         W terminalu (ścieżka rozpakowania: ...\KancelariaAHG_SpringBoot\ )wykonaj budowę poleceniem:
                 mvn clean install -DskipTest
                 UWAGA: (do zbudowania aplikacji z testami jest niezbędne utworzenie lokalnej bazy danych zgodnie
                 z ustawieniami dla testów)

        2. Pobierz repozytorium frontend:
         https://github.com/MGajowy/KancelariaAHGApp.git

         Zbuduj aplikację w terminalu za pomoca polecenia : ng build --prod

         3. Pobierz repozytorium reputation
         https://github.com/MGajowy/KancelariaAHG_Reputation.git
         W terminalu (ścieżka rozpakowania: ... \Reputation) wykonaj budowę poleceniem:
         mvn clean install

## 3. Budowa obrazów Docker i uruchomienie contenerów:

### Sposób budowy 1 z poziomu terminala

    1. Otwórz terminal w katalogu...\KancelariaAHG_SpringBoot\AHG-Web\AHG-app
     wykonaj budowę obrazu docker dla frontend:
     docker build . -t ahg-frontend

    2. Otwórz terminal w katalogu...\Reputation
           wykonaj budowę obrazu docker dla reputation:
           docker build . -t ahg-reputation

    3. Otwórz terminal w katalogu  ...\KancelariaAHG_SpringBoot\ i wykonaj polecenie:
    docker compose up
    Po uruchomieniu aplikacji przejdź do przeglądarki pod adres http://localhost:8080

### Sposób budowy 2 z poziomu IntelliJ + plugin Docker

WAŻNE => Jeśli posiadasz IntelliJ + plugin Docker, możesz wykonać kroki 1, 2 i 3 z poziomu IntelliJ,
        budując obrazy wybierając plik Dockerfile w poszególnych repozytoriach (frontend, reputation) klikając
        opcję "Build Image on Docker" dostępną po kliknięciu PPM na dwie zielone strzałki po lewej stronie marginesu.
        Następnie otwierając plik docker-compose.yml w repozytorium backend,
        wybierając dwie zielone strzałki po lewej stronie marginesu, zostanie uruchomiona cała kompletna aplikacja.

        PAMIĘTAJ o odpowiednim ustawieniu nazw przed budową dla poszególnych obrazów (możliwość ustawienia po otwarciu pliku Dockerfile:
        wybierając dwie zielone strzałki po lewej stronie marginesu - "Edit Dockerfile" i ustawić odpowienio Image tag.):
        Dla obrazu backend: ahg-backend
        Dla obrazu frontendu: ahg-frontend
        Dla obrazu reputation: ahg-reputation
---------------------------------------------------
Użytkownik administracyjny aplikacji Kancelaria AHG
---------------------------------------------------
login: admin
password: adminadmin
