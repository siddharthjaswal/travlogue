### Use Case: GetMessagesForSessionUseCase

**Purpose:**

This use case is responsible for retrieving all messages associated with a specific session. It acts as an intermediary between the UI/ViewModel layer and the data layer (repository) to fetch session-specific messages.

**Dependencies:**

*   `MessageRepository`: This use case depends on an implementation of `MessageRepository` to access the underlying data source for messages.

**How it's used:**

The `GetMessagesForSessionUseCase` is invoked with a `sessionId` (of type `Long`) as a parameter. It then calls the `getMessagesForSession` method on the injected `MessageRepository`, passing the `sessionId` to fetch the relevant messages. The result is typically a Flow or LiveData stream of messages that the UI can observe for updates.

**Example Invocation (Conceptual):**