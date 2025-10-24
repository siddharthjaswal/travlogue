import SwiftUI
import Shared

struct TripDetailViewEnhanced: View {
    let trip: Trip
    @StateObject private var viewModel: TripDetailViewModelWrapper
    @State private var selectedTab: TripDetailTab = .timeline

    init(trip: Trip) {
        self.trip = trip
        _viewModel = StateObject(wrappedValue: TripDetailViewModelWrapper(tripId: trip.id))
    }

    var body: some View {
        VStack(spacing: 0) {
            // Trip Header Section (matching Android design)
            TripHeaderSectionView(trip: trip)
                .padding(.horizontal)
                .padding(.vertical, 12)
                .background(Color(.systemBackground))

            Divider()

            // Tab Picker (matching Android tab order)
            Picker("Tab", selection: $selectedTab) {
                Text("Timeline").tag(TripDetailTab.timeline)
                Text("Locations").tag(TripDetailTab.locations)
                Text("Bookings").tag(TripDetailTab.bookings)
                Text("Overview").tag(TripDetailTab.overview)
            }
            .pickerStyle(.segmented)
            .padding()

            // Tab Content
            TabView(selection: $selectedTab) {
                TimelineTabView(viewModel: viewModel)
                    .tag(TripDetailTab.timeline)

                LocationsTabView(viewModel: viewModel)
                    .tag(TripDetailTab.locations)

                BookingsTabView(viewModel: viewModel)
                    .tag(TripDetailTab.bookings)

                OverviewTabView(trip: trip, viewModel: viewModel)
                    .tag(TripDetailTab.overview)
            }
            .tabViewStyle(.page(indexDisplayMode: .never))
        }
        .navigationTitle(trip.name)
        .navigationBarTitleDisplayMode(.inline)
    }
}

// MARK: - Overview Tab
struct OverviewTabView: View {
    let trip: Trip
    @ObservedObject var viewModel: TripDetailViewModelWrapper

    var body: some View {
        ScrollView {
            VStack(alignment: .leading, spacing: 20) {
                // Stats Section
                HStack(spacing: 20) {
                    StatCard(icon: "mappin.and.ellipse", value: "\(viewModel.locations.count)", label: "Locations")
                    StatCard(icon: "ticket", value: "\(viewModel.bookings.count)", label: "Bookings")
                    StatCard(icon: "star", value: "\(viewModel.activities.count)", label: "Activities")
                }
                .padding(.horizontal)

                // Gaps Section
                if !viewModel.gaps.isEmpty {
                    VStack(alignment: .leading, spacing: 12) {
                        Text("Gaps Detected")
                            .font(.headline)
                            .padding(.horizontal)

                        ForEach(viewModel.gaps, id: \.id) { gap in
                            GapCardView(gap: gap)
                        }
                    }
                }

                Spacer()
            }
            .padding()
        }
    }
}

// MARK: - Timeline Tab
struct TimelineTabView: View {
    @ObservedObject var viewModel: TripDetailViewModelWrapper

    var body: some View {
        ScrollView {
            if viewModel.timelineItems.isEmpty {
                EmptyStateView(
                    icon: "calendar",
                    message: "No timeline items yet"
                )
            } else {
                LazyVStack(alignment: .leading, spacing: 0) {
                    ForEach(Array(viewModel.timelineItems.enumerated()), id: \.offset) { index, item in
                        let showDate = shouldShowDate(for: item, at: index, in: viewModel.timelineItems)
                        TimelineItemView(item: item, showDate: showDate)
                    }
                }
                .padding(.horizontal)
                .padding(.vertical, 24)
            }
        }
    }

    private func shouldShowDate(for item: TimelineItem, at index: Int, in items: [TimelineItem]) -> Bool {
        guard let currentDate = extractDate(from: item.getDateTime()) else {
            return false
        }

        if index == 0 {
            return true
        }

        let previousItems = Array(items.prefix(index))
        return !previousItems.contains { extractDate(from: $0.getDateTime()) == currentDate }
    }

    private func extractDate(from isoDateTime: String?) -> String? {
        guard let isoDateTime = isoDateTime else { return nil }
        return String(isoDateTime.prefix(10)) // YYYY-MM-DD
    }
}

// MARK: - Bookings Tab
struct BookingsTabView: View {
    @ObservedObject var viewModel: TripDetailViewModelWrapper

