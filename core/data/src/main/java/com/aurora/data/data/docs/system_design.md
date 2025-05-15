

### Trip
- id: Long (Primary Key)
- name: String
- startDate: LocalDate
- endDate: LocalDate
- plans: List<Plan>

### Plan
- id: Long (Primary Key)
- tripId: Long (Foreign Key referencing Trip)
- date: LocalDate
- arrivalTime: LocalTime? (Optional)
- departureTime: LocalTime? (Optional)
- transitMode: TransitMode? (Enum or String)
- transitDetails: String? (e.g., flight number, train details)
- place: String (City/Location of the plan)
- stay: Stay? (Optional: reference to a Stay entity)
- activities: List<Activity>? (Optional: if you want to track specific activities)
- notes: String?

### Stay
- id: Long (Primary Key)
- name: String
- latitude: Double
- longitude: Double
- checkInTime: LocalTime?
- checkOutTime: LocalTime?
- notes: String?
- price: Double?

### TransitMode (Enum)
- FLIGHT
- TRAIN
- BUS
- CAR
- OTHER

### Activity
- id: Long (Primary Key)
- planId: Long (Foreign Key referencing Plan)
- name: String
- time: LocalTime?
- location: String?
- notes: String?

### ChatSession
- id: Long (Primary Key)
- tripId: Long? (Foreign Key referencing Trip, nullable if session can exist without a trip)
- startTime: Instant
- endTime: Instant? (Optional)

### Message
- id: Long (Primary Key)
- sessionId: Long (Foreign Key referencing ChatSession)
- sender: String (e.g., "User", "AI")
- timestamp: Instant
- content: String
