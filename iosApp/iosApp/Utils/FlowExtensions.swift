import Foundation
import Shared

/// Extension to make Kotlin Flow/StateFlow usable with Swift's async/await
extension Kotlinx_coroutines_coreFlow {
    func watch<T>(_ onValue: @escaping (T) -> Void) -> Task<Void, Never> {
        Task {
            do {
                let iterator = self.makeIterator()
                while let value = try await iterator.next() as? T {
                    onValue(value)
                }
            } catch {
                // Flow completed or was cancelled
            }
        }
    }
}

// Create an AsyncStream from a Kotlin Flow
func asyncStream<T>(from flow: Kotlinx_coroutines_coreFlow) -> AsyncStream<T> {
    AsyncStream { continuation in
        let task = Task {
            do {
                let collector = SimpleFlowCollector<T> { value in
                    continuation.yield(value)
                }
                try await flow.collect(collector: collector, completionHandler: { error in
                    if let error = error {
                        print("Flow collection error: \(error)")
                    }
                    continuation.finish()
                })
            } catch {
                continuation.finish()
            }
        }

        continuation.onTermination = { _ in
            task.cancel()
        }
    }
}

class SimpleFlowCollector<T>: Kotlinx_coroutines_coreFlowCollector {
    let callback: (T) -> Void

    init(callback: @escaping (T) -> Void) {
        self.callback = callback
    }

    func emit(value: Any?) async throws {
        if let value = value as? T {
            callback(value)
        }
    }
}