    var body: some View {
        ScrollView {
            LazyVStack(spacing: 16) {
                if viewModel.bookings.isEmpty {
                    EmptyStateView(
                        icon: "ticket",
                        message: "No bookings yet"
                    )
                } else {
                    ForEach(viewModel.bookings, id: \.id) { booking in
                        BookingCardView(booking: booking)
                    }
                }
            }
            .padding()
        }
    }
}

// MARK: - Locations Tab
struct LocationsTabView: View {
    @ObservedObject var viewModel: TripDetailViewModelWrapper

    var body: some View {
        ScrollView {
            LazyVStack(spacing: 16) {
                if viewModel.locations.isEmpty {
                    EmptyStateView(
                        icon: "mappin",
                        message: "No locations added yet"
                    )
                } else {
                    ForEach(viewModel.locations, id: \.id) { location in
                        LocationCardView(location: location)
                    }
                }
            }
            .padding()
        }
    }
}

// MARK: - Supporting Views
struct StatCard: View {
    let icon: String
    let value: String
    let label: String

    var body: some View {
        VStack(spacing: 8) {
            Image(systemName: icon)
                .font(.title2)
                .foregroundColor(.blue)

            Text(value)
                .font(.title2)
                .fontWeight(.bold)

            Text(label)
                .font(.caption)
                .foregroundColor(.secondary)
        }
        .frame(maxWidth: .infinity)
        .padding()
        .background(Color(.systemGray6))
        .cornerRadius(12)
    }
}

struct GapCardView: View {
    let gap: Gap

    var body: some View {
        HStack(spacing: 12) {
            Image(systemName: gapIcon)
                .font(.title2)
                .foregroundColor(gapColor)

            VStack(alignment: .leading, spacing: 4) {
                Text(gapTypeText)
                    .font(.headline)

                Text("\(gap.fromDate) to \(gap.toDate)")
                    .font(.caption)
                    .foregroundColor(.secondary)
            }

            Spacer()
        }
        .padding()
        .background(gapColor.opacity(0.1))
        .cornerRadius(12)
        .padding(.horizontal)
    }

    var gapIcon: String {
        switch gap.gapType {
        case GapType.missingTransit: return "car.fill"
        case GapType.unplannedDay: return "calendar"
        case GapType.timeConflict: return "exclamationmark.triangle.fill"
        default: return "exclamationmark.triangle.fill"
        }
    }

    var gapColor: Color {
        switch gap.gapType {
        case GapType.missingTransit: return .orange
        case GapType.unplannedDay: return .yellow
        case GapType.timeConflict: return .red
        default: return .gray
        }
    }

    var gapTypeText: String {
        switch gap.gapType {
        case GapType.missingTransit: return "Missing Transit"
        case GapType.unplannedDay: return "Unplanned Day"
        case GapType.timeConflict: return "Time Conflict"
        default: return "Gap"
        }
    }
}

struct DayScheduleCard: View {
    let schedule: DaySchedule

    var body: some View {
        VStack(alignment: .leading, spacing: 12) {
            // Day Header
            HStack {
                Text("Day \(schedule.dayNumber)")
                    .font(.headline)
                    .fontWeight(.bold)

                Spacer()

                if let location = schedule.location {
                    Text(location.name)
                        .font(.subheadline)
                        .foregroundColor(.secondary)
                }
            }

            // Activities by time slot
            ForEach(Array(schedule.activitiesByTimeSlot.keys).sorted(by: { timeSlotOrder($0) < timeSlotOrder($1) }), id: \.self) { timeSlot in
                if let activities = schedule.activitiesByTimeSlot[timeSlot] {
                    VStack(alignment: .leading, spacing: 8) {
                        Text(timeSlotName(timeSlot))
                            .font(.caption)
                            .foregroundColor(.secondary)
                            .textCase(.uppercase)

                        ForEach(activities, id: \.id) { activity in
                            ActivityRowView(activity: activity)
                        }
                    }
                }
            }
        }
        .padding()
        .background(Color(.systemGray6))
        .cornerRadius(12)
    }

    func timeSlotName(_ timeSlot: TimeSlot) -> String {
        switch timeSlot {
        case .morning: return "Morning"
        case .afternoon: return "Afternoon"
        case .evening: return "Evening"
        case .fullDay: return "Full Day"
        default: return "All Day"
        }
    }

    func timeSlotOrder(_ timeSlot: TimeSlot) -> Int {
        switch timeSlot {
        case .morning: return 0
        case .afternoon: return 1
        case .evening: return 2
        case .fullDay: return 3
        default: return 4
        }
    }
}

