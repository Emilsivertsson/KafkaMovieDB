# Projektrapport

### Ert namn
Emil Sivertsson

### Beskrivning av projektet
Uppgiften var att vi skulle skapa en java applikation som skickar information till ett REST API som sedan skickar informationen till en Kafka Producer.\
Kafka Producer skickar informationen till två olika Kafka Consumers.\
Den ena Consumer är orginal- java applikationen som skriver ut informationen till användaren.\
Den andra consumern skriver informationen till en databas.\
jag valde att göra projektet lite mer komplext och la till en producer som skickar informationen vidare efter att den sparats i databasen.\
till en tredje consumer som skriver ut informationen till en fil.

### Vad du har gjort
Jag har skapat ett Huvudprojekt som innehåller 4 moduler.\
Alla moduler är Spring Boot maven applicationer som startat på vars en port.\
Jag ville bygga det i separata moduler för att simulera löskopplade applikationer som kommunicerar med varandra.\
jag har skrivit modulerna och sedan skrivit tester och även satt upp för att använda 3 brokers och replikering i Kafka.\

## Arbetet och dess genomförande
Jag började med att skriva APIet och testade det med Postman.\
sedan skrev jag den första Producern som skulle skriva ut informationen till användaren och till databasen.\
min första tanke var att använda ett JavaFX GUI för att ta input från användaren och visa en lista på alla filmer.\
men det visade inte fungera, för javaFX har en egen launch() funktion som inte fungerar med Spring Boot.\
jag lärde mig sen av Kristoffer Larsson att man kunde göra det, men det var inte på det sättet jag hade tänkte mig funktionaliteten.
så jag skrotade det och skrev om till en vanlig consol gui som gjorde samma sak.
Efter det skrev jag Databas consumern och testade att den fungerade.\
Eftersom jag vill ha det så löstkopplat som möjligt så har jag valt att inte skapa någon Movie klass som jag skickar runt.\
Istället skapar java applikationen ett Json objekt som skickas som en String.\
På så sätt skulle den kunna skicka en vilken typ av information som helst, vilket underlättar för framtida förändringar.\
Databas consumern har en dock en Movie entity som den sparar till databasen.\
Jag har sen skrivit tester och även testat DiffBlue på en del av koden för att testa om autogenererade tester fungerar.\

### Vad som varit svårt
Jag hade ett problem som tog lång tid att hitta, och det var att jag hade skrivit fel i min application.properties fil.\
jag hade Jsondeserializer istället för stringdeserializer, vilket gjorde att mina utskrifter blev helt fel, och jag kunde inte spara till databasen\
efter som det inte gick att Parsa.\
Jag fick till slut ta hjälp av min klasskamrat för att leta reda på problemet.

### Beskriv lite olika lösningar du gjort

### Beskriv något som var besvärligt att få till

### Beskriv om du fått byta lösning och varför i sådana fall


## Slutsatser

### Vad gick bra

### Vad gick dåligt

### Vad har du lärt dig

### Vad hade ni gjort annorlunda om ni gjort om projektet

### Vilka möjligheter ser du med de kunskaper du fått under kursen.
