# Sistem-gestionare-magazin-online
O aplicație desktop completă pentru gestionarea unui magazin online.
## Descriere
Aplicația desktop pentru gestionarea unui magazin online este proiectată să simplifice și să eficientizeze toate procesele de administrare și vânzare, adresându-se atât clienților, cât și administratorilor. Clienții pot explora cu ușurință un catalog de produse bine organizat, având la dispoziție opțiuni pentru a căuta și filtra articolele dorite. Detaliile fiecărui produs, precum prețul, descrierea și disponibilitatea în stoc, sunt afișate clar, ajutând utilizatorii să ia decizii de cumpărare informate. După ce își adaugă produsele dorite în coșul de cumpărături, clienții pot plasa comenzi, iar sistemul actualizează automat stocurile.

Administratorii, pe de altă parte, au un set de instrumente pentru gestionarea eficientă a magazinului. Aceștia pot adăuga, edita sau elimina produse din catalog, având totodată posibilitatea de a actualiza descrierile, prețurile și cantitățile disponibile. De asemenea, aplicația le permite să monitorizeze și să ajusteze stocurile pentru a preveni epuizarea acestora. 

Sistemul include un mecanism de autentificare care diferențiază între clienți și administratori, asigurând accesul corespunzător fiecărui tip de utilizator. Clienții își pot consulta istoricul comenzilor pentru a verifica detalii despre achizițiile trecute. Astfel, aplicația oferă o experiență prietenoasă și organizată, facilitând atât achizițiile online pentru clienți, cât și administrarea eficientă a magazinului pentru manageri.



## Obiective
Scopul aplicației este să ofere o platformă ușor de utilizat atât pentru clienți, cât și pentru administratori, în vederea gestionării eficiente a unui magazin online. Obiectivele principale sunt:

* Crearea unui sistem de administrare a produselor și comenzilor pentru un magazin online.
* Oferirea unui flux eficient pentru gestionarea coșului de cumpărături și a comenzilor pentru utilizatori.
* Implementarea unui sistem de autentificare care să permită diferențierea între rolurile de client și administrator.
* Asigurarea unui mediu prietenos și ușor de folosit pentru clienți în ceea ce privește navigarea și achiziționarea produselor.
* Oferirea administratorilor instrumente pentru gestionarea inventarului și comenzilor în mod eficient.


## Arhitectura
Structura aplicației este bazată pe un design modular și obiect-orientat, organizat în următoarele componente principale:

1. **Clasa `Produs`**: Gestionează detaliile unui produs(nume, descriere, preț, cantitate).
2. **Clasa `Utilizator`**: Reprezintă utilizatorii aplicației (client sau administrator) și rolul fiecăruia.
3. **Clasa `Comanda`**: Conține detalii despre o comandă efectuată(produse, total, data).
4. **Clasa `Coș`**: Gestionează produsele adăugate în coșul de cumpărături și calcularea prețului total.
5. **Clasa `ManagerBazaDeDate`**: Se ocupă de interacțiunea cu baza de date pentru stocarea și preluarea informațiilor legate de produse, utilizatori și comenzi.

Diagrama UML de mai sus ilustrează structura de bază a aplicației și relațiile dintre principalele clase, oferind o perspectivă vizuală asupra arhitecturii și interacțiunilor dintre componente.


## Funcționalități

1. **Autentificarea Utilizatorilor**:
   - Sistemul permite autentificarea și verificarea rolului fiecărui utilizator (client sau administrator).
     
2. **Gestionarea Catalogului de Produse**:
   - Administratorii pot adăuga, vizualiza, modifica și șterge produse, actualizând informațiile legate de descriere, preț și stoc.

3. **Vizualizarea Detaliilor Produselor**:
   - Clienții pot vizualiza detalii despre produse, inclusiv descrierea, prețul și cantitatea disponibilă și pot căuta și filtra produsele.

4. **Gestionarea Coșului de Cumpărături**:
   - Utilizatorii pot adăuga sau elimina produse în coș, cu calcul automat al totalului comenzii.

5. **Plasarea Comenzilor**:
   - Clienții finalizează comanda, iar stocurile produselor se actualizează automat.

6. **Istoricul Comenzilor**:
   - Utilizatorii pot consulta detalii despre comenzile anterioare.