struct ActivityRowView: View {
    let activity: Activity

    var body: some View {
        HStack(spacing: 12) {
            Image(systemName: activityIcon)
                .foregroundColor(.blue)

            VStack(alignment: .leading, spacing: 2) {
                Text(activity.title)
                    .font(.subheadline)
                    .fontWeight(.medium)

                if let startTime = activity.startTime, let endTime = activity.endTime {
                    Text("\(startTime) - \(endTime)")
                        .font(.caption2)
                        .foregroundColor(.secondary)
                }
            }

            Spacer()
        }
        .padding(.vertical, 4)
    }

    var activityIcon: String {
        switch activity.type {
        case ActivityType.attraction: return "star.fill"
        case ActivityType.food: return "fork.knife"
        case ActivityType.booking: return "calendar.badge.checkmark"
        case ActivityType.transit: return "car.fill"
        case ActivityType.other: return "circle.fill"
        default: return "circle.fill"
        }
    }
}

struct BookingCardView: View {
    let booking: Booking

    var body: some View {
        VStack(alignment: .leading, spacing: 12) {
            // Type and provider header
            HStack(spacing: 12) {
                // Type icon
                Text(bookingEmoji)
                    .font(.title)
                    .frame(width: 48, height: 48)
                    .background(Color.purple.opacity(0.12))
                    .cornerRadius(8)

                VStack(alignment: .leading, spacing: 2) {
                    Text(booking.type.name.replacingOccurrences(of: "_", with: " "))
                        .font(.caption)
                        .foregroundColor(.blue)
                        .textCase(.uppercase)
                    Text(booking.provider)
                        .font(.headline)
                }

                Spacer()
            }

            // Date and time
            VStack(alignment: .leading, spacing: 4) {
                Text(formatBookingDateTime(booking.startDateTime, booking.timezone))
                    .font(.subheadline)
                    .foregroundColor(.secondary)

                // From/To locations
                if let from = booking.fromLocation, let to = booking.toLocation {
                    HStack(spacing: 4) {
                        Text(from)
                        Image(systemName: "arrow.right")
                            .font(.caption)
                        Text(to)
                    }
                    .font(.subheadline)
                }

                // End time if different day
                if let endTime = booking.endDateTime {
                    Text("Until: \(formatBookingDateTime(endTime, booking.timezone))")
                        .font(.caption)
                        .foregroundColor(.secondary)
                }
            }

            // Confirmation and price
            HStack {
                if let confirmation = booking.confirmationNumber {
                    VStack(alignment: .leading, spacing: 2) {
                        Text("Confirmation")
                            .font(.caption)
                            .foregroundColor(.secondary)
                        Text(confirmation)
                            .font(.subheadline)
                    }
                }

                Spacer()

                if let price = booking.price, let currency = booking.currency {
                    VStack(alignment: .trailing, spacing: 2) {
                        Text("Price")
                            .font(.caption)
                            .foregroundColor(.secondary)
                        Text("\(currency) \(String(format: "%.2f", price))")
                            .font(.subheadline)
                            .fontWeight(.medium)
                    }
                }
            }

            // Notes section
            if let notes = booking.notes, !notes.isEmpty {
                Text(notes)
                    .font(.caption)
                    .padding(12)
                    .frame(maxWidth: .infinity, alignment: .leading)
                    .background(Color.gray.opacity(0.1))
                    .cornerRadius(8)
            }
        }
        .padding()
        .background(Color(.systemBackground))
        .cornerRadius(12)
        .shadow(color: Color.black.opacity(0.05), radius: 2, x: 0, y: 1)
    }

    var bookingEmoji: String {
        switch booking.type {
        case BookingType.flight: return "✈️"
        case BookingType.hotel: return "🏨"
        case BookingType.train: return "🚂"
        case BookingType.bus: return "🚌"
        case BookingType.ticket: return "🎫"
        default: return "📝"
        }
    }

    func formatBookingDateTime(_ isoString: String?, _ timezone: String?) -> String {
        guard let isoString = isoString else { return "N/A" }
        let formatter = ISO8601DateFormatter()
        formatter.formatOptions = [.withFullDate, .withTime, .withTimeZone]
        if let date = formatter.date(from: isoString) {
            let displayFormatter = DateFormatter()
            displayFormatter.dateFormat = "MMM d, yyyy 'at' h:mm a"
            return displayFormatter.string(from: date)
        }
        return isoString
    }

