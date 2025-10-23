import SwiftUI
import Shared

struct TripDetailView: View {
    let trip: Trip

    var body: some View {
        ScrollView {
            VStack(alignment: .leading, spacing: 24) {
                // Trip Header
                VStack(alignment: .leading, spacing: 8) {
                    Text(trip.name)
                        .font(.largeTitle)
                        .fontWeight(.bold)

                    if !trip.originCity.isEmpty {
                        HStack(spacing: 4) {
                            Image(systemName: "location.fill")
                            Text(trip.originCity)
                                .font(.title3)
                                .foregroundColor(.secondary)
                        }
                    }
                }
                .padding()

                // Date Information
                if trip.dateType == .fixed {
                    if let startDate = trip.startDate, let endDate = trip.endDate {
                        VStack(alignment: .leading, spacing: 12) {
                            Text("Travel Dates")
                                .font(.headline)

                            HStack {
                                VStack(alignment: .leading, spacing: 4) {
                                    Text("Start")
                                        .font(.caption)
                                        .foregroundColor(.secondary)
                                    Text(formatDate(startDate))
                                        .font(.body)
                                }

                                Spacer()

                                Image(systemName: "arrow.right")
                                    .foregroundColor(.secondary)

                                Spacer()

                                VStack(alignment: .trailing, spacing: 4) {
                                    Text("End")
                                        .font(.caption)
                                        .foregroundColor(.secondary)
                                    Text(formatDate(endDate))
                                        .font(.body)
                                }
                            }
                            .padding()
                            .background(Color(.systemGray6))
                            .cornerRadius(12)
                        }
                        .padding(.horizontal)
                    }
                }

                // Placeholder sections for future implementation
                VStack(alignment: .leading, spacing: 16) {
                    Text("Timeline")
                        .font(.headline)
                        .padding(.horizontal)

                    Text("No bookings or activities yet")
                        .font(.subheadline)
                        .foregroundColor(.secondary)
                        .frame(maxWidth: .infinity, alignment: .center)
                        .padding()
                }

                Spacer()
            }
        }
        .navigationTitle("Trip Details")
        .navigationBarTitleDisplayMode(.inline)
    }

    private func formatDate(_ isoDate: String) -> String {
        let formatter = ISO8601DateFormatter()
        formatter.formatOptions = [.withFullDate]

        if let date = formatter.date(from: isoDate) {
            let displayFormatter = DateFormatter()
            displayFormatter.dateFormat = "MMM d, yyyy"
            return displayFormatter.string(from: date)
        }
        return isoDate
    }
}

#Preview {
    NavigationStack {
        TripDetailView(
            trip: Trip(
                id: "1",
                name: "Summer Vacation",
                originCity: "New York",
                dateType: .fixed,
                startDate: "2024-07-01",
                endDate: "2024-07-15",
                flexibleMonth: nil,
                flexibleDuration: nil,
                createdAt: Int64(Date().timeIntervalSince1970 * 1000),
                updatedAt: Int64(Date().timeIntervalSince1970 * 1000)
            )
        )
    }
}
