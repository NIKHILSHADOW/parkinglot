# PARKING LOT

![parkinglot](http://www.plantuml.com/plantuml/proxy?cache=no&src=https://raw.githubusercontent.com/NIKHILSHADOW/parkinglot/v1/static/parkinglot.iuml)

![parkinglot1](http://www.plantuml.com/plantuml/proxy?cache=no&src=https://raw.githubusercontent.com/NIKHILSHADOW/parkinglot/v1/static/parkinglot.puml)

```plantuml

@startuml

    actor "ticket collector" as TC
    actor "ticket issuer" as TI
    actor "admin" as admin

    Rectangle ParkingLot {
        left to right direction

         usecase (check Spot Available of Type) as checkSpotAvailable
         usecase (generate Ticket) as generateTicket
         usecase (change spot Status) as updateSpot

         usecase (generate bill) as generateBill

         usecase (collect amount) as collectAmount

         usecase (check payment status) as checkPaymentStatus

    }

    admin -- ParkingLot

    TI -- checkSpotAvailable
    TI -- generateTicket
    TI -- updateSpot


    TC -- generateBill
    TC -- collectAmount


    TC -- checkPaymentStatus
    TC -- updateSpot

    generateTicket -> updateSpot
    checkSpotAvailable -> generateTicket



    collectAmount <- generateBill

    checkPaymentStatus <- collectAmount

    updateSpot <- checkPaymentStatus

@enduml
```

### API

```
   GET /parkinglot/spots
   GET /parkinglot/spots?status=AVLBL
   GET /parkinglot/spots?floor=1&status=AVLBL

   POST /parkinglot/spot/:id

   GET  /parkinglot/payment/:id
   POST /parkinglot/payment
```

```mermaid
classDiagram
    class ParkingLot {
        - Floor[] floors
        + Spot[] getAvailableSpots()
        + bool updateSpot(SpotStatus)
        + double getBill(Ticket, DateTime)
        + makePayment(bill)
        + Ticket generateTicket(Vehicle, DateTime)
    }

    class Ticket {
        - int id
        - Vehicle vehicle
        - date issuedDateTime
    }

    class Vehicle {
        - int id
        - VehicleType type
    }

    class VehicleType{
        <<Enumeration>>
        AVAILABLE
        OCCUPIED
        OUT_FROM_SERVICE
    }

    class Floor {
        - Spot[] spots

        + Spot[] getAvailableSpots()
    }

    class Spot{
        - int id
        - SpotStatus status
    }

    ParkingLot --* Floor
    Floor --* Spot

    Ticket --o Vehicle
    Vehicle --o VehicleType
```