    var bookingIcon: String {
        switch booking.type {
        case BookingType.flight: return "airplane"
        case BookingType.hotel: return "bed.double.fill"
        case BookingType.train: return "tram.fill"
        case BookingType.bus: return "bus.fill"
        case BookingType.ticket: return "ticket.fill"
        case BookingType.other: return "circle.fill"
        default: return "ticket.fill"
        }
    }

    var bookingColor: Color {
        switch booking.type {
        case BookingType.flight: return .blue
        case BookingType.hotel: return .purple
        case BookingType.train: return .green
        case BookingType.bus: return .orange
        case BookingType.ticket: return .cyan
        default: return .gray
        }
    }
}

struct ActivityCardView: View {
    let activity: Activity

    var body: some View {
        HStack(alignment: .top, spacing: 12) {
            // Activity icon with colored background
            ZStack {
                Circle()
                    .fill(activityColor.opacity(0.15))
                    .frame(width: 40, height: 40)
                Image(systemName: activityIcon)
                    .font(.system(size: 18))
                    .foregroundColor(activityColor)
            }

            VStack(alignment: .leading, spacing: 6) {
                // Title
                Text(activity.title)
                    .font(.headline)

                // Time info
                if let startTime = activity.startTime, let endTime = activity.endTime {
                    HStack(spacing: 4) {
                        Image(systemName: "clock")
                            .font(.caption)
                        Text("\(startTime) - \(endTime)")
                            .font(.subheadline)
                    }
                    .foregroundColor(.secondary)
                } else if let timeSlot = activity.timeSlot {
                    HStack(spacing: 4) {
                        Image(systemName: "clock")
                            .font(.caption)
                        Text(timeSlotDisplay(timeSlot))
                            .font(.subheadline)
                    }
                    .foregroundColor(.secondary)
                }

                // Description
                if let description = activity.description_, !description.isEmpty {
                    Text(description)
                        .font(.caption)
                        .foregroundColor(.secondary)
                        .lineLimit(2)
                }

                // Activity type badge
                Text(activity.type.name.replacingOccurrences(of: "_", with: " "))
                    .font(.caption2)
                    .fontWeight(.medium)
                    .padding(.horizontal, 8)
                    .padding(.vertical, 4)
                    .background(activityColor.opacity(0.15))
                    .cornerRadius(12)
            }

            Spacer()
        }
        .padding()
        .background(Color(.systemBackground))
        .cornerRadius(12)
        .shadow(color: Color.black.opacity(0.05), radius: 2, x: 0, y: 1)
    }

    var activityColor: Color {
        switch activity.type {
        case ActivityType.attraction: return .blue
        case ActivityType.food: return .orange
        case ActivityType.booking: return .purple
        case ActivityType.transit: return .green
        default: return .gray
        }
    }

    func timeSlotDisplay(_ timeSlot: TimeSlot) -> String {
        switch timeSlot {
        case .morning: return "Morning (6 AM - 12 PM)"
        case .afternoon: return "Afternoon (12 PM - 6 PM)"
        case .evening: return "Evening (6 PM - 12 AM)"
        case .fullDay: return "All day"
        default: return "All day"
        }
    }

    var activityIcon: String {
        switch activity.type {
        case ActivityType.attraction: return "star.fill"
        case ActivityType.food: return "fork.knife"
        case ActivityType.booking: return "calendar.badge.checkmark"
        case ActivityType.transit: return "car.fill"
        case ActivityType.other: return "circle.fill"
        default: return "circle.fill"
        }
    }
}

struct LocationCardView: View {
    let location: Location

