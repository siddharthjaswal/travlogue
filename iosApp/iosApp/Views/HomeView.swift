import SwiftUI
import Shared


struct HomeView: View {
    @StateObject private var viewModel = HomeViewModel()
    @State private var showCreateTrip = false
    @State private var showMock = false
    @State private var selectedTrip: Trip?

    var body: some View {
        ZStack {
            if viewModel.isLoading {
                ProgressView("Loading trips...")
            } else if viewModel.trips.isEmpty {
                EmptyTripView(onCreateTrip: {
                    showCreateTrip = true
                })
            } else {
                TripListView(
                    trips: viewModel.trips,
                    onTripClick: { trip in
                        selectedTrip = trip
                    },
                    onDeleteTrip: { trip in
                        viewModel.deleteTrip(tripId: trip.id)
                    }
                )
            }
        }
        .navigationDestination(item: $selectedTrip) { trip in
            TripDetailViewEnhanced(trip: trip)
        }
        .navigationTitle("My Trips")
        .toolbar {
            ToolbarItem(placement: .navigationBarLeading) {
                Button(action: { showMock = true }) {
                    Image(systemName: "flask")
                }
            }
            ToolbarItem(placement: .navigationBarTrailing) {
                Button(action: { showCreateTrip = true }) {
                    Image(systemName: "plus")
                }
            }
        }
        .sheet(isPresented: $showCreateTrip) {
            NavigationStack {
                CreateTripView(onTripCreated: {
                    showCreateTrip = false
                })
            }
        }
        .sheet(isPresented: $showMock) {
            NavigationStack {
                MockView()
            }
        }
        .onAppear {
            viewModel.loadTrips()
        }
    }
}

struct EmptyTripView: View {
    let onCreateTrip: () -> Void

    var body: some View {
        VStack(spacing: 24) {
            Image(systemName: "airplane.departure")
                .font(.system(size: 80))
                .foregroundColor(.gray)

            Text("No trips yet")
                .font(.title2)
                .fontWeight(.semibold)

            Text("Create your first trip to get started")
                .font(.body)
                .foregroundColor(.secondary)
                .multilineTextAlignment(.center)

            Button(action: onCreateTrip) {
                Label("Create Trip", systemImage: "plus.circle.fill")
                    .font(.headline)
                    .foregroundColor(.white)
                    .padding()
                    .frame(maxWidth: .infinity)
                    .background(Color.blue)
                    .cornerRadius(12)
            }
            .padding(.horizontal, 48)
        }
        .padding()
    }
}

struct TripListView: View {
    let trips: [Trip]
    let onTripClick: (Trip) -> Void
    let onDeleteTrip: (Trip) -> Void

    var body: some View {
        ScrollView {
            LazyVStack(spacing: 16) {
                ForEach(trips, id: \.id) { trip in
                    TripCardView(trip: trip)
                        .onTapGesture {
                            onTripClick(trip)
                        }
                        .contextMenu {
                            Button(role: .destructive, action: {
                                onDeleteTrip(trip)
                            }) {
                                Label("Delete", systemImage: "trash")
                            }
                        }
                }
            }
            .padding()
        }
    }
}

struct TripCardView: View {
    let trip: Trip

    var body: some View {
        VStack(alignment: .leading, spacing: 12) {
            // Trip name and destination
            VStack(alignment: .leading, spacing: 4) {
                Text(trip.name)
                    .font(.headline)
                    .fontWeight(.bold)

                if !trip.originCity.isEmpty {
                    HStack(spacing: 4) {
                        Image(systemName: "location.fill")
                            .font(.caption)
                        Text(trip.originCity)
                            .font(.subheadline)
                            .foregroundColor(.secondary)
                    }
                }
            }

            // Date information
            if trip.dateType == .fixed {
                if let startDate = trip.startDate, let endDate = trip.endDate {
                    HStack(spacing: 4) {
                        Image(systemName: "calendar")
                            .font(.caption)
                        Text("\(formatDate(startDate)) - \(formatDate(endDate))")
                            .font(.subheadline)
                            .foregroundColor(.secondary)
                    }
                }
            } else {
                HStack(spacing: 4) {
                    Image(systemName: "calendar.badge.clock")
                        .font(.caption)
                    Text("Flexible dates")
                        .font(.subheadline)
                        .foregroundColor(.secondary)
                }
            }

            // Trip status
            HStack {
                Spacer()
                if isTripUpcoming(trip: trip) {
                    Label("Upcoming", systemImage: "clock.fill")
                        .font(.caption)
                        .foregroundColor(.blue)
                        .padding(.horizontal, 12)
                        .padding(.vertical, 6)
                        .background(Color.blue.opacity(0.1))
                        .cornerRadius(8)
                } else if isTripOngoing(trip: trip) {
                    Label("Ongoing", systemImage: "location.fill")
                        .font(.caption)
                        .foregroundColor(.green)
                        .padding(.horizontal, 12)
                        .padding(.vertical, 6)
                        .background(Color.green.opacity(0.1))
                        .cornerRadius(8)
                }
            }
        }
        .padding()
        .frame(maxWidth: .infinity, alignment: .leading)
        .background(Color(.systemBackground))
        .cornerRadius(12)
        .shadow(color: Color.black.opacity(0.1), radius: 4, x: 0, y: 2)
    }

    private func formatDate(_ isoDate: String) -> String {
        // Parse ISO date and format as "MMM d, yyyy"
        let formatter = ISO8601DateFormatter()
        formatter.formatOptions = [.withFullDate]

        if let date = formatter.date(from: isoDate) {
            let displayFormatter = DateFormatter()
            displayFormatter.dateFormat = "MMM d, yyyy"
            return displayFormatter.string(from: date)
        }
        return isoDate
    }

    private func isTripUpcoming(trip: Trip) -> Bool {
        guard let startDate = trip.startDate else { return false }
        let formatter = ISO8601DateFormatter()
        formatter.formatOptions = [.withFullDate]
        guard let date = formatter.date(from: startDate) else { return false }
        return date > Date()
    }

    private func isTripOngoing(trip: Trip) -> Bool {
        guard let startDate = trip.startDate, let endDate = trip.endDate else { return false }
        let formatter = ISO8601DateFormatter()
        formatter.formatOptions = [.withFullDate]
        guard let start = formatter.date(from: startDate),
              let end = formatter.date(from: endDate) else { return false }
        let now = Date()
        return now >= start && now <= end
    }
}

// ViewModel wrapper for SwiftUI
@MainActor
class HomeViewModel: ObservableObject {
    @Published var trips: [Trip] = []
    @Published var isLoading: Bool = false
    @Published var error: String?

    private let sharedViewModel: Shared.HomeViewModel

    init() {
        // Get shared ViewModel from Koin
        self.sharedViewModel = KoinHelper.companion.shared.getHomeViewModel()
        observeTrips()
    }

    func loadTrips() {
        isLoading = true
        // The shared ViewModel automatically loads trips via init
        // Just need to wait for the state flow to emit
    }

    func deleteTrip(tripId: String) {
        sharedViewModel.deleteTripById(tripId: tripId)
    }

    private func observeTrips() {
        // Poll StateFlow value periodically on main actor
        Task { @MainActor in
            while !Task.isCancelled {
                if let state = sharedViewModel.uiState.value as? HomeUiState {
                    self.trips = state.trips
                    self.isLoading = state.isLoading
                    self.error = state.error
                }
                try? await Task.sleep(nanoseconds: 500_000_000) // 0.5 seconds
            }
        }
    }
}

#Preview {
    NavigationStack {
        HomeView()
    }
}
