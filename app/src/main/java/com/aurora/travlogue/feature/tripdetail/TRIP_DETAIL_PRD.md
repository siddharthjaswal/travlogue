# Trip Detail Feature - Product Requirements Document

## Overview
The Trip Detail feature serves as the central hub for viewing and managing all aspects of a trip. It provides a comprehensive view of the trip timeline, locations, activities, bookings, and allows users to add detailed notes and plans at both the trip-wide and day-by-day level.

## Goals
1. **Visual Timeline**: Provide an intuitive, visual representation of the entire trip
2. **Hierarchical Information**: Display information at trip, location, day, and activity levels
3. **Easy Editing**: Allow quick addition and modification of trip details
4. **Booking Management**: Centralize all bookings (flights, hotels, tickets, transport)
5. **Daily Planning**: Enable detailed day-by-day itinerary planning
6. **Quick Access**: Provide fast access to important trip information

## User Stories

### As a user, I want to:
1. See an overview of my trip including dates, duration, and key locations
2. View a visual timeline showing my entire trip at a glance
3. See all locations I'm visiting in chronological order
4. View detailed information for each day of my trip
5. Add activities, notes, and plans for specific days and times
6. Manage all my bookings (flights, hotels, transportation) in one place
7. Add notes and details at both trip-wide and day-specific levels
8. Quickly access important information like confirmation numbers
9. Edit trip details, locations, and activities easily
10. See a summary of trip statistics (days, locations, activities count)

## Data Model (Existing)

### Trip
- Basic trip information (name, dates, origin)
- Date type (Fixed or Flexible)
- Creation and update timestamps

### Location
- Cities/places within the trip
- Geographic coordinates (optional)
- Chronological order
- Date association

### Activity
- Things to do at each location
- Description and notes
- Date and time slot (Morning/Afternoon/Evening/Full Day)
- Type categorization (Attraction/Food/Booking/Transit/Other)

### Booking
- Travel and accommodation reservations
- Confirmation numbers and provider info
- Start/end date-times with timezone
- Location details (from/to)
- Price and currency
- Screenshot storage capability

## UI/UX Design Specifications

### Screen Structure

#### 1. Header Section
**Purpose**: Quick trip overview
**Components**:
- Trip name (editable on tap)
- Origin city
- Date range display
  - Fixed dates: "Nov 15 - Nov 25, 2025" (11 days)
  - Flexible dates: "~November 2025" (≈10 days)
- Trip cover image placeholder (for future enhancement)
- Edit trip button
- More options menu (share, duplicate, archive)

**Design**:
```
┌──────────────────────────────────────┐
│  [Back]              Spain Adventure │
│                                 [⋮]   │
│  ┌────────────────────────────────┐  │
│  │   [Cover Image Placeholder]    │  │
│  └────────────────────────────────┘  │
│                                       │
│  📍 From New York                     │
│  📅 Nov 15 - Nov 25, 2025 (11 days)  │
│                                       │
│  ┌──────┬──────┬──────┬──────────┐   │
│  │  3   │  12  │   8  │    2     │   │
│  │Loc's │Acts  │Book  │  Notes   │   │
│  └──────┴──────┴──────┴──────────┘   │
└──────────────────────────────────────┘
```

#### 2. Tab Navigation
**Tabs**:
1. **Timeline** (default) - Visual day-by-day view
2. **Locations** - List of all locations
3. **Bookings** - All reservations
4. **Overview** - Trip-wide notes and info

#### 3. Timeline Tab (Primary View)

**Components**:
- Visual timeline with date markers
- Expandable day cards
- Activity items within days
- Quick add buttons

**Day Card Structure**:
```
┌──────────────────────────────────────┐
│ Day 1 • Nov 15 • Barcelona           │
│ ▼                                     │
│                                       │
│  🏨 Hotel Check-in                   │
│  📍 Sagrada Familia                  │
│  🍴 Dinner at La Rambla              │
│                                       │
│  [+ Add Activity] [+ Add Note]       │
└──────────────────────────────────────┘
```

