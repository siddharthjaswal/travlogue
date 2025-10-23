import SwiftUI
import Shared

struct TripDetailViewEnhanced: View {
    let trip: Trip
    @StateObject private var viewModel: TripDetailViewModelWrapper
    @State private var selectedTab: TripDetailTab = .overview

    init(trip: Trip) {
        self.trip = trip
        _viewModel = StateObject(wrappedValue: TripDetailViewModelWrapper(tripId: trip.id))
    }

    var body: some View {
        VStack(spacing: 0) {
            // Tab Picker
            Picker("Tab", selection: $selectedTab) {
                Text("Overview").tag(TripDetailTab.overview)
                Text("Timeline").tag(TripDetailTab.timeline)
                Text("Bookings").tag(TripDetailTab.bookings)
                Text("Activities").tag(TripDetailTab.activities)
                Text("Locations").tag(TripDetailTab.locations)
            }
            .pickerStyle(.segmented)
            .padding()

            // Tab Content
            TabView(selection: $selectedTab) {
                OverviewTabView(trip: trip, viewModel: viewModel)
                    .tag(TripDetailTab.overview)

                TimelineTabView(viewModel: viewModel)
                    .tag(TripDetailTab.timeline)

                BookingsTabView(viewModel: viewModel)
                    .tag(TripDetailTab.bookings)

                ActivitiesTabView(viewModel: viewModel)
                    .tag(TripDetailTab.activities)

                LocationsTabView(viewModel: viewModel)
                    .tag(TripDetailTab.locations)
            }
            .tabViewStyle(.page(indexDisplayMode: .never))
        }
        .navigationTitle(trip.name)
        .navigationBarTitleDisplayMode(.large)
    }
}

// MARK: - Overview Tab
struct OverviewTabView: View {
    let trip: Trip
    @ObservedObject var viewModel: TripDetailViewModelWrapper

    var body: some View {
        ScrollView {
            VStack(alignment: .leading, spacing: 20) {
                // Trip Header
                VStack(alignment: .leading, spacing: 8) {
                    if !trip.originCity.isEmpty {
                        HStack {
                            Image(systemName: "location.fill")
                            Text(trip.originCity)
                        }
                        .foregroundColor(.secondary)
                    }

                    if trip.dateType == .fixed {
                        if let startDate = trip.startDate, let endDate = trip.endDate {
                            HStack {
                                Image(systemName: "calendar")
                                Text("\(startDate) - \(endDate)")
                            }
                            .foregroundColor(.secondary)
                        }
                    } else {
                        HStack {
                            Image(systemName: "calendar.badge.clock")
                            Text("Flexible dates")
                        }
                        .foregroundColor(.secondary)
                    }
                }
                .padding()
                .frame(maxWidth: .infinity, alignment: .leading)
                .background(Color(.systemGray6))
                .cornerRadius(12)

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
            LazyVStack(alignment: .leading, spacing: 16) {
                if viewModel.daySchedules.isEmpty {
                    EmptyStateView(
                        icon: "calendar",
                        message: "No timeline items yet"
                    )
                } else {
                    ForEach(Array(viewModel.daySchedules.enumerated()), id: \.element.date) { index, schedule in
                        DayScheduleCard(schedule: schedule)
                    }
                }
            }
            .padding()
        }
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

// MARK: - Activities Tab
struct ActivitiesTabView: View {
    @ObservedObject var viewModel: TripDetailViewModelWrapper

    var body: some View {
        ScrollView {
            LazyVStack(spacing: 16) {
                if viewModel.activities.isEmpty {
                    EmptyStateView(
                        icon: "star",
                        message: "No activities planned yet"
                    )
                } else {
                    ForEach(viewModel.activities, id: \.id) { activity in
                        ActivityCardView(activity: activity)
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
            HStack {
                Image(systemName: bookingIcon)
                    .font(.title2)
                    .foregroundColor(bookingColor)

                VStack(alignment: .leading, spacing: 4) {
                    Text(booking.confirmationNumber ?? "No confirmation")
                        .font(.headline)

                    Text(booking.provider)
                        .font(.subheadline)
                        .foregroundColor(.secondary)
                }

                Spacer()

                if let price = booking.price {
                    Text("$\(String(format: "%.2f", price))")
                        .font(.headline)
                        .foregroundColor(.blue)
                }
            }

            if let from = booking.fromLocation, let to = booking.toLocation {
                HStack {
                    Text(from)
                    Image(systemName: "arrow.right")
                    Text(to)
                }
                .font(.caption)
                .foregroundColor(.secondary)
            } else if let to = booking.toLocation {
                Text(to)
                    .font(.caption)
                    .foregroundColor(.secondary)
            }
        }
        .padding()
        .background(Color(.systemGray6))
        .cornerRadius(12)
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
        HStack(spacing: 12) {
            Image(systemName: activityIcon)
                .font(.title2)
                .foregroundColor(.blue)

            VStack(alignment: .leading, spacing: 4) {
                Text(activity.title)
                    .font(.headline)

                if let description = activity.description_ {
                    Text(description)
                        .font(.caption)
                        .foregroundColor(.secondary)
                        .lineLimit(2)
                }

                if let startTime = activity.startTime, let endTime = activity.endTime {
                    Text("\(activity.date) • \(startTime) - \(endTime)")
                        .font(.caption2)
                        .foregroundColor(.secondary)
                }
            }

            Spacer()
        }
        .padding()
        .background(Color(.systemGray6))
        .cornerRadius(12)
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
            HStack {
                Image(systemName: "mappin.circle.fill")
                    .font(.title2)
                    .foregroundColor(.red)

                VStack(alignment: .leading, spacing: 4) {
                    Text(location.name)
                        .font(.headline)

                    Text(location.country)
                        .font(.subheadline)
                        .foregroundColor(.secondary)
                }

                Spacer()

                Text("Day \(location.order)")
                    .font(.caption)
                    .padding(.horizontal, 8)
                    .padding(.vertical, 4)
                    .background(Color.blue.opacity(0.2))
                    .cornerRadius(8)
            }

            if let arrival = location.arrivalDateTime, let departure = location.departureDateTime {
                VStack(alignment: .leading, spacing: 4) {
                    HStack {
                        Text("Arrival:")
                            .font(.caption)
                            .foregroundColor(.secondary)
                        Text(formatDateTime(arrival))
                            .font(.caption)
                    }

                    HStack {
                        Text("Departure:")
                            .font(.caption)
                            .foregroundColor(.secondary)
                        Text(formatDateTime(departure))
                            .font(.caption)
                    }
                }
            }
        }
        .padding()
        .background(Color(.systemGray6))
        .cornerRadius(12)
    }

    func formatDateTime(_ isoString: String) -> String {
        // Simple formatting - in production use proper ISO8601 parsing
        return isoString.prefix(16).replacingOccurrences(of: "T", with: " ")
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

// MARK: - Tab Enum
enum TripDetailTab {
    case overview
    case timeline
    case bookings
    case activities
    case locations
}
