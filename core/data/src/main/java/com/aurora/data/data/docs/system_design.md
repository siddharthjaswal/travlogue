### Trip

- id: Long (Primary Key)
- name: String
- startDateTimestamp: Long (UTC milliseconds epoch timestamp)
- endDateTimestamp: Long (UTC milliseconds epoch timestamp)

### DayPlan

- id: Long (Primary Key)
- tripId: Long (Foreign Key referencing Trip)
- dateTimestamp: Long (UTC milliseconds epoch timestamp for the specific day)
- arrivalTime: Long? (Optional, e.g., for first arrival at the 'place')
- departureTime: Long? (Optional, e.g., for last departure from the 'place')
- transitMode: TransitMode? (Enum)
- transitDetails: String? (e.g., flight number, train details)
- place: String (City/Location of the plan for this day)
- notes: String?
- *Stays are linked via DayPlanStayJunction*
- *Activities are linked via Activity.dayPlanId*

### Stay

- id: Long (Primary Key)
- name: String
- latitude: Double
- longitude: Double
- checkInTime: Long?
- checkOutTime: Long?
- notes: String?
- link: String?
- price: Double?
- currency: Currency? (Enum)

### TransitMode (Enum)

- FLIGHT
- TRAIN
- BUS
- CAR
- OTHER

### Currency (Enum)

- USD
- EUR
- GBP
- INR
- JPY
- CAD
- AUD
- OTHER

### Activity

- id: Long (Primary Key)
- dayPlanId: Long (Foreign Key referencing DayPlan)
- name: String
- time: Long? (UTC milliseconds epoch timestamp)
- durationInMins: Int?
- locationName: String?
- latitude: Double?
- longitude: Double?
- notes: String?

### Message

- id: Long (Primary Key)
- tripId: Long (Foreign Key referencing Trip)
- sender: String (e.g., "User", "AI")
- timestamp: Long
- content: String

### Relational Data Classes (POKOs for Queries)

These classes are not database tables themselves but represent how related data is fetched.

#### TripWithDayPlans

*Purpose: To fetch a Trip along with all its associated DayPlans.*
- trip: Trip (Embedded TripEntity with Long timestamps)
- dayPlans: List<DayPlanEntity> (with Long timestamps)
    - Relation: Links `Trip.id` to `DayPlan.tripId`

#### DayPlanStayJunction (Junction Table Entity)

*Purpose: To create a many-to-many relationship between DayPlans and Stays.*
- dayPlanId: Long (Foreign Key referencing DayPlan.id, Part of Composite Primary Key)
- stayId: Long (Foreign Key referencing Stay.id, Part of Composite Primary Key)

#### DayPlanWithActivitiesAndStays

*Purpose: To fetch a DayPlan along with its list of Activities and its list of associated Stays.*
- dayPlan: DayPlan (Embedded DayPlanEntity with Long timestamp)
- activities: List<ActivityEntity>
    - Relation: Links `DayPlan.id` to `Activity.dayPlanId`
- stays: List<StayEntity>
    - Relation: Many-to-many linking `DayPlan.id` to `Stay.id` via `DayPlanStayJunction`