**Expanded Day View**:
```
┌──────────────────────────────────────┐
│ Day 1 • Nov 15, 2025 • Barcelona    │
│ ▼                                     │
├──────────────────────────────────────┤
│ MORNING                              │
│  ☀️ 09:00 - Hotel Check-in          │
│     Grand Barcelona Hotel            │
│     Conf: #HB12345                   │
│                                       │
│  🎨 10:30 - Sagrada Familia Visit   │
│     Pre-booked tickets                │
│     "Must see the sunrise facade!"    │
│                                       │
│ AFTERNOON                            │
│  🍴 13:00 - Lunch at La Rambla       │
│     Notes: Try the paella            │
│                                       │
│ EVENING                              │
│  📝 Free time for exploration        │
│                                       │
├──────────────────────────────────────┤
│ Day Notes:                           │
│ "First day - take it easy, adjust   │
│  to timezone"                        │
├──────────────────────────────────────┤
│ [+ Add Activity] [+ Add Note]        │
│ [+ Add Booking Link]                 │
└──────────────────────────────────────┘
```

#### 4. Locations Tab

**Purpose**: Manage all trip locations

**Components**:
- Location cards with key info
- Drag-to-reorder capability
- Activity count per location
- Date range at location

**Location Card**:
```
┌──────────────────────────────────────┐
│ 1️⃣ Barcelona, Spain                  │
│    Nov 15-18 (4 days) • 5 activities │
│    ⋮                                  │
├──────────────────────────────────────┤
│ 2️⃣ Madrid, Spain                     │
│    Nov 18-22 (4 days) • 7 activities │
│    ⋮                                  │
├──────────────────────────────────────┤
│ 🚄 Travel Day                        │
│    Nov 22 • 1 booking                │
│    ⋮                                  │
└──────────────────────────────────────┘
```

#### 5. Bookings Tab

**Purpose**: Centralized booking management

**Components**:
- Chronological booking list
- Categorized view (Flights, Hotels, etc.)
- Quick access to confirmation numbers
- Attached screenshots/documents

**Booking Types**:
- ✈️ Flights
- 🏨 Hotels
- 🚂 Trains
- 🚌 Buses
- 🎫 Tickets/Events
- 🚗 Other

**Booking Card**:
```
┌──────────────────────────────────────┐
│ ✈️ FLIGHT                            │
│ United Airlines • UA123               │
│                                       │
│ Nov 15 • 08:30 AM EDT                │
│ New York (JFK) → Barcelona (BCN)     │
│ Nov 15 • 10:45 PM CET                │
│                                       │
│ Confirmation: #ABC123XYZ             │
│ $850 USD                              │
│                                       │
│ 📎 View booking screenshot            │
│ [Edit] [Delete]                       │
└──────────────────────────────────────┘
```

#### 6. Overview Tab

**Purpose**: Trip-wide information and notes

**Sections**:
1. **Trip Statistics**
   - Total days
   - Number of locations
   - Total activities
   - Total bookings
   - Estimated budget (if entered)

2. **Trip-Wide Notes**
   - Packing list
   - Important contacts
   - General reminders
   - Visa/passport info
   - Insurance details

3. **Weather Insights** (Future)
   - Expected weather at destinations
   - Packing recommendations

4. **Budget Summary** (Future)
   - Total booking costs
   - Estimated daily expenses
   - Budget tracking

```
┌──────────────────────────────────────┐
│ TRIP STATISTICS                      │
│ • 11 days total                      │
│ • 3 locations                        │
│ • 12 planned activities              │
│ • 8 bookings                         │
└──────────────────────────────────────┘

┌──────────────────────────────────────┐
│ TRIP NOTES                           │
│                                       │
│ 📝 Packing List                      │
│ ☐ Passport & visa                    │
│ ☐ Travel adapter (EU plug)           │
│ ☐ Camera & charger                   │
│                                       │
│ 📞 Important Contacts                │
│ • Emergency: +34 xxx xxx xxx         │
│ • Hotel: +34 xxx xxx xxx             │
│                                       │
│ [+ Add Note Section]                 │
└──────────────────────────────────────┘
```

## Features Breakdown

### Phase 1: MVP (Current Scope) - 85% COMPLETE 🚧

#### 1.1 Trip Header & Overview ✅ COMPLETE (v0.3.0)
- ✅ Display trip name, dates, origin
- ✅ Show trip statistics (locations, activities, bookings count)
- ✅ Tab navigation setup

#### 1.2 Timeline View ✅ COMPLETE (v0.3.0)
- ✅ Day-by-day expandable cards
- ✅ Display activities grouped by time slot
- ✅ Show linked bookings on relevant days
- ✅ Empty states for days without activities

