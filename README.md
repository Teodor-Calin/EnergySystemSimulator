# Proiect Energy System Etapa 2

## Despre

Proiectare Orientata pe Obiecte, Seriile CA, CD
2020-2021

<https://ocw.cs.pub.ro/courses/poo-ca-cd/teme/proiect/etapa2>

Student: Calin Teodor-Georgian, 324CA

## Rulare teste

Clasa Test#main
  * ruleaza solutia pe testele din checker/, comparand rezultatele cu cele de referinta
  * ruleaza checkstyle

Detalii despre teste: checker/README

Biblioteci necesare pentru implementare:
* Jackson Core 
* Jackson Databind 
* Jackson Annotations

Tutorial Jackson JSON: 
<https://ocw.cs.pub.ro/courses/poo-ca-cd/laboratoare/tutorial-json-jackson>

## Implementare

* Main
    * citeste fisierul de input
    * creaza un obiect de tip Simulare pe baza datelor
    * porneste aceasta simulare
    * creaza fisierul de output

* Simulation
    * simuleaza parcurgerea lunilor 
    * produce schimbari inauntrul entitatilor si controleaza interactiunea dintre acestea

### Entitati

* Simulation - clasa de tip singleton, care contine variabile precum numarul de luni simulate, care contine metoda
	     principala a rularii simularii

* BusinessEntity - interfata implementata de clasele Distributor si Consumer

* Producer - clasa de entitati care distribuie energie catre distributori

* Distributor - Distributor - clasa de entitati care pun la dispozitie contracte, ce isi calculeaza preturile
	      in functie de costurile pe care acesta le au de platit si care pot da faliment in
	      cazul in care nu mai au destui bani pentru a-si plati datoriile lunare.

* Consumer - clasa de entitati care semneaza contracte cu distribuitorii cu cele mai mici preturi la
	   momentul respectiv, si care trebuie sa plateasca in fiecare luna preturile impuse de
	   acestia. Acestia pot ramane restanti pe o luna in cazul in care nu mai au bani sa
   	   plateasca taxa lunara, urmand sa dea faliment daca acest lucru se repeta urmatoarea luna.

### Flow

Initial, distribuitorii isi cauta producatori de la care sa ia energie, distribuitorii isi calculeaza preturile, consumatorii primesc salarii, creeaza contracte cu distribuitorii si ii platesc, distribuitorii isi platesc si eu datoriile si isi actualizeaza lista de consumatori.

In fiecare luna:
   * Se produc schimbari pentru consumatori si distribuitori
   * Distribuitorii isi calculeaza preturile si incheie contracte expirate
   * Consumatorii primesc salarii, fac contracte cu distribuitorii (daca e cazul) si ii platesc.
   * Distribuitorii isi platesc datoriile, actualizeaza contractele curente
   * Producatorii au parte de posibile schimbari
   * Distribuitorii care luau energie de la producatorii de mai sus isi reconsidera producatorii de la care sa ia energie 
   * Producatorii pastreaza o lista cu distribuitorii care au luat energie in luna curenta

### Elemente de design OOP

   * Am folosit abstractizare in cazul claselor Observer si Observable, deoarece acestea nu pot fi obiecte de sine statatoare.
   * Interface segregation - Strategy, BusinessEntity
   * Procipiul delegarii - folosirea metodelor deja existente: equals(), size(), etc.

### Design patterns

Factory:
   * BusinessEntityFactory creeaza obiecte de tip BusinessEntity (Consumatori, Distribuitori si Producatori).
   * (Desi obiectele de tip Producer cu folosesc metoda payDebts(), am considerat ca este mai "elegant" sa creez aceste obiecte folosind deasemenea Factory). 

Singleton:
   * clasele Simulation si BusinessEntityFactory sunt de acest tip, deoarece avem nevoie ca aceste obiecte sa fie unice.

Observer:
   * Am creat clasa abstracta Observer, pe care clasa Distributor o mosteneste.
   * Am creat clasa abstracta Observabe, pe care clasa Producer o mosteneste.
   * Atunci cand se produce o schimbare unui producator, distribuitorii care iau energie de la el sunt atentionati ca va trebui sa-si reconsidere optiunile
   
Strategy:
   * Am creat interfata Strategy si 3 clase: GreenStrategy, PriceStrategy si QuantityStrategy ce implementeaza aceasta interfata.
   * Fiecare clasa din cele 3 au o metoda "findProducers" care gaseste cei mai buni producatori pentru distribuitorul cu strategia respectiva.
   * Clasa Distributor contine un camp de tip Strategy, astfel fiecare distribuitor va avea una dintre aceste strategii, in functie de tipul sau.
