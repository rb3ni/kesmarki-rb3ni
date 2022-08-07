# Személyeket nyilvántartó, karbantartó alkalmazás

### Feladat kiírás: 
Készítsen egy személyeket nyilvántartó, karbantartó console alkalmazást. A feladathoz tartozó adatbázis
tartalmazzon 3 táblát. Személyek, címek, elérhetőségek. Egy személynek több címe lehet, egy címhez
több elérhetőség tartozhat. 

Az alkalmazás feladatai:
- Adatok lekérdezése
- Adatok rögzítése, módosítása, törlése, hibakezeléssel

## Információk az alkalmazásról:
A projekt leadásához csatolt DDL és DML segítségével létrehozható az adatbázis és kiinduló értékei.
- Az adatbázis neve: kesmarkiDB
- username: kesmarki
- password: kesmarki

[Ezek az adatok megtalálhatók a kódban is.](src/main/resources/application.yaml)

Az applikáció logikai törlést alkalmaz, így az értékek adatbázis utasításokkal visszaállíthatóak igény esetén.

## Endpointok
### Person
- mentés: Személy mentése
- Id alapú keresés: Személy lekérése ID alapján
- listázás: Összes személy kilistázása
- módosítás: személy keresztnevének frissítése
- törlés: Személy logikai törlése

### Address
- mentés: Cím személyhez való rendelése és mentése
- Id alapú keresés: Cím lekérése ID alapján
- listázás: Összes cím kilistázása
- módosítás: Cím adatainak frissítése
- törlés: Cím logikai törlése

### Contact
- mentés: Elérhetőség címhez való rendelése és mentése
- Id alapú keresés: Elérhetőség lekérése ID alapján
- listázás: Összes elérhetőség kilistázása
- módosítás: Elérhetőség telefonszámának frissítése
- törlés: Elérhetőség logikai törlése

