package com.aurora.travlogue.feature.tripdetail.components.timeline

import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.WavingHand
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.aurora.travlogue.core.common.PreviewData
import com.aurora.travlogue.core.data.local.entities.Location
import java.time.ZonedDateTime
import java.time.format.DateTimeFormatter

/**
 * Warm welcome card shown when arriving at a new city
 */
@Composable
fun WelcomeCityCard(
    location: Location,
    arrivalDateTime: String,
    modifier: Modifier = Modifier
) {
    Card(
        modifier = modifier.fillMaxWidth(),
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.primaryContainer.copy(alpha = 0.5f)
        )
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            horizontalArrangement = Arrangement.spacedBy(12.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            // Welcome icon
            Icon(
                imageVector = Icons.Default.WavingHand,
                contentDescription = "Welcome",
                tint = MaterialTheme.colorScheme.primary,
                modifier = Modifier.size(32.dp)
            )

            Column(
                modifier = Modifier.weight(1f),
                verticalArrangement = Arrangement.spacedBy(4.dp)
            ) {
                Text(
                    text = "Welcome to ${location.name}!",
                    style = MaterialTheme.typography.titleMedium,
                    fontWeight = FontWeight.Bold,
                    color = MaterialTheme.colorScheme.primary
                )

                Text(
                    text = formatWelcomeTime(arrivalDateTime, location),
                    style = MaterialTheme.typography.bodyMedium,
                    color = MaterialTheme.colorScheme.onSurface
                )
            }
        }
    }
}

/**
 * Goodbye card shown when leaving a city
 */
@Composable
fun GoodbyeCityCard(
    location: Location,
    departureDateTime: String,
    modifier: Modifier = Modifier
) {
    Card(
        modifier = modifier.fillMaxWidth(),
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.secondaryContainer.copy(alpha = 0.5f)
        )
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            horizontalArrangement = Arrangement.spacedBy(12.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            // Goodbye icon (waving hand)
            Text(
                text = "👋",
                style = MaterialTheme.typography.headlineMedium
            )

            Column(
                modifier = Modifier.weight(1f),
                verticalArrangement = Arrangement.spacedBy(4.dp)
            ) {
                Text(
                    text = "Goodbye ${location.name}!",
                    style = MaterialTheme.typography.titleMedium,
                    fontWeight = FontWeight.Bold,
                    color = MaterialTheme.colorScheme.secondary
                )

                Text(
                    text = formatGoodbyeTime(departureDateTime),
                    style = MaterialTheme.typography.bodyMedium,
                    color = MaterialTheme.colorScheme.onSurface
                )
            }
        }
    }
}

/**
 * Format welcome time with a friendly message
 */
private fun formatWelcomeTime(isoDateTime: String, location: Location): String {
    return try {
        val zonedDateTime = ZonedDateTime.parse(isoDateTime)
        val time = zonedDateTime.format(DateTimeFormatter.ofPattern("h:mm a"))
        val date = zonedDateTime.format(DateTimeFormatter.ofPattern("EEEE, MMM d"))

        val greeting = when (zonedDateTime.hour) {
            in 5..11 -> "Good morning"
            in 12..16 -> "Good afternoon"
            in 17..21 -> "Good evening"
            else -> "Welcome"
        }

        "$greeting! You've arrived at $time on $date"
    } catch (e: Exception) {
        "Arrived at ${location.name}"
    }
}

/**
 * Format goodbye time with a friendly message
 */
private fun formatGoodbyeTime(isoDateTime: String): String {
    return try {
        val zonedDateTime = ZonedDateTime.parse(isoDateTime)
        val time = zonedDateTime.format(DateTimeFormatter.ofPattern("h:mm a"))
        val date = zonedDateTime.format(DateTimeFormatter.ofPattern("EEEE, MMM d"))

        "Time to head out at $time on $date"
    } catch (e: Exception) {
        "Departing soon"
    }
}

// ==================== Previews ====================

@Preview(name = "Welcome City Card", showBackground = true)
@Composable
private fun WelcomeCityCardPreview() {
    MaterialTheme {
        WelcomeCityCard(
            location = PreviewData.locationTokyo,
            arrivalDateTime = "2025-07-01T14:30:00+09:00"
        )
    }
}

@Preview(name = "Goodbye City Card", showBackground = true)
@Composable
private fun GoodbyeCityCardPreview() {
    MaterialTheme {
        GoodbyeCityCard(
            location = PreviewData.locationKyoto,
            departureDateTime = "2025-07-05T09:00:00+09:00"
        )
    }
}