    var body: some View {
        VStack(alignment: .leading, spacing: 12) {
            // Header with date badge and location name
            HStack(alignment: .top, spacing: 16) {
                // Date Badge
                VStack(spacing: 2) {
                    Text(getDateDay(from: location.arrivalDateTime ?? location.date))
                        .font(.title)
                        .fontWeight(.bold)
                    Text(getDateAbbreviation(from: location.arrivalDateTime ?? location.date))
                        .font(.caption)
                }
                .frame(minWidth: 60)
                .padding(.vertical, 8)
                .padding(.horizontal, 12)
                .background(Color.blue.opacity(0.15))
                .cornerRadius(8)

                VStack(alignment: .leading, spacing: 4) {
                    HStack(spacing: 6) {
                        Image(systemName: "mappin")
                            .font(.system(size: 16))
                            .foregroundColor(.blue)
                        Text(location.name)
                            .font(.headline)
                    }
                    Text(location.country)
                        .font(.subheadline)
                        .foregroundColor(.secondary)
                }

                Spacer()
            }

            // Timing info if available
            if location.arrivalDateTime != nil || location.departureDateTime != nil {
                Divider()

                HStack(alignment: .top, spacing: 12) {
                    VStack(alignment: .leading, spacing: 8) {
                        if let arrival = location.arrivalDateTime {
                            TimingRow(
                                icon: "airplane.arrival",
                                iconColor: .blue,
                                label: "Arrival",
                                value: formatDateTime(arrival)
                            )
                        }

                        if let departure = location.departureDateTime {
                            TimingRow(
                                icon: "airplane.departure",
                                iconColor: .orange,
                                label: "Departure",
                                value: formatDateTime(departure)
                            )
                        }
                    }

                    Spacer()

                    // Stay duration chip
                    if let arrival = location.arrivalDateTime,
                       let departure = location.departureDateTime {
                        let nights = calculateNights(from: arrival, to: departure)
                        HStack(spacing: 4) {
                            Image(systemName: "calendar")
                                .font(.system(size: 12))
                            Text(nights == 0 ? "Same-day" : "\(nights) \(nights == 1 ? "night" : "nights")")
                                .font(.caption)
                                .fontWeight(.medium)
                        }
                        .padding(.horizontal, 10)
                        .padding(.vertical, 6)
                        .background(Color.green.opacity(0.15))
                        .cornerRadius(16)
                    }
                }
            }
        }
        .padding()
        .background(Color(.systemBackground))
        .cornerRadius(12)
        .shadow(color: Color.black.opacity(0.05), radius: 2, x: 0, y: 1)
    }

    func getDateDay(from isoString: String?) -> String {
        guard let isoString = isoString else { return "\(location.order)" }
        let formatter = ISO8601DateFormatter()
        formatter.formatOptions = [.withFullDate, .withTime, .withTimeZone]
        if let date = formatter.date(from: isoString) {
            let dayFormatter = DateFormatter()
            dayFormatter.dateFormat = "d"
            return dayFormatter.string(from: date)
        }
        return "\(location.order)"
    }

    func getDateAbbreviation(from isoString: String?) -> String {
        guard let isoString = isoString else { return "" }
        let formatter = ISO8601DateFormatter()
        formatter.formatOptions = [.withFullDate, .withTime, .withTimeZone]
        if let date = formatter.date(from: isoString) {
            let dayFormatter = DateFormatter()
            dayFormatter.dateFormat = "EEE"
            return dayFormatter.string(from: date)
        }
        return ""
    }

    func formatDateTime(_ isoString: String) -> String {
        let formatter = ISO8601DateFormatter()
        formatter.formatOptions = [.withFullDate, .withTime, .withTimeZone]
        if let date = formatter.date(from: isoString) {
            let displayFormatter = DateFormatter()
            displayFormatter.dateFormat = "MMM d, h:mm a"
            return displayFormatter.string(from: date)
        }
        return isoString.prefix(16).replacingOccurrences(of: "T", with: " ")
    }

    func calculateNights(from arrival: String, to departure: String) -> Int {
        let formatter = ISO8601DateFormatter()
        formatter.formatOptions = [.withFullDate, .withTime, .withTimeZone]
        guard let arrivalDate = formatter.date(from: arrival),
              let departureDate = formatter.date(from: departure) else {
            return 0
        }
        let calendar = Calendar.current
        let components = calendar.dateComponents([.day], from: arrivalDate, to: departureDate)
        return max(components.day ?? 0, 0)
    }
}

// Helper view for timing rows
struct TimingRow: View {
    let icon: String
    let iconColor: Color
    let label: String
    let value: String

    var body: some View {
        HStack(spacing: 12) {
            ZStack {
                RoundedRectangle(cornerRadius: 6)
                    .fill(iconColor.opacity(0.12))
                    .frame(width: 32, height: 32)
                Image(systemName: icon)
                    .font(.system(size: 14))
                    .foregroundColor(iconColor)
            }

            VStack(alignment: .leading, spacing: 2) {
                Text(label)
                    .font(.caption)
                    .foregroundColor(.secondary)
                Text(value)
                    .font(.subheadline)
            }
        }
    }
}

struct EmptyStateView: View {
    let icon: String
    let message: String