#### 1.3 Locations List ✅ COMPLETE (v0.3.0)
- ✅ Display all locations chronologically
- ✅ Show date range and activity count per location
- ✅ Visual order badges (#1, #2, #3...)

#### 1.4 Bookings List ✅ COMPLETE (v0.3.0)
- ✅ Display all bookings chronologically
- ✅ Categorize by booking type
- ✅ Show key information (confirmation, times, price)
- ✅ Timezone-aware datetime display

#### 1.5 Basic Add (Create Operations) ✅ COMPLETE (v0.4.0)
- ✅ Add activities to days with full form validation
- ✅ Add locations with auto-ordering
- ✅ Add bookings with timezone support
- ✅ Context-aware FAB that changes based on tab
- ✅ Form validation with error messages
- ✅ Snackbar notifications
- ⏳ Edit activity/location/booking details (TODO)
- ⏳ Delete items with confirmation (TODO)

### Phase 2: Enhanced Features

#### 2.1 Rich Notes
- Markdown support for notes
- Checklist functionality
- Photo attachments
- Voice notes

#### 2.2 Booking Management
- Screenshot capture/upload
- Document scanning
- Email parsing for confirmations
- Calendar sync

#### 2.3 Smart Suggestions
- Activity recommendations based on location
- Optimal routing suggestions
- Time slot auto-assignment
- Gap detection (free time identification)

#### 2.4 Collaboration
- Share trip with travel companions
- Real-time updates
- Comments on activities
- Assignment of activities to travelers

### Phase 3: Advanced Features

#### 3.1 AI Integration
- Natural language trip planning
- Smart itinerary generation
- Personalized recommendations
- Budget optimization

#### 3.2 Offline Support
- Full offline access
- Offline maps
- Download all bookings
- Sync when online

#### 3.3 Real-Time Updates
- Flight status tracking
- Weather alerts
- Location-based notifications
- Traffic and delay warnings

## User Interactions

### Navigation Flows

#### From Home to Trip Detail
```
Home Screen → Tap Trip Card → Trip Detail (Timeline Tab)
```

#### Within Trip Detail
```
Timeline Tab:
- Tap Day → Expand/Collapse
- Tap Activity → View Details → Edit
- Tap "Add Activity" → Activity Form → Save
- Long press Activity → Quick actions menu

Locations Tab:
- Tap Location → Filter timeline to that location
- Drag location → Reorder
- Tap "⋮" → Edit/Delete location

Bookings Tab:
- Tap Booking → View full details
- Tap "Edit" → Booking form
- Tap screenshot → Full screen view

Overview Tab:
- Tap note section → Edit notes
- Tap "Add Note Section" → Create custom section
```

### Add Activity Flow
```
1. Tap [+ Add Activity] on day card
2. Activity Sheet opens:
   ┌──────────────────────────────────┐
   │ Add Activity                     │
   ├──────────────────────────────────┤
   │ Title*                           │
   │ [                              ] │
   │                                  │
   │ Type                             │
   │ [🎨 Attraction ▼]               │
   │                                  │
   │ Time Slot                        │
   │ [Morning] [Afternoon] [Evening]  │
   │                                  │
   │ Description                      │
   │ [                              ] │
   │                                  │
   │ Link to Booking (optional)       │
   │ [Select booking...]              │
   │                                  │
   │ [Cancel]  [Save Activity]        │
   └──────────────────────────────────┘
3. Fill in details
4. Save → Activity appears in timeline
```

### Add Booking Flow
```
1. Tap [+ Add Booking] (FAB or from bookings tab)
2. Booking Type Selection:
   ┌──────────────────────────────────┐
   │ What type of booking?            │
   │                                  │
   │  ✈️  Flight                      │
   │  🏨  Hotel                       │
   │  🚂  Train                       │
   │  🚌  Bus                         │
   │  🎫  Ticket/Event                │
   │  📝  Other                       │
   └──────────────────────────────────┘
3. Booking Form (e.g., Flight):
   ┌──────────────────────────────────┐
   │ Flight Booking                   │
   ├──────────────────────────────────┤
   │ Airline*                         │
   │ [                              ] │
   │                                  │
   │ Flight Number                    │
   │ [                              ] │
   │                                  │
   │ From*                            │
   │ [New York (JFK)               ] │
   │                                  │
   │ To*                              │
   │ [Barcelona (BCN)              ] │
   │                                  │
   │ Departure*                       │
   │ [Nov 15, 2025  08:30 AM  EDT  ] │
   │                                  │
   │ Arrival*                         │
   │ [Nov 15, 2025  10:45 PM  CET  ] │
   │                                  │
   │ Confirmation Number              │
   │ [                              ] │
   │                                  │
   │ Price                            │
   │ [850] [USD ▼]                   │
   │                                  │
   │ Notes                            │
   │ [                              ] │
   │                                  │
   │ 📷 Add Screenshot                │
   │                                  │
   │ [Cancel]      [Save Booking]     │
   └──────────────────────────────────┘
4. Save → Booking appears in list and timeline
```

## Technical Considerations

### Data Relationships
```
Trip (1) ──→ (Many) Locations
Trip (1) ──→ (Many) Bookings
Location (1) ──→ (Many) Activities

Query Strategy:
- Load trip with locations (eager)
- Load activities by location (lazy, on demand)
- Load all bookings for trip (eager)
- Load day-specific data when day is expanded
```

### State Management
```kotlin
TripDetailUiState:
- trip: Trip
- locations: List<Location>
- activitiesByDay: Map<String, List<Activity>>
- bookings: List<Booking>
- expandedDays: Set<String>
- selectedTab: TripDetailTab
- isLoading: Boolean
- error: String?
```

### Performance Optimizations
1. **Lazy Loading**: Load activities only when day is expanded
2. **Pagination**: For trips with many days/activities
3. **Caching**: Cache frequently accessed data
4. **Incremental Updates**: Only refresh changed items

### Accessibility
- Screen reader support for timeline
- High contrast mode for dates/times
- Large touch targets for expandable sections
- Keyboard navigation support

## Success Metrics

### User Engagement
- Average time spent in trip detail view
- Number of activities added per trip
- Number of bookings added per trip
- Daily active usage during trip

### Feature Adoption
- % of users who add at least one activity
- % of users who add at least one booking
- % of users who use notes feature
- Average number of days with planned activities

### User Satisfaction
- Task completion rate (can user find what they need?)
- Navigation ease score
- Feature discovery rate
- User feedback ratings

## Future Enhancements

### Short-term (Next Release)
1. Drag-and-drop reordering of activities
2. Duplicate activities across days
3. Activity templates (common activities)
4. Quick filters (by type, location)

### Medium-term
1. Map view integration
2. Photo gallery per trip
3. Expense tracking per day
4. Collaboration features
5. Share itinerary as PDF

### Long-term
1. AI trip planner
2. Real-time updates and alerts
3. Offline maps
4. Multi-trip comparison
5. Travel blog generation

## Open Questions

1. **Timeline Density**: How much information should be visible in collapsed day view?
2. **Booking Parsing**: Should we auto-parse booking emails?
3. **Activity Timing**: Should we support exact times or just time slots?
4. **Location Granularity**: Support for neighborhoods/districts within cities?
5. **Budget Tracking**: Track actual expenses vs. estimates?
6. **Collaboration**: Multi-user editing or view-only sharing first?

## Dependencies

### Existing Components
- Trip entity and repository
- Location, Activity, Booking entities
- Navigation system (type-safe)
- Material 3 design system

### New Components Needed
1. TripDetailScreen (main screen)
2. TimelineTab component
3. LocationsTab component
4. BookingsTab component
5. OverviewTab component
6. DayCard component
7. ActivityCard component
8. BookingCard component
9. AddActivityDialog/Screen
10. AddBookingDialog/Screen
11. TripDetailViewModel

### External Dependencies
- Date/time formatting utilities
- Timeline visualization library (consider custom)
- Image picker for booking screenshots
- Potentially: Markdown renderer for rich notes

## Conclusion

This Trip Detail feature will serve as the core user experience for Travlogue, transforming it from a simple trip list into a comprehensive travel planning and management tool. The phased approach allows for rapid iteration while maintaining a clear vision for the full feature set.

The MVP focuses on essential viewing and basic editing capabilities, ensuring users can effectively plan their trips. Future phases will add intelligence, collaboration, and real-time features that truly differentiate Travlogue in the travel app market.
