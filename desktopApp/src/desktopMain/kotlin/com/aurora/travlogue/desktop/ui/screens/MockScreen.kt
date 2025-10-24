package com.aurora.travlogue.desktop.ui.screens

import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.aurora.travlogue.feature.mock.presentation.MockViewModel
import org.koin.compose.viewmodel.koinViewModel

/**
 * Screen for generating mock trip data
 */
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MockScreen(
    onNavigateBack: () -> Unit,
    onNavigateToTripDetail: (String) -> Unit,
    viewModel: MockViewModel = koinViewModel()
) {
    var isGeneratingComplete by remember { mutableStateOf(false) }
    var isGeneratingGaps by remember { mutableStateOf(false) }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Mock Data Generator") },
                navigationIcon = {
                    IconButton(onClick = onNavigateBack) {
                        Icon(Icons.AutoMirrored.Filled.ArrowBack, "Back")
                    }
                }
            )
        }
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .padding(24.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            Text(
                text = "Generate Mock Trips",
                style = MaterialTheme.typography.headlineMedium,
                fontWeight = FontWeight.Bold
            )

            Text(
                text = "Create sample trips with realistic data for testing and demonstration purposes.",
                style = MaterialTheme.typography.bodyMedium,
                color = MaterialTheme.colorScheme.onSurfaceVariant
            )

            Spacer(modifier = Modifier.height(8.dp))

            // Complete Trip Card
            Card(
                modifier = Modifier.fillMaxWidth()
            ) {
                Column(
                    modifier = Modifier.padding(20.dp)
                ) {
                    Text(
                        text = "Complete Japan Trip",
                        style = MaterialTheme.typography.titleLarge,
                        fontWeight = FontWeight.Bold
                    )

                    Spacer(modifier = Modifier.height(8.dp))

                    Text(
                        text = "A fully planned 10-day trip to Japan with:",
                        style = MaterialTheme.typography.bodyMedium,
                        color = MaterialTheme.colorScheme.onSurfaceVariant
                    )

                    Spacer(modifier = Modifier.height(8.dp))

                    Column(
                        verticalArrangement = Arrangement.spacedBy(4.dp)
                    ) {
                        BulletPoint("3 locations: Tokyo, Kyoto, Osaka")
                        BulletPoint("7 activities across different time slots")
                        BulletPoint("Complete bookings: flights, hotels, trains")
                        BulletPoint("Proper timezone handling (PDT to JST)")
                        BulletPoint("No gaps - everything connected")
                    }

                    Spacer(modifier = Modifier.height(16.dp))

                    Button(
                        onClick = {
                            isGeneratingComplete = true
                            viewModel.createCompleteTrip { tripId ->
                                isGeneratingComplete = false
                                onNavigateToTripDetail(tripId)
                            }
                        },
                        modifier = Modifier.fillMaxWidth(),
                        enabled = !isGeneratingComplete && !isGeneratingGaps
                    ) {
                        if (isGeneratingComplete) {
                            CircularProgressIndicator(
                                modifier = Modifier.size(20.dp),
                                color = MaterialTheme.colorScheme.onPrimary,
                                strokeWidth = 2.dp
                            )
                            Spacer(modifier = Modifier.width(8.dp))
                        }
                        Text(if (isGeneratingComplete) "Generating..." else "Generate Complete Trip")
                    }
                }
            }

            // Incomplete Trip Card
            Card(
                modifier = Modifier.fillMaxWidth()
            ) {
                Column(
                    modifier = Modifier.padding(20.dp)
                ) {
                    Text(
                        text = "Incomplete Italy Trip",
                        style = MaterialTheme.typography.titleLarge,
                        fontWeight = FontWeight.Bold
                    )

                    Spacer(modifier = Modifier.height(8.dp))

                    Text(
                        text = "A 15-day trip with missing details to demonstrate gap detection:",
                        style = MaterialTheme.typography.bodyMedium,
                        color = MaterialTheme.colorScheme.onSurfaceVariant
                    )

                    Spacer(modifier = Modifier.height(8.dp))

                    Column(
                        verticalArrangement = Arrangement.spacedBy(4.dp)
                    ) {
                        BulletPoint("3 locations: Rome, Florence, Venice")
                        BulletPoint("Only 3 activities (sparse planning)")
                        BulletPoint("Missing transit between cities")
                        BulletPoint("Missing hotels in Florence & Venice")
                        BulletPoint("Missing return flight")
                        BulletPoint("Will trigger gap detection")
                    }

                    Spacer(modifier = Modifier.height(16.dp))

                    Button(
                        onClick = {
                            isGeneratingGaps = true
                            viewModel.createTripWithGaps { tripId ->
                                isGeneratingGaps = false
                                onNavigateToTripDetail(tripId)
                            }
                        },
                        modifier = Modifier.fillMaxWidth(),
                        enabled = !isGeneratingComplete && !isGeneratingGaps,
                        colors = ButtonDefaults.buttonColors(
                            containerColor = MaterialTheme.colorScheme.secondary
                        )
                    ) {
                        if (isGeneratingGaps) {
                            CircularProgressIndicator(
                                modifier = Modifier.size(20.dp),
                                color = MaterialTheme.colorScheme.onSecondary,
                                strokeWidth = 2.dp
                            )
                            Spacer(modifier = Modifier.width(8.dp))
                        }
                        Text(if (isGeneratingGaps) "Generating..." else "Generate Incomplete Trip")
                    }
                }
            }

            Spacer(modifier = Modifier.weight(1f))

            // Info card
            Card(
                colors = CardDefaults.cardColors(
                    containerColor = MaterialTheme.colorScheme.secondaryContainer
                )
            ) {
                Row(
                    modifier = Modifier.padding(16.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        text = "Note: Generated trips will appear in your trip list and can be edited or deleted.",
                        style = MaterialTheme.typography.bodySmall,
                        color = MaterialTheme.colorScheme.onSecondaryContainer
                    )
                }
            }
        }
    }
}

@Composable
private fun BulletPoint(text: String) {
    Row(
        horizontalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        Text(
            text = "•",
            style = MaterialTheme.typography.bodyMedium,
            color = MaterialTheme.colorScheme.primary
        )
        Text(
            text = text,
            style = MaterialTheme.typography.bodyMedium,
            color = MaterialTheme.colorScheme.onSurfaceVariant
        )
    }
}
