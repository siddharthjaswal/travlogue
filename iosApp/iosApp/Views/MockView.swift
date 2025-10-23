import SwiftUI
import Shared

struct MockView: View {
    @Environment(\.dismiss) private var dismiss
    @State private var isCreatingTrip = false
    @State private var createdTripId: String? = nil
    @State private var showSuccessAlert = false

    private let viewModel: MockViewModel

    init() {
        self.viewModel = KoinHelper.companion.shared.getMockViewModel()
    }

    var body: some View {
        VStack(spacing: 24) {
            Text("Create Mock Data")
                .font(.largeTitle)
                .fontWeight(.bold)
                .padding(.top, 40)

            Text("Generate sample trips with complete itineraries for testing")
                .font(.body)
                .foregroundColor(.secondary)
                .multilineTextAlignment(.center)
                .padding(.horizontal)

            Spacer()

            VStack(spacing: 16) {
                // Complete Trip Button
                Button(action: createCompleteTrip) {
                    VStack(spacing: 8) {
                        Image(systemName: "checkmark.circle.fill")
                            .font(.system(size: 48))
                            .foregroundColor(.green)

                        Text("Complete Japan Trip")
                            .font(.headline)

                        Text("Full itinerary with all bookings, hotels, and activities")
                            .font(.caption)
                            .foregroundColor(.secondary)
                            .multilineTextAlignment(.center)
                    }
                    .frame(maxWidth: .infinity)
                    .padding()
                    .background(Color(.systemGray6))
                    .cornerRadius(12)
                }
                .disabled(isCreatingTrip)

                // Trip with Gaps Button
                Button(action: createTripWithGaps) {
                    VStack(spacing: 8) {
                        Image(systemName: "exclamationmark.triangle.fill")
                            .font(.system(size: 48))
                            .foregroundColor(.orange)

                        Text("Italy Trip (Incomplete)")
                            .font(.headline)

                        Text("Missing transit and hotels to demonstrate gap detection")
                            .font(.caption)
                            .foregroundColor(.secondary)
                            .multilineTextAlignment(.center)
                    }
                    .frame(maxWidth: .infinity)
                    .padding()
                    .background(Color(.systemGray6))
                    .cornerRadius(12)
                }
                .disabled(isCreatingTrip)
            }
            .padding(.horizontal)

            if isCreatingTrip {
                ProgressView("Creating trip...")
                    .padding()
            }

            Spacer()
        }
        .navigationTitle("Mock Data")
        .navigationBarTitleDisplayMode(.inline)
        .toolbar {
            ToolbarItem(placement: .navigationBarTrailing) {
                Button("Done") {
                    dismiss()
                }
            }
        }
        .alert("Trip Created", isPresented: $showSuccessAlert) {
            Button("OK") {
                dismiss()
            }
        } message: {
            Text("Mock trip has been created successfully!")
        }
    }

    private func createCompleteTrip() {
        isCreatingTrip = true

        viewModel.createCompleteTrip { tripId in
            DispatchQueue.main.async {
                isCreatingTrip = false
                createdTripId = tripId
                showSuccessAlert = true
            }
        }
    }

    private func createTripWithGaps() {
        isCreatingTrip = true

        viewModel.createTripWithGaps { tripId in
            DispatchQueue.main.async {
                isCreatingTrip = false
                createdTripId = tripId
                showSuccessAlert = true
            }
        }
    }
}

#Preview {
    NavigationStack {
        MockView()
    }
}
