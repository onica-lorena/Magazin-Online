# Magazin Online

Această aplicație este destinată gestionării unui magazin online de îmbrăcăminte și accesorii, oferind funcționalități pentru clienți și administratori, precum plasarea comenzilor, gestionarea produselor și urmărirea istoricului de achiziții.

## Funcționalități

### 1. **Pentru Clienți**
- **Autentificare și înregistrare**
  - Crearea unui cont nou.
  - Autentificare utilizatori existenți.
- **Vizualizare produse**
  - Filtrare produse după categorie.
  - Căutare rapidă după nume.
- **Gestionarea coșului de cumpărături**
  - Adăugarea și eliminarea produselor.
  - Actualizarea cantităților din coș.
  - Calcularea automată a totalului comenzii.
- **Plasarea comenzilor**
  - Confirmarea și trimiterea comenzilor.
  - Actualizarea automată a stocurilor.
- **Istoric comenzi**
  - Vizualizarea comenzilor anterioare.
  - Detalii despre data achiziției și valoarea totală.

### 2. **Pentru Administratori**
- **Gestionarea produselor**
  - Adăugare, editare și ștergere produse.
  - Actualizarea stocurilor și prețurilor.
- **Gestionarea utilizatorilor**
  - Modificarea rolurilor (client/admin).
  - Vizualizarea și administrarea conturilor utilizatorilor.
- **Gestionarea comenzilor**
  - Vizualizarea comenzilor plasate.
  - Confirmarea și procesarea comenzilor.

---

## Structura Proiectului

### **Tehnologii Utilizate**
- **Frontend:** Java Swing (Interfață Grafică)
- **Backend:** Java SE (Logica aplicației)
- **Bază de date:** SQLite (Stocarea datelor)

---

## Arhitectura Proiectului

![Diagrama de componente](https://github.com/onica-lorena/Magazin-Online/blob/4353408b0db0d9a9da6ec9baa9b7f8dd95e4c7b3/resources/Component%20diagram%20(2).png)

## Cum să rulezi aplicația

### **1. Cerințe preliminare**
Pentru a rula această aplicație, ai nevoie de:
- **Java Development Kit (JDK) 11+** *(pentru compilare și rulare)*
- **Eclipse sau IntelliJ IDEA** *(pentru dezvoltare și debugging)*
- **SQLite Database Browser** *(opțional, pentru vizualizarea bazei de date)*
- **Git** *(pentru clonarea proiectului din GitHub)*

---

### **2. Instalarea proiectului**
1. **Clonează repository-ul utilizând Git:**
   ```bash
   git clone https://github.com/onica-lorena/Magazin-Online
   cd Magazin-Online
2. **Deschide proiectul într-un mediu de dezvoltare:**
- **În Eclipse:**
   ```plaintext
   File → Open Projects from File System → Selectează folderul proiectului
- **În IntelliJ IDEA:**
   ```plaintext
   File → Open → Selectează folderul proiectului
3. **Adaugă driverul SQLite (dacă nu este deja inclus în proiect):**
   - Descarcă driverul de pe [sqlite-jdbc]([https://github.com](https://github.com/xerial/sqlite-jdbc))
   - Adaugă-l în proiect:
   ```plaintext
   Project Structure → Libraries → Add .jar
4. **Rulează aplicația:**
   ```bash
   java -jar OnlineStore.jar

---

## Testare Automată

Aplicația include teste unitare și funcționale folosind JUnit.

**Pentru a rula toate testele:**
   ```bash
   mvn test
   ```
**În mediile de dezvoltare:**
- Eclipse:   
   ```plaintext
   Run → Run as JUnit Test
- IntelliJ IDEA:
   ```plaintext
   Alt + Shift + F10 → Selectează testele și rulează-le

**Exemple de teste existente:**
- Testare autentificare utilizatori (`UtilizatorServiceTest`)
- Testare adăugare produs în coș (`ProdusServiceTest`)
- Testare finalizare comandă (`ComandaServiceTest`)

---

## Documentație
Documentația completă a proiectului este disponibilă [aici](./doc).
