import SwiftUI
import Shared

struct CreateTripView: View {
    @Environment(\.dismiss) private var dismiss
    @StateObject private var viewModel = CreateTripViewModel()

    var onTripCreated: () -> Void

    var body: some View {
        Form {
            Section("Trip Details") {
                TextField("Trip Name", text: $viewModel.tripName)

                TextField("Origin City", text: $viewModel.originCity)
                    .textContentType(.addressCity)
            }

            Section("Travel Dates") {
                Picker("Date Type", selection: $viewModel.dateType) {
                    Text("Fixed Dates").tag(DateType.fixed)
                    Text("Flexible").tag(DateType.flexible)
                }
                .pickerStyle(.segmented)

                if viewModel.dateType == .fixed {
                    DatePicker(
                        "Start Date",
                        selection: Binding(
                            get: { viewModel.startDate ?? Date() },
                            set: { viewModel.startDate = $0 }
                        ),
                        displayedComponents: .date
                    )

                    DatePicker(
                        "End Date",
                        selection: Binding(
                            get: { viewModel.endDate ?? Date() },
                            set: { viewModel.endDate = $0 }
                        ),
                        in: (viewModel.startDate ?? Date())...,
                        displayedComponents: .date
                    )
                }
            }

            if let error = viewModel.error {
                Section {
                    Text(error)
                        .foregroundColor(.red)
                        .font(.caption)
                }
            }
        }
        .navigationTitle("Create Trip")
        .navigationBarTitleDisplayMode(.inline)
        .toolbar {
            ToolbarItem(placement: .navigationBarLeading) {
                Button("Cancel") {
                    dismiss()
                }
            }

            ToolbarItem(placement: .navigationBarTrailing) {
                Button("Create") {
                    viewModel.createTrip { success in
                        if success {
                            onTripCreated()
                        }
                    }
                }
                .disabled(!viewModel.isFormValid)
            }
        }
    }
}

// ViewModel wrapper for SwiftUI
@MainActor
class CreateTripViewModel: ObservableObject {
    @Published var tripName: String = ""
    @Published var originCity: String = ""
    @Published var dateType: DateType = .fixed
    @Published var startDate: Date?
    @Published var endDate: Date?
    @Published var error: String?

    private let sharedViewModel: Shared.CreateTripViewModel

    init() {
        self.sharedViewModel = KoinHelper.companion.shared.getCreateTripViewModel()
        observeState()
    }

    var isFormValid: Bool {
        !tripName.isEmpty && !originCity.isEmpty &&
        (dateType == .flexible || (startDate != nil && endDate != nil))
    }

    func createTrip(completion: @escaping (Bool) -> Void) {
        guard isFormValid else {
            error = "Please fill in all required fields"
            completion(false)
            return
        }

        sharedViewModel.onTripNameChanged(name: tripName)
        sharedViewModel.onOriginCityChanged(city: originCity)
        sharedViewModel.onDateTypeSelected(dateType: dateType)
        // TODO: Parse ISO string back to LocalDate for dates
        // For now, just trigger creation with the current state

        sharedViewModel.onCreateTripClicked()

        // Wait a moment for the trip to be created
        DispatchQueue.main.asyncAfter(deadline: .now() + 0.5) {
            completion(true)
        }
    }

    private func observeState() {
        // Poll StateFlow value periodically on main actor
        Task { @MainActor in
            while !Task.isCancelled {
                if let state = sharedViewModel.uiState.value as? CreateTripUiState {
                    self.tripName = state.tripName
                    self.originCity = state.originCity
                    self.dateType = state.selectedDateType

                    // Convert kotlinx.datetime.LocalDate to Swift Date if needed
                    if let kotlinStartDate = state.startDate {
                        self.startDate = self.parseISODate(kotlinStartDate.description())
                    }
                    if let kotlinEndDate = state.endDate {
                        self.endDate = self.parseISODate(kotlinEndDate.description())
                    }
                }
                try? await Task.sleep(nanoseconds: 500_000_000) // 0.5 seconds
            }
        }
    }

    private func formatDateToISO(_ date: Date) -> String {
        let formatter = ISO8601DateFormatter()
        formatter.formatOptions = [.withFullDate]
        return formatter.string(from: date)
    }

    private func parseISODate(_ isoString: String) -> Date? {
        let formatter = ISO8601DateFormatter()
        formatter.formatOptions = [.withFullDate]
        return formatter.date(from: isoString)
    }
}

#Preview {
    NavigationStack {
        CreateTripView(onTripCreated: {})
    }
}