    var body: some View {
        VStack(spacing: 16) {
            Image(systemName: icon)
                .font(.system(size: 60))
                .foregroundColor(.gray)

            Text(message)
                .font(.body)
                .foregroundColor(.secondary)
        }
        .frame(maxWidth: .infinity)
        .padding(.top, 100)
    }
}

// MARK: - ViewModel Wrapper
@MainActor
class TripDetailViewModelWrapper: ObservableObject {
    @Published var locations: [Location] = []
    @Published var bookings: [Booking] = []
    @Published var activities: [Activity] = []
    @Published var gaps: [Gap] = []
    @Published var daySchedules: [DaySchedule] = []
    @Published var timelineItems: [TimelineItem] = []
    @Published var isLoading: Bool = true

    private let sharedViewModel: TripDetailViewModel

    init(tripId: String) {
        self.sharedViewModel = KoinHelper.companion.shared.getTripDetailViewModel(tripId: tripId)
        observeState()
    }

    private func observeState() {
        Task { @MainActor in
            while !Task.isCancelled {
                if let state = sharedViewModel.uiState.value as? TripDetailUiState {
                    self.locations = state.locations
                    self.bookings = state.bookings
                    self.gaps = state.gaps
                    self.daySchedules = state.daySchedules
                    self.timelineItems = state.timelineItems
                    // Extract all activities from day schedules
                    self.activities = state.daySchedules.flatMap { schedule in
                        schedule.activitiesByTimeSlot.values.flatMap { $0 }
                    }
                    self.isLoading = state.isLoading
                }
                try? await Task.sleep(nanoseconds: 500_000_000) // 0.5 seconds
            }
        }
    }
}

// MARK: - Timeline Item View
struct TimelineItemView: View {
    let item: TimelineItem
    let showDate: Bool

    var body: some View {
        HStack(alignment: .top, spacing: 12) {
            // Date badge (left side)
            if showDate, let dateTime = item.getDateTime() {
                CircularDateBadge(dateTime: dateTime, color: dotColor)
                    .frame(width: 50)
            } else {
                Spacer()
                    .frame(width: 50)
            }

            // Content (right side)
            VStack(spacing: 0) {
                TimelineItemContent(item: item)
                Spacer().frame(height: 12)
            }
        }
    }

    private var dotColor: Color {
        switch item {
        case is TimelineItem.TransitArrival, is TimelineItem.CityWelcome, is TimelineItem.ActivityItem:
            return .blue
        case is TimelineItem.HotelCheckIn, is TimelineItem.InTransit, is TimelineItem.BookingItem:
            return .purple
        case is TimelineItem.HotelCheckOut, is TimelineItem.CityGoodbye, is TimelineItem.TransitDeparture:
            return .orange
        case is TimelineItem.NothingPlanned:
            return .gray.opacity(0.5)
        default:
            return .blue
        }
    }
}

// MARK: - Circular Date Badge
struct CircularDateBadge: View {
    let dateTime: String
    let color: Color

    var body: some View {
        VStack(spacing: 2) {
            // Weekday
            Text(weekday)
                .font(.system(size: 9, weight: .semibold))
                .foregroundColor(color.opacity(0.8))
                .textCase(.uppercase)

            // Circle with day number
            ZStack {
                Circle()
                    .fill(color.opacity(0.15))
                    .shadow(color: .black.opacity(0.05), radius: 1, x: 0, y: 1)

                Text(day)
                    .font(.system(size: 12, weight: .regular))
                    .foregroundColor(color)
            }
            .frame(width: 32, height: 32)
        }
    }

    private var day: String {
        parseDateComponents().day
    }

    private var weekday: String {
        parseDateComponents().weekday
    }

    private func parseDateComponents() -> (day: String, weekday: String) {
        let formatter = ISO8601DateFormatter()
        formatter.formatOptions = [.withFullDate, .withTime, .withTimeZone]

        if let date = formatter.date(from: dateTime) {
            let dayFormatter = DateFormatter()
            dayFormatter.dateFormat = "d"
            let weekdayFormatter = DateFormatter()
            weekdayFormatter.dateFormat = "EEE"

            return (dayFormatter.string(from: date), weekdayFormatter.string(from: date).uppercased())
        }

        // Fallback: try parsing just the date part
        let datePart = String(dateTime.prefix(10))
        let dateFormatter = DateFormatter()
        dateFormatter.dateFormat = "yyyy-MM-dd"

        if let date = dateFormatter.date(from: datePart) {
            let dayFormatter = DateFormatter()
            dayFormatter.dateFormat = "d"
            let weekdayFormatter = DateFormatter()
            weekdayFormatter.dateFormat = "EEE"

            return (dayFormatter.string(from: date), weekdayFormatter.string(from: date).uppercased())
        }

        return ("--", "---")
    }
}

