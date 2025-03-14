# AutocompleteResponse Documentation

## Data Classes

### `AutocompleteResponse`

```kotlin
data class AutocompleteResponse(
    val suggestions: List<AutocompletePrediction>
)
```

- **suggestions**: A list of `AutocompletePrediction` objects.

### `AutocompletePrediction`

```kotlin
data class AutocompletePrediction(
    val placeId: String,
    val distanceMeters: Int,
    val placeTypes: List<String>,
    val types: List<String>,
    val fullText: String,
    val primaryText: String,
    val secondaryText: String,
    val fullTextMatchedSubstrings: List<SubstringMatch>,
    val primaryTextMatchedSubstrings: List<SubstringMatch>,
    val secondaryTextMatchedSubstrings: List<SubstringMatch>
)
```

- **placeId**: Unique identifier for the place.
- **distanceMeters**: Distance from the current location in meters.
- **placeTypes**: List of place type classifications.
- **types**: Additional classifications for the place.
- **fullText**: Complete name of the place.
- **primaryText**: Main part of the place name.
- **secondaryText**: Additional details about the place.
- **fullTextMatchedSubstrings**: List of matched substrings within `fullText`.
- **primaryTextMatchedSubstrings**: List of matched substrings within `primaryText`.
- **secondaryTextMatchedSubstrings**: List of matched substrings within `secondaryText`.

### `SubstringMatch`

```kotlin
data class SubstringMatch(
    val offset: Int,
    val length: Int
)
```

- **offset**: Start position of the match.
- **length**: Length of the matched substring.

---

## Example JSON Response

```json
{
  "suggestions": [
    {
      "placeId": "ChIJN1t_tDeuEmsRUsoyG83frY4",
      "distanceMeters": 150,
      "placeTypes": [
        "restaurant",
        "food"
      ],
      "types": [
        "point_of_interest",
        "establishment"
      ],
      "fullText": "Central Park, New York, USA",
      "primaryText": "Central Park",
      "secondaryText": "New York, USA",
      "fullTextMatchedSubstrings": [
        {
          "offset": 0,
          "length": 7
        }
      ],
      "primaryTextMatchedSubstrings": [
        {
          "offset": 0,
          "length": 7
        }
      ],
      "secondaryTextMatchedSubstrings": [
        {
          "offset": 0,
          "length": 3
        }
      ]
    }
  ]
}
```

