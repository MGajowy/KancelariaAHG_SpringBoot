
---------------------------------------------------------------------------------------------------
#### INSTRUKCJA URUCHOMIENIA APLIKACJI "Kancelaria AHG" ####
---------------------------------------------------------------------------------------------------

1. Informacje techniczne :
- docker
- baza danych PostgreSQL 9.6
- java 11

2. Budowa i uruchomienie obrazów docker:
        1. Pobierz repozytowium backend:
        https://github.com/MGajowy/KancelariaAHG_SpringBoot.git
        2. Pobierz repozytorium frontend:
         https://github.com/MGajowy/KancelariaAHGApp.git
         do katalogu:
         ...\KancelariaAHG_SpringBoot\AHG-Web\AHG-app
         3. W terminalu ze ścieżki repozytorium ...\KancelariaAHG_SpringBoot\ wykonaj budowę poleceniem :
         mvn clean install -DskipTest  (do zbudowania aplikacji z testami jest niezbędne utworzenie lokalnej bazy danych zgodnie
         z ustawieniami dla tesów )
3. Otwórz termial w katalogu ...\KancelariaAHG_SpringBoot\AHG-Web\AHG-app i zbuduj aplikację frontend:
    ng build --prod
    Po wykonaniu budowy aplikcji z tej samej ścieżki w termianlu wykonaj budowę obrazu docker:
    docker build . -t ahg-frontend

4. Otwórz terminal w katalogu  ...\KancelariaAHG_SpringBoot\ i wykonaj polecenie:
    docker compose up
5. Po uruchomieniu się aplikaci przejdź do przeglądarki pod adres http://localhost:8080

---------------------------------------------------
Użytkownik administracyjny aplikacji Kancelaria AHG
---------------------------------------------------
login: admin
password: adminadmin