// MARK: - Timeline Item Content
struct TimelineItemContent: View {
    let item: TimelineItem

    var body: some View {
        Group {
            switch item {
            case let welcomeItem as TimelineItem.CityWelcome:
                WelcomeCityCard(
                    location: welcomeItem.location,
                    arrivalDateTime: welcomeItem.arrivalDateTime
                )

            case let hotelCheckIn as TimelineItem.HotelCheckIn:
                HotelCheckInCardView(booking: hotelCheckIn.booking)

            case let activityItem as TimelineItem.ActivityItem:
                ActivityCardView(activity: activityItem.activity)

            case let nothingPlanned as TimelineItem.NothingPlanned:
                NothingPlannedCard()

            case let hotelCheckOut as TimelineItem.HotelCheckOut:
                HotelCheckOutCardView(booking: hotelCheckOut.booking)

            case let cityGoodbye as TimelineItem.CityGoodbye:
                GoodbyeCityCard(
                    location: cityGoodbye.location,
                    departureDateTime: cityGoodbye.departureDateTime
                )

            case let transitDeparture as TimelineItem.TransitDeparture:
                CityDepartureCard(
                    location: transitDeparture.location,
                    departureDateTime: transitDeparture.departureDateTime,
                    departureBooking: transitDeparture.departureBooking
                )

            case let inTransit as TimelineItem.InTransit:
                InTransitCard(booking: inTransit.booking)

            case let originDeparture as TimelineItem.OriginDeparture:
                OriginDepartureCard(
                    originCity: originDeparture.originCity,
                    departureBooking: originDeparture.booking
                )

            case let bookingItem as TimelineItem.BookingItem:
                BookingCardView(booking: bookingItem.booking)

            case let transitArrival as TimelineItem.TransitArrival:
                CityArrivalCard(
                    location: transitArrival.location,
                    arrivalDateTime: transitArrival.arrivalDateTime,
                    arrivalBooking: transitArrival.arrivalBooking
                )

            default:
                Text("Unknown timeline item")
                    .font(.caption)
                    .foregroundColor(.secondary)
            }
        }
    }
}

// MARK: - Simple Card Components
struct NothingPlannedCard: View {
    var body: some View {
        Text("Nothing planned")
            .font(.subheadline)
            .foregroundColor(.secondary)
            .padding()
            .frame(maxWidth: .infinity, alignment: .leading)
            .background(Color(.systemGray6))
            .cornerRadius(12)
    }
}

struct HotelCheckInCardView: View {
    let booking: Booking

    var body: some View {
        VStack(alignment: .leading, spacing: 8) {
            HStack {
                Image(systemName: "bed.double.fill")
                    .foregroundColor(.purple)
                Text("Check-in: \(booking.provider)")
                    .font(.subheadline)
                    .fontWeight(.medium)
            }
        }
        .padding()
        .frame(maxWidth: .infinity, alignment: .leading)
        .background(Color(.systemBackground))
        .cornerRadius(12)
        .shadow(color: .black.opacity(0.05), radius: 2, x: 0, y: 1)
    }
}

struct HotelCheckOutCardView: View {
    let booking: Booking

    var body: some View {
        VStack(alignment: .leading, spacing: 8) {
            HStack {
                Image(systemName: "bed.double.fill")
                    .foregroundColor(.orange)
                Text("Check-out: \(booking.provider)")
                    .font(.subheadline)
                    .fontWeight(.medium)
            }
        }
        .padding()
        .frame(maxWidth: .infinity, alignment: .leading)
        .background(Color(.systemBackground))
        .cornerRadius(12)
        .shadow(color: .black.opacity(0.05), radius: 2, x: 0, y: 1)
    }
}

struct GoodbyeCityCard: View {
    let location: Location
    let departureDateTime: String

    var body: some View {
        VStack(alignment: .leading, spacing: 8) {
            HStack {
                Image(systemName: "hand.wave.fill")
                    .foregroundColor(.orange)
                Text("Leaving \(location.name)")
                    .font(.subheadline)
                    .fontWeight(.medium)
            }
        }
        .padding()
        .frame(maxWidth: .infinity, alignment: .leading)
        .background(Color(.systemBackground))
        .cornerRadius(12)
        .shadow(color: .black.opacity(0.05), radius: 2, x: 0, y: 1)
    }
}

