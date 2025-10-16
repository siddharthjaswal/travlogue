# Home Feature - Domain Layer

This package contains feature-specific business logic for the Home feature.

## Structure

```
domain/
├── usecase/     # Use cases for complex business logic
└── model/       # Domain models (if needed, different from data entities)
```

## When to Use

Create use cases here when you have:
- Complex business logic that doesn't belong in the ViewModel
- Logic that combines multiple repositories
- Logic that might be reused across multiple screens

## Example

For the gap detection feature, you might create:
- `usecase/DetectGapsUseCase.kt` - Analyzes itinerary for gaps
- `model/GapAnalysis.kt` - Domain model representing gap analysis results

## Current Status

This package is currently empty and ready for future use cases as the home feature grows more complex.
