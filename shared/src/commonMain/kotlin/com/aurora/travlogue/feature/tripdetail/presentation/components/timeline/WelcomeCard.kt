package com.aurora.travlogue.feature.tripdetail.presentation.components.timeline

import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.aurora.travlogue.core.domain.model.Location
import kotlinx.datetime.Instant
import kotlinx.datetime.TimeZone
import kotlinx.datetime.toLocalDateTime

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
                imageVector = Icons.Default.Favorite,
                contentDescription = "Welcome",
                tint = MaterialTheme.colorScheme.primary,
                modifier = Modifier.size(32.dp)
            )

            Column(
                modifier = Modifier.weight(1f),
                verticalArrangement = Arrangement.spacedBy(2.dp)
            ) {
                Text(
                    text = "Welcome to ${location.name}!",
                    style = MaterialTheme.typography.titleSmall,
                    fontWeight = FontWeight.Bold,
                    color = MaterialTheme.colorScheme.primary
                )

                Text(
                    text = formatWelcomeTime(arrivalDateTime, location),
                    style = MaterialTheme.typography.bodySmall,
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
                verticalArrangement = Arrangement.spacedBy(2.dp)
            ) {
                Text(
                    text = "Goodbye ${location.name}!",
                    style = MaterialTheme.typography.titleSmall,
                    fontWeight = FontWeight.Bold,
                    color = MaterialTheme.colorScheme.secondary
                )

                Text(
                    text = formatGoodbyeTime(departureDateTime),
                    style = MaterialTheme.typography.bodySmall,
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
        val instant = Instant.parse(isoDateTime)
        val localDateTime = instant.toLocalDateTime(TimeZone.currentSystemDefault())

        val hour = if (localDateTime.hour == 0) 12 else if (localDateTime.hour > 12) localDateTime.hour - 12 else localDateTime.hour
        val minute = localDateTime.minute.toString().padStart(2, '0')
        val period = if (localDateTime.hour < 12) "AM" else "PM"
        val time = "$hour:$minute $period"

        val dayOfWeek = localDateTime.dayOfWeek.name.lowercase().capitalize()
        val month = localDateTime.month.name.take(3).lowercase().capitalize()
        val day = localDateTime.dayOfMonth
        val date = "$dayOfWeek, $month $day"

        val greeting = when (localDateTime.hour) {
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
        val instant = Instant.parse(isoDateTime)
        val localDateTime = instant.toLocalDateTime(TimeZone.currentSystemDefault())

        val hour = if (localDateTime.hour == 0) 12 else if (localDateTime.hour > 12) localDateTime.hour - 12 else localDateTime.hour
        val minute = localDateTime.minute.toString().padStart(2, '0')
        val period = if (localDateTime.hour < 12) "AM" else "PM"
        val time = "$hour:$minute $period"

        val dayOfWeek = localDateTime.dayOfWeek.name.lowercase().capitalize()
        val month = localDateTime.month.name.take(3).lowercase().capitalize()
        val day = localDateTime.dayOfMonth
        val date = "$dayOfWeek, $month $day"

        "Time to head out at $time on $date"
    } catch (e: Exception) {
        "Departing soon"
    }
}