struct CityDepartureCard: View {
    let location: Location
    let departureDateTime: String
    let departureBooking: Booking

    var body: some View {
        BookingCardView(booking: departureBooking)
    }
}

struct InTransitCard: View {
    let booking: Booking

    var body: some View {
        VStack(alignment: .leading, spacing: 8) {
            HStack {
                Image(systemName: "airplane")
                    .foregroundColor(.purple)
                Text("In transit")
                    .font(.subheadline)
                    .fontWeight(.medium)
            }
        }
        .padding()
        .frame(maxWidth: .infinity, alignment: .leading)
        .background(Color(.systemBackground))
        .cornerRadius(12)
        .shadow(color: .black.opacity(0.05), radius: 2, x: 0, y: 1)
    }
}

struct OriginDepartureCard: View {
    let originCity: String
    let departureBooking: Booking

    var body: some View {
        VStack(alignment: .leading, spacing: 8) {
            HStack {
                Image(systemName: "airplane.departure")
                    .foregroundColor(.blue)
                Text("Departing \(originCity)")
                    .font(.subheadline)
                    .fontWeight(.medium)
            }
            BookingCardView(booking: departureBooking)
        }
    }
}

struct CityArrivalCard: View {
    let location: Location
    let arrivalDateTime: String
    let arrivalBooking: Booking

    var body: some View {
        VStack(alignment: .leading, spacing: 8) {
            BookingCardView(booking: arrivalBooking)
        }
    }
}

struct WelcomeCityCard: View {
    let location: Location
    let arrivalDateTime: String

    var body: some View {
        VStack(alignment: .leading, spacing: 8) {
            HStack {
                Image(systemName: "mappin.circle.fill")
                    .font(.system(size: 24))
                    .foregroundColor(.blue)
                Text("Welcome to \(location.name)!")
                    .font(.headline)
            }
        }
        .padding()
        .frame(maxWidth: .infinity, alignment: .leading)
        .background(Color.blue.opacity(0.1))
        .cornerRadius(12)
    }
}

// MARK: - Trip Header Section
struct TripHeaderSectionView: View {
    let trip: Trip

    var body: some View {
        VStack(alignment: .leading, spacing: 12) {
            // Origin City
            HStack(spacing: 8) {
                Image(systemName: "airplane.departure")
                    .font(.system(size: 16))
                    .foregroundColor(.blue)

                Text("From \(trip.originCity)")
                    .font(.subheadline)
                    .foregroundColor(.secondary)
            }

            // Date Range
            HStack(spacing: 8) {
                Image(systemName: "calendar")
                    .font(.system(size: 16))
                    .foregroundColor(.blue)

                Text(formatDateRange(trip: trip))
                    .font(.subheadline)
                    .foregroundColor(.secondary)
            }
        }
        .frame(maxWidth: .infinity, alignment: .leading)
    }

    private func formatDateRange(trip: Trip) -> String {
        if trip.dateType == .fixed {
            guard let startDate = trip.startDate,
                  let endDate = trip.endDate else {
                return "Dates not set"
            }

            let formatter = DateFormatter()
            formatter.dateFormat = "yyyy-MM-dd"

            guard let start = formatter.date(from: startDate),
                  let end = formatter.date(from: endDate) else {
                return "\(startDate) – \(endDate)"
            }

            let calendar = Calendar.current
            let days = calendar.dateComponents([.day], from: start, to: end).day ?? 0
            let totalDays = days + 1

            let displayFormatter = DateFormatter()
            displayFormatter.dateFormat = "MMM d, yyyy"

            let formattedStart = displayFormatter.string(from: start)
            let formattedEnd = displayFormatter.string(from: end)

            return "\(formattedStart) – \(formattedEnd) · \(totalDays) \(totalDays == 1 ? "day" : "days")"
        } else {
            let duration = trip.flexibleDuration != nil ? " · ≈\(trip.flexibleDuration!) days" : ""
            return trip.flexibleMonth != nil ? "~\(trip.flexibleMonth!)\(duration)" : "Flexible dates\(duration)"
        }
    }
}

// MARK: - Tab Enum
enum TripDetailTab {
    case timeline
    case locations
    case bookings
    case overview
}
